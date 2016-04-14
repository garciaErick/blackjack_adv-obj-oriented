/**
 * Advanced Object Oriented Programing Black Jack
 *
 * @author Erick Garcia / egarcia87@miners.utep.edu
 */
package blackjack;

import java.util.*;

public class User {

    public ArrayList<Card> hand;
    public boolean isDealer;
    public String name;
    private int score;
    public boolean human;
    //Creating a User with a hand
    public User() {

        hand = new ArrayList<Card>();
    }

    //This method will add a Card object to the ArrayList Hand
    public void getCard(Card newCard) {
        hand.add(newCard);
    }

    //This method will remove a card from the hand 
    public void removeCards(ArrayList<Card> hand) {
        hand.removeAll(hand);
    }

    //This method will print the ArrayList Hand
    public void showCards() {
        System.out.println(hand);
    }

    /* This method will compute the score for a game of Black Jack, initially taking the value of an Ace card as 11
     * but if the user goes over 21 points then an ace value will be taken as 1 and the process can continue until
     * the user has run out of Aces.
     */
    public int getScore() {
        int i = 0;//Score Counter
        int score = 0;
        boolean aceOfIndexes[] = new boolean[21];//Keep track of Aces
        for (Card C : hand) {
            if (hand.get(i).getValueName().equals("Ace")) {
                aceOfIndexes[i] = true;
                score += 11;
                i++;
            } else if (hand.get(i).getValueName().equals("Jack") || hand.get(i).getValueName().equals("Queen") || hand.get(i).getValueName().equals("King")) {
                score += 10;
                i++;
            } else {
                score += (hand.get(i).getValue() + 1);
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
}
