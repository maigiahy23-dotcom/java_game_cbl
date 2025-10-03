package Engine.Networking;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * A server class to send and receive data.
 */
public class Server extends Thread {
    private DatagramSocket socket;
    private int port;
    private boolean running;
    private byte[] buf = new byte[256];

    /**
     * Set a port and start server.
     * @param port port
     * @throws Exception throws an exception if server cannot be opened
     */
    public void startServer(int port) throws Exception {
        socket = new DatagramSocket(port);
        this.port = port;
        running = true;
        start();
    }

    @Override
    public void run() {
        while (running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (Exception e) {
                System.out.println("Failed to receive packet");
                continue;
            }
            
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);
            String received = new String(packet.getData(), 0, packet.getLength());
            
            if (received.equals("end")) {
                running = false;
                continue;
            }

            try {
                socket.send(packet);
            } catch (Exception e) {
                System.out.println("Failed to send packet");
                continue;
            }
            
        }
        socket.close();
    }
}
