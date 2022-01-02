package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class RegistrationDetails implements Serializable {
    @Id
    private String registrationDetailsId;
    @ManyToOne
    @JoinColumn(referencedColumnName = "registrationId")
    private Registration registrationId;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Student studentId;
    @ManyToOne
    @JoinColumn(referencedColumnName = "interviewFaced")
    private Student interview;

    @Id
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Programs courseId;
    @ManyToOne
    @JoinColumn(referencedColumnName = "name")
    private Programs courseName;
    @ManyToOne
    @JoinColumn(referencedColumnName = "fee")
    private Programs courseFee;

    public RegistrationDetails() {
    }

    public RegistrationDetails(String registrationDetailsId, Registration registrationId, Student studentId, Student interview, Programs courseId, Programs courseName, Programs courseFee) {
        this.registrationDetailsId = registrationDetailsId;
        this.registrationId = registrationId;
        this.studentId = studentId;
        this.interview = interview;
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseFee = courseFee;
    }

    public String getRegistrationDetailsId() {
        return registrationDetailsId;
    }

    public void setRegistrationDetailsId(String orderDetailsId) {
        this.registrationDetailsId = orderDetailsId;
    }

    public Registration getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Registration registrationId) {
        this.registrationId = registrationId;
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
                "orderDetailsId='" + registrationDetailsId + '\'' +
                ", ordersId=" + registrationId +
                ", studentId=" + studentId +
                ", interview=" + interview +
                ", courseId=" + courseId +
                ", courseName=" + courseName +
                ", courseFee=" + courseFee +
                '}';
    }
}
