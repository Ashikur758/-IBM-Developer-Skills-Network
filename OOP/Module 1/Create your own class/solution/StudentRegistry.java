public class StudentRegistry {
    public static void main(String[] args) {
        Student student1 = new Student();
        Student student2 = new Student();

        student1.setStudentId("S001");
        student1.setName("John Doe");
        student1.setGrade(85.5);
        student1.setIsActive(true);

        student2.setStudentId("S002");
        student2.setName("Jane Smith");
        student2.setGrade(92.0);
        student2.setIsActive(true);

        System.out.println("Displaying information for both students:");
        System.out.println(student1.displayStudentDetails());
        System.out.println();
        System.out.println(student2.displayStudentDetails());

        Student higherGradeStudent = compareGrades(student1, student2);
        System.out.println("\nComparing grades:");
        System.out.println(higherGradeStudent.getName() + " has the higher grade with " +
                higherGradeStudent.getGrade());

        System.out.println("\nLetter Grades:");
        System.out.println(student1.getName() + "'s letter grade: " + student1.getLetterGrade());
        System.out.println(student2.getName() + "'s letter grade: " + student2.getLetterGrade());

        System.out.println("\nPassing Status:");
        System.out.println(student1.getName() + " is " +
                (student1.isPassing() ? "passing" : "not passing"));
        System.out.println(student2.getName() + " is " +
                (student2.isPassing() ? "passing" : "not passing"));

        System.out.println("\nChanging " + student1.getName() + "'s status to inactive");
        student1.setIsActive(false);
        System.out.println(student1.displayStudentDetails());

        System.out.println("\nTesting grade validation:");
        student1.setGrade(105);
    }

    public static Student compareGrades(Student student1, Student student2) {
        if (student1.getGrade() > student2.getGrade()) {
            return student1;
        } else {
            return student2;
        }
    }
}
