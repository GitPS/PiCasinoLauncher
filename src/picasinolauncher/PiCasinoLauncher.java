/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picasinolauncher;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import networking.LauncherNetworkHandler;
import networking.User;

/**
 *
 * @author Phil-Custom
 */
public class PiCasinoLauncher {
    private static LauncherNetworkHandler network;
    private User user;
    private String hostName;
    private String gameType;
    public static final Logger LOGGER= Logger.getLogger("Launcher");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PiCasinoLauncher launcher = new PiCasinoLauncher();
        /* Pass control to login UI */
        launcher.launchHostnameUI();
    }
    
    public PiCasinoLauncher(){
        user = new User("", "");
        hostName = "LOCALHOST";
        gameType = "blackjack";        
    }
    
    public void launchHostnameUI(){
        PiCasinoLauncherUIHostname ui = new PiCasinoLauncherUIHostname(this);
        /* Display hostname window */
        ui.setVisible(true);
    }
    
    public void launchLoginUI(){
        PiCasinoLauncherUILogin ui = new PiCasinoLauncherUILogin(this);
        /* Display login window */
        ui.setVisible(true);        
    }
    
    public void laucnhRegisterUI(){
        PiCasinoLauncherRegisterUI ui = new PiCasinoLauncherRegisterUI(this);
        /* Displaye update UI */
        ui.setVisible(true);
    }
    
    public void launchPiCasino(){
        String[] args = {gameType, "client", hostName, user.getUsername()};
        com.piindustries.picasino.PiCasino.main(args);
    }
    
    public boolean connectToServer(String host){
        network = new LauncherNetworkHandler(host);
        return network.isConnected();
    }
    
    public LauncherNetworkHandler getNetworkHandler(){
        return network;
    }
    
    public User getUser(){
        return user;
    }
    
    public void setUser(User u){
        user = u;
    }   
    
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
}
