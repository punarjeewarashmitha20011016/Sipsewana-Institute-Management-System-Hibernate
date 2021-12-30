package dao.custom;

import dao.CrudDao;
import entity.Student;

public interface StudentDAO extends CrudDao<Student,String> {
    public boolean ifStudentExists(String id);
    public String generateStudentId();
}
