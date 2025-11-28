import java.util.Scanner;

//blueprint for question structure
class Question {
    String question;
    String[] choices;
    char answer;

    // Constructor
    Question(String question, String[] choices, char answer) {
        this.question = question;
        this.choices = choices;
        this.answer = Character.toUpperCase(answer);
    }

    // Display question and choices
    void show() {
        System.out.println("\n" + question);
        System.out.println("A) " + choices[0]);
        System.out.println("B) " + choices[1]);
        System.out.println("C) " + choices[2]);
        System.out.println("D) " + choices[3]);
    }
}

// Main Game Class
public class JesBillionaireGame {
    Scanner scan = new Scanner(System.in);

    // Player Profile Info
    String playerName;
    int age;
    String gradeLevel;
    String favoriteSubject;

    // Money and Questions
    int[] money = { 10000, 50000, 100000, 500000, 1000000, 5000000, 10000000, 50000000, 100000000, 500000000,
            1000000000 };

    // questions array
    Question[] questions = {
            new Question("What is programming?",
                    new String[] { "Giving instructions to a computer", "Playing games", "Designing clothes",
                            "Typing fast" },
                    'A'),

            new Question("What is a variable?",
                    new String[] { "A storage for data", "A type of computer", "A game", "A printer" },
                    'A'),

            new Question("Which is used to display text on screen in Java?",
                    new String[] { "System.out.println()", "show.text()", "display()", "write()" },
                    'A'),

            new Question("Which data type is used for text?",
                    new String[] { "int", "String", "double", "boolean" },
                    'A'),

            new Question("Which symbol is used for addition in programming?",
                    new String[] { "+", "-", "*", "/" },
                    'A'),

            new Question("What does boolean mean?",
                    new String[] { "Number", "Text", "True/False", "Decimal" },
                    'A'),

            new Question("Which is a loop?",
                    new String[] { "if", "while", "stop", "print" },
                    'A'),

            new Question("What is an algorithm?",
                    new String[] { "A step-by-step solution", "A computer virus", "A code error", "A robot" },
                    'A'),

            new Question("Which of these is a programming language?",
                    new String[] { "Java", "Banana", "English", "Math" },
                    'A'),

            new Question("What do you call a mistake in code?",
                    new String[] { "Error", "Winner", "Output", "Memory" },
                    'A')
    };

    // Bonus Round Question
    Question bonus = new Question(
            "BONUS ROUND: What is the rarest blood type?",
            new String[] { "A-", "O+", "AB-", "B+" },
            'C');

    public static void main(String[] args) {
        new JesBillionaireGame().mainMenu();
    }

