package blackjack;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class Server extends JFrame implements Runnable {

    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;

    public Server() {
        super("Multiplayer Blackjack");
        userText = new JTextField();

        //doesnt allow to type
        userText.setEditable(true);

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
        add(new JScrollPane(chatWindow));
        setSize(500, 500);
        setVisible(true);
    }

    public void startRunning() {
        try {
            // "1234" random number for a port
            // "2" how many people can connect
            // "2" is the queue length

            server = new ServerSocket(12345, 2);
            while (true) {
                try {
                    //connect
                    waitForConnection();

                    //output and input string
                    setupStreams();

                    whileRunning();

                } catch (EOFException eofException) {
                    showMessage("\n Server ended the connection");
                } finally {
                    closeAll();
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void waitForConnection() throws IOException {
        showMessage("Waiting for someone to connect... \n");

        //server accept : accepts a connection to the socket
        // and checks for such connection
        connection = server.accept();
        showMessage("Now connected to " + connection.getInetAddress().getHostName());

    }

    private void setupStreams() throws IOException {
        //tells us what computer to connect to
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        showMessage("\n Streams are setup");

    }

    //get stream to send and recieve data
    private void whileRunning() throws IOException {
        String message = "";
        ableToType(true);
        do {
            // exchange data
            try {
                message = (String) input.readObject();
                showMessage("\n" + message);
                System.out.println(message);
            } catch (ClassNotFoundException classNotFoundException) {
                showMessage("\n there was an error my ninja!");
            }
        } while (!message.equals("CLIENT - END"));

    }

    private void closeAll() {
        showMessage("\n Closing connection...\n");
        ableToType(false);
        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    void sendMessage(String message) {
        try {
            output.writeObject("SERVER -" + message);
            output.flush();
            showMessage("\nSERVER -" + message);
        } catch (IOException ioException) {
            chatWindow.append("\n Couldnt send that message");
        }

    }

    private void showMessage(final String text) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        chatWindow.append(text);
                        System.out.println(text);
                    }
                }
        );
    }

    private void ableToType(final boolean tof) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        chatWindow.setEditable(tof);
                    }
                }
        );
    }
    public void getMessage() throws IOException, ClassNotFoundException{
        input.readObject();
        
    }

    @Override
    public void run() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.startRunning();
    }
    
    public void run(String Message) {
        this.sendMessage(Message);
    }

}
