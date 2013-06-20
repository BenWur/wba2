/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import guidata.TickerContent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;



/**
 *
 * @author Dario
 */
public class EventTabPanel extends GridPane {
    public Tab joinTab;
    public ListView<String> liveticks;
    public TickerContent cevents;
    public ObservableList<String> items;
    public int index2;
    public ListView<String> ticklist;
    public Button joinTicker;
    
    public EventTabPanel() {
            
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
            TextArea comments = new TextArea();
            
            final TextField chatText = new TextField();
            
            joinTicker = new Button("Join");
            
            
					

            liveticks = new ListView<String>();
            cevents = new TickerContent();
            items = FXCollections.observableArrayList();
            index2 = ticklist.getSelectionModel().getSelectedIndex() + 1;

            sp2.getChildren().add(comments);
            sp.getItems().addAll(sp1, sp2);

            sp1.getChildren().add(liveticks);



            final Button sendchat = new Button("Send");
            sendchat.setMinWidth(50);






            this.add(sendchat, 1, 1);
            this.add(chatText, 0, 1);

            this.add(sp, 0, 0);

            joinTab.setContent(this);
					

					
            }
    }
		

