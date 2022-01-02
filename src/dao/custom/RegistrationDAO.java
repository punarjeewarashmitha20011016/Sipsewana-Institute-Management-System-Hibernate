package dao.custom;

import dao.CrudDao;
import entity.Registration;

public interface RegistrationDAO extends CrudDao<Registration,String> {
    public boolean ifOrderExists(String id);
    public String generateOrderId();
}
