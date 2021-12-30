package controller;

import bo.BOFactory;
import bo.custom.RegisterStudentsBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dto.OrderDTO;
import dto.OrderDetailsDTO;
import dto.ProgramsDTO;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.CommonFunctions;
import view.assets.tm.AddToCartTm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import static controller.LoginFormController.userNameArray;
import static util.CommonFunctions.setDate;
import static util.CommonFunctions.setTime;

public class RegisterStudentsFormController {
    public AnchorPane registerStudentsPane;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentDOB;
    public JFXButton clearIdBtn;
    public JFXButton addToCartBtn;
    public JFXTextField txtCourseName;
    public JFXComboBox cmbCourseId;
    public TableView<AddToCartTm> tblAddToCart;
    public TableColumn tblStudentId;
    public TableColumn tblStudentName;
    public TableColumn tblInterview;
    public TableColumn tblCourseId;
    public TableColumn tblCourseName;
    public TableColumn tblCourseFee;
    public JFXButton DiscardBtn;
    public JFXButton PlaceOrderBtn;
    public JFXTextField txtCourseFee;
    public Label lblTotalAmount;
    public JFXRadioButton rdbRegisteredToday;
    public Label lblSetOrderId;
    Pattern idPattern = Pattern.compile("^(ST-)[0-9]{3}$");
    private RegisterStudentsBO registerStudentsBO = (RegisterStudentsBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.RegisterStudents);
    private double totalAmount = 0.0;
    int selectedIndex = -1;

