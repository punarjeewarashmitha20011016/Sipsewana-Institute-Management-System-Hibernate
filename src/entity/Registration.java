package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Registration implements Serializable {
    @Id
    private String registrationId;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Student student;
    private String orderDate;
    private String orderTime;
    private String orderPrice;
    @OneToMany(mappedBy = "registrationId",fetch = FetchType.EAGER)
    private List<RegistrationDetails> registrationDetails = new ArrayList<>();

    public Registration() {
    }

    public Registration(String registrationId, Student student, String orderDate, String orderTime, String orderPrice) {
        this.registrationId = registrationId;
        this.student = student;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderPrice = orderPrice;
    }

    public Registration(String registrationId, Student student, String orderDate, String orderTime, String orderPrice, List<RegistrationDetails> registrationDetails) {
        this.registrationId = registrationId;
        this.student = student;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderPrice = orderPrice;
        this.registrationDetails = registrationDetails;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String orderId) {
        this.registrationId = orderId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public List<RegistrationDetails> getRegistrationDetails() {
        return registrationDetails;
    }

    public void setRegistrationDetails(List<RegistrationDetails> registrationDetails) {
        this.registrationDetails = registrationDetails;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + registrationId + '\'' +
                ", student=" + student +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", orderPrice='" + orderPrice + '\'' +
                '}';
    }
}
