/**
 * Advanced Object Oriented Programing Black Jack
 *
 * @author Erick Garcia / egarcia87@miners.utep.edu
 */
package blackjack;

import java.util.*;

public class User {

    ArrayList<Card> hand;
    boolean isDealer;
    String name;
    int score;
    boolean human;
    private boolean isWinner;
    private int money;

    //Creating a User with a hand

    public User() {
        isWinner = false;
        hand = new ArrayList<Card>();
    }

    //This method will add a Card object to the ArrayList Hand
    public void getCard(Card newCard) {
        getHand().add(newCard);
    }

    //This method will remove a card from the hand 
    public void removeCards(ArrayList<Card> hand) {
        hand.removeAll(hand);
    }

    //This method will print the ArrayList Hand
    public void showCards() {
        System.out.println(getHand());
    }

    /* This method will compute the score for a game of Black Jack, initially taking the value of an Ace card as 11
     * but if the user goes over 21 points then an ace value will be taken as 1 and the process can continue until
     * the user has run out of Aces.
     */
    public int getScore() {
        int i = 0;//Score Counter
        int score = 0;
        boolean aceOfIndexes[] = new boolean[21];//Keep track of Aces
        for (Card C : getHand()) {
            if (getHand().get(i).getValueName().equals("Ace")) {
                aceOfIndexes[i] = true;
                score += 11;
                i++;
            } else if (getHand().get(i).getValueName().equals("Jack") || getHand().get(i).getValueName().equals("Queen") || getHand().get(i).getValueName().equals("King")) {
                score += 10;
                i++;
            } else {
                score += (getHand().get(i).getValue() + 1);
                i++;
            }

        }
        if (score > 21) {
            for (i = 0; i < aceOfIndexes.length; i++) {
                if (aceOfIndexes[i] == true) {
                    score -= 10;
                }
            }
        }
        return score;
    }

    /**
     * @return the hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }
    public String getLastCard(){
        return hand.get(hand.size()-1).toString();
    }
    public int getLastIndex(){
        return hand.size()-1;
    }
    /**
     * @return the isDealer
     */
    public boolean isIsDealer() {
        return isDealer;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the human
     */
    public boolean isHuman() {
        return human;
    }

    public boolean hasLost() {
        if (this.getScore() <= 21) {
            return false;
        }
        else
            return true;
    }

    /**
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * @param isWinner the isWinner to set
     */
    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    /**
     * @return the isWinner
     */
    public boolean isWinner() {
        return isWinner;
    }
    
}
