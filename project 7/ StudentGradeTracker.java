import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }
    public String getLetterGrade() {
        if (grade >= 92) return "A";
        else if (grade >= 83) return "B";
        else if (grade >= 74) return "C";
        else if (grade >= 65) return "D";
        else return "F";
    }

    public String toString() {
        return name + " - " + grade + " (" + getLetterGrade() + ")";
    }
}

public class StudentGradeTracker {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        students.add(new Student("Alice", 95));
        students.add(new Student("Bob", 82));
        students.add(new Student("Charlie", 76));
        students.add(new Student("Diana", 88));
        students.add(new Student("Ethan", 91));
        students.add(new Student("Fiona", 67));
        students.add(new Student("George", 100));
        students.add(new Student("Hannah", 73));
        students.add(new Student("Isabella", 85));
        students.add(new Student("Jack", 59));

        int choice;
        do {
            System.out.println("\n=== STUDENT GRADE TRACKER ===");
            System.out.println("1. Show all students");
            System.out.println("2. Search student by name");
            System.out.println("3. Show class statistics");
            System.out.println("4. Sort students by grade (high to low)");
            System.out.println("5. Sort students by name (A to Z)");
            System.out.println("6. Add new student");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = input.nextInt();
            input.nextLine(); 

            switch (choice) {
                case 1:
                    showAllStudents();
                    break;
                case 2:
                    searchStudent();
                    break;
                case 3:
                    showStatistics();
                    break;
                case 4:
                    sortByGrade();
                    break;
                case 5:
                    sortByName();
                    break;
                case 6:
                    addStudent();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    public static void showAllStudents() {
        System.out.println("\n--- All Students ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public static void searchStudent() {
        System.out.print("\nEnter the student's name: ");
        String name = input.nextLine().toLowerCase();
        boolean found = false;

        for (Student s : students) {
            if (s.getName().toLowerCase().equals(name)) {
                System.out.println("Found: " + s);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public static void showStatistics() {
        if (students.isEmpty()) {
            System.out.println("No students in the list.");
            return;
        }

        double total = 0, highest = students.get(0).getGrade(), lowest = students.get(0).getGrade();

        for (Student s : students) {
            double grade = s.getGrade();
            total += grade;
            if (grade > highest) highest = grade;
            if (grade < lowest) lowest = grade;
        }

        double average = total / students.size();

        System.out.println("\n--- Class Statistics ---");
        System.out.printf("Average Grade: %.2f\n", average);
        System.out.println("Highest Grade: " + highest);
        System.out.println("Lowest Grade: " + lowest);
    }

    public static void sortByGrade() {
        students.sort((a, b) -> Double.compare(b.getGrade(), a.getGrade()));
        System.out.println("\nStudents sorted by grade (high → low).");
        showAllStudents();
    }

    public static void sortByName() {
        students.sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));
        System.out.println("\nStudents sorted by name (A → Z).");
        showAllStudents();
    }

    public static void addStudent() {
        System.out.print("\nEnter new student's name: ");
        String name = input.nextLine();

        System.out.print("Enter grade (0 - 100): ");
        double grade = input.nextDouble();
        input.nextLine();

        students.add(new Student(name, grade));
        System.out.println("Student added successfully!");
    }
}
