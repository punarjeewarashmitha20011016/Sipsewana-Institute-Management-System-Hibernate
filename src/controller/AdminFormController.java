package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.CommonFunctions;

import java.io.IOException;
import java.net.URL;

public class AdminFormController {
    public BorderPane adminFormPane;
    public ImageView imgWheel2;
    public ImageView imgWheel1;
    public AnchorPane adminChildPane;
    public Label lblSetName;
    public Label lblTime;
    public ImageView imgNotification;
    public Label lblNotificationCount;

    public void initialize(){
        CommonFunctions.animatedWheels(imgWheel1,imgWheel2);
        CommonFunctions.loadDateAndTime(lblTime);
    }
    public void btnManagePrograms(ActionEvent actionEvent) throws IOException {
        loadUi("ManageProgramsForm");
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        CommonFunctions.animatedWheels(imgWheel1,imgWheel2);
        Parent parent = FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"));
        Scene scene=new Scene(parent);
        Stage primaryStage= (Stage) adminFormPane.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Form");
        primaryStage.show();
    }

    public void btnManageReceptionists(ActionEvent actionEvent) throws IOException {
        loadUi("ManageReceptionistsForm");
    }

    public void showNotifications(MouseEvent mouseEvent) {
    }
    public void loadUi(String fileName) throws IOException {
        CommonFunctions.animatedWheels(imgWheel1,imgWheel2);
        URL resource = getClass().getResource("../view/"+fileName+".fxml");
        Parent load = FXMLLoader.load(resource);
        adminChildPane.getChildren().clear();
        adminChildPane.getChildren().add(load);
    }
}
