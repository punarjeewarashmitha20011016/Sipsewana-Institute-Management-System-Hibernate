package view.assets.tm;

public class StudentsTm {
    private String id;
    private String name;
    private String dob;
    private String nic;
    private String interviewFaced;
    private String registeredDate;

    public StudentsTm() {
    }

    public StudentsTm(String id, String name, String dob, String nic, String interviewFaced, String registeredDate) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.nic = nic;
        this.interviewFaced = interviewFaced;
        this.registeredDate = registeredDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getInterviewFaced() {
        return interviewFaced;
    }

    public void setInterviewFaced(String interviewFaced) {
        this.interviewFaced = interviewFaced;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    @Override
    public String toString() {
        return "StudentsTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", nic='" + nic + '\'' +
                ", interviewFaced='" + interviewFaced + '\'' +
                ", registeredDate='" + registeredDate + '\'' +
                '}';
    }
}
