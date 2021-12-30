package bo.custom.impl;

import bo.custom.ManageReceptionistsBO;
import dao.DAOFactory;
import dao.custom.ReceptionistsDAO;
import dto.ReceptionistDTO;
import entity.Receptionist;

public class ManageReceptionistsBOImpl implements ManageReceptionistsBO {
    public ReceptionistsDAO receptionistsDAO= (ReceptionistsDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.RECEPTIONISTS);
    @Override
    public boolean saveReceptionists(ReceptionistDTO dto) {
        return receptionistsDAO.save(new Receptionist(dto.getId(),dto.getName(),dto.getNic(),dto.getUserName(),dto.getPassword()));
    }

    @Override
    public boolean updateReceptionists(ReceptionistDTO dto) {
        return receptionistsDAO.update(new Receptionist(dto.getId(),dto.getName(),dto.getNic(),dto.getUserName(),dto.getPassword()));
    }

    @Override
    public boolean deleteReceptionists(ReceptionistDTO dto) {
        return receptionistsDAO.delete(new Receptionist(dto.getId(),dto.getName(),dto.getNic(),dto.getUserName(),dto.getPassword()));
    }

    @Override
    public ReceptionistDTO searchReceptionists(String id) {
        return null;
    }

    @Override
    public boolean ifReceptionistsExists(String id) {
        return receptionistsDAO.ifReceptionistsExists(id);
    }

    @Override
    public String generateReceptionistId() {
        return receptionistsDAO.generateReceptionistsId();
    }
}
