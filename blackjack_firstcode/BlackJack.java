/**
 * Advanced Object Oriented Programing Black Jack
 *
 * @author Erick Garcia / egarcia87@miners.utep.edu
 */
package blackjack;

import java.util.*;

public class BlackJack {
    //Rules to display if the user asks for them
    String rules = "\nSystem rules\n"
            + "=================================================================================================================================\n"
            + "1.The system shall support only two players.\n"
            + "2.The system shall provide a mechanism to input player Names.\n"
            + "3.The system shall provide a mechanism to designate a player as the “Dealer”.\n"
            + "4.The system shall designate the other player as the “Player”.\n"
            + "5.The system shall alternate the “Dealer” designation between the players after each game.\n"
            + "6.The system interface shall accept input via the command line for: Name of players, Dealer or Player designation, or to quit.\n"
            + "7.The system shall display cards in the command line as “1-10, J, Q, K, A” for each player.\n"
            + "8.The system shall declare a winner when:\n"
            + " \t•Player gets 21 points on the player's first two cards (called a blackjack), without the Dealer getting blackjack;\n"
            + " \t•Player reaches a final score higher than the Dealer without exceeding 21; or\n"
            + " \t•Dealer draws additional cards until his or her hand exceeds 21.\n"
            + "9.The system shall not display the Dealer’s hand until the Player is done receiving cards.\n"
            + "10.The system shall use a standard 52 card deck (no Jokers).\n"
            + "11.The system shall shuffle and reset the 52 card deck after every game.\n"
            + "12.The system shall give each player cards randomly from the deck.\n"
            + "=================================================================================================================================\n";
    //The game will contain 2 players a Dealer and a Player, a user will play against the machine
    Dealer dealer;
    Player player;
    Deck deck;
    int wasPlayerFirst=3; // Variable used for alternating roles at the end

    public BlackJack() {

    }

