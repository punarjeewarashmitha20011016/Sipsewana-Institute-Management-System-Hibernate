package dao.custom.impl;

import dao.custom.StudentDAO;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student student) {
        if (student != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Student student) {
        if (student != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Student student) {
        if (student != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public Student search(String id) {
        if (id != null) {
            Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
            Transaction transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            transaction.commit();
            session.close();
            return student;
        }
        return null;
    }

    @Override
    public ArrayList<Student> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Student";
        Query query = session.createQuery(hql);
        List<Student> list = query.list();
        ArrayList<Student> students = new ArrayList<>();
        for (Student s : list
        ) {
            students.add(new Student(s.getId(), s.getName(), s.getDob(), s.getNic(), s.getInterviewFaced(), s.getRegisteredDate()));
        }
        transaction.commit();
        session.close();
        return students;
    }

    @Override
    public boolean ifStudentExists(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, id);
        transaction.commit();
        session.close();
        if (student != null) {
            System.out.println("Student Id : " + student.getId());
            return true;
        }
        return false;
    }

    @Override
    public String generateStudentId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT id FROM Student order by id desc limit 1";
        NativeQuery sqlQuery = session.createSQLQuery(hql);
        List<String> list = sqlQuery.list();
        transaction.commit();
        session.close();

        for (String id : list
        ) {
            int temp = Integer.parseInt(id.split("-")[1]);
            temp = temp + 1;
            if (temp <= 9) {
                return "ST-00" + temp;
            } else if (temp <= 99) {
                return "ST-0" + temp;
            } else {
                return "ST-" + temp;
            }
        }
        return "ST-001";

    }

}
