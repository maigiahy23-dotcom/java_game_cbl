package Scenes;

import java.awt.event.*;

import javax.swing.*;

import Engine.Scene;

public class CreateLobbyScene extends Scene {
    
    @Override
    public void setupScene() {

        JLabel createLobbyText = new JLabel("New lobby");

        createLobbyText.setBounds(150, 200, 220, 50);

        add(createLobbyText);
    }
    
}
