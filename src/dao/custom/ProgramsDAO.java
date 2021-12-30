package dao.custom;

import dao.CrudDao;
import entity.Programs;

public interface ProgramsDAO extends CrudDao<Programs,String> {
    public boolean ifProgramsExists(String id);
    public String generateProgramId();
    public Programs searchProgramByName(String name);
}
