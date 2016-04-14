/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Erick Garcia
 */
public class BlackJackGameP extends javax.swing.JFrame {

    @Override
    public Component add(Component comp) {
        return super.add(comp); //To change body of generated methods, choose Tools | Templates.
    }

    private class HandlerClass implements MouseListener, MouseMotionListener {

        public void mouseEntered(MouseEvent event) {
            Object o = event.getSource();
            JButton b = null;
            JLabel l = null;
            String labelText = "";
            String buttonText = "";

            if (o instanceof JButton) {
                b = (JButton) o;
            }

            if (b != null) {
                buttonText = b.getText();
                Date now = new Date();
                sdf.format(new Date(now.getTime() - start.getTime()));
                if (dealerTurn) {
                    System.out.println("On Dealer " + dealerName + "'s turn, at " + now + " the mouse button was hovering over " + buttonText);
                } else {
                    System.out.println("On Player " + playerName + "'s turn, at " + now + " the mouse button was hovering over " + buttonText);
                }

            }
        }

        public void mouseExited(MouseEvent event) {
//            System.out.println("Exit");

        }

        public void mouseClicked(MouseEvent event) {

        }

        public void mousePressed(MouseEvent event) {
//          a
        }

        public void mouseReleased(MouseEvent event) {
//            System.out.println("Released");
        }

        public void mouseDragged(MouseEvent event) {

        }

        public void mouseMoved(MouseEvent event) {

        }

    }

    String playerName;
    String dealerName;
    String playerCards;
    private ArrayList<String> playerCardsArr;
    private ArrayList<String> dealerCardsArr;
    int playerScore;
    String dealerCards;
    int dealerScore;
    BlackJack game;
    boolean isPlayer;
    boolean isDealer;
    private ArrayList<JLabel> playersHandGUI;
    private ArrayList<JLabel> dealersHandGUI;
    boolean dealerTurn = false;
    Date start;
    SimpleDateFormat sdf;
    boolean isHost;
    Server s1;
    Client c1;
    
    Thread t;
    

