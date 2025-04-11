//Feel free to add features you think you'll need or send a text on the group and I'll add it

// Some parts of the code have been commented out cause I was too scared to delete them. I didn't want to break my program by accident


import java.util.Scanner;
public class PublicTest {
     // Everyone player starts with $100
    public double balance = 100;
    public double deposit = 0;
     // games played affect the balance a sample class will be made

    public double getBalance(){
        return balance;
    }
    public double getDeposit(){
        return deposit;
    }
    public void setDeposit(double newDeposit){
        this.deposit = newDeposit;
    }
    public void setBalance(double newBalance){
        this.balance = newBalance;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PublicTest bal = new PublicTest(); //declare main class


        System.out.println("Enter 1. To play Coin Flip");
        System.out.print(">> ");
        char game = sc.next().charAt(0);

        System.out.println("How much would you like to deposit it into this game");
        System.out.print(">> ");
        double deposit = sc.nextDouble();
        sc.nextLine();
        bal.setDeposit(deposit);
        

        if (game == '1') {
            System.out.print("Enter 'H' for heads or 'T' for tails >> ");
            char choice = sc.next().charAt(0);

            CoinFlip coin = new CoinFlip(choice);
            // coin.accessVariables(bal);
            coin.flipCoin(bal);
        }
        else{
            System.out.println("Invalid game selection");
        }


        sc.close();
    }
}
