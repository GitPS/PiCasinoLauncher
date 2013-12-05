/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package picasinolauncher;

import java.util.logging.Logger;
import networking.LauncherNetworkHandler;

/**
 *
 * @author Phil-Custom
 */
public class PiCasinoLauncher {
    private LauncherNetworkHandler network;
    public static Logger LOGGER= Logger.getLogger("Launcher");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PiCasinoLauncher launcher = new PiCasinoLauncher();
        /* Pass control to login UI */
        launcher.launchHostnameUI();
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
    
    public void launchUpdateUI(String username){
        PiCasinoLauncherUI ui = new PiCasinoLauncherUI();
        /* Displaye update UI */
        ui.setVisible(true);
    }
    
    public boolean connectToServer(String host){
        network = new LauncherNetworkHandler(host);
        return network.isConnected();
    }
    
}
