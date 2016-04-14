/**
 * Advanced Object Oriented Programing Black Jack
 *
 * @author Erick Garcia / egarcia87@miners.utep.edu
 */
package blackjack;

public class Dealer extends User {
    //Setting a boolean true for a User that is going to be a dealer in constructor
    public Dealer(String dealerName, boolean isHuman, int money) {
        this.name = dealerName;
        this.setMoney(money);
        isDealer = true;
        human = isHuman;
    }
    //Dealer give card to player
    public void giveCard(Player p, Deck deck) {
        p.getCard(deck.deal());

    }

}
