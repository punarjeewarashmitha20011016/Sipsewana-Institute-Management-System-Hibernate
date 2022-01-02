package controller;

import bo.BOFactory;
import bo.custom.ManageStudentsBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import util.CommonFunctions;
import util.Validator;

import java.util.HashMap;
import java.util.regex.Pattern;

import static util.CommonFunctions.setDate;

public class ManageStudentsFormController {
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentDOB;
    public JFXTextField txtStudentNic;
    public Label lblRegistrationDate;
    public Label lblCountOfCoursesEnrolled;
    public JFXButton updateBtnId;
    public JFXButton deleteBtnId;
    public JFXButton saveBtnId;
    public Label lblSetStudentId;
    public JFXRadioButton rdbFacedInterview;

    HashMap<TextField, Pattern> map = new HashMap<>();
    Pattern idPattern = Pattern.compile("^(ST-)[0-9]{3}$");
    Pattern namePattern = Pattern.compile("^[A-z0-9 ]{4,}$");
    Pattern dobPattern = Pattern.compile("^[0-9]{4}[/.][0-9]{2}[/.][0-9]{2}$");
    Pattern nicPattern = Pattern.compile("^(([0-9]{9}[V|v])|([0-9]{12}))$");

    ManageStudentsBO studentsBO = (ManageStudentsBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.ManageStudents);
    private String interviewFaced = "";

    public void initialize() {
        validateFields();
        generateStudentId();
    }

    private void validateFields() {
        map.put(txtStudentId, idPattern);
        map.put(txtStudentName, namePattern);
        map.put(txtStudentDOB, dobPattern);
        map.put(txtStudentNic, nicPattern);
    }

    public void validateEvent(KeyEvent event) {
        if ((txtStudentId.getText().length() == 6) && (studentsBO.ifStudentExists(txtStudentId.getText()))) {
            System.out.println("true");
            StudentDTO dto = studentsBO.searchStudent(txtStudentId.getText());
            txtStudentName.setText(dto.getName());
            txtStudentDOB.setText(dto.getDob());
            txtStudentNic.setText(dto.getNic());
            lblRegistrationDate.setText(dto.getRegisteredDate());
            lblCountOfCoursesEnrolled.setText(String.valueOf(studentsBO.getEnrolledProgramsCount(txtStudentId.getText())));
            rdbFacedInterview.isSelected();
        } else {
            return;
        }
        if (event.getCode().equals(KeyCode.BACK_SPACE)) {
            txtStudentName.clear();
            txtStudentNic.clear();
            txtStudentDOB.clear();
        }

        Object response = Validator.validate(map);
        if (response instanceof Boolean) {
            saveBtnId.setDisable(false);
            updateBtnId.setDisable(false);
            deleteBtnId.setDisable(false);
        }

        if (response instanceof TextField) {
            if (event.getCode().equals(KeyCode.TAB)) {
                TextField txt = (TextField) response;
                txt.requestFocus();
                saveBtnId.setDisable(true);
                updateBtnId.setDisable(true);
                deleteBtnId.setDisable(true);
            }
        }
    }

    public void btnSaveStudent(ActionEvent actionEvent) {
        StudentDTO dto = new StudentDTO(txtStudentId.getText(), txtStudentName.getText(), txtStudentDOB.getText(), txtStudentNic.getText(), setDate, interviewFaced);
        if (dto != null) {
            if (studentsBO.saveStudent(dto)) {
                CommonFunctions.setNotificationSuccess("Student Saved", "Saving Student is successful");
                clearFields();
                generateStudentId();
            } else {
                return;
            }
        } else {
            return;
        }
    }

    private void clearFields() {
        txtStudentId.clear();
        txtStudentName.clear();
        txtStudentDOB.clear();
        txtStudentNic.clear();
        rdbFacedInterview.setSelected(false);
    }

    public void btnUpdateStudent(ActionEvent actionEvent) {
        StudentDTO dto = new StudentDTO(txtStudentId.getText(), txtStudentName.getText(), txtStudentDOB.getText(), txtStudentNic.getText(), setDate, interviewFaced);

        if (dto != null) {
            if (studentsBO.ifStudentExists(txtStudentId.getText())) {
                if (studentsBO.updateStudent(dto)) {
                    CommonFunctions.setNotificationSuccess("Student Updated", "Updating Student is successful");
                    clearFields();
                    generateStudentId();
                } else {
                    return;
                }
            }
        } else {
            return;
        }
    }

    public void btnDeleteStudent(ActionEvent actionEvent) {
        StudentDTO dto = new StudentDTO(txtStudentId.getText(), txtStudentName.getText(), txtStudentDOB.getText(), txtStudentNic.getText(), setDate, interviewFaced);

        if (dto != null) {
            if (studentsBO.ifStudentExists(txtStudentId.getText())) {
                if (studentsBO.deleteStudent(dto)) {
                    CommonFunctions.setNotificationSuccess("Student Deleted", "Deleting Student is successful");
                    clearFields();
                    generateStudentId();
                } else {
                    return;
                }
            }
        } else {
            return;
        }
    }

    public void generateStudentId() {
        String id = studentsBO.generateStudentId();
        txtStudentId.setText(id);
        lblSetStudentId.setText(id);
    }


    public void btnFacedTheInterview(MouseEvent mouseEvent) {
        if (rdbFacedInterview.isSelected()) {
            saveBtnId.setDisable(false);
            updateBtnId.setDisable(false);
            deleteBtnId.setDisable(false);
            interviewFaced = "Faced";
        }
    }
}
