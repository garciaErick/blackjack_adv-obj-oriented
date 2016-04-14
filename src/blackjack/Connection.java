/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.io.IOException;
import java.net.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Erick Garcia
 */
public class Connection {
    String hostName;
    int portNumber = 1;
    Socket echoSocket = new Socket(hostName, portNumber);
    

    public Connection() throws UnknownHostException, IOException {
        this.hostName = Inet4Address.getLocalHost().getHostAddress();
        this.portNumber = 12345;
    }
}
