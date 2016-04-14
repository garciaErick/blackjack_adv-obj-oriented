/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Erick Garcia
 */
public class DatabaseConnection {

    String url = "jdbc:derby://localhost:1527/BETS";
    String username = "admin1";
    String password = "admin1";

    public DatabaseConnection() {

    }

    public void insert(String playerName, String money) throws SQLException {
        java.sql.Connection con = DriverManager.getConnection(url, username, password);
        String insert = "INSERT INTO BETS(Buser, Bmoney) VALUES('"+playerName+"', '"+money+"')";
        Statement stmt = con.createStatement();
        stmt.execute(insert);

    }

    public static void main(String args[]) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        db.insert("juanito","300");
    }
}
