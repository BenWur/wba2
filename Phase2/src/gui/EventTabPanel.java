/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.control.SplitPane;
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
            final GridPane geoGridk = new GridPane();
            geoGridk.setHgap(5); // Abstand links/rechts
            geoGridk.setVgap(5); // Abstand oben/unten

            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(85);
            ColumnConstraints column2 = new ColumnConstraints();
            column2.setPercentWidth(15);
            RowConstraints row1 = new RowConstraints();
            row1.setPercentHeight(92);
            RowConstraints row2 = new RowConstraints();
            row2.setPercentHeight(8);
            geoGridk.getColumnConstraints().addAll(column1, column2);
            geoGridk.getRowConstraints().addAll(row1, row2);

            SplitPane sp = new SplitPane();
            final StackPane sp1 = new StackPane();

            final StackPane sp2 = new StackPane();
            TextArea comments = new TextArea();
            
            final TextField chatText = new TextField();
                            
}
