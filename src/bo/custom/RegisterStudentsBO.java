package bo.custom;

import bo.SuperBO;
import dto.OrderDTO;
import dto.OrderDetailsDTO;
import dto.ProgramsDTO;
import dto.StudentDTO;
import javafx.collections.ObservableList;

public interface RegisterStudentsBO extends SuperBO {
    public StudentDTO searchStudent(String id);

    public boolean ifStudentExists(String id);

    public Double getRegistrationFee();

    public ProgramsDTO searchPrograms(String id);

    public ObservableList<ProgramsDTO> getAllPrograms();

    /*Order*/
    public boolean saveOrder(OrderDTO order);
    public String getOrderId();
    public String getOrderDetailsId();

    public String getReceptionistName(String userName);
}
