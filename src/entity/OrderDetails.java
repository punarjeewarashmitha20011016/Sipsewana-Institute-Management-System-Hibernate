package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class OrderDetails implements Serializable {
    @Id
    private String orderDetailsId;
    @ManyToOne
    private Orders ordersId;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Student studentId;
    @ManyToOne
    @JoinColumn(referencedColumnName = "interviewFaced")
    private Student interview;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Programs courseId;
    @ManyToOne
    @JoinColumn(referencedColumnName = "name")
    private Programs courseName;
    @ManyToOne
    @JoinColumn(referencedColumnName = "fee")
    private Programs courseFee;

    public OrderDetails() {
    }

    public OrderDetails(String orderDetailsId, Orders ordersId, Student studentId, Student interview, Programs courseId, Programs courseName, Programs courseFee) {
        this.orderDetailsId = orderDetailsId;
        this.ordersId = ordersId;
        this.studentId = studentId;
        this.interview = interview;
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseFee = courseFee;
    }

    public String getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(String orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public Orders getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Orders ordersId) {
        this.ordersId = ordersId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Student getInterview() {
        return interview;
    }

    public void setInterview(Student interview) {
        this.interview = interview;
    }

    public Programs getCourseId() {
        return courseId;
    }

    public void setCourseId(Programs courseId) {
        this.courseId = courseId;
    }

    public Programs getCourseName() {
        return courseName;
    }

    public void setCourseName(Programs courseName) {
        this.courseName = courseName;
    }

    public Programs getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(Programs courseFee) {
        this.courseFee = courseFee;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderDetailsId='" + orderDetailsId + '\'' +
                ", ordersId=" + ordersId +
                ", studentId=" + studentId +
                ", interview=" + interview +
                ", courseId=" + courseId +
                ", courseName=" + courseName +
                ", courseFee=" + courseFee +
                '}';
    }
}
