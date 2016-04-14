package blackjack;

import javax.swing.JFrame;
import javax.swing.*;

public class ClientTest {
	public static void main(String []args){
		Client client;
		String IP = " ";
		IP = JOptionPane.showInputDialog(IP);
		client = new Client(IP);
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.StartRunning();
	}
}
