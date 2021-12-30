package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;

import java.util.ArrayList;

public interface ViewStudentsBO extends SuperBO {
    public ArrayList<StudentDTO>getAll();
}
