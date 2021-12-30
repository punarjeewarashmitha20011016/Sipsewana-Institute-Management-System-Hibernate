package dao.custom;

import dao.CrudDao;
import entity.OrderDetails;

public interface OrderDetailsDAO extends CrudDao<OrderDetails, String> {
    public boolean ifOrderDetailsExists(String id);
    public String generateOrderDetailsId();
}
