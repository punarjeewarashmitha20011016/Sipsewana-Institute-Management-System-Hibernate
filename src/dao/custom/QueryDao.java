package dao.custom;

import dao.SuperDAO;
import dto.CustomDTO;

import java.math.BigInteger;
import java.util.List;

public interface QueryDao extends SuperDAO {
    public List<CustomDTO>getStudentDetails(String id);
    public BigInteger getCountOfEnrolledPrograms(String id);
}
