import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizApplication {
    private List<Question> questions;
    private Scanner scanner;

    public QuizApplication() {
        questions = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void startQuiz() {
        System.out.println("Welcome to the Java Quiz!");
        int score = 0;

        for (Question question : questions) {
            System.out.println(question.getQuestion());

            // Display options
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            int userChoice = getUserChoice(options.size());

            // Check if the selected option is correct
            if (question.isCorrect(userChoice)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is " + question.getCorrectAnswer());
            }
        }

        System.out.println("Your final score is " + score + "/" + questions.size());
    }

    private int getUserChoice(int numOptions) {
        int choice = -1;
        while (true) {
            System.out.print("Enter your choice (1-" + numOptions + "): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= numOptions) {
                    scanner.nextLine(); // consume newline
                    break;
                } else {
                    System.out.println("Invalid choice. Please select a number between 1 and " + numOptions + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
        return choice;
    }

    public static void main(String[] args) {
        QuizApplication quiz = new QuizApplication();

        // Add 15 Java-related questions with 4 options each
        quiz.addQuestion(new Question("What is the size of an int variable in Java?", List.of("4 bytes", "2 bytes", "8 bytes", "16 bytes"), 1));
        quiz.addQuestion(new Question("Which of the following is not a Java keyword?", List.of("static", "Boolean", "void", "private"), 2));
        quiz.addQuestion(new Question("Which method is used to start a thread in Java?", List.of("start()", "run()", "begin()", "execute()"), 1));
        quiz.addQuestion(new Question("Which of the following is a wrapper class in Java?", List.of("int", "float", "Integer", "char"), 3));
        quiz.addQuestion(new Question("Which package contains the String class?", List.of("java.util", "java.io", "java.net", "java.lang"), 4));
        quiz.addQuestion(new Question("What is the default value of a boolean variable?", List.of("true", "false", "null", "0"), 2));
        quiz.addQuestion(new Question("Which of these is a marker interface in Java?", List.of("Serializable", "Cloneable", "Remote", "All of the above"), 4));
        quiz.addQuestion(new Question("Which method is called when an object is garbage collected?", List.of("finalize()", "dispose()", "cleanUp()", "delete()"), 1));
        quiz.addQuestion(new Question("Which keyword is used to inherit a class in Java?", List.of("extends", "implements", "inherits", "super"), 1));
        quiz.addQuestion(new Question("Which of the following is not a valid access modifier in Java?", List.of("private", "protected", "public", "extern"), 4));
        quiz.addQuestion(new Question("Which of the following loops is guaranteed to execute at least once?", List.of("for loop", "while loop", "do-while loop", "None of the above"), 3));
        quiz.addQuestion(new Question("Which of the following is used to create an object in Java?", List.of("class", "object", "new", "constructor"), 3));
        quiz.addQuestion(new Question("Which operator is used for logical 'AND' in Java?", List.of("&", "|", "&&", "||"), 3));
        quiz.addQuestion(new Question("Which method is used to compare two strings in Java?", List.of("compare()", "equals()", "==", "compareTo()"), 2));
        quiz.addQuestion(new Question("Which class is the parent class of all classes in Java?", List.of("Object", "String", "Class", "System"), 1));

        quiz.startQuiz();
    }
}

class Question {
    private String question;
    private List<String> options;
    private int correctAnswerIndex;

    public Question(String question, List<String> options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;  // This should be the index of the correct option (1-based)
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return options.get(correctAnswerIndex - 1);  // Convert 1-based index to 0-based
    }

    public boolean isCorrect(int userChoice) {
        return userChoice == correctAnswerIndex;
    }
}
