package Engine;

import java.util.ArrayList;

import javax.swing.*;

public abstract class Scene {
    JPanel mainPanel;
    JPanel uiPanel;
    JPanel gamePanel;
    ArrayList<GameObject> gameObjects;

    public Scene() {
        mainPanel = new JPanel();
        uiPanel = new JPanel();
        gamePanel = new JPanel();
        uiPanel.setLayout(null);
        gamePanel.setLayout(null);
        //uiPanel.setOpaque(false);
        setupScene();
        mainPanel.add(uiPanel);
        //mainPanel.add(gamePanel);
        
    }

    public abstract void setupScene();

    public JPanel getUiPanel() {
        return uiPanel;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    protected JPanel getMainPanel() {
        return mainPanel;
    }

    public void addObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

}
