package dao.custom.impl;

import dao.custom.RegistrationFeeDAO;
import entity.RegistrationFee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.ArrayList;
import java.util.List;

public class RegistrationFeeDAOImpl implements RegistrationFeeDAO {
    @Override
    public boolean save(RegistrationFee registrationFee) {
        if (registrationFee != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.save(registrationFee);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(RegistrationFee registrationFee) {
        if (registrationFee != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.update(registrationFee);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(RegistrationFee registrationFee) {
        if (registrationFee != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.delete(registrationFee);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public RegistrationFee search(String s) {
        return null;
    }

    @Override
    public ArrayList<RegistrationFee> getAll() {
        return null;
    }

    @Override
    public boolean ifRegistrationFeeExists(String fee) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT fee FROM RegistrationFee WHERE fee=:fee";
        Query hqlQuery = session.createQuery(hql);
        hqlQuery.setParameter("fee", fee);
        List<Double> list = hqlQuery.list();
        transaction.commit();
        session.close();
        if (list != null) {
            return true;
        }
        return false;
    }

    @Override
    public String generateFeeId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT id FROM RegistrationFee order by id desc limit 1";
        NativeQuery sqlQuery = session.createSQLQuery(hql);
        List<String> list = sqlQuery.list();
        transaction.commit();
        session.close();

        for (String id : list
        ) {
            int temp = Integer.parseInt(id.split("-")[1]);
            temp = temp + 1;
            if (temp <= 9) {
                return "F-00" + temp;
            } else if (temp <= 99) {
                return "F-0" + temp;
            } else {
                return "F-" + temp;
            }
        }
        return "F-001";
    }

    @Override
    public double getAllFees() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT fee FROM RegistrationFee";
        Query query = session.createQuery(hql);
        List<Double> list = query.list();
        transaction.commit();
        session.close();
        return list.get(0);
    }
}
