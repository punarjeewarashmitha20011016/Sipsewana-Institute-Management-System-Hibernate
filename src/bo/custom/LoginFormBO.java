package bo.custom;

import bo.SuperBO;
import dto.ReceptionistDTO;

public interface LoginFormBO extends SuperBO{
    public boolean receptionistsLogin(String userName,String password);
}
