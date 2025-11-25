import java.util.*;

public class QuizGame {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("🎮 Welcome to the Quiz Game!");
        System.out.println("Choose a difficulty: ");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.print("Enter choice (1-3): ");

        int choice = getValidInt(input);

        while (choice < 1 || choice > 3) {
            System.out.print("Invalid choice! Enter 1, 2, or 3: ");
            choice = getValidInt(input);
        }

        // Load questions for chosen difficulty
        ArrayList<Question> quizQuestions = loadQuestions(choice);

        // Shuffle to randomize order
        Collections.shuffle(quizQuestions);

        // Run quiz
        int score = runQuiz(quizQuestions, input);

        // Display final score
        System.out.println("\n=============================");
        System.out.println("Quiz Complete!");
        System.out.println("Your Score: " + score + "/" + quizQuestions.size());
        System.out.println("=============================");

        input.close();
    }

    // ---------------------------------------------------------
    // QUESTION OBJECT
    // ---------------------------------------------------------
    static class Question {
        String prompt;
        String[] choices;
        char answer;

        Question(String prompt, String[] choices, char answer) {
            this.prompt = prompt;
            this.choices = choices;
            this.answer = answer;
        }
    }

    // ---------------------------------------------------------
    // LOAD QUESTIONS FOR EACH DIFFICULTY LEVEL
    // ---------------------------------------------------------
    public static ArrayList<Question> loadQuestions(int difficulty) {

        ArrayList<Question> q = new ArrayList<>();

        if (difficulty == 1) {
            // EASY (10 Questions)
            q.add(new Question("What color is the sky?", new String[]{"A) Blue", "B) Green", "C) Red"}, 'A'));
            q.add(new Question("How many days are in a week?", new String[]{"A) 5", "B) 7", "C) 10"}, 'B'));
            q.add(new Question("What is 2 + 2?", new String[]{"A) 3", "B) 4", "C) 5"}, 'B'));
            q.add(new Question("Which animal barks?", new String[]{"A) Cat", "B) Dog", "C) Sheep"}, 'B'));
            q.add(new Question("What planet do we live on?", new String[]{"A) Mars", "B) Earth", "C) Venus"}, 'B'));
            q.add(new Question("What is the first letter of the alphabet?", new String[]{"A) A", "B) B", "C) C"}, 'A'));
            q.add(new Question("Which shape has 3 sides?", new String[]{"A) Circle", "B) Triangle", "C) Square"}, 'B'));
            q.add(new Question("What do bees make?", new String[]{"A) Milk", "B) Honey", "C) Bread"}, 'B'));
            q.add(new Question("Which season is the coldest?", new String[]{"A) Summer", "B) Fall", "C) Winter"}, 'C'));
            q.add(new Question("How many legs does a spider have?", new String[]{"A) 6", "B) 8", "C) 10"}, 'B'));
        }

        else if (difficulty == 2) {
            // MEDIUM (10 Questions)
            q.add(new Question("What gas do plants breathe in?", new String[]{"A) Oxygen", "B) Nitrogen", "C) Carbon Dioxide"}, 'C'));
            q.add(new Question("Which ocean is the largest?", new String[]{"A) Atlantic", "B) Pacific", "C) Indian"}, 'B'));
            q.add(new Question("Who wrote 'Harry Potter'?", new String[]{"A) J.K. Rowling", "B) Tolkien", "C) Mark Twain"}, 'A'));
            q.add(new Question("What is H2O?", new String[]{"A) Salt", "B) Water", "C) Sugar"}, 'B'));
            q.add(new Question("Which country invented pizza?", new String[]{"A) Italy", "B) France", "C) USA"}, 'A'));
            q.add(new Question("What is the capital of Japan?", new String[]{"A) Beijing", "B) Seoul", "C) Tokyo"}, 'C'));
            q.add(new Question("How many continents are there?", new String[]{"A) 5", "B) 7", "C) 6"}, 'B'));
            q.add(new Question("Which metal is liquid at room temperature?", new String[]{"A) Silver", "B) Mercury", "C) Copper"}, 'B'));
            q.add(new Question("Which organ pumps blood?", new String[]{"A) Heart", "B) Brain", "C) Lungs"}, 'A'));
            q.add(new Question("What galaxy do we live in?", new String[]{"A) Andromeda", "B) Milky Way", "C) Whirlpool"}, 'B'));
        }

        else {
            // HARD (10 Questions)
            q.add(new Question("What is the powerhouse of the cell?", new String[]{"A) Ribosome", "B) Mitochondria", "C) Nucleus"}, 'B'));
            q.add(new Question("Einstein's equation E = mc² means?", new String[]{"A) Energy = Mass × Speed²", "B) Energy = Mass × Light speed²", "C) Mass = Energy × Speed²"}, 'B'));
            q.add(new Question("Which element has atomic number 1?", new String[]{"A) Helium", "B) Oxygen", "C) Hydrogen"}, 'C'));
            q.add(new Question("Who painted the Mona Lisa?", new String[]{"A) Picasso", "B) Leonardo da Vinci", "C) Van Gogh"}, 'B'));
            q.add(new Question("Which planet has the most moons?", new String[]{"A) Saturn", "B) Jupiter", "C) Neptune"}, 'A'));
            q.add(new Question("What year did WW2 end?", new String[]{"A) 1940", "B) 1945", "C) 1950"}, 'B'));
            q.add(new Question("What is the longest river?", new String[]{"A) Nile", "B) Amazon", "C) Yangtze"}, 'B'));
            q.add(new Question("Who founded Microsoft?", new String[]{"A) Steve Jobs", "B) Bill Gates", "C) Elon Musk"}, 'B'));
            q.add(new Question("Which math constant equals 3.14159?", new String[]{"A) e", "B) π", "C) φ"}, 'B'));
            q.add(new Question("How many bones in the adult human body?", new String[]{"A) 206", "B) 210", "C) 201"}, 'A'));
        }

        return q;
    }

    // ---------------------------------------------------------
    // RUN THE QUIZ
    // ---------------------------------------------------------
    public static int runQuiz(ArrayList<Question> questions, Scanner input) {

        int score = 0;

        for (int i = 0; i < questions.size(); i++) {

            Question q = questions.get(i);

            System.out.println("\nQuestion " + (i + 1) + ": " + q.prompt);

            for (String c : q.choices) {
                System.out.println(c);
            }

            System.out.print("Your answer (A/B/C): ");
            char answer = getValidAnswer(input);

            if (Character.toUpperCase(answer) == q.answer) {
                System.out.println("✔ Correct!");
                score++;
            } else {
                System.out.println("❌ Incorrect! The correct answer was: " + q.answer);
            }
        }

        return score;
    }

    // ---------------------------------------------------------
    // INPUT VALIDATION HELPERS
    // ---------------------------------------------------------
    public static int getValidInt(Scanner input) {
        while (!input.hasNextInt()) {
            System.out.print("Invalid number! Try again: ");
            input.next();
        }
        return input.nextInt();
    }

    public static char getValidAnswer(Scanner input) {
        String ans = input.next().trim().toUpperCase();
        while (!(ans.equals("A") || ans.equals("B") || ans.equals("C"))) {
            System.out.print("Invalid input! Enter A, B, or C: ");
            ans = input.next().trim().toUpperCase();
        }
        return ans.charAt(0);
    }

}
