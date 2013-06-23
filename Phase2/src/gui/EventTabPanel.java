package gui;

import eventcontentlist.Kommentar;
import eventcontentlist.TickerBeitrag;
import eventlist.Event;
import guidata.TickerContent;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import nodepackage.PubSubController;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

/**
 * Verwaltung des gejointen Events
 * @author Dario & Ben
 */

public class EventTabPanel extends GridPane implements ItemEventListener<Item> {

    private ListView<String> beitraege;			//beiträge zeigt alle Beiträge
    private ListView<String> comments;			//comments zeigt alle Kommentare
    private TickerContent eventContent;
    private ObservableList<String> beitragItems;	//alle Beiträge als Items um die Liste zu befüllen
    private ObservableList<String> commentItems;	//das selbe mit comments
    private PubSubController pubSubControl;
    public String user;							//username
    private Event events;						//die Events
    final StackPane sp1 = new StackPane();		//Beitrag Spalte		
    final StackPane sp2 = new StackPane();		//Kommentar Spalte
    final TextField chatText = new TextField();	

    /**
     * Methode für den pubSubController. Löst das aktualisieren der Liste aus
     * @param items
     */
    public void handlePublishedItems(ItemPublishEvent<Item> items) {
        System.out.println("Item count: " + items.getItems().size());

        for (Item item : items.getItems()) {
            System.out.println(item.getId());
            System.out.println(item.toXML());
        }
        update();
    }

    /**
     * Methode zum aktualisieren der Beitrag Liste 
     */
    public void update() {
        List<TickerBeitrag> tickerBeitrag = eventContent.contentList(events.getEventID().intValue()).getTickerBeitrag();
        //aktualisiert die Liste und fügt das neue Item hinzu
        for (int f = beitragItems.size(); f < tickerBeitrag.size(); f++) {
            beitragItems.add(tickerBeitrag.get(f).getZeit() + ": " + tickerBeitrag.get(f).getText());
        }
        beitraege.setItems(beitragItems);	//Beiträge werden neu hinzugefügt
        updateComment();				  	//Kommentare werden aktualisiert
    }
    
    /**
     * Methode zum aktualisieren der Kommentar Liste 
     */
    public void updateComment() {
        int selectedIndex = beitraege.getSelectionModel().getSelectedIndex();
        List<Kommentar> kommentare = eventContent.contentList(events.getEventID().intValue()).getTickerBeitrag().get(selectedIndex).getKommentar();
        for (int f = commentItems.size(); f < kommentare.size(); f++) {
            commentItems.add(kommentare.get(f).getKommentarUser() +" wrote:\n"+ kommentare.get(f).getKommentarText());
        }
        comments.setItems(commentItems);	//Kommentare werden hinzugefügt
    }
    
    /**
     * Haupt-Methode für alle Funktionen
     * @param events Das übergebene Event 
     */
    public EventTabPanel(final Event events) {
        this.events = events;	//Event wird übergeben
        SplitPane sp = new SplitPane();

        pubSubControl = new PubSubController();
        beitraege = new ListView<String>();
        comments = new ListView<String>();
        eventContent = new TickerContent();
        beitragItems = FXCollections.observableArrayList();
        commentItems = FXCollections.observableArrayList();
        
        this.setHgap(5); // Abstand links/rechts
        this.setVgap(5); // Abstand oben/unten

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(85);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(15);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(92);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(8);
        this.getColumnConstraints().addAll(column1, column2);
        this.getRowConstraints().addAll(row1, row2);

        

        sp1.getChildren().add(beitraege);
        sp2.getChildren().add(comments);
        sp.getItems().addAll(sp1, sp2);

        final Button sendchat = new Button("Send");	//Send Button zum verschicken der Nachrichten
        sendchat.setMinWidth(50);

        this.add(sendchat, 1, 1);
        this.add(chatText, 0, 1);
        this.add(sp, 0, 0);

        //Falls ein Beitrag ausgewählt wird
        beitraege.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 1) {	//einfaches klicken wählt aus
                        comments.getItems().removeAll(commentItems);
                        updateComment();
                    }
                    if (mouseEvent.getClickCount() == 2) {	//Doppelklick wählt ab. Wichtig für Admin!
                        beitraege.getSelectionModel().clearSelection();
                    }
                }
            }
        });

        sendchat.setDefaultButton(true);	//Um mit Enter bedienen zu können
        sendchat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (beitraege.getSelectionModel().isEmpty() && user.equals(events.getUsername())) { //Posten von Beiträgen
                    String beitrag = chatText.getText();
                    chatText.clear();										//damit Textfeld leer wird
                    int eventnr = events.getEventID().intValue();			//Nummer des Events
                    new TickerContent().createBeitrag(eventnr, beitrag);	//erstellt neuen Beitrag
                    pubSubControl.nodeVeroeffentlichen(events.getEventname(), "<beitrag>" + beitrag + "</beitrag>");
                } else if (!beitraege.getSelectionModel().isEmpty()) {	//Posten von Kommentaren
                    String kommentar = chatText.getText();
                    chatText.clear();
                    int eventnr = events.getEventID().intValue();
                    int ticknr = beitraege.getSelectionModel().getSelectedIndex() + 1;
                    new TickerContent().createKommentar(eventnr, ticknr, user, kommentar);	//Neuen Kommentar erstellen
                    pubSubControl.nodeVeroeffentlichen(events.getEventname(), "<kommentar>" + kommentar + "</kommentar>");
                }
            }
        });
    }
}