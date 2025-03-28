import java.util.Scanner;

public class Slotomainia implements Game {
    boolean running = true;
    double tempCurrency = 100;
    double betAmt;
    Scanner sc = new Scanner(System.in);
    String input;

    public void run() {
        System.out.println("Welcome to Slotomania! Let\\'s go over the rules:\n" +
                "\n" +
                "WAYS TO WIN: \n" +
                "    - The player will enter an amount of money they would like to bet\n" +
                "\n" +
                "    - 3 matching symbols return a JACKPOT (double the amount of the initial bet)\n" +
                " \n" +
                "    - A \'WILD\' symbol can be a placeholder for any symbol (EX: HEART, WILD, HEART will be a jackpot)\n"
                +
                "\n" +
                "    - If at least one \'SCATTER\' card is in a slot, 1/3rd of the money betted is returned\n" +
                "\n" +
                "WAYS TO LOSE\n" +
                "    - Not getting any of these will result in a LOSS of whatever the player bets");

        mainLoop();
    }

    public void mainLoop() {

        System.out.print("\nCurrent Currency: $" + String.valueOf(tempCurrency)
                + "\nHow much Money would you like to bet (or type 'q' to quit)? ");
        input = sc.nextLine().trim();
        if (input.equalsIgnoreCase("q")) {
            System.out.println("Understandable. Have a great day!");
            return;
        }

        try {
            betAmt = Double.parseDouble(input);

            if (betAmt > tempCurrency) {
                throw new RuntimeException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please be sure to type a valid input.\n");
            mainLoop();
        } catch (RuntimeException e) {
            System.out.println("This amount is out of your betting range.\n");
            mainLoop();
        }
    }
}
