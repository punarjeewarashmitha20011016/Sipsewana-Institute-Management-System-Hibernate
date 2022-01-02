package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Registration implements Serializable {
    @Id
    private String orderId;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Student student;
    private String orderDate;
    private String orderTime;
    private String orderPrice;
    @OneToMany(mappedBy = "registrationId")
    private List<RegistrationDetails> registrationDetails = new ArrayList<>();

    public Registration() {
    }

    public Registration(String orderId, Student student, String orderDate, String orderTime, String orderPrice) {
        this.orderId = orderId;
        this.student = student;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderPrice = orderPrice;
    }

    public Registration(String orderId, Student student, String orderDate, String orderTime, String orderPrice, List<RegistrationDetails> registrationDetails) {
        this.orderId = orderId;
        this.student = student;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderPrice = orderPrice;
        this.registrationDetails = registrationDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
                "orderId='" + orderId + '\'' +
                ", student=" + student +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", orderPrice='" + orderPrice + '\'' +
                '}';
    }
}
