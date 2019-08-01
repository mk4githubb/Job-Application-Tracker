package sample;

import javafx.application.Application;

import javafx.beans.binding.Bindings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import javafx.stage.Stage;

public class Main extends Application {

    static {
        Driver.setup();
    }

    private static ObservableList<Job> observableListInProgressJobs = FXCollections.observableList(Driver.getInProgressTransversal());
    private static ObservableList<Job> observableListAppliedJobs = FXCollections.observableList(Driver.getAppliedTransversal());

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Root
        ScrollPane root = new ScrollPane();
        root.setId("root");
        root.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setFitToWidth(true);
        root.setPadding(new Insets(5));

                // Vbox
                VBox verticalBox = new VBox();
                verticalBox.prefWidthProperty().bind(root.widthProperty());
                verticalBox.setId("verticalBox");
                verticalBox.setSpacing(7);
                verticalBox.setStyle("-fx-background-color:#E4D9FF;");
                verticalBox.setAlignment(Pos.CENTER_LEFT);

                        // Border Pane
                        BorderPane topBar1 = new BorderPane();

                                Label progressLabel = new Label("Applications In Progress");
                                progressLabel.setStyle("-fx-color:#30343F; -fx-font-size:20; -fx-underline: true");

                                TextField searchString = new TextField();
                                searchString.setPromptText("find job...");
                                searchString.setPrefWidth(400);
                                topBar1.setLeft(progressLabel);
                                topBar1.setRight(searchString);
                                topBar1.setMargin(progressLabel, new Insets(7,0,0,25));
                                topBar1.setMargin(searchString, new Insets(7,25,0,0));

                        // Table 1
                        TableView<Job> progressTable = new TableView<>(observableListInProgressJobs);
                                progressTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                                progressTable.setItems(observableListInProgressJobs);
                                progressTable.setStyle("-fx-background-color: #427391");
                                progressTable.setPlaceholder(new Label("No Job Applications Added\nAdd Jobs to start tracking!"));
                                progressTable.setId("progressTable");

                                TableColumn<Job, String> ipStauts = new TableColumn<>("Status");
                                ipStauts.setCellValueFactory(new PropertyValueFactory<Job, String>("jobStatus"));

                                TableColumn<Job, String> daysLeft = new TableColumn<>("Days Left to Apply");
                                daysLeft.setCellValueFactory(new PropertyValueFactory<Job, String>("daysLeftToApply"));

                                TableColumn<Job, String> ipRoleName = new TableColumn<>("Name of Role");
                                ipRoleName.setCellValueFactory(new PropertyValueFactory<Job, String>("roleName"));

                                TableColumn<Job, String> ipCompanyName = new TableColumn<>("Name of Company");
                                ipCompanyName.setCellValueFactory(new PropertyValueFactory<Job, String>("companyName"));

                                TableColumn<Job, String> ipLastDate= new TableColumn<>("Last Date to Apply");
                                ipLastDate.setCellValueFactory(new PropertyValueFactory<Job, String>("lastDate"));

                                TableColumn<Job, String> temp = new TableColumn<>("Applied on");
                                temp.setCellValueFactory(new PropertyValueFactory<Job, String>("dateApplied"));

                                progressTable.getColumns().addAll(ipStauts,daysLeft, ipRoleName, ipCompanyName, ipLastDate);

                                    progressTable.setRowFactory(tv -> {
                                        TableRow<Job> row = new TableRow<>();

                                        row.itemProperty().addListener((obs, previous, current) -> {
                                            if(current!= null && current.getJobStatus()==JobStatus.urgent ){
                                                row.setStyle("-fx-background: #F08080");
                                            }
                                        });
                                        return row ;
                                    });

                                progressTable.setFixedCellSize(25);
                                progressTable.prefHeightProperty().bind(progressTable.fixedCellSizeProperty().multiply(Bindings.size(progressTable.getItems()).add(1.01)));
                                progressTable.setMaxHeight(500);
                                progressTable.setMinHeight(300);


                        // Hbox
                        HBox topBar2 = new HBox();

                                Button markAppliedButton = new Button("Mark Selected Jobs Applied");
                                markAppliedButton.setPrefWidth(400);
                                /*
                                    Button listener
                                 */
                                topBar2.getChildren().addAll(markAppliedButton);

                        // Hbox
                        HBox topBar3 = new HBox();
                        topBar3.setSpacing(5);

                                TextField inRoleName = new TextField(); inRoleName.setPromptText("Role Name");
                                TextField inCompanyName = new TextField(); inCompanyName.setPromptText("Company Name");
                                TextField inLastDate = new TextField(); inLastDate.setPromptText("DD/MM/YYYY");

                                Button addJobButton = new Button("Add Job Application!");

                                HBox.setHgrow(inCompanyName,Priority.ALWAYS);
                                HBox.setHgrow(inLastDate,Priority.ALWAYS);
                                HBox.setHgrow(inRoleName,Priority.ALWAYS);
                                HBox.setHgrow(addJobButton,Priority.ALWAYS);
                                topBar3.setMargin(inRoleName, new Insets(0,0,0,3));
                                topBar3.setMargin(addJobButton, new Insets(0,3,0,0));
                                topBar3.getChildren().addAll(inRoleName, inCompanyName, inLastDate, addJobButton);

