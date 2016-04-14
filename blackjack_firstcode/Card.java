/**
 * Advanced Object Oriented Programing Black Jack
 *
 * @author Erick Garcia / egarcia87@miners.utep.edu
 */
package blackjack;

public class Card {
    //The Actual values
    private int suit;
    private int value;
    
    //The name spaces for the values
    String[] suitname = {"Hearts", "Diamonds", "Spades", "Clubs"};
    String[] valuename = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    
    //Constructor
    public Card(int suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    //Getters
    public int getValue() {
        return value;
    }

    public int getSuit() {
        return suit;
    }

    public String getSuitName() {
        return suitname[getSuit()];
    }

    public String getValueName() {
        return valuename[getValue()];
    }
    //Card To STring
    public String toString() {
        return getSuitName() + " " + getValueName();
    }
}
