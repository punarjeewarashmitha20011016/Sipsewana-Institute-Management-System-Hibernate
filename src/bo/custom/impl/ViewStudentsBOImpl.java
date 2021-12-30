package bo.custom.impl;

import bo.custom.ViewStudentsBO;
import dao.DAOFactory;
import dao.custom.StudentDAO;
import dto.StudentDTO;
import entity.Student;

import java.util.ArrayList;

public class ViewStudentsBOImpl implements ViewStudentsBO {
    private StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.STUDENT);
    @Override
    public ArrayList<StudentDTO> getAll() {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO>dtos=new ArrayList<>();
        for (Student s:all
             ) {
            dtos.add(new StudentDTO(s.getId(),s.getName(),s.getDob(),s.getNic(),s.getInterviewFaced(),s.getRegisteredDate()));
        }return dtos;
    }
}
