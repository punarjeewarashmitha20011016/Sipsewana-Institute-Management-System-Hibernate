package bo.custom;

import bo.SuperBO;
import dto.ProgramsDTO;
import javafx.collections.ObservableList;

public interface ViewProgramsBO extends SuperBO {
    public ObservableList<ProgramsDTO> programs();
}
