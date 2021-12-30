package dao.custom;

import dao.CrudDao;
import entity.Orders;

public interface OrderDAO extends CrudDao<Orders,String> {
    public boolean ifOrderExists(String id);
    public String generateOrderId();
}
