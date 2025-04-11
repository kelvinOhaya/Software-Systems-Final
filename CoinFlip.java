import java.util.Random;

public class CoinFlip {
    Random rand = new Random();
    boolean isHeads;
    char choice;
    // map parameters

    public CoinFlip(char choice) {
        // map parameters
        this.choice = choice;
    }

    // public void accessVariables(PublicTest bal){
    //     double balance = bal.getBalance();
    //     double deposit = bal.getDeposit();
    //     // access variables
    // }

    public void flipCoin(PublicTest bal) {
        boolean coin = rand.nextBoolean(); // Coin flip
        boolean heads = true;
        boolean tails = false;
        if (coin == true){
            System.out.println("The result of the coin flip was..........");
            System.out.println("//////////////////");
            System.out.println("//////HEADS//////");
            System.out.println("//////////////////");
        }
        if (coin == false){
            System.out.println("The result of the coin flip was..........");
            System.out.println("//////////////////");
            System.out.println("//////TAILS//////");
            System.out.println("//////////////////");
        }
        // PublicTest bal = new PublicTest(); // accessing variables
        double deposit = bal.getDeposit(); // accessing deposit value
        double balance = bal.getBalance(); // accessing balance value
        double newBalance;
        double newDeposit;
        if (choice == 'H' || choice == 'h') {
            if (heads == coin) {
                System.out.println("Yayyyy!!! you won");
                newBalance = balance - deposit;
                newDeposit = deposit*2; //change value of deposit
                // bal.setDeposit(deposit); // Use setter to confirm changed value

                newBalance += newDeposit;
                balance = newBalance;
                bal.setBalance(balance);
            }
            else {
                System.out.println("Sorry bro, you lost");
                newBalance = balance - deposit;
                newDeposit = 0;
                balance = newBalance;
                bal.setBalance(balance);
            
            }
        }
        else if (choice == 'T' || choice == 't'){
            if (tails == coin){
                System.out.println("Yayyyy!!! you won");
                newBalance = balance - deposit;
                newDeposit = deposit*2; //change value of deposit
                // bal.setDeposit(deposit); // Use setter to confirm changed value

                newBalance += newDeposit;
                balance = newBalance;
                bal.setBalance(balance);
            }
            else {
                System.out.println("Sorry bro, you lost");
                newBalance = balance - deposit;
                newDeposit = 0;
                balance = newBalance;
                bal.setBalance(balance);

            }
        }
        System.out.println("New balance = " + bal.getBalance() );
    }
}
