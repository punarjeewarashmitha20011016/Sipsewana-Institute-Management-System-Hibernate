package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Programs implements Serializable {
    @Id
    private String id;
    @Column(name = "name")
    private String name;
    private String duration;
    @Column(name = "fee")
    private double fee;

    @OneToMany(mappedBy = "courseId")
    private List<RegistrationDetails> registrationDetailsListId = new ArrayList<>();

    @OneToMany(mappedBy = "courseName")
    private List<RegistrationDetails> registrationDetailsListName = new ArrayList<>();

    @OneToMany(mappedBy = "courseFee", cascade = CascadeType.ALL)
    private List<RegistrationDetails> registrationDetailsListFee = new ArrayList<>();

    public Programs() {
    }

    public Programs(String id, String name, String duration, double fee) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.fee = fee;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<RegistrationDetails> getRegistrationDetailsListId() {
        return registrationDetailsListId;
    }

    public void setRegistrationDetailsListId(List<RegistrationDetails> registrationDetailsListId) {
        this.registrationDetailsListId = registrationDetailsListId;
    }

    public List<RegistrationDetails> getRegistrationDetailsListName() {
        return registrationDetailsListName;
    }

    public void setRegistrationDetailsListName(List<RegistrationDetails> registrationDetailsListName) {
        this.registrationDetailsListName = registrationDetailsListName;
    }

    public List<RegistrationDetails> getRegistrationDetailsListFee() {
        return registrationDetailsListFee;
    }

    public void setRegistrationDetailsListFee(List<RegistrationDetails> registrationDetailsListFee) {
        this.registrationDetailsListFee = registrationDetailsListFee;
    }

    @Override
    public String toString() {
        return "Programs{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                '}';
    }
}
