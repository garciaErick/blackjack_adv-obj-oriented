/**
 * Advanced Object Oriented Programing Black Jack
 *
 * @author Erick Garcia / egarcia87@miners.utep.edu
 */
package blackjack;

import java.util.*;

public class Deck {

    ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<Card>(52);
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 12; j++) {
                deck.add(new Card(i, j));
            }
        }
    }

    //This method will shuffle all the cards in the deck
    public void shuffle() {
        Collections.shuffle(deck);
    }
//Previous method without colections   public void shuffle(){
//    for(int i=0;i<deck.size();i++){
//      int random=(int)(Math.random()*((deck.size()-i)+1));
//      Card temp= deck.get(i);
//      deck.set(i, deck.get(random));
//      deck.set(random,temp);
//    }
//  }

    //This method will remove a card from the top of the deck and return it
    public Card deal() {
        Card temp = deck.get(0);
        deck.remove(0);
        return temp;
    }

    //This method will return a card to the deck
    public void returnCard(Card c) {
        deck.add(c);
    }

    //This method will print all the cards in the Deck
    public void printCards() {
        for (int i = 0; i < deck.size(); i++) {
            System.out.println("Card Number " + (i + 1) + ": " + getCards(i));
        }
    }

    //This method will return a Card from a certain index
    public Card getCards(int i) {
        return deck.get(i);
    }
}
