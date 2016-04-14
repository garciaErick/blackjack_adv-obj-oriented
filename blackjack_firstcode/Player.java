/**
 * Advanced Object Oriented Programing Black Jack
 *
 * @author Erick Garcia / egarcia87@miners.utep.edu
 */
package blackjack;

public class Player extends User {

    boolean stay = false;
    //Defining that this User is a player and not a Dealer
    public Player(String playerName, boolean isHuman) {
        this.name = playerName;
        isDealer = false;
        human = isHuman;
    }
    //Do not ask Dealer for more cards
    public void stay() {
        stay = true;
    }
    //Ask the dealer for one card more
    public void hitMe(Dealer player2, Deck deck) {
        player2.giveCard(this, deck);
    }

}