    public void initialize() {
        setOrderId();
        setDataToProgramsCombo();
        setAddToCartDataToTable();

        tblAddToCart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedIndex = (int) newValue;
            if (selectedIndex != -1) {
                clearIdBtn.setDisable(false);
            }
        });

    }

    public void validateStudentId(KeyEvent event) {
        if (idPattern.matcher(txtStudentId.getText()).matches() && registerStudentsBO.ifStudentExists(txtStudentId.getText())) {
            StudentDTO dto = registerStudentsBO.searchStudent(txtStudentId.getText());
            txtStudentName.setText(dto.getName());
            txtStudentDOB.setText(dto.getDob());
            cmbCourseId.setDisable(false);

            txtStudentId.setStyle("-fx-border-color: green");
            rdbRegisteredToday.setDisable(false);
        } else {
            txtStudentId.setStyle("-fx-border-color: red");
            rdbRegisteredToday.setDisable(true);
        }
    }

    public void setDataToProgramsCombo() {
        ObservableList<ProgramsDTO> allPrograms = registerStudentsBO.getAllPrograms();
        ObservableList<String> ids = FXCollections.observableArrayList();
        for (ProgramsDTO p : allPrograms
        ) {
            ids.add(p.getId());
        }
        cmbCourseId.setItems(ids);
    }

    public void cmbSelectCourseID(ActionEvent actionEvent) {
        String courseId = cmbCourseId.getValue().toString();
        ProgramsDTO programsDTO = registerStudentsBO.searchPrograms(courseId);
        txtCourseName.setText(programsDTO.getName());
        txtCourseFee.setText(String.valueOf(programsDTO.getFee()));
        if (!cmbCourseId.getSelectionModel().isSelected(-1)) {
            addToCartBtn.setDisable(false);
        }
    }

    public void selectRegisteredTodayRdb(ActionEvent actionEvent) {
        double fee = 0.0;
        if (rdbRegisteredToday.isSelected()) {
            fee = registerStudentsBO.getRegistrationFee();
            totalAmount += fee;
            lblTotalAmount.setText(String.valueOf(totalAmount));
        } else {
            rdbRegisteredToday.setSelected(false);
            lblTotalAmount.setText("");
        }

    }

    public void btnAddToCart(ActionEvent actionEvent) {
        String studentId = txtStudentId.getText();
        String studentName = txtStudentName.getText();
        StudentDTO dto = registerStudentsBO.searchStudent(studentId);
        String interviewFaced = dto.getInterviewFaced();
        String courseId = cmbCourseId.getValue().toString();
        String courseName = txtCourseName.getText();
        double courseFee = Double.parseDouble(txtCourseFee.getText());

        AddToCartTm addToCartTm = new AddToCartTm(studentId, studentName, interviewFaced, courseId, courseName, courseFee);
        tblAddToCart.getItems().add(addToCartTm);
        System.out.println(addToCartTm);

        ObservableList<AddToCartTm> items = tblAddToCart.getItems();
        calculateTotalAmount(items);
        PlaceOrderBtn.setDisable(false);
        DiscardBtn.setDisable(false);
        clearIdBtn.setDisable(false);
    }

    public void calculateTotalAmount(ObservableList<AddToCartTm> items) {
        String fee = lblTotalAmount.getText();
        double registrationFee = 0.0;
        if (fee.isEmpty()) {
            registrationFee = 0.0;
        } else {
            registrationFee = Double.parseDouble(fee);
        }
        double total = registrationFee + 0.0;
        for (AddToCartTm tm : items
        ) {
            total += tm.getCourseFee();
        }
        lblTotalAmount.setText(String.valueOf(total));
        PlaceOrderBtn.setDisable(true);
        DiscardBtn.setDisable(true);
    }

    public void setAddToCartDataToTable() {
        tblStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        tblInterview.setCellValueFactory(new PropertyValueFactory<>("interviewFaced"));
        tblCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        tblCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tblCourseFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));
        tblAddToCart.refresh();
    }

    public void btnClear(ActionEvent actionEvent) {
        ObservableList<AddToCartTm> items = tblAddToCart.getItems();
        for (int i = 0; i < items.size(); i++) {
            if (i == selectedIndex) {
                items.remove(i);
            }
        }
    }

    public void btnPlaceOrder(ActionEvent actionEvent) {
        ObservableList<AddToCartTm> items = tblAddToCart.getItems();
        List<OrderDetailsDTO> list = new ArrayList<>();

        for (AddToCartTm tm : items
        ) {
            String orderDetailsId = loadOrderDetailsId();
            list.add(new OrderDetailsDTO(orderDetailsId, lblSetOrderId.getText(), tm.getStudentId(), tm.getInterviewFaced(), tm.getCourseId(), tm.getCourseName(), tm.getCourseFee()));
        }
        OrderDTO orderDTO = new OrderDTO(lblSetOrderId.getText(), txtStudentId.getText(), setDate, setTime, lblTotalAmount.getText(), list);
        if (registerStudentsBO.saveOrder(orderDTO)) {
            CommonFunctions.setNotificationSuccess("Order Placed", "Placing Order is Successful");
            try {
                generateInvoice(txtStudentName.getText(), lblSetOrderId.getText(), Double.parseDouble(lblTotalAmount.getText()));
            } catch (JRException e) {
                e.printStackTrace();
            }
            setOrderId();
            lblTotalAmount.setText("");
            clearFields();
            addToCartBtn.setDisable(true);
            clearIdBtn.setDisable(true);
            PlaceOrderBtn.setDisable(true);
            DiscardBtn.setDisable(true);
            rdbRegisteredToday.setSelected(false);
            tblAddToCart.getItems().clear();
        } else {
            CommonFunctions.setNotificationWarning("Try Again", "Placing Order is unsuccessful");
            return;
        }
    }

    public void clearFields() {
        txtStudentId.clear();
        txtStudentName.clear();
        txtStudentDOB.clear();
        cmbCourseId.getSelectionModel().clearSelection();
        txtCourseName.clear();
        txtCourseFee.clear();
    }

    public void btnDiscard(ActionEvent actionEvent) {
    }

    public void setOrderId() {
        lblSetOrderId.setText(registerStudentsBO.getOrderId());
    }

    public String loadOrderDetailsId() {
        return registerStudentsBO.getOrderDetailsId();
    }

    public String getReceptionistName(){
        String receptionistUserName = userNameArray[0];
        String receptionistName = registerStudentsBO.getReceptionistName(receptionistUserName);
        return receptionistName;
    }

    public void generateInvoice(String sName, String orderId, double total) throws JRException {
        ObservableList<AddToCartTm> items = tblAddToCart.getItems();
        HashMap map = new HashMap();
        map.put("ordersId", orderId);
        map.put("studentName", sName);
        map.put("receptionistName", getReceptionistName());
        map.put("subTotal", total);
        map.put("discount", total);
        map.put("total", total);
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/SalesInvoice.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(design);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JRBeanArrayDataSource(items.toArray()));
        JasperViewer.viewReport(jasperPrint, false);
    }
}
