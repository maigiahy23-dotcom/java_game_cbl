package Engine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.*;

public abstract class Scene extends JPanel {
    // JPanel uiPanel;
    // JPanel gamePanel;
    // JPanel mainPanel;
    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    public Scene() {
        // mainPanel = new JPanel();
        // uiPanel = new JPanel();
        // gamePanel = new JPanel();

        this.setLayout(null);
        // gamePanel.setLayout(new BorderLayout());
        // uiPanel.setLayout(null);
        
        // this.setBackground(Color.BLUE);
        
        setupScene();
        // uiPanel.setOpaque(false);

        // gamePanel.add(uiPanel);
        // mainPanel.add(gamePanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (GameObject gameObject : gameObjects) {
            gameObject.draw((Graphics2D) g);
        }
    }

    protected void update() {
        repaint();
    }

    public abstract void setupScene();

    // public JPanel getUiPanel() {
    //     return this;
    // }

    // public JPanel getGamePanel() {
    //     return this;
    // }

    protected JPanel getMainPanel() {
        return this;
    }

    public void addObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

}
