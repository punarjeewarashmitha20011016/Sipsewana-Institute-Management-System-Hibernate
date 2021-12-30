package bo.custom;

import bo.SuperBO;
import dto.ReceptionistDTO;

public interface ManageReceptionistsBO extends SuperBO {
    public boolean saveReceptionists(ReceptionistDTO dto);
    public boolean updateReceptionists(ReceptionistDTO dto);
    public boolean deleteReceptionists(ReceptionistDTO dto);
    public ReceptionistDTO searchReceptionists(String id);
    public boolean ifReceptionistsExists(String id);
    public String generateReceptionistId();

}