    /**
     * Creates new form BlackJackGame
     */
    public BlackJackGameP(String playerName, String dealerName, boolean isPlayer, boolean isDealer, int playerMoney, int dealerMoney, boolean isHost) {
        initComponents();
        start = new Date();
        sdf = new SimpleDateFormat("hh:mm:ss");
        this.isHost = isHost;
        
        HandlerClass handler = new HandlerClass();
        this.addMouseListener(handler);
        this.hitMeBtn.addMouseListener(handler);
        this.stayBtn.addMouseListener(handler);
        this.quitBtn.addMouseListener(handler);
        this.rulesBtn.addMouseListener(handler);
        this.dealBtn.addMouseListener(handler);
        this.againBtn.addMouseListener(handler);
        this.endPlayBtn.addMouseListener(handler);
        this.menuBtn.addMouseListener(handler);
        this.dCard1Lbl.addMouseListener(handler);
        this.dCard2Lbl.addMouseListener(handler);
        this.dCard3Lbl.addMouseListener(handler);
        this.dCard4Lbl.addMouseListener(handler);
        this.dCard5Lbl.addMouseListener(handler);
        this.pCard1Lbl.addMouseListener(handler);
        this.pCard2Lbl.addMouseListener(handler);
        this.pCard3Lbl.addMouseListener(handler);
        this.pCard4Lbl.addMouseListener(handler);
        this.pCard5Lbl.addMouseListener(handler);

        //Labels for the icons of Player Cards
        playersHandGUI = new ArrayList<javax.swing.JLabel>();
        playersHandGUI.add(pCard1Lbl);
        playersHandGUI.add(pCard2Lbl);
        playersHandGUI.add(pCard3Lbl);
        playersHandGUI.add(pCard4Lbl);
        playersHandGUI.add(pCard5Lbl);

        //Labels for the icons of Dealer Cards
        dealersHandGUI = new ArrayList<javax.swing.JLabel>();
        dealersHandGUI.add(dCard1Lbl);
        dealersHandGUI.add(dCard2Lbl);
        dealersHandGUI.add(dCard3Lbl);
        dealersHandGUI.add(dCard4Lbl);
        dealersHandGUI.add(dCard5Lbl);

        this.playerName = playerName;
        this.dealerName = dealerName;
        this.playerNameLbl.setText("Player: " + playerName);
        this.dealerNameLbl.setText("Dealer: " + dealerName);

        game = new BlackJack(playerName, dealerName, isPlayer, isDealer, playerMoney, dealerMoney);
        game.newGame();
        playerCardsArr = new ArrayList<String>();
        playerCardsArr.add(game.getPlayer().getHand().get(0).toString());
        playerCardsArr.add(game.getPlayer().getHand().get(1).toString());
        dealerCardsArr = new ArrayList<String>();
        dealerCardsArr.add(game.getDealer().getHand().get(0).toString());
        dealerCardsArr.add(game.getDealer().getHand().get(1).toString());
        playersHandGUI.get(0).setIcon(new javax.swing.ImageIcon(getClass().getResource("Cards/" + getPlayerCardsArr().get(0) + ".png")));
        playersHandGUI.get(1).setIcon(new javax.swing.ImageIcon(getClass().getResource("Cards/" + getPlayerCardsArr().get(1) + ".png")));
        dealersHandGUI.get(0).setIcon(new javax.swing.ImageIcon(getClass().getResource("Cards/" + getDealerCardsArr().get(0) + ".png")));
        dealersHandGUI.get(1).setIcon(new javax.swing.ImageIcon(getClass().getResource("Cards/faceDown.png")));

        //Bets
        int playerMoneyGame = this.game.getPlayer().getMoney() - 100;
        int dealerMoneyGame = this.game.getDealer().getMoney() - 100;
        playerCards = game.getPlayer().getHand().toString();
        this.playerCardsLbl.setText("Cards: " + playerCards);
        playerScore = game.getPlayer().getScore();
        this.playerScoreLbl.setText("Score: " + playerScore);
        this.playerMoneyLbl.setText("Money: $" + playerMoneyGame);
        dealerCards = game.getDealer().getHand().toString();
        this.dealerCardsLbl.setText("Cards: " + game.getDealer().getHand().get(0));
        dealerScore = game.getDealer().getHand().get(0).getValue1();
        this.dealerScoreLbl.setText("Score: " + dealerScore);
        this.dealerMoneyLbl.setText("Money: $" + dealerMoneyGame);
        this.outcomeLbl.setVisible(false);
        this.againBtn.setVisible(false);
        this.menuBtn.setVisible(false);
        this.dealBtn.setVisible(false);
        this.endPlayBtn.setVisible(false);
        if (this.game.getPlayer().getScore() == 21 && this.game.getPlayer().getScore() > this.game.getDealer().getScore()) {
            this.outcomeLbl.setText("BlackJack!!!!! Player has won");
            this.game.getPlayer().setIsWinner(true);
            this.outcomeLbl.setVisible(true);
            this.againBtn.setVisible(true);
            this.menuBtn.setVisible(true);
        }
            if (isHost) {
                t = new Thread(s1 = new Server());
                t.start();
                
//            Class[] args = new Class[1];
//            String Message = "hi";
//            Method sendMessage = t.getClass().getMethod("sendMessage", String.class);
//            System.out.println("");
//            t.getClass().run(Message);

            } else {
                String hostName = "";
                hostName = JOptionPane.showInputDialog(hostName);
                t = new Thread(new Client(hostName));
                t.start();
            }
        
    }

