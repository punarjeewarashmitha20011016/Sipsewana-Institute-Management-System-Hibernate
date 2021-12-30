package dao.custom.impl;

import dao.custom.OrderDetailsDAO;
import entity.OrderDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import util.FactoryConfiguration;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean save(OrderDetails order) {
        if (order != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(OrderDetails order) {
        if (order != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(OrderDetails order) {
        if (order != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public OrderDetails search(String id) {
        if (id != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            OrderDetails orderDetails = session.get(OrderDetails.class, id);
            transaction.commit();
            session.close();
            return orderDetails;
        }
        return null;
    }

    @Override
    public ArrayList<OrderDetails> getAll() {
        return null;
    }

    @Override
    public boolean ifOrderDetailsExists(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        OrderDetails orderDetails = session.get(OrderDetails.class, id);
        if (orderDetails != null) {
            transaction.commit();
            return true;
        }
        session.close();
        return false;
    }

    @Override
    public String generateOrderDetailsId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT orderDetailsId FROM OrderDetails Order by orderDetailsId desc limit 1";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        List<String> list = sqlQuery.list();
        transaction.commit();
        session.close();

        for (String ids : list
        ) {
            int temp = Integer.parseInt(ids.split("-")[1]);
            temp = temp + 1;
            if (temp <= 9) {
                return "OD-00" + temp;
            } else if (temp <= 99) {
                return "OD-0" + temp;
            } else {
                return "OD-" + temp;
            }
        }
        return "OD-001";
    }
}
