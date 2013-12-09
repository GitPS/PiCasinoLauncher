
package networking;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.io.IOException;
import java.net.SocketTimeoutException;
import picasinolauncher.PiCasinoLauncher;

public class LauncherNetworkHandler {
    private Client client;

    public LauncherNetworkHandler(String host) {
        client = new Client();
        client.start();

        /* Register any classes that will be sent over the network */
        Network.register(client);

        /* Create a threaded listener */
        client.addListener(new Listener.ThreadedListener(new Listener() {
            /* Connection with a server is established. */
            public void connected(Connection connection) {
                PiCasinoLauncher.LOGGER.info("Connection with server established.");
            }

            /* Object received from the server. */
            public synchronized void received(Connection connection, Object object) {
                if (object instanceof User) {
                    PiCasinoLauncher.LOGGER.info("Received a user object from the server.");
                    // TODO  
                }
                else if(object instanceof Update){
                    PiCasinoLauncher.LOGGER.info("Received an update object from the server.");
                    // TODO
                }
            }

            /* Connection with a server is lost. */
            public void disconnected(Connection connection) {
                PiCasinoLauncher.LOGGER.severe("Launcher has disconnected from server!");
            }
        }));

        try {
            client.connect(10000, host, Network.port);
            // Server communication after connection can go here, or in Listener#connected().
        } catch (SocketTimeoutException e){
            PiCasinoLauncher.LOGGER.severe(e.getMessage());
        } catch (IOException e) {
            PiCasinoLauncher.LOGGER.severe(e.getMessage());
        }
    }

    public synchronized void sendUser(User toSend) {
        client.sendTCP(toSend);
        PiCasinoLauncher.LOGGER.info("Sending user to server.");
    }
    
    public synchronized void sendUpdate(Update toSend){
        client.sendTCP(toSend);
        PiCasinoLauncher.LOGGER.info("Sending udate to server.");        
    }

    public boolean isConnected(){
        return client.isConnected();
    }
    
}
