package dto;

import entity.Programs;
import entity.Student;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class OrderDetailsDTO {
    private String orderDetailsId;
    private String orderId;
    private String studentId;
    private String interview;
    private String courseId;
    private String courseName;
    private double courseFee;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String orderDetailsId, String orderId, String studentId, String interview, String courseId, String courseName, double courseFee) {
        this.orderDetailsId = orderDetailsId;
        this.orderId = orderId;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getInterview() {
        return interview;
    }

    public void setInterview(String interview) {
        this.interview = interview;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" +
                "orderDetailsId='" + orderDetailsId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", interview='" + interview + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseFee=" + courseFee +
                '}';
    }
}
