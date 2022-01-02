package controller;

import bo.BOFactory;
import bo.custom.ViewStudentsBO;
import com.jfoenix.controls.JFXButton;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import view.assets.tm.StudentsTm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class ViewStudentsFormController {
    public TableView<StudentsTm> tblViewStudents;
    public TableColumn tblStudentId;
    public TableColumn tblStudentName;
    public TableColumn tblStudentDob;
    public TableColumn tblStudentNic;
    public TableColumn tblStudentRegisteredDate;
    public TableColumn tblInterviewFaced;
    public JFXButton searchBtnId;
    public TextField txtSearch;
    private ViewStudentsBO studentsBO= (ViewStudentsBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.ViewStudents);
    private ObservableList<StudentsTm>studentsTms= FXCollections.observableArrayList();
    public static String sId;
    HashMap<TextField, Pattern> map = new HashMap<>();
    Pattern idPattern = Pattern.compile("^(ST-)[0-9]{3}$");

    public void initialize(){
        validateSearch();
        searchBtnId.setDisable(true);
        tblStudentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudentDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudentNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblInterviewFaced.setCellValueFactory(new PropertyValueFactory<>("interviewFaced"));
        tblStudentRegisteredDate.setCellValueFactory(new PropertyValueFactory<>("registeredDate"));

        setDataToTable();
    }
    public void validateSearch(){
        map.put(txtSearch,idPattern);
    }

    public void btnSearchDetails(ActionEvent actionEvent) {
        sId=txtSearch.getText();
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("../view/ViewStudentDetailsForm.fxml"));
            Scene scene = new Scene(parent);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Student Details");
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchEvent(KeyEvent event) {
        if (idPattern.matcher(txtSearch.getText()).matches()){
            txtSearch.setStyle("-fx-border-color: green");
            searchBtnId.setDisable(false);
        }else {
            txtSearch.setStyle("-fx-border-color: red");
            searchBtnId.setDisable(true);
        }
    }
    public void setDataToTable(){
        ArrayList<StudentDTO> all = studentsBO.getAll();
        for (StudentDTO s:all
             ) {
            studentsTms.add(new StudentsTm(s.getId(),s.getName(),s.getDob(),s.getNic(),s.getInterviewFaced(),s.getRegisteredDate()));
        }
        tblViewStudents.getItems().clear();
        tblViewStudents.setItems(studentsTms);
    }
}
