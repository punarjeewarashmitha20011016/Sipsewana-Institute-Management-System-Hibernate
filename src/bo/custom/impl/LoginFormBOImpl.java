package bo.custom.impl;

import bo.custom.LoginFormBO;
import dao.DAOFactory;
import dao.custom.ReceptionistsDAO;
import dto.ReceptionistDTO;
import entity.Receptionist;

public class LoginFormBOImpl implements LoginFormBO {
    ReceptionistsDAO receptionistsDAO= (ReceptionistsDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.RECEPTIONISTS);
    @Override
    public boolean receptionistsLogin(String userName,String password) {
        return receptionistsDAO.receptionistsLogin(userName,password);
    }
}
