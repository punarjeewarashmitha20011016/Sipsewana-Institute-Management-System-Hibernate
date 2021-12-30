package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orders implements Serializable {
    @Id
    private String orderId;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Student student;
    private String orderDate;
    private String orderTime;
    private String orderPrice;
    @OneToMany(mappedBy = "ordersId")
    private List<OrderDetails>orderDetails=new ArrayList<>();

    public Orders() {
    }

    public Orders(String orderId, Student student, String orderDate, String orderTime, String orderPrice) {
        this.orderId = orderId;
        this.student = student;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderPrice = orderPrice;
    }

    public Orders(String orderId, Student student, String orderDate, String orderTime, String orderPrice, List<OrderDetails> orderDetails) {
        this.orderId = orderId;
        this.student=student;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderPrice = orderPrice;
        this.orderDetails = orderDetails;
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

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
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
