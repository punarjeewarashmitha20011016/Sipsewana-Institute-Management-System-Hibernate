package controller;

import bo.BOFactory;
import bo.custom.LoginFormBO;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane loginPane;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public static String[] userNameArray = new String[1];
    LoginFormBO loginFormBO = (LoginFormBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.LOGINFORM);


    public void btnLogin(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equals("admin") && txtPassword.getText().equals("admin")) {
            Parent parent = FXMLLoader.load(this.getClass().getResource("../view/AdminForm.fxml"));
            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) loginPane.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Admin Form");
            primaryStage.centerOnScreen();
            primaryStage.show();
        } else if (loginFormBO.receptionistsLogin(txtUserName.getText(), txtPassword.getText())) {
            userNameArray[0]=txtUserName.getText();
            Parent parent = FXMLLoader.load(this.getClass().getResource("../view/ReceptionistsForm.fxml"));
            Scene scene = new Scene(parent);
            Stage primaryStage = (Stage) loginPane.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Receptionists Form");
            primaryStage.centerOnScreen();
            primaryStage.show();

        }
    }
}
