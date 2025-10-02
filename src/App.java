
import java.io.*;
import javax.swing.*;


public class App {
    public static void main(String[] args) throws Exception {
        // Creating instance of JFrame
        JFrame frame = new JFrame();
        
        
        JButton createLobbyButton = new JButton("Create lobby");
        JButton joinLobbyButton = new JButton("Join lobby");

        createLobbyButton.setBounds(150, 200, 220, 50);
        joinLobbyButton.setBounds(150, 260, 220, 50);

        // adding button in JFrame
        frame.add(createLobbyButton);
        frame.add(joinLobbyButton);
        
        frame.setTitle("Cool game");
        frame.setSize(500, 600);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
