import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Slotomainia extends Game {

    double tempCurrency = 100;
    double betAmt;
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();
    String input;
    int[] slot = { 0, 0, 0 };
    String continuePlaying;

    String[] symbols = { "WILD", "SCATTER", "SPADES", "DIAMONDS", "CLOVERS", "HEARTS" };

    @Override
    public void run() {

        System.out.println("Welcome to Slotomania! Let\'s go over the rules:\n" +
                "\n" +
                "WAYS TO WIN: \n" +
                "    - The player will enter an amount of money they would like to bet\n" +
                "\n" +
                "    - 3 matching symbols return a JACKPOT (double the amount of the initial bet)\n" +
                " \n" +
                "    - A WILD symbol can be a placeholder for any symbol (EX: HEART, WILD, HEART will be a jackpot)\n"
                +
                "\n" +
                "    - If at least one SCATTER card is in a slot, 1/3rd of the money betted is returned. SCATTERS ARE NOT STACKABLE\n"
                +
                "\n" +
                "WAYS TO LOSE\n" +
                "    - Not getting any of these will result in a LOSS of whatever the player bets");

        mainLoop();
    }

    public void mainLoop() {

        while (true) {
            Arrays.setAll(slot, i -> slot[i] = rand.nextInt(5));
            if (tempCurrency == 0) {
                System.out.println(
                        "\nIt seems you are out of money. Don't worry! Since this is a virtual game, we'll give you a free $100!");
                tempCurrency += 100;
            } else {
                System.out.println("\nCurrent Currency: $" + String.valueOf(tempCurrency));
            }

            System.out.print("\nHow much Money would you like to bet (or type 'q' to quit)? ");
            input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Understandable. Have a great day!");
                break;
            }

            try {
                betAmt = Double.parseDouble(input);

                if (betAmt > tempCurrency || betAmt < 0.01) {
                    throw new RuntimeException();
                }
            } catch (NumberFormatException e) {
                System.out.println("\nPlease be sure to type a valid input.");
                continue;
            } catch (RuntimeException e) {
                System.out.println(
                        "\nThis amount is either more money than you have or is under a cent. Please type a valid amount of money.\n");
                continue;
            }

            System.out
                    .println("\nResults:\n| " + symbols[slot[0]] + " | "
                            + symbols[slot[1]]
                            + " | " + symbols[slot[2]]);

            if ((slot[0] == slot[1] && slot[1] == slot[2])
                    || (checkDuplicate(slot) && countInstances(slot, 0) == 1)) {
                System.out.println("\nJACKPOT!!! x4 The reward payout");
                tempCurrency += betAmt * 4;
            } else if (slot[0] == 1 || slot[1] == 1 || slot[2] == 1) {
                System.out.println("SCATTER! You will recieve all the money you betted!");
                tempCurrency += betAmt;
            } else {
                System.out.println("You lost :( . Since no combo was hit, you lose out on the money you wagered)");
                tempCurrency -= betAmt;
            }

            System.out
                    .print("\nWould you like to continue playing (type \'y\' to continue, or press any key to quit)? ");

            continuePlaying = sc.nextLine().trim();

            if (continuePlaying.equalsIgnoreCase("y")) {
                continue;
            } else {
                System.out.println("Understandable. Have a great day!");
                break;
            }
        }

    }

    public boolean checkDuplicate(int[] arr) {
        HashSet<Integer> seen = new HashSet<>();
        boolean hasDuplicate = false;

        for (int num : arr) {
            if (!seen.add(num)) {
                hasDuplicate = true;
                break;
            }
        }
        return hasDuplicate;
    }

    public int countInstances(int[] arr, int element) {
        int instances = 0;
        for (int i : arr) {
            if (i == element) {
                instances++;
            }
        }

        return instances;
    }
}
