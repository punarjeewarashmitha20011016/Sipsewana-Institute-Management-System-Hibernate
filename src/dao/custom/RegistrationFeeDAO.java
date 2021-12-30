package dao.custom;

import dao.CrudDao;
import entity.RegistrationFee;

public interface RegistrationFeeDAO extends CrudDao<RegistrationFee,String> {
    public boolean ifRegistrationFeeExists(String fee);
    public String generateFeeId();
    public double getAllFees();
}
