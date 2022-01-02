package dao.custom.impl;

import dao.custom.QueryDao;
import dto.CustomDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDao {
    @Override
    public List<CustomDTO> getStudentDetails(String id) {
        System.out.println(id);
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        /*String sql="select o.orderDetailsId,o.ordersId_orderId,o.studentId_id,o.interview_interviewFaced,o.courseId_id,o.courseName_name,o.courseFee_fee from orderdetails o inner join student s on studentId_id='"+id+"'";*/
        String sql="select r.registrationDetailsId,r.registrationId_registrationId,r.studentId_id,r.interview_interviewFaced,r.courseId_id,r.courseName_name,r.courseFee_fee from registrationdetails r inner join student s on r.studentId_id=s.id where s.id='"+id+"'";
        Query query = session.createNativeQuery(sql);
        List<Object[]> list = query.list();
        transaction.commit();
        session.close();
        List<CustomDTO>dtos=new ArrayList<>();
        for (Object[] obj:list
             ) {
            /*for (int i = 0; i < obj.length; i++) {
                dtos.add(new CustomDTO(obj[2].toString(),obj[5].toString()));
            }*/
            dtos.add(new CustomDTO(obj[2].toString(),obj[5].toString()));
            System.out.println(obj[2]+" - "+obj[5]);
        }

        /*Iterator it = list.iterator();
        while (it.hasNext()){
            Object[] line = (Object[]) it.next();
            for (int i = 0; i < line.length; i++) {
                System.out.println(line[i]);
            }
        }*/
        System.out.println(dtos.toString());
        return dtos;
    }

    @Override
    public BigInteger getCountOfEnrolledPrograms(String id) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        String sql="select count(courseName_name) from registrationDetails inner join Student s on s.id='"+id+"'";
        NativeQuery nativeQuery = session.createNativeQuery(sql);
        List<BigInteger>list = nativeQuery.list();
        transaction.commit();
        session.close();
        return list.get(0);
    }
}
