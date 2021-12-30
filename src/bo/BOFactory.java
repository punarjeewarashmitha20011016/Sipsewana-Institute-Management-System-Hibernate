package bo;

import bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}
    public static BOFactory getBoFactory(){
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }
    public SuperBO getBOTypes(BOTypes types){
        switch (types){
            case ManageReceptionists:return new ManageReceptionistsBOImpl();
            case ManagePrograms:return new ManageProgramsBOImpl();
            case LOGINFORM:return new LoginFormBOImpl();
            case ManageStudents:return new ManageStudentsBOImpl();
            case RegisterStudents:return new RegisterStudentsBOImpl();
            case RECEPTIONISTSFORM:return new ReceptionistFormBOImpl();
            case ViewPrograms:return new ViewProgramsBOImpl();
            case ViewStudents:return new ViewStudentsBOImpl();
            case ViewStudentDetails:return new ViewStudentsDetailsFormBOImpl();
            default:break;
        }return null;
    }
    public enum BOTypes{
        ManageReceptionists,ManagePrograms,LOGINFORM,ManageStudents,RegisterStudents,RECEPTIONISTSFORM,
        ViewPrograms,ViewStudents,ViewStudentDetails;
    }
}
