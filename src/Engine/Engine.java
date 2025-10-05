package Engine;

import Engine.Networking.Client;
import Engine.Networking.Server;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Engine {
    Server server;
    Client client;
    Scene currentScene;
    JFrame jFrame;
    boolean running;
    static Engine engine;

    public Engine() {
        engine = this;
    }

    public void setup() {
        jFrame = new JFrame();
        jFrame.setSize(500, 600);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        running = true;
    }

    public static Scene getCurrentScene() {
        return engine.currentScene;
    }

    protected static Engine getEngine() {
        return engine;
    }

    public boolean isRunning() {
        return running;
    }

    public void update() {
        currentScene.update();
    }

    public static void changeScene(Scene scene) {
        if (Engine.getCurrentScene() != null) {
            Engine.getEngine().jFrame.remove(Engine.getCurrentScene().getMainPanel());
        }
        
        Engine.getEngine().currentScene = scene;
        
        Engine.getEngine().jFrame.add(scene.getMainPanel());
        Engine.getEngine().jFrame.validate();
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
