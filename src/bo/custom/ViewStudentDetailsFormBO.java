package bo.custom;

import bo.SuperBO;
import dto.CustomDTO;
import dto.ProgramsDTO;
import dto.StudentDTO;

import java.util.List;

public interface ViewStudentDetailsFormBO extends SuperBO {
    public List<CustomDTO> getStudentDetailsFromOrderDetails(String id);
    public StudentDTO searchStudent(String id);
    public ProgramsDTO searchProgramByName(String name);
}
