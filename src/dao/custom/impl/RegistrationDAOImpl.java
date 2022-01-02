package dao.custom.impl;

import dao.custom.RegistrationDAO;
import entity.Registration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import util.FactoryConfiguration;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean save(Registration registration) {
        if (registration != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.save(registration);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Registration registration) {
        if (registration != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.update(registration);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Registration registration) {
        if (registration != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.delete(registration);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public Registration search(String id) {
        if (id != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            Registration registration = session.get(Registration.class, id);
            transaction.commit();
            session.close();
            return registration;
        }
        return null;
    }

    @Override
    public ArrayList<Registration> getAll() {
        return null;
    }

    @Override
    public boolean ifOrderExists(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Registration registration = session.get(Registration.class, id);
        if (registration != null) {
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
                return "RE-00" + temp;
            } else if (temp <= 99) {
                return "RE-0" + temp;
            } else {
                return "RE-" + temp;
            }
        }
        return "RE-001";
    }
}
