package gui;

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
import javafx.scene.control.TextArea;
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
 *
 * @author Dario & Ben
 */
public class EventTabPanel extends GridPane implements ItemEventListener<Item> {

    private ListView<String> liveticks;
    private TickerContent cevents;
    private ObservableList<String> items;
    private ListView<String> ticklist;
    private PubSubController pubSubControl;
    public String user;
    private Event events;

    public void handlePublishedItems(ItemPublishEvent<Item> items) {
        System.out.println("Item count: " + items.getItems().size());

        for (Item item : items.getItems()) {
            System.out.println(item.getId());
            System.out.println(item.toXML());
        }
        update();
    }

    public void update() {
        List<TickerBeitrag> tickerBeitrag = cevents.contentList(events.getEventID().intValue()).getTickerBeitrag();

        for (int f = items.size(); f < tickerBeitrag.size(); f++) {
            System.out.println("update:" + tickerBeitrag.get(f).getZeit());

            items.add(tickerBeitrag.get(f).getZeit() + ": " + tickerBeitrag.get(f).getText());
        }
        liveticks.setItems(items);
    }

    public EventTabPanel(final Event events) {
        this.events = events;

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

        SplitPane sp = new SplitPane();
        final StackPane sp1 = new StackPane();
        final StackPane sp2 = new StackPane();
        final TextArea comments = new TextArea();
        final TextField chatText = new TextField();

        pubSubControl = new PubSubController();
        liveticks = new ListView<String>();
        cevents = new TickerContent();
        items = FXCollections.observableArrayList();

        sp2.getChildren().add(comments);
        sp.getItems().addAll(sp1, sp2);
        sp1.getChildren().add(liveticks);

        final Button sendchat = new Button("Send");
        sendchat.setMinWidth(50);

        /*
         joinTab.setOnClosed(new EventHandler<javafx.event.Event>() {
         public void handle(javafx.event.Event t) {
         pubSubControl.nodeKuendigen(ticklist.getSelectionModel().getSelectedItem()); //Kündigt die Node
         }
         });

         */

        this.add(sendchat, 1, 1);
        this.add(chatText, 0, 1);
        this.add(sp, 0, 0);

        //joinTab.setContent(this);

        //hier benmagic!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //if (pubSubControl.nodesAuslesen().contains(events.getEventname())) {
        //pubSubControl.nodeAbonnieren(events.getEventname());
        //   } else {

        for (int f = 0; f < cevents.contentList(events.getEventID().intValue()).getTickerBeitrag().size(); f++) {
            items.add(cevents.contentList(events.getEventID().intValue()).getTickerBeitrag().get(f).getZeit() + ": " + cevents.contentList(events.getEventID().intValue()).getTickerBeitrag().get(f).getText());
            liveticks.setItems(items);
        }
        //    }

        liveticks.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 1) {
                        final int index3 = liveticks.getSelectionModel().getSelectedIndex();
                        comments.clear();
                        for (int h = 0; h < cevents.contentList(events.getEventID().intValue()).getTickerBeitrag().get(index3).getKommentar().size(); h++) {
                            comments.appendText(cevents.contentList(events.getEventID().intValue()).getTickerBeitrag().get(index3).getKommentar().get(h).getKommentarUser() + " wrote:\n");
                            comments.appendText(cevents.contentList(events.getEventID().intValue()).getTickerBeitrag().get(index3).getKommentar().get(h).getKommentarText() + "\n");
                        }
                    }
                }
            }
        });
        
        sendchat.setDefaultButton(true);
        sendchat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final List<TickerBeitrag> tickerBeitrag = cevents.contentList(events.getEventID().intValue()).getTickerBeitrag();
                if (liveticks.getSelectionModel().isEmpty()) {
                    String beitrag = chatText.getText();
                    chatText.clear();
                    int eventnr = events.getEventID().intValue();
                    new TickerContent().createBeitrag(eventnr, beitrag);
                    
                    //hier benmagic!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                    pubSubControl.nodeVeroeffentlichen(events.getEventname(), "<beitrag>" + beitrag + "</beitrag>");
                    //update();
                } else if (!liveticks.getSelectionModel().isEmpty()) {
                    String kommentar = chatText.getText();
                    chatText.clear();
                    int eventnr = events.getEventID().intValue();
                    int ticknr = liveticks.getSelectionModel().getSelectedIndex() + 1;
                    new TickerContent().createKommentar(eventnr, ticknr, user, kommentar);
                }
            }
        });
    }
}