    private BlackJackGameP() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        playerNameLbl = new javax.swing.JLabel();
        dealerNameLbl = new javax.swing.JLabel();
        playerCardsLbl = new javax.swing.JLabel();
        hitMeBtn = new javax.swing.JButton();
        playerScoreLbl = new javax.swing.JLabel();
        dealerCardsLbl = new javax.swing.JLabel();
        dealerScoreLbl = new javax.swing.JLabel();
        stayBtn = new javax.swing.JButton();
        rulesBtn = new javax.swing.JButton();
        quitBtn = new javax.swing.JButton();
        playerCardsPanel = new javax.swing.JPanel();
        pCard1Lbl = new javax.swing.JLabel();
        pCard2Lbl = new javax.swing.JLabel();
        pCard3Lbl = new javax.swing.JLabel();
        pCard4Lbl = new javax.swing.JLabel();
        pCard5Lbl = new javax.swing.JLabel();
        dealerCardsPanel = new javax.swing.JPanel();
        dCard1Lbl = new javax.swing.JLabel();
        dCard2Lbl = new javax.swing.JLabel();
        dCard3Lbl = new javax.swing.JLabel();
        dCard4Lbl = new javax.swing.JLabel();
        dCard5Lbl = new javax.swing.JLabel();
        dealBtn = new javax.swing.JButton();
        outcomePanel = new javax.swing.JPanel();
        outcomeLbl = new javax.swing.JLabel();
        againBtn = new javax.swing.JButton();
        menuBtn = new javax.swing.JButton();
        endPlayBtn = new javax.swing.JButton();
        dealerMoneyLbl = new javax.swing.JLabel();
        playerMoneyLbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        playerNameLbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        playerNameLbl.setText("Player Name:");
        playerNameLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playerNameLblMouseEntered(evt);
            }
        });

        dealerNameLbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        dealerNameLbl.setText("Dealer Name:");
        dealerNameLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dealerNameLblMouseEntered(evt);
            }
        });

        playerCardsLbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        playerCardsLbl.setText("Player Cards:");

        hitMeBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        hitMeBtn.setText("Hit Me!");
        hitMeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitMeBtnActionPerformed(evt);
            }
        });

        playerScoreLbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        playerScoreLbl.setText("Score: ");
        playerScoreLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playerScoreLblMouseEntered(evt);
            }
        });

        dealerCardsLbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dealerCardsLbl.setText("Dealer Cards:");

        dealerScoreLbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        dealerScoreLbl.setText("Score:");
        dealerScoreLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dealerScoreLblMouseEntered(evt);
            }
        });

        stayBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        stayBtn.setText("Stay");
        stayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stayBtnActionPerformed(evt);
            }
        });

        rulesBtn.setText("Rules");
        rulesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rulesBtnActionPerformed(evt);
            }
        });

        quitBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quitBtn.setText("Quit");
        quitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitBtnActionPerformed(evt);
            }
        });

        pCard1Lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pCard1LblMouseEntered(evt);
            }
        });

        pCard2Lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pCard2LblMouseEntered(evt);
            }
        });

        pCard3Lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pCard3LblMouseEntered(evt);
            }
        });

        pCard4Lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pCard4LblMouseEntered(evt);
            }
        });

        pCard5Lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pCard5LblMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout playerCardsPanelLayout = new javax.swing.GroupLayout(playerCardsPanel);
        playerCardsPanel.setLayout(playerCardsPanelLayout);
        playerCardsPanelLayout.setHorizontalGroup(
            playerCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerCardsPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pCard1Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(pCard2Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(pCard3Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(pCard4Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(pCard5Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
        );
        playerCardsPanelLayout.setVerticalGroup(
            playerCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerCardsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(playerCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pCard5Lbl, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(pCard3Lbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pCard2Lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pCard4Lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pCard1Lbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        dCard1Lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dCard1LblMouseEntered(evt);
            }
        });

        dCard2Lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dCard2LblMouseEntered(evt);
            }
        });

        dCard3Lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dCard3LblMouseEntered(evt);
            }
        });

        dCard4Lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dCard4LblMouseEntered(evt);
            }
        });

        dCard5Lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dCard5LblMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout dealerCardsPanelLayout = new javax.swing.GroupLayout(dealerCardsPanel);
        dealerCardsPanel.setLayout(dealerCardsPanelLayout);
        dealerCardsPanelLayout.setHorizontalGroup(
            dealerCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dealerCardsPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(dCard1Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(dCard2Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(dCard3Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dCard4Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dCard5Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dealerCardsPanelLayout.setVerticalGroup(
            dealerCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dealerCardsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dealerCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dCard5Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dCard3Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dCard1Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dCard2Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dCard4Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        dealBtn.setText("Deal");
        dealBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dealBtnActionPerformed(evt);
            }
        });

        outcomeLbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        outcomeLbl.setText("Outcome");

        againBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        againBtn.setText("Play Again?");
        againBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                againBtnActionPerformed(evt);
            }
        });

        menuBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        menuBtn.setText("Return to Menu");
        menuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout outcomePanelLayout = new javax.swing.GroupLayout(outcomePanel);
        outcomePanel.setLayout(outcomePanelLayout);
        outcomePanelLayout.setHorizontalGroup(
            outcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outcomePanelLayout.createSequentialGroup()
                .addGroup(outcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(outcomePanelLayout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(againBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(menuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(outcomePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(outcomeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        outcomePanelLayout.setVerticalGroup(
            outcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, outcomePanelLayout.createSequentialGroup()
                .addComponent(outcomeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(outcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(againBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        endPlayBtn.setText("End Play");
        endPlayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endPlayBtnActionPerformed(evt);
            }
        });

        dealerMoneyLbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dealerMoneyLbl.setText("Money: 1000");
        dealerMoneyLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dealerMoneyLblMouseEntered(evt);
            }
        });

        playerMoneyLbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        playerMoneyLbl.setText("Money: $1000");
        playerMoneyLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playerMoneyLblMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(hitMeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(stayBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(rulesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(225, 225, 225)
                        .addComponent(playerMoneyLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(playerScoreLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playerNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))
                    .addComponent(playerCardsLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(dealerNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(dealBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(endPlayBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dealerScoreLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(quitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(dealerCardsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dealerMoneyLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dealerCardsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(outcomePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(playerCardsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(endPlayBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dealBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dealerNameLbl)
                        .addComponent(quitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3)
                .addComponent(dealerScoreLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dealerCardsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dealerMoneyLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(dealerCardsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outcomePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerCardsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerCardsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(playerScoreLbl)
                        .addGap(16, 16, 16)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(playerNameLbl)
                            .addComponent(playerMoneyLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hitMeBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stayBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rulesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stayBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stayBtnActionPerformed
//        if (this.game.getPlayer().getScore() <= this.game.getDealer().getScore() && this.game.getDealer().getScore() <= 21 && !(this.game.getPlayer().getScore() == this.game.getDealer().getScore())) {
//            JOptionPane.showMessageDialog(null, "You cannot stay you have to beat the Dealer.", "Error", JOptionPane.WARNING_MESSAGE);
//        } else {
//            this.game.dealerAi();
//            dealerCards = game.getDealer().getHand().toString();
//            this.dealerCardsLbl.setText("Cards: " + dealerCards);
//            dealerScore = game.getDealer().getScore();
//            this.dealerScoreLbl.setText("Score: " + dealerScore);
        dealerTurn = true;
        this.hitMeBtn.setVisible(false);
        this.stayBtn.setVisible(false);
        int i = game.getDealer().getLastIndex();
        dealersHandGUI.get(1).setIcon(new javax.swing.ImageIcon(getClass().getResource("Cards/" + getDealerCardsArr().get(1) + ".png")));
        String outcome = this.game.rulesP();
//            this.outcomeLbl.setText(outcome);
//            this.outcomeLbl.setVisible(true);
        dealerScore = game.getDealer().getScore();
        this.dealerScoreLbl.setText("Score: " + dealerScore);
        this.dealBtn.setVisible(true);
        this.endPlayBtn.setVisible(true);
        this.dealerCardsLbl.setText("Cards: " + dealerCards);
        dealerScore = game.getDealer().getHand().get(0).getValue1();
//        }

    }//GEN-LAST:event_stayBtnActionPerformed

    private void againBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_againBtnActionPerformed
        isPlayer = false;
        isDealer = true;
        int playerMoney = this.game.getPlayer().getMoney();
        int dealerMoney = this.game.getDealer().getMoney();
        //Player Won
        if (this.game.getPlayer().isWinner()) {
            playerMoney += 200;
            dealerMoney -= 100;
        } //Dealer Won
        else if (this.game.getDealer().isWinner()) {
            playerMoney -= 100;
            dealerMoney += 200;
        }

        //Else if it was a draw the bets stay the same.
        if (playerMoney <= 100) {
            String dealerName = this.game.getDealer().getName();
            JOptionPane.showMessageDialog(null, "There isn't enough money to continue playing Dealer " + dealerName + " is the ultimate winner"
                    + "\nreturning to start screen to start a new session", "Game outcome", JOptionPane.WARNING_MESSAGE);
            this.dispose();
            BlackJackStartScreen gui = new BlackJackStartScreen();
            gui.setLocationRelativeTo(null);
            gui.setVisible(true);
        } else if (dealerMoney <= 100) {
            String playerName = this.game.getPlayer().getName();
            JOptionPane.showMessageDialog(null, "There isn't enough money to continue playing Player " + playerName + " is the ultimate winner"
                    + "\nreturning to start screen to start a new session", "Game outcome", JOptionPane.WARNING_MESSAGE);
            this.dispose();
            BlackJackStartScreen gui = new BlackJackStartScreen();
            gui.setLocationRelativeTo(null);
            gui.setVisible(true);
        } else {
            BlackJackGameP game = new BlackJackGameP(dealerName, playerName, isDealer, isPlayer, dealerMoney, playerMoney, isHost);
            JOptionPane.showMessageDialog(null, "Restarting game and inverting roles", "Game outcome", JOptionPane.PLAIN_MESSAGE);
            this.dispose();
            game.setLocationRelativeTo(null);
            game.setVisible(true);

        }
    }//GEN-LAST:event_againBtnActionPerformed

    private void quitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitBtnActionPerformed
        JOptionPane.showMessageDialog(null, "Goodbye", "Game outcome", JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }//GEN-LAST:event_quitBtnActionPerformed

    private void menuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBtnActionPerformed
        BlackJackStartScreen menu = new BlackJackStartScreen();
        this.dispose();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }//GEN-LAST:event_menuBtnActionPerformed

    private void rulesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rulesBtnActionPerformed
        Rules rules = new Rules(this.game.getRules());
        rules.setVisible(true);
        rules.setLocationRelativeTo(null);
    }//GEN-LAST:event_rulesBtnActionPerformed

    private void dealBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dealBtnActionPerformed
        this.game.getDealer().getCard(this.game.getDeck().deal());
        dealerCards = game.getDealer().getHand().toString();
        this.dealerCardsLbl.setText("Cards: " + dealerCards);
        dealerScore = game.getDealer().getScore();
        this.dealerScoreLbl.setText("Score: " + dealerScore);
        getDealerCardsArr().add(game.getDealer().getLastCard());
        int i = game.getDealer().getLastIndex();
        dealersHandGUI.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("Cards/" + getDealerCardsArr().get(i) + ".png")));
        if (this.game.getPlayer().getScore() > 21) {
            this.outcomeLbl.setText("Player has more than 21, you win");
            this.outcomeLbl.setVisible(true);
            this.againBtn.setVisible(true);
            this.menuBtn.setVisible(true);
        }

        if (this.game.getDealer().getScore() == 21 && this.game.getDealer().getScore() > this.game.getPlayer().getScore()) {
            this.outcomeLbl.setText("BlackJack!!!!! Dealer has won");
            this.outcomeLbl.setVisible(true);
            this.againBtn.setVisible(true);
            this.menuBtn.setVisible(true);
        }
        if (this.game.getDealer().hasLost()) {
            String outcome = this.game.rulesD();
            this.outcomeLbl.setText(outcome);
            this.outcomeLbl.setVisible(true);
            this.againBtn.setVisible(true);
            this.menuBtn.setVisible(true);
            this.dealBtn.setVisible(false);
            this.endPlayBtn.setVisible(false);
        } else if (getDealerCardsArr().size() == 5 && this.game.getDealer().getScore() <= 21) {
            this.outcomeLbl.setText("Dealer has won he has 5 cards without exceeding 21");
            this.outcomeLbl.setVisible(true);
            this.againBtn.setVisible(true);
            this.menuBtn.setVisible(true);
            this.hitMeBtn.setVisible(false);
            this.stayBtn.setVisible(false);
        }
    }//GEN-LAST:event_dealBtnActionPerformed

    private void endPlayBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endPlayBtnActionPerformed
        if (this.game.getPlayer().getScore() >= this.game.getDealer().getScore() && this.game.getPlayer().getScore() <= 21) {
            JOptionPane.showMessageDialog(null, "You cannot stay you have to beat the player.", "Game outcome", JOptionPane.WARNING_MESSAGE);
        } else {
            dealerCards = game.getDealer().getHand().toString();
            this.dealerCardsLbl.setText("Cards: " + dealerCards);
            dealerScore = game.getDealer().getScore();
            this.dealerScoreLbl.setText("Score: " + dealerScore);
            this.dealBtn.setVisible(false);
            this.endPlayBtn.setVisible(false);
            String outcome = this.game.rulesD();
            this.outcomeLbl.setText(outcome);
            this.outcomeLbl.setVisible(true);
            this.againBtn.setVisible(true);
            this.menuBtn.setVisible(true);
        }
    }//GEN-LAST:event_endPlayBtnActionPerformed

    private void pCard1LblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pCard1LblMouseEntered
        Date now = new Date();
        sdf.format(new Date(now.getTime() - start.getTime()));
        if (dealerTurn) {
            System.out.println("On Dealer " + dealerName + "'s turn, at " + now + " the mouse was hovering over pCard1Lbl");
        } else {
            System.out.println("On Player " + playerName + "'s turn, at " + now + " the mouse was hovering over pCard1Lbl");
        }
    }//GEN-LAST:event_pCard1LblMouseEntered

    private void hitMeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitMeBtnActionPerformed
// TODO add your handling code here:

        this.game.getPlayer().getCard(this.game.getDeck().deal());
        playerCards = game.getPlayer().getHand().toString();
        getPlayerCardsArr().add(game.getPlayer().getLastCard());
        int i = game.getPlayer().getLastIndex();
        playersHandGUI.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("Cards/" + getPlayerCardsArr().get(i) + ".png")));
        this.playerCardsLbl.setText("Cards: " + playerCards);
        playerScore = game.getPlayer().getScore();
        this.playerScoreLbl.setText("Score :" + playerScore);
        s1.sendMessage(getPlayerCardsArr().get(i));
        if (this.game.getPlayer().hasLost()) {
            String outcome = this.game.rulesP();
            this.outcomeLbl.setText(outcome);
            this.outcomeLbl.setVisible(true);
            this.againBtn.setVisible(true);
            this.menuBtn.setVisible(true);
            this.hitMeBtn.setVisible(false);
            this.stayBtn.setVisible(false);
        } else if (getPlayerCardsArr().size() == 5 && this.game.getPlayer().hasLost()) {
            this.outcomeLbl.setText("You have won you have 5 cards without exceeding 21");
            this.outcomeLbl.setVisible(true);
            this.againBtn.setVisible(true);
            this.menuBtn.setVisible(true);
            this.hitMeBtn.setVisible(false);
            this.stayBtn.setVisible(false);
        }    }//GEN-LAST:event_hitMeBtnActionPerformed

    private void pCard2LblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pCard2LblMouseEntered
        Date now = new Date();
        sdf.format(new Date(now.getTime() - start.getTime()));
        if (dealerTurn) {
            System.out.println("On Dealer " + dealerName + "'s turn, at " + now + " the mouse was hovering over pCard2Lbl");
        } else {
            System.out.println("On Player " + playerName + "'s turn, at " + now + " the mouse was hovering over pCard2Lbl");
        }    }//GEN-LAST:event_pCard2LblMouseEntered

    private void pCard3LblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pCard3LblMouseEntered
        Date now = new Date();
        sdf.format(new Date(now.getTime() - start.getTime()));
        if (dealerTurn) {
            System.out.println("On Dealer " + dealerName + "'s turn, at " + now + " the mouse was hovering over pCard3Lbl");
        } else {
            System.out.println("On Player " + playerName + "'s turn, at " + now + " the mouse was hovering over pCard3Lbl");
        }    }//GEN-LAST:event_pCard3LblMouseEntered

    private void pCard4LblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pCard4LblMouseEntered
        Date now = new Date();
        sdf.format(new Date(now.getTime() - start.getTime()));
        if (dealerTurn) {
            System.out.println("On Dealer " + dealerName + "'s turn, at " + now + " the mouse was hovering over pCard4Lbl");
        } else {
            System.out.println("On Player " + playerName + "'s turn, at " + now + " the mouse was hovering over pCard4Lbl");
        }    }//GEN-LAST:event_pCard4LblMouseEntered

    private void pCard5LblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pCard5LblMouseEntered
        Date now = new Date();
        sdf.format(new Date(now.getTime() - start.getTime()));
        if (dealerTurn) {
            System.out.println("On Dealer " + dealerName + "'s turn, at " + now + " the mouse was hovering over pCard5Lbl");
        } else {
            System.out.println("On Player " + playerName + "'s turn, at " + now + " the mouse was hovering over pCard5Lbl");
        }    }//GEN-LAST:event_pCard5LblMouseEntered

    private void dCard1LblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dCard1LblMouseEntered
        Date now = new Date();
        sdf.format(new Date(now.getTime() - start.getTime()));
        if (dealerTurn) {
            System.out.println("On Dealer " + dealerName + "'s turn, at " + now + " the mouse was hovering over dCard1Lbl");
        } else {
            System.out.println("On Player " + playerName + "'s turn, at " + now + " the mouse was hovering over dCard1Lbl");
        }
    }//GEN-LAST:event_dCard1LblMouseEntered

    private void dCard2LblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dCard2LblMouseEntered
        Date now = new Date();
        sdf.format(new Date(now.getTime() - start.getTime()));
        if (dealerTurn) {
            System.out.println("On Dealer " + dealerName + "'s turn, at " + now + " the mouse was hovering over dCard2Lbl");
        } else {
            System.out.println("On Player " + playerName + "'s turn, at " + now + " the mouse was hovering over dCard2Lbl");
        }
    }//GEN-LAST:event_dCard2LblMouseEntered

    private void dCard3LblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dCard3LblMouseEntered
        Date now = new Date();
        sdf.format(new Date(now.getTime() - start.getTime()));
        if (dealerTurn) {
            System.out.println("On Dealer " + dealerName + "'s turn, at " + now + " the mouse was hovering over dCard3Lbl");
        } else {
            System.out.println("On Player " + playerName + "'s turn, at " + now + " the mouse was hovering over dCard3Lbl");
        }
    }//GEN-LAST:event_dCard3LblMouseEntered

    private void dCard4LblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dCard4LblMouseEntered
        Date now = new Date();
        sdf.format(new Date(now.getTime() - start.getTime()));
        if (dealerTurn) {
            System.out.println("On Dealer " + dealerName + "'s turn, at " + now + " the mouse was hovering over dCard4Lbl");
        } else {
            System.out.println("On Player " + playerName + "'s turn, at " + now + " the mouse was hovering over dCard4Lbl");
        }
    }//GEN-LAST:event_dCard4LblMouseEntered

    private void dCard5LblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dCard5LblMouseEntered
        Date now = new Date();
        sdf.format(new Date(now.getTime() - start.getTime()));
        if (dealerTurn) {
            System.out.println("On Dealer " + dealerName + "'s turn, at " + now + " the mousewas hovering over dCard5Lbl");
        } else {
            System.out.println("On Player " + playerName + "'s turn, at " + now + " the mousewas hovering over dCard5Lbl");
        }
    }//GEN-LAST:event_dCard5LblMouseEntered

    private void playerNameLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerNameLblMouseEntered
        Date now = new Date();
        sdf.format(new Date(now.getTime() - start.getTime()));
        if (dealerTurn) {
            System.out.println("On Dealer " + dealerName + "'s turn, at " + now + " the mouse was hovering over playerNameLbl");
        } else {
            System.out.println("On Player " + playerName + "'s turn, at " + now + " the mouse was hovering over playerNameLbl");
        }
    }//GEN-LAST:event_playerNameLblMouseEntered

    private void playerScoreLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerScoreLblMouseEntered
        Date now = new Date();
        sdf.format(new Date(now.getTime() - start.getTime()));
        if (dealerTurn) {
            System.out.println("On Dealer " + dealerName + "'s turn, at " + now + " the mouse was hovering over playerScoreLbl");
        } else {
            System.out.println("On Player " + playerName + "'s turn, at " + now + " the mouse was hovering over playerScoreLbl");
        }
    }//GEN-LAST:event_playerScoreLblMouseEntered

    private void playerMoneyLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerMoneyLblMouseEntered
        Date now = new Date();
        sdf.format(new Date(now.getTime() - start.getTime()));
        if (dealerTurn) {
            System.out.println("On Dealer " + dealerName + "'s turn, at " + now + " the mouse was hovering over playerMoneyLbl");
        } else {
            System.out.println("On Player " + playerName + "'s turn, at " + now + " the mouse was hovering over playerMoneyLbl");
        }
    }//GEN-LAST:event_playerMoneyLblMouseEntered

    private void dealerNameLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dealerNameLblMouseEntered
        Date now = new Date();
        sdf.format(new Date(now.getTime() - start.getTime()));
        if (dealerTurn) {
            System.out.println("On Dealer " + dealerName + "'s turn, at " + now + " the mouse was hovering over dealerNameLbl");
        } else {
            System.out.println("On Player " + playerName + "'s turn, at " + now + " the mouse was hovering over dealerNameLbl");
        }
    }//GEN-LAST:event_dealerNameLblMouseEntered

    private void dealerScoreLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dealerScoreLblMouseEntered
        Date now = new Date();
        sdf.format(new Date(now.getTime() - start.getTime()));
        if (dealerTurn) {
            System.out.println("On Dealer " + dealerName + "'s turn, at " + now + " the mouse was hovering over dealerScoreLbl");
        } else {
            System.out.println("On Player " + playerName + "'s turn, at " + now + " the mouse was hovering over dealerScoreLbl");
        }
    }//GEN-LAST:event_dealerScoreLblMouseEntered

    private void dealerMoneyLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dealerMoneyLblMouseEntered
        Date now = new Date();
        sdf.format(new Date(now.getTime() - start.getTime()));
        if (dealerTurn) {
            System.out.println("On Dealer " + dealerName + "'s turn, at " + now + " the mouse was hovering over dealerMoneyLbl");
        } else {
            System.out.println("On Player " + playerName + "'s turn, at " + now + " the mouse was hovering over dealerMoneyLbl");
        }
    }//GEN-LAST:event_dealerMoneyLblMouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BlackJackGameP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BlackJackGameP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BlackJackGameP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BlackJackGameP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BlackJackGameP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton againBtn;
    private javax.swing.JLabel dCard1Lbl;
    private javax.swing.JLabel dCard2Lbl;
    private javax.swing.JLabel dCard3Lbl;
    private javax.swing.JLabel dCard4Lbl;
    private javax.swing.JLabel dCard5Lbl;
    private javax.swing.JButton dealBtn;
    private javax.swing.JLabel dealerCardsLbl;
    private javax.swing.JPanel dealerCardsPanel;
    private javax.swing.JLabel dealerMoneyLbl;
    private javax.swing.JLabel dealerNameLbl;
    private javax.swing.JLabel dealerScoreLbl;
    private javax.swing.JButton endPlayBtn;
    private javax.swing.JButton hitMeBtn;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton menuBtn;
    private javax.swing.JLabel outcomeLbl;
    private javax.swing.JPanel outcomePanel;
    private javax.swing.JLabel pCard1Lbl;
    private javax.swing.JLabel pCard2Lbl;
    private javax.swing.JLabel pCard3Lbl;
    private javax.swing.JLabel pCard4Lbl;
    private javax.swing.JLabel pCard5Lbl;
    private javax.swing.JLabel playerCardsLbl;
    private javax.swing.JPanel playerCardsPanel;
    private javax.swing.JLabel playerMoneyLbl;
    private javax.swing.JLabel playerNameLbl;
    private javax.swing.JLabel playerScoreLbl;
    private javax.swing.JButton quitBtn;
    private javax.swing.JButton rulesBtn;
    private javax.swing.JButton stayBtn;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the playerCardsArr
     */
    public ArrayList<String> getPlayerCardsArr() {
        return playerCardsArr;
    }

    /**
     * @return the dealerCardsArr
     */
    public ArrayList<String> getDealerCardsArr() {
        return dealerCardsArr;
    }
}
