package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class RegistrationFee implements Serializable {
    @Id
    private String id;
    private double fee;

    public RegistrationFee() {
    }

    public RegistrationFee(String id, double fee) {
        this.id = id;
        this.fee = fee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "RegistrationFee{" +
                "id='" + id + '\'' +
                ", fee=" + fee +
                '}';
    }
}
