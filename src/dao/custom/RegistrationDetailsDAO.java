package dao.custom;

import dao.CrudDao;
import entity.RegistrationDetails;

public interface RegistrationDetailsDAO extends CrudDao<RegistrationDetails, String> {
    public boolean ifOrderDetailsExists(String id);
    public String generateOrderDetailsId();
}
