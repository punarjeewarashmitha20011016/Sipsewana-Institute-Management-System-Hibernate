package bo.custom;

import bo.SuperBO;
import dto.ProgramsDTO;
import dto.RegistrationFeeDTO;

public interface ManageProgramsBO extends SuperBO {
    public boolean savePrograms(ProgramsDTO dto);

    public boolean updatePrograms(ProgramsDTO dto);

    public boolean deletePrograms(ProgramsDTO dto);

    public ProgramsDTO searchPrograms(String id);

    public boolean ifProgramsExists(String id);

    public String generateProgramsId();

    public boolean saveRegistrationFee(RegistrationFeeDTO feeDTO);

    public boolean updateRegistrationFee(RegistrationFeeDTO feeDTO);

    public boolean deleteRegistrationFee(RegistrationFeeDTO feeDTO);

    public boolean ifRegistrationFeeExists(String fee);

}
