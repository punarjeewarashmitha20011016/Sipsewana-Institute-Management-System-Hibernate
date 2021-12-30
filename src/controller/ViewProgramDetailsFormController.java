package controller;

import bo.BOFactory;
import bo.custom.ViewProgramsBO;
import com.jfoenix.controls.JFXButton;
import dto.ProgramsDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import view.assets.tm.ProgramsTm;

import java.util.HashMap;
import java.util.regex.Pattern;

public class ViewProgramDetailsFormController {
    public TableView<ProgramsTm> tblViewPrograms;
    public TableColumn tblId;
    public TableColumn tblName;
    public TableColumn tblDuration;
    public TableColumn tblFee;
    public TextField txtSearch;
    public JFXButton searchBtnId;
    private ViewProgramsBO programsBO = (ViewProgramsBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.ViewPrograms);
    private ObservableList<ProgramsTm> programsTms = FXCollections.observableArrayList();
    HashMap<TextField, Pattern> map = new HashMap<>();
    Pattern idPattern = Pattern.compile("^(CT-)[0-9]{3}$");
    Pattern namePattern = Pattern.compile("^[A-z0-9 ]{4,}$");
    Pattern durationPattern = Pattern.compile("^[0-9]+[ ](months|day|days|month|year|years)$");
    Pattern feePattern = Pattern.compile("^[0-9]+[.]?[0-9]+$");


    public void initialize() {
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        tblFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        setDataToProgramsTable();
        validateFields();
        searchBtnId.setDisable(true);
    }
    public void validateFields(){
        map.put(txtSearch, idPattern);
        map.put(txtSearch, namePattern);
        map.put(txtSearch, durationPattern);
        map.put(txtSearch, feePattern);
    }

    public void btnSearchPrograms(ActionEvent actionEvent) {
        searchProgramsFromTheTable();
    }

    public void searchEvent(KeyEvent event) {
        if (idPattern.matcher(txtSearch.getText()).matches()){
            txtSearch.setStyle("-fx-border-color: green");
            searchBtnId.setDisable(false);
        }else if (namePattern.matcher(txtSearch.getText()).matches()){
            txtSearch.setStyle("-fx-border-color: green");
            searchBtnId.setDisable(false);
        }else if (durationPattern.matcher(txtSearch.getText()).matches()){
            txtSearch.setStyle("-fx-border-color: green");
            searchBtnId.setDisable(false);
        }else if (feePattern.matcher(txtSearch.getText()).matches()){
            txtSearch.setStyle("-fx-border-color: green");
            searchBtnId.setDisable(false);
        }else {
            searchBtnId.setDisable(true);
            txtSearch.setStyle("-fx-border-color: red");
        }
    }

    public void setDataToProgramsTable() {
        ObservableList<ProgramsDTO> programs = programsBO.programs();
        for (ProgramsDTO dto : programs
        ) {
            programsTms.add(new ProgramsTm(dto.getId(), dto.getName(), dto.getDuration(), dto.getFee()));
        }
        tblViewPrograms.setItems(programsTms);
    }

    public void searchProgramsFromTheTable() {
        ObservableList<ProgramsTm> programsTms = tblViewPrograms.getItems();
        FilteredList<ProgramsTm> filteredData = new FilteredList<>(programsTms, p -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                String fee = String.valueOf(person.getFee());
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getId().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getDuration().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (fee.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<ProgramsTm> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblViewPrograms.comparatorProperty());
        tblViewPrograms.setItems(sortedData);
    }
}
