package controller;

import bo.BOFactory;
import bo.custom.ManageProgramsBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.ProgramsDTO;
import dto.RegistrationFeeDTO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import util.CommonFunctions;
import util.Validator;

import java.util.HashMap;
import java.util.regex.Pattern;

public class ManageProgramsFormController {
    public JFXTextField txtProgramId;
    public JFXTextField txtProgramName;
    public JFXTextField txtProgramDuration;
    public JFXTextField txtProgramFee;
    public JFXButton updateBtnId;
    public JFXButton deleteBtnId;
    public JFXButton saveBtnId;
    public Label lblSetProgramId;
    public JFXTextField txtRegistrationFee;
    public JFXButton updateBtnFee;
    public JFXButton deleteBtnFee;
    public JFXButton saveFeeBtn;

    HashMap<TextField, Pattern> map = new HashMap<>();
    Pattern idPattern = Pattern.compile("^(CT-)[0-9]{3}$");
    Pattern namePattern = Pattern.compile("^[A-z0-9 ]{4,}$");
    Pattern durationPattern = Pattern.compile("^[0-9]+[ ](months|day|days|month|year|years)$");
    Pattern feePattern = Pattern.compile("^[0-9]+[.]?[0-9]+$");

    Pattern registrationFeePattern = Pattern.compile("^[0-9]+[.]?[0-9]+$");

    ManageProgramsBO manageProgramsBO = (ManageProgramsBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.ManagePrograms);

    public void initialize() {
        validateFields();
    }

    public void validateEvent(KeyEvent event) {
        if (txtProgramId.getText().length() == 6 && manageProgramsBO.ifProgramsExists(txtProgramId.getText())) {
            ProgramsDTO programsDTO = manageProgramsBO.searchPrograms(txtProgramId.getText());
            txtProgramName.setText(programsDTO.getName());
            txtProgramDuration.setText(programsDTO.getDuration());
            txtProgramFee.setText(String.valueOf(programsDTO.getFee()));
        } else {
            txtProgramId.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode().equals(KeyCode.BACK_SPACE)) {
                        txtProgramName.clear();
                        txtProgramDuration.clear();
                        txtProgramFee.clear();
                    }
                }
            });
        }

    Object response = Validator.validate(map);
        if(response instanceof Boolean)

    {
        saveBtnId.setDisable(false);
        updateBtnId.setDisable(false);
        deleteBtnId.setDisable(false);
    }
        if(response instanceof TextField)

    {
        if (event.getCode().equals(KeyCode.TAB)) {
            TextField txt = (TextField) response;
            txt.requestFocus();
            saveBtnId.setDisable(true);
            updateBtnId.setDisable(true);
            deleteBtnId.setDisable(true);
        }
    }

}

    public void validateFields() {
        map.put(txtProgramId, idPattern);
        map.put(txtProgramName, namePattern);
        map.put(txtProgramDuration, durationPattern);
        map.put(txtProgramFee, feePattern);
    }

    public void btnSaveProgram(ActionEvent actionEvent) {
        ProgramsDTO dto = new ProgramsDTO(txtProgramId.getText(), txtProgramName.getText(),
                txtProgramDuration.getText(), Double.parseDouble(txtProgramFee.getText()));

        if (dto != null) {
            if (manageProgramsBO.savePrograms(dto)) {
                CommonFunctions.setNotificationSuccess("Program Saved", "Saving Program is successful");
                clearFields();
            } else {
                return;
            }
        } else {
            return;
        }
    }

    public void btnUpdateProgram(ActionEvent actionEvent) {
        ProgramsDTO dto = new ProgramsDTO(txtProgramId.getText(), txtProgramName.getText(),
                txtProgramDuration.getText(), Double.parseDouble(txtProgramFee.getText()));

        if (dto != null) {
            if (manageProgramsBO.ifProgramsExists(txtProgramId.getText())) {
                if (manageProgramsBO.updatePrograms(dto)) {
                    CommonFunctions.setNotificationSuccess("Program Updated", "Updating Program is successful");
                    clearFields();
                } else {
                    return;
                }
            }
        } else {
            return;
        }
    }

    public void btnDeleteProgram(ActionEvent actionEvent) {
        ProgramsDTO dto = new ProgramsDTO(txtProgramId.getText(), txtProgramName.getText(),
                txtProgramDuration.getText(), Double.parseDouble(txtProgramFee.getText()));

        if (dto != null) {
            if (manageProgramsBO.ifProgramsExists(txtProgramId.getText())) {
                if (manageProgramsBO.deletePrograms(dto)) {
                    CommonFunctions.setNotificationSuccess("Program Deleted", "Deleting Program is successful");
                    clearFields();
                } else {
                    return;
                }
            }
        } else {
            return;
        }
    }

    public void btnGenerateProgramId(ActionEvent actionEvent) {
        String id = manageProgramsBO.generateProgramsId();
        txtProgramId.setText(id);
        lblSetProgramId.setText(id);
    }

    public void clearFields() {
        txtProgramId.clear();
        txtProgramName.clear();
        txtProgramDuration.clear();
        txtProgramFee.clear();
    }

    public void btnSaveFee(ActionEvent actionEvent) {
        RegistrationFeeDTO dto = new RegistrationFeeDTO(Double.parseDouble(txtRegistrationFee.getText()));
        if (dto != null) {
            if (manageProgramsBO.saveRegistrationFee(dto)) {
                CommonFunctions.setNotificationSuccess("Registration Fee Saved", "Saving Registration Fee is successful");
                clearRegistrationFeeField();
            } else {
                return;
            }
        } else {
            return;
        }
    }

    public void btnUpdateFee(ActionEvent actionEvent) {
        RegistrationFeeDTO dto = new RegistrationFeeDTO(Double.parseDouble(txtRegistrationFee.getText()));
        if (dto != null) {
            if (manageProgramsBO.ifRegistrationFeeExists(txtRegistrationFee.getText())) {
                if (manageProgramsBO.updateRegistrationFee(dto)) {
                    CommonFunctions.setNotificationSuccess("Registration Fee updated", "Registration fee updating is successful");
                    clearRegistrationFeeField();
                } else {
                    return;
                }
            }
        } else {
            return;
        }
    }

    public void btnDeleteFee(ActionEvent actionEvent) {
        RegistrationFeeDTO dto = new RegistrationFeeDTO(Double.parseDouble(txtRegistrationFee.getText()));
        if (dto != null) {
            if (manageProgramsBO.ifRegistrationFeeExists(txtRegistrationFee.getText())) {
                if (manageProgramsBO.deleteRegistrationFee(dto)) {
                    CommonFunctions.setNotificationSuccess("Registration Fee Deleted", "Deleting Registration Fee is successful");
                    clearRegistrationFeeField();
                } else {
                    return;
                }
            }
        } else {
            return;
        }
    }

    public void validateRegistrationEvent(KeyEvent event) {
        if (registrationFeePattern.matcher(txtRegistrationFee.getText()).matches()) {
            saveFeeBtn.setDisable(false);
            updateBtnFee.setDisable(false);
            deleteBtnFee.setDisable(false);
            txtProgramFee.setStyle("-fx-border-color: green");
        } else {
            saveFeeBtn.setDisable(true);
            updateBtnFee.setDisable(true);
            deleteBtnFee.setDisable(true);
            txtProgramFee.setStyle("-fx-border-color: red");
        }
    }

    public void clearRegistrationFeeField() {
        txtRegistrationFee.clear();
    }
}
