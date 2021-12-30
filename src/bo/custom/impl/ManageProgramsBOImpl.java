package bo.custom.impl;

import bo.custom.ManageProgramsBO;
import dao.DAOFactory;
import dao.custom.ProgramsDAO;
import dao.custom.RegistrationFeeDAO;
import dto.ProgramsDTO;
import dto.RegistrationFeeDTO;
import entity.Programs;
import entity.RegistrationFee;

public class ManageProgramsBOImpl implements ManageProgramsBO {
    private ProgramsDAO programsDAO= (ProgramsDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.PROGRAMS);
    private RegistrationFeeDAO registrationFeeDAO= (RegistrationFeeDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.RegistrationFee);
    @Override
    public boolean savePrograms(ProgramsDTO dto) {
        return programsDAO.save(new Programs(dto.getId(),dto.getName(),dto.getDuration(),dto.getFee()));
    }

    @Override
    public boolean updatePrograms(ProgramsDTO dto) {
        return programsDAO.update(new Programs(dto.getId(),dto.getName(),dto.getDuration(),dto.getFee()));
    }

    @Override
    public boolean deletePrograms(ProgramsDTO dto) {
        return programsDAO.delete(new Programs(dto.getId(),dto.getName(),dto.getDuration(),dto.getFee()));
    }

    @Override
    public ProgramsDTO searchPrograms(String id) {
        Programs search = programsDAO.search(id);
        return new ProgramsDTO(search.getId(),search.getName(),search.getDuration(),search.getFee());
    }

    @Override
    public boolean ifProgramsExists(String id) {
        return programsDAO.ifProgramsExists(id);
    }

    @Override
    public String generateProgramsId() {
        return programsDAO.generateProgramId();
    }

    @Override
    public boolean saveRegistrationFee(RegistrationFeeDTO feeDTO) {
        return registrationFeeDAO.save(new RegistrationFee(registrationFeeDAO.generateFeeId(),feeDTO.getFee()));
    }

    @Override
    public boolean updateRegistrationFee(RegistrationFeeDTO feeDTO) {
        return registrationFeeDAO.update(new RegistrationFee(registrationFeeDAO.generateFeeId(),feeDTO.getFee()));
    }

    @Override
    public boolean deleteRegistrationFee(RegistrationFeeDTO feeDTO) {
        return registrationFeeDAO.delete(new RegistrationFee(registrationFeeDAO.generateFeeId(),feeDTO.getFee()));
    }

    @Override
    public boolean ifRegistrationFeeExists(String fee) {
        return registrationFeeDAO.ifRegistrationFeeExists(fee);
    }
}
