package view.assets.tm;

public class AddToCartTm {
    private String studentId;
    private String studentName;
    private String interviewFaced;
    private String courseId;
    private String courseName;
    private double courseFee;

    public AddToCartTm() {
    }

    public AddToCartTm(String studentId, String studentName, String interviewFaced, String courseId, String courseName, double courseFee) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.interviewFaced = interviewFaced;
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseFee = courseFee;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getInterviewFaced() {
        return interviewFaced;
    }

    public void setInterviewFaced(String interviewFaced) {
        this.interviewFaced = interviewFaced;
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
        return "AddToCartTm{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", interviewFaced='" + interviewFaced + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseFee=" + courseFee +
                '}';
    }
}
