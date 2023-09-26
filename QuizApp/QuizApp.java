import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

class Question {
    String questionText;
    List<String> options;
    int correctOption;

    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
}

public class QuizApp {
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static List<Question> questions;
    private static Timer timer;

    public static void main(String[] args) {
        initializeQuestions();
        startQuiz();
    }

    private static void initializeQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", List.of("A. Berlin", "B. London", "C. Paris", "D. Madrid"), 2));
        questions.add(new Question("Which planet is known as the Red Planet?", List.of("A. Mars", "B. Venus", "C. Jupiter", "D. Saturn"), 0));
        questions.add(new Question("What is the largest mammal in the world?", List.of("A. Elephant", "B. Giraffe", "C. Blue Whale", "D. Lion"), 2));
        questions.add(new Question("Which gas do plants absorb from the atmosphere?", List.of("A. Oxygen", "B. Carbon Dioxide", "C. Nitrogen", "D. Hydrogen"), 1));
        questions.add(new Question("Who wrote the play 'Romeo and Juliet'?", List.of("A. William Shakespeare", "B. Charles Dickens", "C. Mark Twain", "D. Jane Austen"), 0));
        questions.add(new Question("What is the chemical symbol for gold?", List.of("A. Go", "B. Au", "C. Ag", "D. Gl"), 1));
    }

    private static void startQuiz() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            displayQuestion(currentQuestion);

            // Set a timer for the question
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up!");
                    checkAnswer(-1); // -1 indicates no answer
                }
            }, 15000); // 15 seconds per question, adjust as needed

            getUserAnswer(currentQuestion);
        } else {
            endQuiz();
        }
    }

    private static void displayQuestion(Question question) {
        System.out.println(question.questionText);
        for (String option : question.options) {
            System.out.println(option);
        }
    }

    private static void getUserAnswer(Question question) {
        System.out.print("Your answer (enter A, B, C, or D): ");
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.next().toUpperCase();
        int optionIndex = userAnswer.charAt(0) - 'A';

        checkAnswer(optionIndex);
    }

    private static void checkAnswer(int userChoice) {
        timer.cancel(); // Cancel the timer
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (userChoice == currentQuestion.correctOption) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer is: " + currentQuestion.options.get(currentQuestion.correctOption));
        }

        currentQuestionIndex++;
        startQuiz();
    }

    private static void endQuiz() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your score: " + score + "/" + questions.size());
        // Display a summary of correct/incorrect answers if needed.
    }
}
