package blackjack;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame implements Runnable {

    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String message = " ";
    private String serverIP;
    private Socket connection;

    public Client(String host) {
        super("Client!");
        serverIP = host;
        userText = new JTextField();
        userText.setEditable(false);
        userText.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        sendMessage(event.getActionCommand());
                        userText.setText("");
                    }
                }
        );

        add(userText, BorderLayout.NORTH);
        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow), BorderLayout.CENTER);
        setSize(500, 500);
        this.setLocation(0, 500);
        setVisible(true);

    }

    public void StartRunning() {
        try {
            connectToServer();
            setupStreams();
            whileChatting();
        } catch (EOFException eofException) {
            showMessage("\n Client terminated connection");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            closeAll();
        }

    }

    private void connectToServer() throws IOException {
        showMessage("Trying to connect...\n");
        connection = new Socket(InetAddress.getByName(serverIP), 12345);
        showMessage("Connected to:" + connection.getInetAddress().getHostName());
    }

    private void setupStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        showMessage("\n Streams are now good to go");
    }

    private void whileChatting() throws IOException {
        ableToType(true);
        do {
            try {
                message = (String) input.readObject();
                showMessage("\n" + message);
                getMessage();
            } catch (ClassNotFoundException classNotFoundException) {
                showMessage("\n I dont know that object type");
            }
        } while (!message.equals("SERVER - END"));
    }

    private void closeAll() {
        showMessage("\n closing everything down..");
        ableToType(false);
        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void sendMessage(String message) {
        try {
            output.writeObject("CLIENT - " + message);
            output.flush();
            showMessage("\nCLIENT -" + message);
        } catch (IOException ioException) {
            chatWindow.append("\n NOPEEEEE");
        }
    }

    private void showMessage(final String m) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        chatWindow.append(m);
                    }
                });
    }

    private void ableToType(final boolean tof) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        userText.setEditable(tof);
                    }
                });
    }
    
    public void getMessage() throws IOException, ClassNotFoundException{
        sendMessage("message recieved" + (String)input.readObject());
        
    }
    

    @Override
    public void run() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("hi");
        this.StartRunning();

    }
    
}