    // Typing animation
    void slowPrint(String text, long delay) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (Exception e) {
            }
        }
        System.out.println();
    }

    // Main Menu
    void mainMenu() {
        printHeader();
        System.out.print("Loading game");
        slowPrint(".....", 1000);
        slowPrint("Welcome to Who Wants to Be a Billionaire!\n", 40);
        slowPrint("Let's set up your profile first.", 20);
        System.out.print("Loading Profile Setup");
        slowPrint("....", 1000);

        registerPlayer(); // Player Profile
        showRules();
        showAgreement();
    }

    // Player Registration
    void registerPlayer() {
        System.out.println("========== PLAYER PROFILE ==========");

        System.out.print("Enter your name: ");
        playerName = scan.nextLine().trim();
        if (playerName.isEmpty())
            playerName = "Player";

        System.out.print("Enter your age: ");
        age = Integer.parseInt(scan.nextLine());
        if (age >= 18) {
            System.out.println("You are eligible to play!");
        } else {
            System.out.println("You must be at least 18 years old to play. Exiting game...");
            registerPlayer();
        }
            

        System.out.print("Enter your province: ");
        gradeLevel = scan.nextLine().trim();

        System.out.println("\nWelcome " + playerName + "!");
        System.out.println("Age: " + age + " | From province of: " + gradeLevel + "\n");
        System.out.println("=====================================\n");
    }

    // Show Game Rules
    void showRules() {
        System.out.println("========== GAME RULES ==========");
        System.out.println("1. Answer using A, B, C, or D.");
        System.out.println("2. You only have 2 chances.");
        System.out.println("3. Correct answers give you money.");
        System.out.println("4. Wrong answers remove 1 chance.");
        System.out.println("5. Finish all 10 questions → BILLIONAIRE!");
        System.out.println("Press Enter to continue...");
        scan.nextLine();
    }

    // Show Agreement
    void showAgreement() {
        System.out.println("\n========== AGREEMENT ==========");
        System.out.println("By playing, you agree to the rules.");
        System.out.print("Do you accept the terms? (yes/no): ");

        String response = scan.nextLine().trim().toLowerCase();

        if (response.equals("yes") || response.equals("y")) {
            slowPrint("\nGreat! Let's begin, " + playerName + "!\n", 100);
            start();
        } else {
            System.out.println("You did not accept. Exiting game...");
        }
    }

    // Game Start
    void start() {
        int chances = 2;

        for (int i = 0; i < questions.length; i++) {
            boolean correct = false;

            while (!correct) {
                System.out.println("\n----- QUESTION " + (i + 1) + " -----");
                System.out.println("For: ₱" + money[i]);
                questions[i].show();

                System.out.print("\nEnter A/B/C/D: ");
                String input = scan.nextLine().trim().toUpperCase();

                if (!input.matches("[A-D]")) {
                    System.out.println("Invalid input! Please enter A, B, C, or D.");
                    continue;
                }

                char user = input.charAt(0);

                if (user == questions[i].answer) {
                    System.out.println("Correct, " + playerName + "! You won ₱" + money[i]);
                    System.out.printf("Total Winnings: ₱%,d\n", money[i]);
                    correct = true;
                } else {
                    chances--;
                    System.out.println("Sad to Say it's Wrong! You have chances: " + chances + " left.");

                    if (chances == 0) {
                        System.out.println("\nGAME OVER!");
                        int earned = (i == 0) ? 0 : money[i - 1];
                        System.out.printf("Nice Try %s, you finished with ₱%,d\n", playerName, earned);
                        return;
                    }

                    System.out.println("Repeating question...");

                }
            }
        }

        // Win animation
        slowPrint("\nCONGRATULATIONS", 50);
        slowPrint("YOU FINISHED ALL QUESTIONS! ", 30);
        slowPrint("You are now a BILLIONAIRE! ₱1,000,000,000\n", 30);
        slowPrint("Preparing BONUS ROUND...", 50);
        slowPrint("Get ready!", 50);
        slowPrint("3...", 500);
        slowPrint("2...", 500);
        slowPrint("1...", 500);

        // Bonus Round
        bonusRound();
    }

    // Bonus Round
    void bonusRound() {
        System.out.println("\n========== BONUS ROUND ==========");
        slowPrint("One last SUPER HARD question...", 40);
        slowPrint("You only get 1 chance!", 40);

        bonus.show();

        System.out.print("Enter A/B/C/D: ");
        String input = scan.nextLine().trim().toUpperCase();

        // Check answer for bonus round
        if (input.length() == 1 && input.charAt(0) == bonus.answer) {

            slowPrint("\n AMAZING! You answered the BONUS QUESTION correctly!", 100);
            slowPrint("You earn an extra ₱50,000,000!", 40);
            System.out.printf("Total Winnings: ₱%,d\n", 1000000000 + 50000000);
            slowPrint("You are truly a BILLIONAIRE CHAMPION!", 40);
            System.out.print("=================================");
            slowPrint("         YOU WIN THE GAME!       ", 40);
            System.out.print("=================================");
            slowPrint("Thank you for playing, " + playerName + "!", 40);
            System.out.print("=================================");

        } else {
            System.out.println("\nWrong! No bonus earned.");
            System.out.printf("Total Winnings: ₱%,d\n", 1000000000);
            System.out.println("You are still a BILLIONAIRE!");

        }

        // End of Game
        slowPrint("\nThank you for playing, " + playerName + "!", 50);

    }

    // Print Header
    void printHeader() {
        System.out.println("=====================================");
        System.out.println("     WHO WANTS TO BE A BILLIONAIRE    ");
        System.out.println("=====================================");
        slowPrint("Presented by: Jesriel Dave Pandan", 30);
        slowPrint("ITE 12 - Final Project", 30);
        System.out.println("=====================================\n");

    }
}
