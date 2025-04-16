import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BlackJack extends Game{
    
    //class for cards
    private class Card{
        String value;
        String type;

        public Card(String value, String type){
            this.value = value;
            this.type = type;
        }
        public String toString(){
            return value + "-"+ type;
        }
        //get card values- Ace or >=10 or int values
        public int getValue(){
            if("AJQK".contains(value)){
               if (value == "A"){
                    return 11;
               }
               return 10; 
            }
            //2-10 from string
            return Integer.parseInt(value);
        }
        public boolean isAce(){
            return value == "A";
        }
    }
    //casino
    double tempMoney = 100;
    double bet;
    //store deck & shuffle
    ArrayList<Card> deck;
    Random random = new Random();

    //dealer
    Card hiddenCard;
    ArrayList<Card> dealerHand;
    int dealerSum;
    int dealerISum;
    int dealerAceCount;

    //Player
    ArrayList<Card> playerHand;
    int playerSum;
    int playerAceCount;

    //user
    Scanner scan = new Scanner(System.in);
    String input;

    //game
    String message = "";


    }
    @Override
    public void run(){
        System.out.println("""
                        -----------------------------
                            Welcome to Black Jack!
                        -----------------------------
                                    Rules: 
                        1. Choose an amount to bet 
                           with the dealer 
                        2. 2 cards of value 1-11 will
                           given to the player and
                           dealer
                        3. The winner is determined by
                           who ever is closer to or at 
                           the total value 21. Any value 
                           over is an automatic loss
                        4. Player and dealer have the 
                           option to hit(Recieve another
                           card) after the initial 2 
                           cards or stay(keep their
                           current hand)
                        5. Aces can have a value of 1
                           or 11 depending on which is
                           needed for use
                        6. One of the dealer's first two
                           cards will be hidden and the 
                           dealer will onl be able to
                           hit or stay after the player
                        7. Ties occur when the dealer and
                           player has the same value 
                           meaning no loss or gain in
                           currency for the player
                            """);
        loop();
        /*  Checks
        System.out.println("Dealer:");
        System.out.println(hiddenCard);
        System.out.println(dealerHand);
        System.out.println(dealerSum);
        System.out.println(dealerAceCount);
        */
        
        /* 
        System.out.println("Player:");
        System.out.println(playerHand);
        System.out.println(playerSum);
        System.out.println(playerAceCount);
        */
    }
    //Same as kelvins loop
    public void loop(){
        while(true){
            if (tempMoney == 0) {
                System.out.println("\nIt seems you are out of money. Don't worry! Since this is a virtual game, we'll give you a free $100!");
                tempMoney += 100;
            } 
            else {
                System.out.println("\nCurrent Currency: $" + String.valueOf(tempMoney));
            }
            System.out.print("\nHow much Money would you like to bet (or type 'q' to quit)? ");
            input = scan.nextLine().trim();
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Understandable. Have a great day!");
                break;
            }
            try {
                bet = Double.parseDouble(input);
                if(bet > tempMoney || .01 > bet){
                    throw new RuntimeException();
                }
                
            } catch (RuntimeException e) {
                System.out.println("\nThis amount is either more money than you have or is under a $0.01. Please type a valid amount of money.\n");
                continue;
            }
            //deck
            buildDeck();
            shuffleDeck();
            //dealer
            dealerHand = new ArrayList<>();
            dealerSum = 0;
            dealerAceCount = 0;
            dealerISum = 0;
            //take last element of deck
            hiddenCard = deck.remove(deck.size()-1);
            dealerSum += hiddenCard.getValue();
            // if else operator- there is an ace apply value else 0
            dealerAceCount += hiddenCard.isAce()?1:0;
            Card card = deck.remove(deck.size()-1);
            dealerSum += card.getValue();
            dealerISum += card.getValue();
            // same if else
            dealerAceCount += card.isAce()?1:0;
            dealerHand.add(card);
            

            //do same for player
            playerHand = new ArrayList<>();
            playerSum = 0;
            playerAceCount = 0;
            //give initial cards
            for (int i =0; i<2; i++){
            card = deck.remove(deck.size()-1);
            playerHand.add(card);
            playerSum += card.getValue();
            // if else operator- there is an ace apply value else 0
            playerAceCount += card.isAce()?1:0;
            }
            reduceDealerAce();
            reducePlayerAce();
            System.out.println("\n----------------------\nCurrent bet is: $"+bet);
            System.out.println("\n--Player--");
            System.out.println("Your cards: "+playerHand);
            System.out.println("Your sum: "+playerSum);

            System.out.println("\n--Dealer--");
            System.out.println("Dealer's Cards: [Hidden Card], "+ dealerHand);
            System.out.println("Dealer visible sum: "+ dealerISum);



            while (playerSum <=21){
                System.out.println("\nWould you like to hit or stay (Type h or s): ");
                input = scan.nextLine().trim();
                if (input.equals("h")){
                    card = deck.remove(deck.size()-1);
                    playerHand.add(card);
                    playerSum += card.getValue();
                    playerAceCount += card.isAce()?1:0;
                    reducePlayerAce();
                    System.out.println("\nYour new hand: "+playerHand);
                    System.out.println("Your sum: "+playerSum);
                    if(playerSum > 21){
                        message = "BUST- Sum greater than 21\n--You lose!--";
                        tempMoney -= bet;
                        break;
                    }
                    continue;
                }
                else if(input.equals("s")){
                    dealerHand.add(0,hiddenCard);
                    System.out.println("\nDealer's Cards: "+ dealerHand);
                    System.out.println("Dealer sum: "+ dealerSum);
                    if(playerSum < dealerSum){  
                        message = "You lose!";
                        tempMoney -= bet;
                    }
                    while(dealerSum < playerSum){
                        System.out.println("\nDealer hit!\n");
                        card = deck.remove(deck.size()-1);
                        dealerHand.add(card);
                        dealerSum += card.getValue();
                        dealerAceCount += card.isAce()?1:0;
                        reduceDealerAce();
                        System.out.println("Dealer's new hand: "+ dealerHand);
                        System.out.println("Dealer sum: "+ dealerSum);
                        if(dealerSum > 21){
                            message = "Dealer BUST- Sum greater than 21\n--You win!--";
                            tempMoney += bet;
                            break;
                        }
                    }
                }
                if(playerSum == dealerSum){
                    message = "Tie";
                }
                else if(playerSum > dealerSum){
                    message = "You win!";
                    tempMoney += bet;
                }
                break;
            }

            System.out.println(message);
        }
    }
    //deck of cards
    public void buildDeck(){
        deck = new ArrayList<>();
        String[] values = {"A", "2", "3", "4","5","6","7","8","10","J","Q","K"};
        String[] types = {"C","D","H","S"};
        for(int i=0; i < types.length; i++){
            for(int j = 0; j<values.length; j++){
               Card card = new Card(values[j], types[i]);
               deck.add(card);
            }
        }
        /* 
        System.out.println("Build Deck: ");
        System.out.println(deck);
        */
    }
    //shuffle deck
    public void shuffleDeck(){
        for(int i=0; i < deck.size(); i++ ){
            int j = random.nextInt(deck.size());
            Card currCard = deck.get(i);
            Card randomCard = deck.get(j);
            deck.set(i, randomCard);
            deck.set(j, currCard);
        }
        /* 
        System.out.println("shuffled deck: ");
        System.out.println(deck);
        */
    }
    //Ace can be 11 or 1 these methods ensure the right one is used
    public int reducePlayerAce(){
        while (playerSum > 21 && playerAceCount > 0){
            playerSum -=10;
            playerAceCount -=1;
        }
        return playerSum;
    }
    public int reduceDealerAce(){
        while (dealerSum > 21 && dealerAceCount > 0){
            dealerSum -=10;
            dealerAceCount -=1;
        }
        return dealerSum;
    }


}

