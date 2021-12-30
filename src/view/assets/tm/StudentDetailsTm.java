package view.assets.tm;

public class StudentDetailsTm {
    private String id;
    private String name;
    private String registeredDate;
    private String program;
    private String duration;

    public StudentDetailsTm() {
    }

    public StudentDetailsTm(String id, String name, String registeredDate, String program, String duration) {
        this.id = id;
        this.name = name;
        this.registeredDate = registeredDate;
        this.program = program;
        this.duration = duration;
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

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "StudentDetailsTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", registeredDate='" + registeredDate + '\'' +
                ", program='" + program + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
