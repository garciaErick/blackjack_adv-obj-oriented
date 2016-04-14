/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Erick Garcia
 */
public class BlackJackSelectScreen extends javax.swing.JFrame {

    BlackJack game;

    /**
     * Creates new form BlackJackGameScreen
     */
    public BlackJackSelectScreen() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        userNameLbl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        userNameTxt = new javax.swing.JTextField();
        opponentNameTxt = new javax.swing.JTextField();
        playerCheck = new javax.swing.JCheckBox();
        dealerCheck = new javax.swing.JCheckBox();
        beginBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        clientChk = new javax.swing.JCheckBox();
        hostChk = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        userNameLbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        userNameLbl.setText("Hello, please insert your name player 1: ");
        userNameLbl.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Hello, please insert your name player 2: ");
        jLabel2.setToolTipText("");
        jLabel2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel2AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        userNameTxt.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        userNameTxt.setToolTipText("");
        userNameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameTxtActionPerformed(evt);
            }
        });

        opponentNameTxt.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        opponentNameTxt.setToolTipText("");
        opponentNameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opponentNameTxtActionPerformed(evt);
            }
        });

        playerCheck.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        playerCheck.setText("Player");
        playerCheck.setToolTipText("");
        playerCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerCheckActionPerformed(evt);
            }
        });

        dealerCheck.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        dealerCheck.setText("Dealer");
        dealerCheck.setToolTipText("");
        dealerCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dealerCheckActionPerformed(evt);
            }
        });

        beginBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        beginBtn.setText("Begin Game");
        beginBtn.setToolTipText("");
        beginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beginBtnActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("What does player 1 want to play as first?");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Are you going to be the host or client of the server?");

        clientChk.setText("Client");
        clientChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientChkActionPerformed(evt);
            }
        });

        hostChk.setText("Host");
        hostChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostChkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(userNameTxt)
                                        .addComponent(opponentNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(91, 91, 91))
                                .addComponent(userNameLbl, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(9, 9, 9))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(playerCheck)
                                    .addGap(58, 58, 58)
                                    .addComponent(dealerCheck)
                                    .addGap(102, 102, 102)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(99, 99, 99)
                                .addComponent(hostChk)
                                .addGap(131, 131, 131)
                                .addComponent(clientChk))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(421, 421, 421)
                        .addComponent(beginBtn)))
                .addContainerGap(244, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(userNameLbl)
                .addGap(42, 42, 42)
                .addComponent(userNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(opponentNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerCheck)
                    .addComponent(dealerCheck))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(clientChk)
                        .addComponent(hostChk)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(beginBtn)
                .addGap(63, 63, 63))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel2AncestorAdded
    }//GEN-LAST:event_jLabel2AncestorAdded

    private void userNameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameTxtActionPerformed

    private void opponentNameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opponentNameTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opponentNameTxtActionPerformed

    private void beginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beginBtnActionPerformed
        String userName = this.userNameTxt.getText();
        String opponentName = this.opponentNameTxt.getText();
        boolean isPlayer;
        boolean isDealer;
        boolean isHost;
        if ((playerCheck.isSelected() && hostChk.isSelected()) && !dealerCheck.isSelected()) {
            isPlayer = true;
            isDealer = false;
            isHost = true;
            BlackJackGameP game = new BlackJackGameP(userName, opponentName, isDealer, isPlayer, 1000, 1000, isHost);
            game.setVisible(true);
            game.setLocationRelativeTo(null);
            //Host


        } else if ((dealerCheck.isSelected() && hostChk.isSelected()) && !playerCheck.isSelected()) {
            isPlayer = false;
            isDealer = true;
            isHost = true;
            BlackJackGameP game = new BlackJackGameP(opponentName, userName, isDealer, isPlayer, 1000, 1000, isHost);
            game.setVisible(true);
            game.setLocationRelativeTo(null);
            //Host

  
        } else if ((playerCheck.isSelected() && clientChk.isSelected()) && !dealerCheck.isSelected()) {
            isPlayer = true;
            isDealer = false;
            isHost = false;
            BlackJackGameP game = new BlackJackGameP(userName, opponentName, isDealer, isPlayer, 1000, 1000, isHost);
            game.setVisible(true);
            game.setLocationRelativeTo(null);
        } else if ((dealerCheck.isSelected() && clientChk.isSelected()) && !playerCheck.isSelected()) {
            isPlayer = false;
            isDealer = true;
            isHost = false;
            BlackJackGameP game = new BlackJackGameP(opponentName, userName, isDealer, isPlayer, 1000, 1000, isHost);
            game.setVisible(true);
            game.setLocationRelativeTo(null);
        } else if (playerCheck.isSelected() && dealerCheck.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please select either to start out as a player or a dealer", "Error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
            BlackJackSelectScreen new1 = new BlackJackSelectScreen();
            new1.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please select either to start out as a player or a dealer", "Error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
            BlackJackSelectScreen new2 = new BlackJackSelectScreen();
            new2.setVisible(true);
        }

        this.dispose();
    }//GEN-LAST:event_beginBtnActionPerformed

    private void dealerCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dealerCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dealerCheckActionPerformed

    private void clientChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientChkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientChkActionPerformed

    private void hostChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostChkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hostChkActionPerformed

    private void playerCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_playerCheckActionPerformed

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
            java.util.logging.Logger.getLogger(BlackJackSelectScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BlackJackSelectScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BlackJackSelectScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BlackJackSelectScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BlackJackSelectScreen().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton beginBtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox clientChk;
    private javax.swing.JCheckBox dealerCheck;
    private javax.swing.JCheckBox hostChk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField opponentNameTxt;
    private javax.swing.JCheckBox playerCheck;
    private javax.swing.JLabel userNameLbl;
    private javax.swing.JTextField userNameTxt;
    // End of variables declaration//GEN-END:variables
}