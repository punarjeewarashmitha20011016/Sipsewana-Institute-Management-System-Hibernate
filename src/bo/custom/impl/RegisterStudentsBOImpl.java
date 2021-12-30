package bo.custom.impl;

import bo.custom.RegisterStudentsBO;
import dao.DAOFactory;
import dao.custom.*;
import dto.OrderDTO;
import dto.OrderDetailsDTO;
import dto.ProgramsDTO;
import dto.StudentDTO;
import entity.Orders;
import entity.OrderDetails;
import entity.Programs;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class RegisterStudentsBOImpl implements RegisterStudentsBO {
    private StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.STUDENT);
    private RegistrationFeeDAO registrationFeeDAO = (RegistrationFeeDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.RegistrationFee);
    private ProgramsDAO programsDAO= (ProgramsDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.PROGRAMS);
    private OrderDAO orderDAO= (OrderDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.ORDER);
    private OrderDetailsDAO orderDetailsDAO= (OrderDetailsDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.ORDERDETAILS);
    private ReceptionistsDAO receptionistsDAO= (ReceptionistsDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.RECEPTIONISTS);

    @Override
    public StudentDTO searchStudent(String id) {
        Student search = studentDAO.search(id);
        return new StudentDTO(search.getId(), search.getName(), search.getDob(), search.getNic(), search.getRegisteredDate(),search.getInterviewFaced());
    }

    @Override
    public boolean ifStudentExists(String id) {
        return studentDAO.ifStudentExists(id);
    }

    @Override
    public Double getRegistrationFee() {
        double allFees = registrationFeeDAO.getAllFees();
        return allFees;
    }

    @Override
    public ProgramsDTO searchPrograms(String id) {
        Programs search = programsDAO.search(id);
        return new ProgramsDTO(search.getId(),search.getName(),search.getDuration(),search.getFee());
    }

    @Override
    public ObservableList<ProgramsDTO> getAllPrograms() {
        ArrayList<Programs> all = programsDAO.getAll();
        ObservableList<ProgramsDTO>programsDTOS= FXCollections.observableArrayList();
        for (Programs p:all
             ) {
            programsDTOS.add(new ProgramsDTO(p.getId(),p.getName(),p.getDuration(),p.getFee()));
        }return programsDTOS;
    }

    @Override
    public boolean saveOrder(OrderDTO orderDTO) {
        if (orderDAO.ifOrderExists(orderDTO.getOrderId())){
            return false;
        }
        Student student = studentDAO.search(orderDTO.getStudentId());
        Orders orders =new Orders(orderDTO.getOrderId(),student,orderDTO.getOrderDate(),orderDTO.getOrderTime(),orderDTO.getOrderPrice());
        if (!orderDAO.save(orders)){
            return false;
        }
        for (OrderDetailsDTO dto:orderDTO.getOrderDetails()
             ) {
            Student studentSearch = studentDAO.search(dto.getStudentId());
            Programs programSearch = programsDAO.search(dto.getCourseId());
            OrderDetails orderDetails=new OrderDetails(dto.getOrderDetailsId()
                    ,new Orders(orderDTO.getOrderId(),
                    studentSearch,orderDTO.getOrderDate(),
                    orderDTO.getOrderTime(),orderDTO.getOrderPrice()),
                    studentSearch,studentSearch,programSearch,
                    programSearch,programSearch);

            if (!orderDetailsDAO.save(orderDetails)){
                return false;
            }
        }return true;
    }

    @Override
    public String getOrderId() {
        return orderDAO.generateOrderId();
    }

    @Override
    public String getOrderDetailsId() {
         return orderDetailsDAO.generateOrderDetailsId();
    }

    @Override
    public String getReceptionistName(String userName) {
        return receptionistsDAO.searchReceptionistByUsername(userName);
    }
}
