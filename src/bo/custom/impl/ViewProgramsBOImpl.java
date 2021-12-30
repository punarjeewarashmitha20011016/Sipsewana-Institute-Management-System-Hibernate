package bo.custom.impl;

import bo.custom.ViewProgramsBO;
import dao.DAOFactory;
import dao.custom.ProgramsDAO;
import dto.ProgramsDTO;
import entity.Programs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ViewProgramsBOImpl implements ViewProgramsBO {
    private ProgramsDAO programsDAO= (ProgramsDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.PROGRAMS);
    @Override
    public ObservableList<ProgramsDTO> programs() {
        ArrayList<Programs> all = programsDAO.getAll();
        ObservableList<ProgramsDTO>dto= FXCollections.observableArrayList();
        for (Programs p:all
             ) {
            dto.add(new ProgramsDTO(p.getId(),p.getName(),p.getDuration(),p.getFee()));
        }
        return dto;
    }
}