                        // Label
                        Label appliedLabel = new Label("Applied Applications ");
                                appliedLabel.setStyle("-fx-color:#30343F; -fx-font-size:20; -fx-underline: true");
                                appliedLabel.setPadding(new Insets(5,0,0,25));

                        // Table
                        TableView<Job> appliedTable = new TableView<>(observableListInProgressJobs);
                                appliedTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                                appliedTable.setItems(observableListAppliedJobs);
                                appliedTable.setStyle("-fx-background-color: #427391");
                                appliedTable.setMaxHeight(400);
                                appliedTable.setPlaceholder(new Label("No Jobs Applied Yet."));
                                appliedTable.setFixedCellSize(25);
                                appliedTable.setId("appliedTable");



                                TableColumn<Job, String> apStauts = new TableColumn<>("Status");
                                apStauts.setCellValueFactory(new PropertyValueFactory<Job, String>("jobStatus"));

                                TableColumn<Job, String> apRoleName = new TableColumn<>("Name of Role");
                                apRoleName.setCellValueFactory(new PropertyValueFactory<Job, String>("roleName"));

                                TableColumn<Job, String> apCompanyName = new TableColumn<>("Name of Company");
                                apCompanyName.setCellValueFactory(new PropertyValueFactory<Job, String>("companyName"));

                                TableColumn<Job, String> apLastDate= new TableColumn<>("Applied on");
                                apLastDate.setCellValueFactory(new PropertyValueFactory<Job, String>("dateApplied"));

                                appliedTable.getColumns().addAll(apStauts, apRoleName, apCompanyName, apLastDate);
                                appliedTable.prefHeightProperty().bind(appliedTable.fixedCellSizeProperty().multiply(Bindings.size(appliedTable.getItems()).add(1.01)));
                                appliedTable.setMaxHeight(500);
                                appliedTable.setMinHeight(300);
                verticalBox.getChildren().addAll(topBar1, progressTable, topBar2, topBar3, appliedLabel,appliedTable);

        root.setContent(verticalBox);


        /**
         *
         * Event Listeners Start
         *
         */


        //      add job button
        addJobButton.setOnAction(e->{
            Job adedJob;
            try{
                adedJob = Driver.addJob(inRoleName.getText(), inCompanyName.getText(), inLastDate.getText());
            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Date is not Valid!");
                alert.show();
                return;
            }

            if(adedJob==null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Name of the Role OR Name of the Company \n \n AND \n\n Last Date is required");
                alert.show();
            }
            else{
                observableListInProgressJobs.add(Driver.getInProgressTransversal().indexOf(adedJob), adedJob);
            }

            progressTable.prefHeightProperty().bind(progressTable.fixedCellSizeProperty().multiply(Bindings.size(progressTable.getItems()).add(1.01)));
            progressTable.refresh();
            inCompanyName.clear();
            inRoleName.clear();
            inLastDate.clear();
        });


        //      mark applied button
        markAppliedButton.setOnAction(e ->{
            Job selectedJob = progressTable.getSelectionModel().getSelectedItem();
            if(selectedJob == null){
                return;
            }
            Driver.markJobApplied(selectedJob);

            observableListInProgressJobs.remove(selectedJob);
            observableListAppliedJobs.add(Driver.getAppliedTransversal().indexOf(selectedJob), selectedJob);

            appliedTable.prefHeightProperty().bind(appliedTable.fixedCellSizeProperty().multiply(Bindings.size(appliedTable.getItems()).add(1.01)));

            progressTable.refresh();
            appliedTable.refresh();
        });


        //      find Jobs
        searchString.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.equals("")) {
                progressTable.setItems(observableListInProgressJobs);
                progressLabel.setText("Applications In Progress");
                progressTable.getColumns().removeAll(temp);
                verticalBox.getChildren().addAll(topBar2, topBar3, appliedLabel,appliedTable);
                progressTable.prefHeightProperty().bind(progressTable.fixedCellSizeProperty().multiply(Bindings.size(progressTable.getItems()).add(1.01)));
                progressTable.refresh();

            }
            else {
                ObservableList<Job> foundJobs = FXCollections.observableList(Driver.searchJob(searchString.getText()));
                progressTable.setItems(foundJobs);
                progressLabel.setText("Search Results");
                verticalBox.getChildren().removeAll(topBar2, topBar3, appliedLabel,appliedTable);
                progressTable.getColumns().removeAll(temp);
                progressTable.getColumns().add(temp);
                progressTable.prefHeightProperty().bind(progressTable.fixedCellSizeProperty().multiply(Bindings.size(progressTable.getItems()).add(1.01)));
                progressTable.refresh();
            }

        });

        //      On main window closing update and Save
        primaryStage.setOnCloseRequest(e->{
            Driver.updateAndSave();
        });



        /**
         *
         * Event Listeners END
         *
         */


        primaryStage.setTitle("Job Application Tracker");
        Scene mainScene  = new Scene(root, 750, 765);
        mainScene.getStylesheets().add(getClass().getResource("/sample/css.css").toExternalForm());
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
