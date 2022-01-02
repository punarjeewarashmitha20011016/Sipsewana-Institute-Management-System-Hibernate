package dao.custom.impl;

import dao.custom.RegistrationDetailsDAO;
import entity.RegistrationDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import util.FactoryConfiguration;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDetailsDAOImpl implements RegistrationDetailsDAO {
    @Override
    public boolean save(RegistrationDetails order) {
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
    public boolean update(RegistrationDetails order) {
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
    public boolean delete(RegistrationDetails order) {
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
    public RegistrationDetails search(String id) {
        if (id != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            RegistrationDetails registrationDetails = session.get(RegistrationDetails.class, id);
            transaction.commit();
            session.close();
            return registrationDetails;
        }
        return null;
    }

    @Override
    public ArrayList<RegistrationDetails> getAll() {
        return null;
    }

    @Override
    public boolean ifOrderDetailsExists(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        RegistrationDetails registrationDetails = session.get(RegistrationDetails.class, id);
        if (registrationDetails != null) {
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
        String sql = "SELECT registrationDetailsId FROM registrationDetails Order by registrationDetailsId desc limit 1";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        List<String> list = sqlQuery.list();
        transaction.commit();
        session.close();

        for (String ids : list
        ) {
            int temp = Integer.parseInt(ids.split("-")[1]);
            temp = temp + 1;
            if (temp <= 9) {
                return "RED-00" + temp;
            } else if (temp <= 99) {
                return "RED-0" + temp;
            } else {
                return "RED-" + temp;
            }
        }
        return "RED-001";
    }
}