    public void newGame() {

        //Asking the User his name, and if he wants to be a player or a dealer
        Scanner input = new Scanner(System.in);
        System.out.println("Hello, please insert your name: ");
        String pName = input.nextLine();
        System.out.println("Hello, please insert the name of your opponent: ");
        String OpName = input.nextLine();
        System.out.println("Do you want to be the dealer? (d) or a Player?(p)");
        String decision = input.next();

        //User wants to be a player
        if (decision.equals("P") || decision.equals("p")) {
            System.out.println("Assigning you as a normal player, you will be playing against\n the dealer " + OpName + "\n");
            dealer = new Dealer(OpName, false);
            player = new Player(pName, true);

        } //User wants to be a Dealer.
        else if (decision.equals("D") || decision.equals("d")) {
            System.out.println("Assigning you as the dealer, you will be playing against the player " + OpName + "\n");
            dealer = new Dealer(pName, true);
            player = new Player(OpName, false);
            System.out.println("");
        } //Else if user does not enter a valid input
        else {
            System.out.println("please enter valid input");
        }
        //Start second game from here
        while (true) {
            if (wasPlayerFirst == 1) {
                dealer = new Dealer(pName, true);
                player = new Player(OpName, false);
            } else if (wasPlayerFirst == 0) {
                dealer = new Dealer(OpName, false);
                player = new Player(pName, true);
            }

            //createDeck();
            Deck deck = new Deck();
            deck.shuffle();

            //Dealing initial cards for player and Dealer
            dealer.getCard(deck.deal());
            dealer.getCard(deck.deal());
            player.getCard(deck.deal());
            player.getCard(deck.deal());
            if(player.getScore() == 21){
                System.out.println("BLACK JACK!!! Player " + pName + " has won!!!");
            }
            //If the User choose to be a Player
            if (player.human == true) {
                wasPlayerFirst = 1;

                System.out.println(player.name + "'s(Player) turn:");
                player.showCards();
                System.out.println("Score: " + player.getScore() + "\n");
                //While player's decision is not to stay the loop will continue
                while (player.stay != true && player.getScore() <= 21) {
                    //Options to choose from "Hit me | Stay | Quit"
                    System.out.println("What you want to do:");
                    System.out.println("Hit me | Stay | Quit ('Alternate Players' (q))| Check Rules (r)");
                    decision = input.next();
                    if (decision.equals("Hit me") || decision.equals("hit me") || decision.equals("HIT ME") || decision.equals("hitme") || decision.equals("hitme") || decision.equals("h")) {
                        player.hitMe(dealer, deck);
                        player.showCards();
                        System.out.println("Score: " + player.getScore() + "\n");
                    } else if (decision.equals("Stay") || decision.equals("stay") || decision.equals("STAY") || decision.equals("s")) {
                        player.stay();
                    } else if (decision.equals("Quit") || decision.equals("quit") || decision.equals("QUIT") || decision.equals("q") || decision.equals("Q")) {
                        quitGame();
                    } else if (decision.equals("r")) {
                        System.out.println(rules);
                    } //If user didnt enter a valid input
                    else {
                        System.out.println("Please enter a valid input :)");
                    }

                }
                //Ending users turn, now its time for the Dealer's turn (Computer)
                System.out.println(player.name + "'s turn has ended:\n");
                System.out.println(dealer.name + "'s(Dealer) turn:");
                dealer.showCards();
                System.out.println("Score: " + dealer.getScore());

                //The dealer won't draw anymore cards because you have lost
                if (player.getScore() <= 21) {
                    while (player.getScore() > dealer.getScore()) {
                        dealer.getCard(deck.deal());
                        dealer.showCards();
                        System.out.println("Score: " + dealer.getScore() + "\n");
                    }
                }
                //Calculating both Scores to see who won.
                int finalScoreP = player.getScore();
                int finalScoreD = dealer.getScore();

                //All possible cases
                System.out.println("Final Scores:\n" + player.name + " " + finalScoreP + "\n" + dealer.name + " " + finalScoreD + "\n");
                if (finalScoreP > finalScoreD && finalScoreP <= 21) {
                    System.out.println("Congratulations you've won!!!\n");
                } else if (finalScoreD > finalScoreP && finalScoreD <= 21) {
                    System.out.println("You lost, your opponent has more points than you :(\n");
                } else if (finalScoreP == finalScoreD) {
                    System.out.println("It's a Draw");
                } else if (finalScoreP <= 21 && finalScoreD > 21) {
                    System.out.println("Your Opponent has exceeded 21 points, You win\n");
                } else if (finalScoreD <= 21 && finalScoreP > 21) {
                    System.out.println("Your have exceeded 21 points, You loose :(\n");
                } else {
                    System.out.println("You have both exceeded 21 points\n");
                }

//            dealerAI();
            }//Else if User has chosen to be a dealer
            else {
                wasPlayerFirst = 0;
                System.out.println(player.name + "'s(Player) turn:");
                player.showCards();
                System.out.println("Score: " + player.getScore() + "\n");

                while (player.getScore() <= 15) {
                    System.out.println(player.name + "'s(Player) turn:");
                    player.getCard(deck.deal());
                    player.showCards();
                    System.out.println("Score: " + player.getScore() + "\n");
                }
                System.out.println("Player " + player.name + " decided to stay\n");
                player.stay = false;

//        playerAI();
                System.out.println(player.name + "'s turn has ended, its your turn\n");
                System.out.print("Your hand: \n");
                dealer.showCards();
                System.out.println(dealer.getScore() + "\n");
                if (player.getScore() <= 21) {
                    while (player.getScore() >= dealer.getScore()) {

                        System.out.println(dealer.name + "'s(Dealer) turn:");
                        System.out.println("What you want to do:");
                        System.out.println("Get Card | Stay | Quit ('Alternate Players' (q))| Check Rules (r)");
                        decision = input.next();
                        //Possible Choices (Get | Stay | Quit)
                        if (decision.equals("get") || decision.equals("Get") || decision.equals("GET") || decision.equals("getcard") || decision.equals("GETCARD") || decision.equals("GET CARD") || decision.equals("get card") || decision.equals("g") || decision.equals("G")) {
                            dealer.getCard(deck.deal());
                            dealer.showCards();
                            System.out.println("Score: " + dealer.getScore() + "\n");
                        } else if (decision.equals("Stay") || decision.equals("stay") || decision.equals("STAY") || decision.equals("s")) {
                            if (dealer.getScore() <= player.getScore()) {
                                System.out.println("You cannot stay, you're the dealer. YOU HAVE TO WIN!!!\n");
                            } else {
                                break;
                            }
                        } else if (decision.equals("Quit") || decision.equals("quit") || decision.equals("QUIT") || decision.equals("q") || decision.equals("Q")) {
                            quitGame();
                        } else if (decision.equals("r")) {
                            System.out.println(rules);
                        } else {
                            System.out.println("Please enter a valid input :)\n");
                        }

                    }
                }

                //Calculating both scores to see who won
                int finalScoreP = player.getScore();
                int finalScoreD = dealer.getScore();
                //All of the possible cases
                if (finalScoreP > finalScoreD && finalScoreP <= 21) {
                    System.out.println("You lost, your opponent has more points than you :( ");
                } else if (finalScoreD > finalScoreP && finalScoreD <= 21) {
                    System.out.println("Congratulations you've won!!!");
                } else if (finalScoreP == finalScoreD) {
                    System.out.println("It's a Draw");
                } else if (finalScoreP <= 21 && finalScoreD > 21) {
                    System.out.println("Your have exceeded 21 points, You loose :(");
                } else if (finalScoreD <= 21 && finalScoreP > 21) {
                    System.out.println("Your Opponent has exceeded 21 points, You win!!!");
                } else {
                    System.out.println("You have both exceeded 21 points");
                }
            }
            quitGame();//Prompts the user if he wants to quit the game

            resetGame(deck);//Starts a new game and alternates roles of the current Users

        }
    }

 
    //This method will shuffle and reset the 52 card deck after every game
    public void resetGame(Deck d) {
        for (Card c : player.hand) {
            d.returnCard(c);
        }
        for (Card c : dealer.hand) {
            d.returnCard(c);
        }
        player.removeCards(player.hand);
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
}
