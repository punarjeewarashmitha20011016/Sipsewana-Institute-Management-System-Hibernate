package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student implements Serializable {
    @Id
    private String id;
    private String name;
    private String dob;
    private String nic;
    private String interviewFaced;
    private String registeredDate;
    @OneToMany(mappedBy = "student")
    private final List<Registration> registrationList = new ArrayList<>();

    public Student() {
    }

    public Student(String id, String name, String dob, String nic, String registeredDate, String interviewFaced) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.nic = nic;
        this.registeredDate = registeredDate;
        this.interviewFaced = interviewFaced;
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

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getInterviewFaced() {
        return interviewFaced;
    }

    public void setInterviewFaced(String interviewFaced) {
        this.interviewFaced = interviewFaced;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", nic='" + nic + '\'' +
                ", interviewFaced='" + interviewFaced + '\'' +
                ", registeredDate='" + registeredDate + '\'' +
                '}';
    }
}
