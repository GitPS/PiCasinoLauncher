/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

/**
 *
 * @author phil
 */
public class Network {
    static final int port = 65001;
    
    static public void register(EndPoint endPoint){
        Kryo kryo = endPoint.getKryo();
        kryo.register(User.class);
    }
    
}
