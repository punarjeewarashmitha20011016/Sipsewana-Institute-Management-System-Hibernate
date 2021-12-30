package dao;

import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDao(DAOTypes types) {
        switch (types) {
            case RECEPTIONISTS:
                return new ReceptionistsDAOImpl();
            case PROGRAMS:return new ProgramsDAOImpl();
            case STUDENT:return new StudentDAOImpl();
            case RegistrationFee:return new RegistrationFeeDAOImpl();
            case ORDER:return new OrderDAOImpl();
            case ORDERDETAILS:return new OrderDetailsDAOImpl();
            case QueryDAO:return new QueryDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        RECEPTIONISTS,PROGRAMS,STUDENT,RegistrationFee,ORDER,ORDERDETAILS,QueryDAO;
    }
}