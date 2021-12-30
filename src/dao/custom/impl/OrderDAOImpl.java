package dao.custom.impl;

import dao.custom.OrderDAO;
import entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import util.FactoryConfiguration;

import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean save(Orders orders) {
        if (orders != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.save(orders);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Orders orders) {
        if (orders != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.update(orders);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Orders orders) {
        if (orders != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.delete(orders);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public Orders search(String id) {
        if (id != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            Orders orders = session.get(Orders.class, id);
            transaction.commit();
            session.close();
            return orders;
        }
        return null;
    }

    @Override
    public ArrayList<Orders> getAll() {
        return null;
    }

    @Override
    public boolean ifOrderExists(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Orders orders = session.get(Orders.class, id);
        if (orders != null) {
            transaction.commit();
            return true;
        }
        session.close();
        return false;
    }

    @Override
    public String generateOrderId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT orderId FROM Orders Order by orderId desc limit 1";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        List<String> list = sqlQuery.list();
        transaction.commit();
        session.close();

        for (String ids : list
        ) {
            int temp = Integer.parseInt(ids.split("-")[1]);
            temp = temp + 1;
            if (temp <= 9) {
                return "O-00" + temp;
            } else if (temp <= 99) {
                return "O-0" + temp;
            } else {
                return "O-" + temp;
            }
        }
        return "O-001";
    }
}
