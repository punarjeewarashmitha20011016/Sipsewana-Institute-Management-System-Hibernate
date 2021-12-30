package controller;

import bo.BOFactory;
import bo.custom.ManageReceptionistsBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.ReceptionistDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import util.CommonFunctions;
import util.Validator;

import java.util.HashMap;
import java.util.regex.Pattern;

public class ManageReceptionistsFormController {
    public JFXTextField txtReceptionistId;
    public JFXTextField txtReceptionistName;
    public JFXTextField txtReceptionistNic;
    public JFXTextField txtReceptionistUserName;
    public JFXTextField txtReceptionistPassword;
    public JFXButton updateBtnId;
    public JFXButton deleteBtnId;
    public JFXButton saveBtnId;
    public Label lblSetReceptionistId;

    HashMap<TextField, Pattern> map = new HashMap<>();
    Pattern idPattern = Pattern.compile("^(R-)[0-9]{3}$");
    Pattern namePattern = Pattern.compile("^[A-z0-9 ]{4,}$");
    Pattern nicPattern = Pattern.compile("^(([0-9]{9}[V|v])|([0-9]{12}))$");
    Pattern usernamePattern = Pattern.compile("^[A-z0-9]{4,}[ ]*[A-z0-9]*[@]?(gmail|yahoo)?[.]?(com|lk|uk)?$");
    Pattern passwordPattern = Pattern.compile("^[A-z0-9]{4,}[ ]*[A-z0-9]*[@]?(gmail|yahoo)?[.]?(com|lk|uk)?$");

    ManageReceptionistsBO receptionistsBO = (ManageReceptionistsBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.ManageReceptionists);

    public void initialize() {
        validateFields();
    }

    public void validateFields() {
        map.put(txtReceptionistId, idPattern);
        map.put(txtReceptionistName, namePattern);
        map.put(txtReceptionistNic, nicPattern);
        map.put(txtReceptionistUserName, usernamePattern);
        map.put(txtReceptionistPassword, passwordPattern);
    }

    public void validateEvent(KeyEvent event) {
        Object response = Validator.validate(map);

        if (response instanceof Boolean) {
            saveBtnId.setDisable(false);
            updateBtnId.setDisable(false);
            deleteBtnId.setDisable(false);
        }
        if (response instanceof TextField) {
            if (event.getCode().equals(KeyCode.TAB)) {
                TextField txt = (TextField) response;
                saveBtnId.setDisable(true);
                updateBtnId.setDisable(true);
                deleteBtnId.setDisable(true);
                txt.requestFocus();
            }
        }
    }

    public void btnSaveReceptionist(ActionEvent actionEvent) {
        ReceptionistDTO receptionist = new ReceptionistDTO(txtReceptionistId.getText(), txtReceptionistName.getText(),
                txtReceptionistNic.getText(), txtReceptionistUserName.getText(),
                txtReceptionistPassword.getText());

        if (receptionist != null) {
            if (receptionistsBO.saveReceptionists(receptionist)) {
                CommonFunctions.setNotificationSuccess("Receptionist Saved", "Saving Receptionist is successful");
                clearFields();
            } else {
                return;
            }
        } else {
            return;
        }
    }

    public void btnUpdateReceptionist(ActionEvent actionEvent) {
        ReceptionistDTO receptionist = new ReceptionistDTO(txtReceptionistId.getText(), txtReceptionistName.getText(),
                txtReceptionistNic.getText(), txtReceptionistUserName.getText(),
                txtReceptionistPassword.getText());

        if (receptionist != null) {
            if (receptionistsBO.ifReceptionistsExists(txtReceptionistId.getText())) {
                if (receptionistsBO.updateReceptionists(receptionist)) {
                    CommonFunctions.setNotificationSuccess("Receptionist Updated", "Updating Receptionist is successful");
                    clearFields();
                } else {
                    return;
                }
            }
        } else {
            return;
        }
    }

    public void btnDeleteReceptionist(ActionEvent actionEvent) {
        ReceptionistDTO receptionist = new ReceptionistDTO(txtReceptionistId.getText(), txtReceptionistName.getText(),
                txtReceptionistNic.getText(), txtReceptionistUserName.getText(),
                txtReceptionistPassword.getText());

        if (receptionist != null) {
            if (receptionistsBO.ifReceptionistsExists(txtReceptionistId.getText())) {
                if (receptionistsBO.deleteReceptionists(receptionist)) {
                    CommonFunctions.setNotificationSuccess("Receptionist Deleted", "Deleting Receptionist is successful");
                    clearFields();
                } else {
                    return;
                }
            }
        } else {
            return;
        }
    }

    public void btnGenerateReceptionistId(ActionEvent actionEvent) {
        String id = receptionistsBO.generateReceptionistId();
        txtReceptionistId.setText(id);
        lblSetReceptionistId.setText(id);
    }

    public void clearFields() {
        txtReceptionistId.clear();
        txtReceptionistName.clear();
        txtReceptionistNic.clear();
        txtReceptionistUserName.clear();
        txtReceptionistPassword.clear();
    }

}
