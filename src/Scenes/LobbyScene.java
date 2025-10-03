package Scenes;

import java.awt.event.*;

import javax.swing.*;

import Engine.Scene;

public class LobbyScene extends Scene {
    
    @Override
    public void setupScene() {
        JPanel uiPanel = getUiPanel();

        JButton createLobbyButton = new JButton("Create lobby");
        JButton joinLobbyButton = new JButton("Join lobby");
        JTextField textField = new JTextField();

        createLobbyButton.setBounds(150, 200, 220, 50);
        joinLobbyButton.setBounds(150, 260, 220, 50);
        textField.setBounds(150, 320, 220, 50);

        createLobbyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        joinLobbyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        uiPanel.add(textField);
        uiPanel.add(joinLobbyButton);
        uiPanel.add(createLobbyButton);
    }
    
}
