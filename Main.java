import java.util.Scanner;

public class Main {

    int playerScore = 0;
    int botScore = 0;
    int totalPlayerBalls = 0;
    int totalBotBalls = 0;
    boolean playerBatFirst;

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        Main game = new Main();
        game.startGame();
    }

    void startGame() throws InterruptedException {
        System.out.println("----Welcome to the Odd-Even Cricket Game!----");
        Thread.sleep(2000);
        System.out.println("        Get ready for the toss...");
        Thread.sleep(2000);
        toss();
    }

    void toss() throws InterruptedException {
        int playerRun = validator("Enter a number between 1-10 for toss: ");
        Thread.sleep(1000);

        System.out.println("Choose Odd(O/o) or Even(E/e): ");
        System.out.println("Note: You can only choose O/o or E/e If you enter any word, the first letter will be taken as your choice.");
        char choice = sc.next().charAt(0);
        choice = tossValidator(choice);
        System.out.println("You chose: " + (choice == 'E' || choice == 'e' ? "Even" : "Odd"));
        Thread.sleep(1000);

        int botRun = (int) (Math.random() * 10) + 1;
        System.out.println("Bot chose: " + botRun);
        Thread.sleep(1500);

        int runs = playerRun + botRun;
        boolean isEven = runs % 2 == 0;

        if ((isEven && (choice == 'E' || choice == 'e')) || (!isEven && (choice == 'O' || choice == 'o'))) {
            System.out.println(" You won the toss!");
            Thread.sleep(1000);
            chooseBatOrBowl();
        } else {
            System.out.println("🤖 Bot won the toss!");
            Thread.sleep(1000);
            playerBatFirst = Math.random() < 0.5;
            System.out.println("Bot chooses to " + (playerBatFirst ? "bowl first." : "bat first."));
            Thread.sleep(2000);
            playMatch();
        }
    }

    char tossValidator(char choice) {
        char temp;
        while (true) {
            if (choice == 'O' || choice == 'o' || choice == 'E' || choice == 'e') {
                temp = choice;
                break;
            } else {
                System.out.println("Invalid choice. Please enter O/o for Odd or E/e for Even.");
                choice = sc.next().charAt(0);
            }
        }
        return choice;
    }

    void chooseBatOrBowl() throws InterruptedException {
        int choice;
        while (true) {
            System.out.println("Enter 1 to Bat first or 2 to Bowl first: ");
            choice = sc.nextInt();
            if (choice == 1 || choice == 2)
                break;
            System.out.println("Invalid choice. Try again.");
        }
        playerBatFirst = (choice == 1);
        Thread.sleep(1000);
        playMatch();
    }

    void playMatch() throws InterruptedException {
        if (playerBatFirst) {
            System.out.println("\n🔵 You are batting first!");
            Thread.sleep(2000);
            playerBatting(true);
            System.out.println("\n🟢 Bot is now batting.");
            Thread.sleep(2000);
            System.out.println("Target: Bot needs " + (playerScore + 1) + " runs to win.\n");
            Thread.sleep(2000);
            botBatting(false);
        } else {
            System.out.println("\n🟢 Bot is batting first!");
            Thread.sleep(2000);
            botBatting(true);
            System.out.println("\n🔵 You are now batting.");
            Thread.sleep(2000);
            System.out.println("Target: You need " + (botScore + 1) + " runs to win.\n");
            Thread.sleep(2000);
            playerBatting(false);
        }

        declareResult();
    }

    void playerBatting(boolean isFirst) throws InterruptedException {
        while (true) {
            int playerRun = validator("Enter your run (1-10): ");
            int botBall = (int) (Math.random() * 10) + 1;
            System.out.println("Bot bowled: " + botBall);
            Thread.sleep(1000);

            if (playerRun == botBall) {
                System.out.println("You're OUT!");
                break;
            } else {
                playerScore += playerRun;
                totalPlayerBalls++;
                System.out.println("Your score: " + playerScore + "/" + totalPlayerBalls + " balls");
            }

            if (!isFirst && playerScore > botScore) {
                System.out.println("✅ You've chased the target!");
                break;
            }

            Thread.sleep(1000);
        }
    }

    void botBatting(boolean isFirstInnings) throws InterruptedException {
        while (true) {
            int playerBall = validator("Enter your ball (1-10): ");
            int botRun = (int) (Math.random() * 10) + 1;
            System.out.println("Bot played: " + botRun);
            Thread.sleep(1000);

            if (botRun == playerBall) {
                System.out.println("Bot is OUT!");
                break;
            } else {
                botScore += botRun;
                totalBotBalls++;
                System.out.println("Bot score: " + botScore + "/" + totalBotBalls + " balls");
            }

            if (!isFirstInnings && botScore > playerScore) {
                System.out.println("🤖 Bot has chased the target!");
                break;
            }

            Thread.sleep(1000);
        }
    }

    void declareResult() throws InterruptedException {
        System.out.println("\n--- 🏁 MATCH OVER ---");
        Thread.sleep(1000);
        System.out.println("Your final score: " + playerScore);
        System.out.println("Bot's final score: " + botScore);
        Thread.sleep(1000);

        if (playerScore > botScore) {
            System.out.println("🎉 Congratulations! You Win!");
        } else if (botScore > playerScore) {
            System.out.println("😞 Bot Wins! Better luck next time.");
        } else {
            System.out.println("🤝 It's a Tie!");
        }
    }

    int validator(String st) {
        int num;
        while (true) {
            System.out.println(st);
            num = sc.nextInt();
            if (num >= 1 && num <= 10)
                break;
            System.out.println("Please enter a number between 1 and 10.");
        }
        return num;
    }
}
