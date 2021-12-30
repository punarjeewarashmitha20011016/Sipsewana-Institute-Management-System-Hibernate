package bo.custom.impl;

import bo.custom.ViewStudentDetailsFormBO;
import dao.DAOFactory;
import dao.custom.ProgramsDAO;
import dao.custom.QueryDao;
import dao.custom.StudentDAO;
import dto.CustomDTO;
import dto.ProgramsDTO;
import dto.StudentDTO;
import entity.Programs;
import entity.Student;

import java.util.List;

public class ViewStudentsDetailsFormBOImpl implements ViewStudentDetailsFormBO {
    private QueryDao queryDao= (QueryDao) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.QueryDAO);
    private StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.STUDENT);
    private ProgramsDAO programsDAO= (ProgramsDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.PROGRAMS);

    @Override
    public List<CustomDTO> getStudentDetailsFromOrderDetails(String id) {
       return queryDao.getStudentDetails(id);
    }

    @Override
    public StudentDTO searchStudent(String id) {
        Student search = studentDAO.search(id);
        return new StudentDTO(search.getId(),search.getName(),search.getDob(),search.getNic(),search.getInterviewFaced(),search.getRegisteredDate());
    }

    @Override
    public ProgramsDTO searchProgramByName(String name) {
        Programs search = programsDAO.searchProgramByName(name);
        return new ProgramsDTO(search.getId(),search.getName(),search.getDuration(),search.getFee());
    }
}
