
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
                    // TODO  
                }
                else if(object instanceof Update){
                    // TODO
                }
            }

            /* Connection with a server is lost. */
            public void disconnected(Connection connection) {
                PiCasinoLauncher.LOGGER.severe("Connection with server lost. Program will exit.");
                System.exit(0);
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

    public void send(Object toSend) {
        client.sendTCP(toSend);
    }

    public boolean isConnected(){
        return client.isConnected();
    }
    
}
