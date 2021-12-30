package dao.custom;

import dao.CrudDao;
import dao.SuperDAO;
import entity.Receptionist;

public interface ReceptionistsDAO extends CrudDao<Receptionist,String> {
    public boolean ifReceptionistsExists(String id);
    public String generateReceptionistsId();
    public boolean receptionistsLogin(String username,String password);
    public String searchReceptionistByUsername(String userName);
}
