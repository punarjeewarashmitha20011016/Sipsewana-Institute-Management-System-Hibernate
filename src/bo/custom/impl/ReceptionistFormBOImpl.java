package bo.custom.impl;

import bo.custom.ReceptionistFormBO;
import dao.DAOFactory;
import dao.custom.ReceptionistsDAO;

public class ReceptionistFormBOImpl implements ReceptionistFormBO {
    private ReceptionistsDAO receptionistsDAO= (ReceptionistsDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.RECEPTIONISTS);
    @Override
    public String getReceptionistName(String userName) {
        return receptionistsDAO.searchReceptionistByUsername(userName);
    }
}
