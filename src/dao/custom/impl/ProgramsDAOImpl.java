package dao.custom.impl;

import dao.custom.ProgramsDAO;
import entity.Programs;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ProgramsDAOImpl implements ProgramsDAO {
    @Override
    public boolean save(Programs programs) {
        try {
            if (programs != null) {
                Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
                Transaction transaction = session.beginTransaction();
                session.save(programs);
                transaction.commit();
                session.close();
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean update(Programs programs) {
        if (programs != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.update(programs);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Programs programs) {
        if (programs != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.delete(programs);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public Programs search(String id) {
        if (id != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            Programs programs = session.get(Programs.class, id);
            System.out.println(programs);
            transaction.commit();
            session.close();
            return programs;
        }
        return null;
    }

    @Override
    public ArrayList<Programs> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Programs ";
        Query query = session.createQuery(hql);
        List<Programs> list = query.list();
        transaction.commit();
        session.close();
        ArrayList<Programs> course = new ArrayList<>();
        for (Programs p : list
        ) {
            course.add(new Programs(p.getId(), p.getName(), p.getDuration(), p.getFee()));
        }
        return course;
    }

    @Override
    public boolean ifProgramsExists(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Programs programs = session.get(Programs.class, id);
        if (programs != null) {
            transaction.commit();
            return true;
        }
        session.close();
        return false;
    }

    @Override
    public String generateProgramId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT id FROM Programs Order by id desc limit 1";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        List<String> list = sqlQuery.list();
        transaction.commit();
        session.close();

        for (String ids : list
        ) {
            int temp = Integer.parseInt(ids.split("-")[1]);
            temp = temp + 1;
            if (temp <= 9) {
                return "CT-00" + temp;
            } else if (temp <= 99) {
                return "CT-0" + temp;
            } else {
                return "CT-" + temp;
            }
        }
        return "CT-001";
    }

    @Override
    public Programs searchProgramByName(String name) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Programs where name=:name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        List<Programs> list = query.list();
        transaction.commit();
        session.close();
        for (Programs p : list
        ) {
            return new Programs(p.getId(), p.getName(), p.getDuration(), p.getFee());
        }
        return null;
    }
}
