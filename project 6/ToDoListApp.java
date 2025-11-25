import java.io.*;
import java.util.*;

public class ToDoListApp {

    // Text file for persistent storage
    private static final String FILE_NAME = "tasks.txt";

    // ArrayList to store tasks
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        loadTasksFromFile();   // Load existing tasks on program start

        boolean running = true;

        while (running) {
            System.out.println("\n===== TO-DO LIST MENU =====");
            System.out.println("1. View Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Remove Task");
            System.out.println("4. Mark as Done/Undo");
            System.out.println("5. Save & Exit");
            System.out.print("Choose an option: ");

            int choice = getValidInt(input);

            switch (choice) {
                case 1 -> viewTasks();
                case 2 -> addTask(input);
                case 3 -> removeTask(input);
                case 4 -> toggleTaskStatus(input);
                case 5 -> {
                    saveTasksToFile();
                    System.out.println("Tasks saved! Goodbye 👋");
                    running = false;
                }
                default -> System.out.println("❌ Invalid choice. Enter 1–5.");
            }
        }

        input.close();
    }

    // -------------------------------------------------------------
    // TASK OBJECT (Stores description, status, and priority)
    // -------------------------------------------------------------
    static class Task {
        String description;
        boolean completed;
        String priority; // High, Medium, Low

        Task(String desc, String priority, boolean completed) {
            this.description = desc;
            this.priority = priority;
            this.completed = completed;
        }

        @Override
        public String toString() {
            String statusMark = completed ? "✅" : "❌";
            return statusMark + " [" + priority + "] " + description;
        }
    }

    // -------------------------------------------------------------
    // VIEW TASKS
    // -------------------------------------------------------------
    public static void viewTasks() {
        System.out.println("\n===== YOUR TASKS =====");

        if (tasks.isEmpty()) {
            System.out.println("No tasks found!");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    // -------------------------------------------------------------
    // ADD TASK
    // -------------------------------------------------------------
    public static void addTask(Scanner input) {
        input.nextLine(); // clear buffer
        System.out.print("Enter task description: ");
        String desc = input.nextLine().trim();

        System.out.print("Set priority (High/Medium/Low): ");
        String priority = input.nextLine().trim();

        while (!(priority.equalsIgnoreCase("High") ||
                 priority.equalsIgnoreCase("Medium") ||
                 priority.equalsIgnoreCase("Low"))) {
            System.out.print("Invalid. Enter High, Medium, or Low: ");
            priority = input.nextLine().trim();
        }

        tasks.add(new Task(desc, capitalize(priority), false));
        System.out.println("Task added!");
    }

    // -------------------------------------------------------------
    // REMOVE TASK
    // -------------------------------------------------------------
    public static void removeTask(Scanner input) {
        viewTasks();

        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to remove: ");
        int num = getValidInt(input);

        if (num < 1 || num > tasks.size()) {
            System.out.println("❌ Invalid task number!");
            return;
        }

        tasks.remove(num - 1);
        System.out.println("Task removed!");
    }

    // -------------------------------------------------------------
    // MARK DONE / UNDO
    // -------------------------------------------------------------
    public static void toggleTaskStatus(Scanner input) {
        viewTasks();

        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to toggle: ");
        int num = getValidInt(input);

        if (num < 1 || num > tasks.size()) {
            System.out.println("❌ Invalid task number!");
            return;
        }

        Task task = tasks.get(num - 1);
        task.completed = !task.completed;

        if (task.completed) {
            System.out.println("Task marked as DONE! ✅");
        } else {
            System.out.println("Task marked as PENDING ❌");
        }
    }

    // -------------------------------------------------------------
    // SAVE TASKS TO FILE
    // -------------------------------------------------------------
    public static void saveTasksToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Task t : tasks) {
                // Format: description|priority|completed
                writer.write(t.description + "|" + t.priority + "|" + t.completed + "\n");
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving file: " + e.getMessage());
        }
    }

    // -------------------------------------------------------------
    // LOAD TASKS FROM FILE
    // -------------------------------------------------------------
    public static void loadTasksFromFile() {
        File file = new File(FILE_NAME);

        if (!file.exists()) return; // No tasks yet

        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length == 3) {
                    String desc = parts[0];
                    String priority = parts[1];
                    boolean completed = Boolean.parseBoolean(parts[2]);

                    tasks.add(new Task(desc, priority, completed));
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error loading file.");
        }
    }

    // -------------------------------------------------------------
    // HELPER METHODS
    // -------------------------------------------------------------
    public static int getValidInt(Scanner input) {
        while (!input.hasNextInt()) {
            System.out.print("Invalid number. Try again: ");
            input.next();
        }
        return input.nextInt();
    }

    public static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
