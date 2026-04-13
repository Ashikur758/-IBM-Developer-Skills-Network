public class Student {
    private String studentId;
    private String name;
    private double grade;
    private boolean isActive;

    public String getStudentId() {
        return this.studentId;
    }

    public String getName() {
        return this.name;
    }

    public double getGrade() {
        return this.grade;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(double grade) {
        if (grade >= 0 && grade <= 100) {
            this.grade = grade;
        } else {
            System.out.println("Error: Grade must be between 0 and 100");
        }
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String displayStudentDetails() {
        return "ID: " + this.studentId +
                "\nName: " + this.name +
                " Grade: " + this.grade +
                "\nStatus: " + (this.isActive ? "Active" : "Inactive");
    }

    public String getLetterGrade() {
        if (this.grade >= 90) {
            return "A";
        } else if (this.grade >= 80) {
            return "B";
        } else if (this.grade >= 70) {
            return "C";
        } else if (this.grade >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public boolean isPassing() {
        return this.grade >= 60;
    }

    public static void main(String[] args) {
        Student s = new Student();

        s.setStudentId("S101");
        s.setName("Ashik");
        s.setGrade(85);
        s.setIsActive(true);

        System.out.println(s.displayStudentDetails());
        System.out.println("Letter Grade: " + s.getLetterGrade());
        System.out.println("Passing: " + s.isPassing());
    }
}
