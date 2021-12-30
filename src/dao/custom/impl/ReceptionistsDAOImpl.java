package dao.custom.impl;

import dao.custom.ReceptionistsDAO;
import entity.Receptionist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ReceptionistsDAOImpl implements ReceptionistsDAO {
    @Override
    public boolean save(Receptionist receptionist) {
        if (receptionist != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.save(receptionist);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Receptionist receptionist) {
        if (receptionist != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.update(receptionist);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Receptionist receptionist) {
        if (receptionist != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.delete(receptionist);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public Receptionist search(String id) {
        if (id != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            Receptionist receptionist = session.get(Receptionist.class, id);
            transaction.commit();
            session.close();
            return receptionist;
        }
        return null;
    }

    @Override
    public ArrayList<Receptionist> getAll() {
        /*Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        session.beginTransaction();
        String hql="FROM Receptionist";
        Query query = session.createQuery(hql);
        session.close();*/
        return null;
    }

    @Override
    public boolean ifReceptionistsExists(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Receptionist receptionist = session.get(Receptionist.class, id);
        transaction.commit();
        session.close();
        if (receptionist!= null) {
            return true;
        }
        return false;
    }

    @Override
    public String generateReceptionistsId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT id FROM Receptionist Order by id desc limit 1";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        List<String> list = sqlQuery.list();
        transaction.commit();
        session.close();

        for (String ids : list
        ) {
            int temp = Integer.parseInt(ids.split("-")[1]);
            temp = temp + 1;
            if (temp <= 9) {
                return "R-00" + temp;
            } else if (temp <= 99) {
                return "R-0" + temp;
            } else {
                return "R-" + temp;
            }
        }
        return "R-001";
    }

    @Override
    public boolean receptionistsLogin(String username, String password) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT userName,password FROM Receptionist WHERE userName=:username AND password=:password";
        Query hqlQuery = session.createQuery(hql);
        hqlQuery.setParameter("username", username);
        hqlQuery.setParameter("password", password);
        List<Receptionist> list = hqlQuery.list();
        transaction.commit();
        session.close();
        if (list != null) {
            return true;
        }
        return false;
    }

    @Override
    public String searchReceptionistByUsername(String userName) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String hql="SELECT name FROM Receptionist WHERE userName=:userName";
        Query query = session.createQuery(hql);
        query.setParameter("userName",userName);
        List<String>list = query.list();
        transaction.commit();
        session.close();
        if (list!=null){
            return list.get(0);
        }return null;
    }
}
