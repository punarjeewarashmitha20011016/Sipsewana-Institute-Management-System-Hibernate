package controller;

import bo.BOFactory;
import bo.custom.ReceptionistFormBO;
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

import static controller.LoginFormController.userNameArray;

public class ReceptionistsFormController {
    public BorderPane recepFormPane;
    public ImageView imgWheel2;
    public ImageView imgWheel1;
    public AnchorPane recepChildPane;
    public Label lblSetName;
    public Label lblTime;
    public ImageView imgNotification;
    public Label lblNotificationCount;
    private ReceptionistFormBO receptionistFormBO = (ReceptionistFormBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.RECEPTIONISTSFORM);

    public void initialize() {
        CommonFunctions.animatedWheels(imgWheel1,imgWheel2);
        CommonFunctions.loadDateAndTime(lblTime);
        String receptionistUserName = userNameArray[0];
        String receptionistName = receptionistFormBO.getReceptionistName(receptionistUserName);
        lblSetName.setText("Hi "+receptionistName);
        lblSetName.setStyle("-fx-font-size: 15px;-fx-font-weight: 800;-fx-font-family: -apple-system;");
    }

    public void btnRegisterStudents(ActionEvent actionEvent) throws IOException {
        loadUi("RegisterStudentsForm");
    }

    public void btnManageStudents(ActionEvent actionEvent) throws IOException {
        loadUi("ManageStudentsForm");
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        CommonFunctions.animatedWheels(imgWheel1,imgWheel2);
        Parent parent = FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"));
        Scene scene = new Scene(parent);
        Stage primaryStage = (Stage) recepFormPane.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Form");
        primaryStage.show();
    }

    public void btnViewProgramAndDetails(ActionEvent actionEvent) throws IOException {
        loadUi("ViewProgramDetailsForm");
    }

    public void loadUi(String fileName) throws IOException {
        CommonFunctions.animatedWheels(imgWheel1,imgWheel2);
        URL resource = getClass().getResource("../view/" + fileName + ".fxml");
        Parent load = FXMLLoader.load(resource);
        recepChildPane.getChildren().clear();
        recepChildPane.getChildren().add(load);
    }

    public void showNotifications(MouseEvent mouseEvent) {
        CommonFunctions.animatedWheels(imgWheel1,imgWheel2);
    }

    public void btnViewStudentDetails(ActionEvent actionEvent) throws IOException {
        loadUi("ViewStudentsForm");
    }
}
