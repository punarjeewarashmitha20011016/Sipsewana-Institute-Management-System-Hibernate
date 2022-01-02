package controller;

import bo.BOFactory;
import bo.custom.ViewStudentDetailsFormBO;
import dto.CustomDTO;
import dto.ProgramsDTO;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.assets.tm.StudentDetailsTm;

import java.util.List;

import static controller.ViewStudentsFormController.sId;

public class ViewStudentDetailsFormController {
    public TableView<StudentDetailsTm> tblViewStudentsDetails;
    public TableColumn tblStudentId;
    public TableColumn tblStudentName;
    public TableColumn tblRegisteredDate;
    public TableColumn tblProgram;
    public TableColumn tblDuration;
    private ViewStudentDetailsFormBO studentDetailsFormBO = (ViewStudentDetailsFormBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.ViewStudentDetails);
    private ObservableList<StudentDetailsTm> studentDetailsTms = FXCollections.observableArrayList();

    public void initialize() {
        tblStudentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblRegisteredDate.setCellValueFactory(new PropertyValueFactory<>("registeredDate"));
        tblProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        tblDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        setDataToTable();
    }

    public void setDataToTable() {
        String programName = null;
        String studentId = null;
        List<CustomDTO> studentDetailsFromOrderDetails = studentDetailsFormBO.getStudentDetailsFromOrderDetails(sId);
        /*for (int i = 0; i < studentDetailsFromOrderDetails.size(); i++) {
            System.out.println(studentDetailsFromOrderDetails.get(i));
        }*/
        System.out.println(studentDetailsFromOrderDetails.toString());
        for (CustomDTO c : studentDetailsFromOrderDetails
        ) {
            programName = c.getProgram();
            studentId = c.getId();
            ProgramsDTO programsDTO = studentDetailsFormBO.searchProgramByName(programName);
            StudentDTO dto = studentDetailsFormBO.searchStudent(studentId);
            studentDetailsTms.add(new StudentDetailsTm(dto.getId(), dto.getName(), dto.getRegisteredDate(), c.getProgram(), programsDTO.getDuration()));
        }
        tblViewStudentsDetails.getItems().clear();
        tblViewStudentsDetails.setItems(studentDetailsTms);
    }
}
