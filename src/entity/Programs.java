package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Programs implements Serializable {
    @Id
    private String id;
    private String name;
    private String duration;
    private double fee;
    @OneToMany(mappedBy = "courseId")
    private List<OrderDetails> orderDetailsListId = new ArrayList<>();

    @OneToMany(mappedBy = "courseName")
    private List<OrderDetails> orderDetailsListName = new ArrayList<>();

    @OneToMany(mappedBy = "courseFee")
    private List<OrderDetails> orderDetailsListFee = new ArrayList<>();

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

    public List<OrderDetails> getOrderDetailsListId() {
        return orderDetailsListId;
    }

    public void setOrderDetailsListId(List<OrderDetails> orderDetailsListId) {
        this.orderDetailsListId = orderDetailsListId;
    }

    public List<OrderDetails> getOrderDetailsListName() {
        return orderDetailsListName;
    }

    public void setOrderDetailsListName(List<OrderDetails> orderDetailsListName) {
        this.orderDetailsListName = orderDetailsListName;
    }

    public List<OrderDetails> getOrderDetailsListFee() {
        return orderDetailsListFee;
    }

    public void setOrderDetailsListFee(List<OrderDetails> orderDetailsListFee) {
        this.orderDetailsListFee = orderDetailsListFee;
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
