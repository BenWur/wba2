package gui;

import guidata.TickerContent;
import guidata.TickerEvents;
import guidata.UserContent;

import java.util.HashMap;
import java.util.Map;

import nodepackage.PubSubController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WindowSocialMain extends Application {
	private Tab tab1;
	private Tab tab2;
	private Tab createNewEventTab;
	private Tab k;
	public String user;
	public PubSubController pubSubControl = new PubSubController(); // damit
																	// User das
																	// Event
																	// abonniert
/*  HIER KOMMT DIE HYPER REFRESH METHODE REIN TODO
	public void update() {
		list.load();
	}
*/
	@Override
	public void start(final Stage primaryStage) {
		primaryStage.setResizable(false);
		primaryStage.setTitle("SocialTicker");

		AnchorPane root = new AnchorPane();
		primaryStage.setScene(new Scene(root, 480, 380));
		final TabPane tabPane = new TabPane();
		tabPane.setPrefSize(490, 380);
		final SingleSelectionModel<Tab> selectTab = tabPane.getSelectionModel();
		tabPane.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Tab>() {
					@Override
					public void changed(ObservableValue<? extends Tab> tab,
							Tab oldTab, Tab newTab) {
						if (newTab.equals(tab1) || newTab.equals(tab2)) {
							tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
						} else {
							tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
						}
					}
				});

		final GridPane geoGrid = new GridPane();
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(50);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(50);
		geoGrid.getColumnConstraints().addAll(column1, column2);
		geoGrid.setHgap(5); // Abstand links/rechts
		geoGrid.setVgap(5); // Abstand oben/unten

		HBox hbox = new HBox(); // hbox für horizontal aligment
		hbox.setSpacing(10);

		final GridPane geoGrid2 = new GridPane();
		geoGrid2.setHgap(5); // Abstand links/rechts
		geoGrid2.setVgap(5); // Abstand oben/unten

		ColumnConstraints column12 = new ColumnConstraints();
		column12.setPercentWidth(30);
		ColumnConstraints column22 = new ColumnConstraints();
		column22.setPercentWidth(40);
		ColumnConstraints column32 = new ColumnConstraints();
		column32.setPercentWidth(30);
		geoGrid2.getColumnConstraints().addAll(column12, column22, column32);

		UserContent info = new UserContent();
		Label profildata = new Label("Ihre Profildaten:");
		Label userinfo = new Label("Username:");
		Label username = new Label(info.userInfo(user).getUsername());
		Label userfnamet = new Label("Name:");
		Label userfname = new Label(info.userInfo(user).getVorname());
		Label geschlechtText = new Label("Geschlecht:");
		Label geschlecht = new Label(info.userInfo(user).getGender());
		Label gebText = new Label("Geburtsdatum:");
		Label geb = new Label(info.userInfo(user).getGeburtsdatum().toString());
		Label landText = new Label("Land:");
		Label land = new Label(info.userInfo(user).getLand().toString());

		geoGrid2.setMaxWidth(350);
		geoGrid2.add(profildata, 1, 1);
		geoGrid2.add(userinfo, 1, 2);
		geoGrid2.add(username, 2, 2);
		geoGrid2.add(userfnamet, 1, 3);
		geoGrid2.add(userfname, 2, 3);
		geoGrid2.add(geschlechtText, 1, 4);
		geoGrid2.add(geschlecht, 2, 4);
		geoGrid2.add(gebText, 1, 5);
		geoGrid2.add(geb, 2, 5);
		geoGrid2.add(landText, 1, 6);
		geoGrid2.add(land, 2, 6);

		final GridPane geoGrid3 = new GridPane();

		tab1 = new Tab();
		tab1.setText("Live Tickers");

		tab2 = new Tab();
		tab2.setText("My Profile");

		final ListView<String> ticklist = new ListView<String>();
		final TickerEvents tevents = new TickerEvents();
		final ObservableList<String> items = FXCollections
				.observableArrayList();
		for (int i = 0; i < tevents.eventList().size(); i++) {
			items.add(tevents.eventList().get(i).getEventname());
		}
		ticklist.setItems(items);
		final Button joinTicker = new Button("Join");
		joinTicker.setMinWidth(50);

		Label welcome = new Label();
		welcome.setText("Hello, " + user + "!");

		final Label beschreibung = new Label();
		final Label eventBeschreibung = new Label();
		eventBeschreibung.setWrapText(true);

		final Label eventname = new Label();
		final Label eventnametext = new Label();
		eventnametext.setWrapText(true);

		final Label eventtyp = new Label();
		final Label eventtyptext = new Label();
		eventtyptext.setWrapText(true);

		final Label eventadmin = new Label();
		final Label eventadmintext = new Label();
		eventadmintext.setWrapText(true);

		final Label eventstart = new Label();
		final Label eventstarttext = new Label();
		eventstarttext.setWrapText(true);

		final Label eventend = new Label();
		final Label eventendtext = new Label();
		eventendtext.setWrapText(true);

		final Label eventscore = new Label();
		final Label eventscoretext = new Label();

		ticklist.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						joinTicker.fire();
					}
					if (mouseEvent.getClickCount() == 1) {
						eventname.setText("Name:");
						beschreibung.setText("Description:");
						eventtyp.setText("Type:");
						eventadmin.setText("Admin:");
						eventstart.setText("Start:");
						eventend.setText("End:");
						eventscore.setText("Score:");
						int index = ticklist.getSelectionModel()
								.getSelectedIndex();
						eventBeschreibung.setText(tevents.eventList()
								.get(index).getEventbeschreibung());
						eventnametext.setText(tevents.eventList().get(index)
								.getEventname());
						eventtyptext.setText(tevents.eventList().get(index)
								.getEventtyp());
						eventadmintext.setText(tevents.eventList().get(index)
								.getUsername());
						eventstarttext.setText(tevents.eventList().get(index)
								.getEventstart().toXMLFormat());
						eventendtext.setText(tevents.eventList().get(index)
								.getEventdauer().toXMLFormat());
						eventscoretext.setText(tevents.eventList().get(index)
								.getEventbewertung().toString());
					}
				}
			}
		});

		joinTicker.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for (Tab opentab : tabPane.getTabs()) {
					if (opentab.getText().equals(
							ticklist.getSelectionModel().getSelectedItem())) {
						return;
					}
				}

				if (tabPane.getTabs().size() < 6
						&& !ticklist.getSelectionModel().isEmpty()) {
					int i = tabPane.getTabs().size();
					k = new Tab();
					k.setText(ticklist.getSelectionModel().getSelectedItem());
					tabPane.getTabs().add(i, k);
					selectTab.select(k);

					pubSubControl.nodeAbonnieren(ticklist.getSelectionModel()
							.getSelectedItem());

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
					final TextArea comments = new TextArea();
					sp2.getChildren().add(comments);
					sp.getItems().addAll(sp1, sp2);

					final ListView<String> liveticks = new ListView<String>();
					final TickerContent cevents = new TickerContent();
					final ObservableList<String> items = FXCollections
							.observableArrayList();
					final int index2 = ticklist.getSelectionModel()
							.getSelectedIndex() + 1;
					for (int f = 0; f < cevents.contentList(index2)
							.getTickerBeitrag().size(); f++) {

						items.add(cevents.contentList(index2)
								.getTickerBeitrag().get(f).getZeit()
								+ ": "
								+ cevents.contentList(index2)
										.getTickerBeitrag().get(f).getText());
						liveticks.setItems(items);

						liveticks
								.setOnMouseClicked(new EventHandler<MouseEvent>() {
									@Override
									public void handle(MouseEvent mouseEvent) {
										if (mouseEvent.getButton().equals(
												MouseButton.PRIMARY)) {
											if (mouseEvent.getClickCount() == 1) {
												final int index3 = liveticks
														.getSelectionModel()
														.getSelectedIndex();
												comments.clear();
												for (int h = 0; h < cevents
														.contentList(index2)
														.getTickerBeitrag()
														.get(index3)
														.getKommentar().size(); h++) {

													comments.appendText(cevents
															.contentList(index2)
															.getTickerBeitrag()
															.get(index3)
															.getKommentar()
															.get(h)
															.getKommentarUser()
															+ " wrote:\n");
													comments.appendText(cevents
															.contentList(index2)
															.getTickerBeitrag()
															.get(index3)
															.getKommentar()
															.get(h)
															.getKommentarText()
															+ "\n");

												}
											}
										}
									}
								});
					}
					sp1.getChildren().add(liveticks);

					final TextField chatText = new TextField();

					final Button sendchat = new Button("Send");
					sendchat.setMinWidth(50);
					sendchat.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							if (liveticks.getSelectionModel().isEmpty()) {
								String beitrag = chatText.getText();
								int eventnr = ticklist.getSelectionModel()
										.getSelectedIndex() + 1;
								new TickerContent().createBeitrag(eventnr,
										beitrag);
								pubSubControl.nodeVeroeffentlichen(ticklist
										.getSelectionModel().getSelectedItem(),
										"<beitrag>" + beitrag + "</beitrag>");
							} else if (!liveticks.getSelectionModel().isEmpty()) {

								String beitrag = chatText.getText();
								int eventnr = ticklist.getSelectionModel()
										.getSelectedIndex() + 1;
								int ticknr = liveticks.getSelectionModel()
										.getSelectedIndex() + 1;
								new TickerContent().createKommentar(eventnr,
										ticknr, user, beitrag);
							}
							final ObservableList<String> items = FXCollections
									.observableArrayList();
							liveticks.getItems().setAll(items);

							for (int f = 0; f < cevents.contentList(index2)
									.getTickerBeitrag().size(); f++) {

								items.add(cevents.contentList(index2)
										.getTickerBeitrag().get(f).getZeit()
										+ ": "
										+ cevents.contentList(index2)
												.getTickerBeitrag().get(f)
												.getText());
								liveticks.setItems(items);
							}

							// Das sollte eig funktionieren, tut es aber nicht?!
							int eventnr = ticklist.getSelectionModel()
									.getSelectedIndex() + 1;
							int ticknr = liveticks.getSelectionModel()
									.getSelectedIndex() + 1;

							comments.clear();
							for (int h = 0; h < cevents.contentList(eventnr)
									.getTickerBeitrag().get(ticknr)
									.getKommentar().size(); h++) {

								comments.appendText(cevents
										.contentList(eventnr)
										.getTickerBeitrag().get(ticknr)
										.getKommentar().get(h)
										.getKommentarUser()
										+ " wrote:\n");
								comments.appendText(cevents
										.contentList(eventnr)
										.getTickerBeitrag().get(ticknr)
										.getKommentar().get(h)
										.getKommentarText()
										+ "\n");
							}
							// Bis hier

						}
					});

					geoGridk.add(sendchat, 1, 1);
					geoGridk.add(chatText, 0, 1);

					geoGridk.add(sp, 0, 0);

					k.setContent(geoGridk);
					k.setOnClosed(new EventHandler<javafx.event.Event>() {

						public void handle(javafx.event.Event t) {
							pubSubControl.nodeKuendigen(ticklist
									.getSelectionModel().getSelectedItem()); // erstellt
																				// neue
																				// Node
						}
					});

					i++;
				}
			}
		});

		final Button create = new Button("Create Ticker");
		create.setMinWidth(50);

		Button refresh = new Button("Refresh");
		refresh.setMinWidth(50);
		refresh.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ticklist.getItems().setAll(items);
				for (int i = 0; i < tevents.eventList().size(); i++) {
					items.add(tevents.eventList().get(i).getEventname());
				}
				ticklist.setItems(items);
			}
		});

		create.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				createNewEventTab = new Tab();
				createNewEventTab.setText("New Ticker");
				selectTab.select(createNewEventTab);
				create.setDisable(true);

				createNewEventTab
						.setOnClosed(new EventHandler<javafx.event.Event>() {

							public void handle(javafx.event.Event t) {
								create.setDisable(false);
								selectTab.select(tab1);
							}
						});

				final GridPane geoGridNew = new GridPane(); // Grid fuer new
															// ticker
				geoGridNew.setHgap(5); // Abstand links/rechts
				geoGridNew.setVgap(5); // Abstand oben/unten

				ColumnConstraints column1 = new ColumnConstraints();
				column1.setPercentWidth(30);
				ColumnConstraints column2 = new ColumnConstraints();
				column2.setPercentWidth(40);
				ColumnConstraints column3 = new ColumnConstraints();
				column3.setPercentWidth(30);
				geoGridNew.getColumnConstraints().addAll(column1, column2,
						column3);

				final Label errmessage = new Label();

				Label eventname = new Label("Event name:");
				final TextField eventnametextField = new TextField();

				Label eventbeschreibung = new Label("Event description:");
				final TextField eventbeschreibungField1 = new TextField();

				Label eventtyp = new Label("Event type:");
				final ChoiceBox typchoice = new ChoiceBox(FXCollections
						.observableArrayList("Football", "Baseball",
								"Basketball", "Formula 1"));
				typchoice.getSelectionModel().selectFirst();

				Label start = new Label("Event start:");
				final TextField startField = new TextField();
				startField.setPromptText("HH:MM");

				Label ende = new Label("Event end:");
				final TextField endeField = new TextField();
				endeField.setPromptText("HH:MM");

				Button createbtn = new Button();
				createbtn.setText("Create");
				createbtn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						TickerEvents make = new TickerEvents();
						errmessage.setText("");

						Map<String, String> eventdata = new HashMap<String, String>();
						eventdata.put("admin", user);
						eventdata.put("name", eventnametextField.getText());
						eventdata.put("beschr",
								eventbeschreibungField1.getText());
						eventdata.put("typ", typchoice.getSelectionModel()
								.getSelectedItem().toString());
						eventdata.put("start", startField.getText() + ":00");
						eventdata.put("ende", endeField.getText() + ":00");
						eventdata.put("bewertung", new Integer(5).toString());

						make.createEvent(eventdata);
						pubSubControl.nodeErstellen(eventnametextField
								.getText()); // erstellt neue Node
						pubSubControl.nodeAbonnieren(eventnametextField
								.getText());

						createNewEventTab.setText(eventnametextField.getText());
						createNewEventTab
								.setOnClosed(new EventHandler<javafx.event.Event>() {

									public void handle(javafx.event.Event t) {
										pubSubControl
												.nodeKuendigen(eventnametextField
														.getText()); // erstellt
																		// neue
																		// Node
									}
								});

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
						geoGridk.getColumnConstraints()
								.addAll(column1, column2);
						geoGridk.getRowConstraints().addAll(row1, row2);

						SplitPane sp = new SplitPane();
						final StackPane sp1 = new StackPane();
						final ListView<String> liveticks = new ListView<String>();
						sp1.getChildren().add(liveticks);
						final StackPane sp2 = new StackPane();
						TextArea comments = new TextArea();
						sp2.getChildren().add(comments);
						sp.getItems().addAll(sp1, sp2);

						final TextField chatText = new TextField();

						Button sendchat = new Button("Send");
						sendchat.setMinWidth(50);
						sendchat.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {

								// liveticks.setItems(items);
								if (liveticks.getSelectionModel().isEmpty()) {
									String beitrag = chatText.getText();
									int eventnr = ticklist.getItems().size();
									new TickerContent().createBeitrag(eventnr,
											beitrag);
									pubSubControl.nodeVeroeffentlichen(
											eventnametextField.getText(),
											"<beitrag>" + beitrag
													+ "</beitrag>");
								} else if (!liveticks.getSelectionModel()
										.isEmpty()) {

									String beitrag = chatText.getText();
									int eventnr = ticklist.getItems().size();
									int ticknr = liveticks.getItems().size() + 1;
									new TickerContent().createKommentar(
											eventnr, ticknr, user, beitrag);
									pubSubControl.nodeVeroeffentlichen(
											eventnametextField.getText(),
											"<beitrag>" + beitrag
													+ "</beitrag>");
								}
								final ObservableList<String> items = FXCollections
										.observableArrayList();
								liveticks.getItems().setAll(items);
								final TickerContent cevents = new TickerContent();
								final int index2 = ticklist.getItems().size();
								for (int f = 0; f < cevents.contentList(index2)
										.getTickerBeitrag().size(); f++) {

									items.add(cevents.contentList(index2)
											.getTickerBeitrag().get(f)
											.getZeit()
											+ ": "
											+ cevents.contentList(index2)
													.getTickerBeitrag().get(f)
													.getText());
									liveticks.setItems(items);
								}
							}
						});

						geoGridk.add(sendchat, 1, 1);
						geoGridk.add(chatText, 0, 1);

						geoGridk.add(sp, 0, 0);

						ticklist.getItems().setAll(items); // damit nach
															// erstellen,
															// automatisch die
															// Liste
															// aktualisiert wird
						for (int i = 0; i < tevents.eventList().size(); i++) {
							items.add(tevents.eventList().get(i).getEventname());
						}
						ticklist.setItems(items);
						createNewEventTab.setContent(geoGridk);

					}
				});

				geoGridNew.add(eventname, 1, 0);
				geoGridNew.add(eventnametextField, 1, 1);
				geoGridNew.add(eventbeschreibung, 1, 2);
				geoGridNew.add(eventbeschreibungField1, 1, 3);
				geoGridNew.add(eventtyp, 1, 4);
				geoGridNew.add(typchoice, 1, 5);
				geoGridNew.add(start, 1, 6);
				geoGridNew.add(startField, 1, 7);
				geoGridNew.add(ende, 1, 8);
				geoGridNew.add(endeField, 1, 9);
				geoGridNew.add(createbtn, 1, 10);

				createNewEventTab.setContent(geoGridNew);
				tabPane.getTabs().add(createNewEventTab);
			}

		});

		Label tickertext = new Label("Choose the ticker you'd like to join");

		Button test = new Button("Create Ticker");

		hbox.getChildren().addAll(joinTicker, refresh);
		geoGrid.add(tickertext, 0, 0);
		geoGrid.add(ticklist, 0, 1);
		geoGrid.add(welcome, 1, 0);
		geoGrid.add(create, 1, 2);
		geoGrid.add(hbox, 0, 2);
		geoGrid3.add(eventname, 0, 0);
		geoGrid3.add(eventnametext, 0, 1);
		geoGrid3.add(beschreibung, 0, 2);
		geoGrid3.add(eventBeschreibung, 0, 3);
		geoGrid3.add(eventtyp, 0, 4);
		geoGrid3.add(eventtyptext, 0, 5);
		geoGrid3.add(eventadmin, 0, 6);
		geoGrid3.add(eventadmintext, 0, 7);
		geoGrid3.add(eventstart, 0, 8);
		geoGrid3.add(eventstarttext, 0, 9);
		geoGrid3.add(eventend, 0, 10);
		geoGrid3.add(eventendtext, 0, 11);
		geoGrid3.add(eventscore, 0, 12);
		geoGrid3.add(eventscoretext, 0, 13);
		geoGrid.add(geoGrid3, 1, 1);

		tab1.setContent(geoGrid);
		tab2.setContent(geoGrid2);

		tabPane.getTabs().addAll(tab1, tab2);
		root.getChildren().add(tabPane);

		primaryStage.show();
	}

	/**
	 * The main() method is ignored in correctly deployed JavaFX application.
	 * main() serves only as fallback in case the application can not be
	 * launched through deployment artifacts, e.g., in IDEs with limited FX
	 * support. NetBeans ignores main().
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
