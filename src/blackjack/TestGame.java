/**
 * Advanced Object Oriented Programing Black Jack
 *
 * @author Erick Garcia / egarcia87@miners.utep.edu
 */
package blackjack;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;
import javax.swing.JOptionPane;

public class TestGame {

    public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        BlackJackStartScreen gui = new BlackJackStartScreen();
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
        

    }
}
