package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;

import java.math.BigInteger;

public interface ManageStudentsBO extends SuperBO {
    public boolean saveStudent(StudentDTO dto);

    public boolean updateStudent(StudentDTO dto);

    public boolean deleteStudent(StudentDTO dto);

    public StudentDTO searchStudent(String id);

    public boolean ifStudentExists(String id);

    public String generateStudentId();

    public BigInteger getEnrolledProgramsCount(String id);
}
