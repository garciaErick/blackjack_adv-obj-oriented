/**
 * Advanced Object Oriented Programing Black Jack
 *
 * @author Erick Garcia / egarcia87@miners.utep.edu
 */
package blackjack;

import java.util.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class BlackJack {

    //Rules to display if the user asks for them
    private String rules = "\nSystem rules\n"
            + "================================================================================================\n"
            + "1.The system shall support only two players.\n"
            + "2.The system shall provide a mechanism to input player Names.\n"
            + "3.The system shall provide a mechanism to designate a player as the “Dealer”.\n"
            + "4.The system shall designate the other player as the “Player”.\n"
            + "5.The system shall alternate the “Dealer” designation between the players after each game.\n"
            + "6.The system interface shall accept input via the command line for: Name of players, \n"
            + "Dealer or Player designation, or to quit.\n"
            + "7.The system shall display cards in the command line as “1-10, J, Q, K, A” for each player.\n"
            + "8.The system shall declare a winner when:\n"
            + " \t•Player gets 21 points on the player's first two cards (called a blackjack), without \n"
            + "\tthe Dealer getting blackjack;\n"
            + " \t•Player reaches a final score higher than the Dealer without exceeding 21; or\n"
            + " \t•Dealer draws additional cards until his or her hand exceeds 21.\n"
            + "9.The system shall not display the Dealer’s hand until the Player is done receiving cards.\n"
            + "10.The system shall use a standard 52 card deck (no Jokers).\n"
            + "11.The system shall shuffle and reset the 52 card deck after every game.\n"
            + "12.The system shall give each player cards randomly from the deck.\n"
            + "================================================================================================\n";
    //The game will contain 2 players a Dealer and a Player, a user will play against the machine
    private Dealer dealer;
    private Player player;
    private Deck deck;
    private int wasPlayerFirst = 3; // Variable used for alternating roles at the end

    public BlackJack(String playerName, String dealerName, boolean isPlayer, boolean isDealer, int playerMoney, int dealerMoney) {
        if (isPlayer) {
            player = new Player(playerName, true, playerMoney);
            dealer = new Dealer(dealerName, false, dealerMoney);
        } else if (isDealer) {
            player = new Player(playerName, false, playerMoney);
            dealer = new Dealer(dealerName, true, dealerMoney);
        }
        deck = new Deck();
        deck.shuffle();
    }
    
    public void newGame() {
        getDealer().getCard(deck.deal());
        getDealer().getCard(deck.deal());
        getPlayer().getCard(deck.deal());
        getPlayer().getCard(deck.deal());
    }

    //This method will shuffle and reset the 52 card deck after every game
    public void resetGame(Deck d) {
        for (Card c : getPlayer().getHand()) {
            d.returnCard(c);
        }
        for (Card c : getDealer().getHand()) {
            d.returnCard(c);
        }
        getPlayer().removeCards(getPlayer().getHand());
        getDealer().removeCards(getDealer().getHand());
        d.shuffle();
    }

    //This method will force the program to terminate
    private int quitGame() {
        Scanner input = new Scanner(System.in);
        System.out.println("Are you sure you wanna quit? (y) or (n)");
        String confirm = input.next();
        if (confirm.equals("y") || confirm.equals("Y") || confirm.equals("Yes") || confirm.equals("yes") || confirm.equals("YES")) {
            try {
                System.out.println();
                System.out.print("Goodbye");
                for (int i = 0; i < 3; i++) {
                    System.out.print("~");
                    Thread.sleep(800L);
                }
                System.out.print("!");
                System.out.println("");
                System.exit(0);
            } catch (Exception e) {
            }
            
        }
        System.out.println("Resuming Game and alternating roles of players");
        
        try {
            for (int i = 0; i < 3; i++) {
                System.out.print(".");
                Thread.sleep(800L);
            }
            System.out.println("");
        } catch (Exception e) {
        }
        
        return 0;
    }
    
    public void dealerAi() {
        
        if (player.getScore() <= 21) {
            while (player.getScore() > dealer.getScore()) {
                dealer.getCard(deck.deal());
            }
        }
    }
    
    public void playerAI() {
        while (player.getScore() <= 15) {
            player.getCard(deck.deal());
        }
    }
    
    public String rulesP() {
        int finalScoreP = player.getScore();
        int finalScoreD = dealer.getScore();
        String outcome = "";
        //All of the possible cases
        if (finalScoreD > finalScoreP && finalScoreD <= 21) {
            outcome = "Player has lost, Dealer has more \n points :( ";
            dealer.setIsWinner(true);
        } else if (finalScoreP > finalScoreD && finalScoreP <= 21) {
            outcome = "Congratulations Player you've won!!!";
            player.setIsWinner(true);
        } else if (finalScoreP == finalScoreD) {
            outcome = "It's a Draw";
        } else if (finalScoreD <= 21 && finalScoreP > 21) {
            outcome = "Player has exceeded 21 points, \n\n Dealer Wins :(";
            dealer.setIsWinner(true);
        } else if (finalScoreP <= 21 && finalScoreD > 21) {
            outcome = "Dealer  has exceeded 21 points,\n Player Wins!!!";
            player.setIsWinner(true);
        } else {
            outcome = "You have both exceeded 21 points";
        }
        return outcome;
        
    }
    
    public String rulesD() {
        int finalScoreP = player.getScore();
        int finalScoreD = dealer.getScore();
        String outcome = "";
        
        if (finalScoreP > finalScoreD && finalScoreP
                <= 21) {
            outcome = "Player has won,he has more \n points than the Dealer :( ";
            player.setIsWinner(true);
        } else if (finalScoreD > finalScoreP && finalScoreD
                <= 21) {
            outcome = "Congratulations Dealer you've won!!!";
            dealer.setIsWinner(true);
        } else if (finalScoreD == finalScoreP) {
            outcome = "It's a Draw";
        } else if (finalScoreP <= 21 && finalScoreD
                > 21) {
            outcome = "Dealer has exceeded 21 points, \n\nHe has lost :(";
            player.setIsWinner(true);
        } else if (finalScoreD <= 21 && finalScoreP
                > 21) {
            outcome = "Player has exceeded 21 points,\n Dealer wins!!!";
            dealer.setIsWinner(true);
        } else {
            outcome = "You have both exceeded 21 points";
        }
        return outcome;
        
    }

    /**
     * @return the rules
     */
    public String getRules() {
        return rules;
    }

    /**
     * @return the dealer
     */
    public Dealer getDealer() {
        return dealer;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return the deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * @return the wasPlayerFirst
     */
    public int getWasPlayerFirst() {
        return wasPlayerFirst;
    }
    
}
