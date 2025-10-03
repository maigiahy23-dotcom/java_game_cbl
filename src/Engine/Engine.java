package Engine;

import Engine.Networking.Client;
import Engine.Networking.Server;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Engine {
    Server server;
    Client client;
    Scene currentScene;
    JFrame jFrame;
    boolean running;


    public void setup() {
        jFrame = new JFrame();
        jFrame.setSize(500, 600);
        jFrame.setVisible(true);
        running = true;
        jFrame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public boolean isRunning() {
        return running;
    }

    public void update() {

    }

    public void changeScene(Scene scene) {
        if (currentScene != null) {
            jFrame.remove(currentScene.getMainPanel());
        }
        
        currentScene = scene;
        
        jFrame.add(scene.getUiPanel());
        jFrame.validate();
    }

    /**
     * Setup server for game. The host also joins as a client.
     * @param port port
     * @return true if successful server is made
     */
    public boolean runServer(int port) {
        server = new Server();
        try {
            server.startServer(port);
        } catch (Exception e) {
            server = null;
            return false;
        }

        return runClient("localhost", port);
    }

    /**
     * Joins a hosted game.
     * @param host host ip
     * @param port port
     * @return true if joined the host
     */
    public boolean runClient(String host, int port) {
        client = new Client();
        try {
            client.connect(host, port);
        } catch (Exception e) {
            client = null;
            return false;
        }
        return true;
    }

    /**
     * Destroy given GameObject.
     */
    public void destroy(GameObject gameObjecet) {

    }


}
