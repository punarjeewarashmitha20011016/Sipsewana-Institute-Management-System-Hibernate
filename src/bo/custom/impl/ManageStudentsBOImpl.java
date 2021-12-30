package bo.custom.impl;

import bo.custom.ManageStudentsBO;
import dao.DAOFactory;
import dao.custom.QueryDao;
import dao.custom.StudentDAO;
import dto.StudentDTO;
import entity.Student;

import java.math.BigInteger;

public class ManageStudentsBOImpl implements ManageStudentsBO {

    StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.STUDENT);
    QueryDao queryDao= (QueryDao) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.QueryDAO);
    @Override
    public boolean saveStudent(StudentDTO dto) {
        return studentDAO.save(new Student(dto.getId(),dto.getName(),dto.getDob(),dto.getNic(),dto.getRegisteredDate(),dto.getInterviewFaced()));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) {
        return studentDAO.update(new Student(dto.getId(),dto.getName(),dto.getDob(),dto.getNic(),dto.getRegisteredDate(),dto.getInterviewFaced()));
    }

    @Override
    public boolean deleteStudent(StudentDTO dto) {
        return studentDAO.delete(new Student(dto.getId(),dto.getName(),dto.getDob(),dto.getNic(),dto.getRegisteredDate(),dto.getInterviewFaced()));
    }

    @Override
    public StudentDTO searchStudent(String id) {
        Student search = studentDAO.search(id);
        return new StudentDTO(search.getId(),search.getName(),search.getDob(),search.getNic(),search.getRegisteredDate(),search.getInterviewFaced());
    }

    @Override
    public boolean ifStudentExists(String id) {
        return studentDAO.ifStudentExists(id);
    }

    @Override
    public String generateStudentId() {
        return studentDAO.generateStudentId();
    }

    @Override
    public BigInteger getEnrolledProgramsCount(String id) {
        return queryDao.getCountOfEnrolledPrograms(id);
    }
}
