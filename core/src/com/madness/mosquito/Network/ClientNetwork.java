package com.madness.mosquito.Network;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.util.List;

/**
 * Created by juanolon on 29/12/15.
 */
public class ClientNetwork extends BufferedNetwork {

    Client client;
    String name;

    List<Server> servers;

    public void close(){
        if (client != null) {
            client.stop();
        }
    }

    public void connect(final Server server, final ConnectCallback cc) {

        client = new Client();
        client.start();

        // For consistency, the classes to be sent over the network are
        // registered by the same method for both the client and server.
        Common.register(client);

        // ThreadedListener runs the listener methods on a different thread.
        client.addListener(new Listener.ThreadedListener(new Listener() {
            public void connected (Connection connection) {
                Common.Login login = new Common.Login();
                login.name = server.name;
                client.sendTCP(login);

            }

            public void received (Connection connection, Object object) {
                Gdx.app.log("server", "object: "+ object.toString());
                if (object instanceof Common.LoginSuccess){
                    cc.onSuccess();
                }

                if (object instanceof Common.Player) {
                    Gdx.app.log("server", "receive new player: "+object.toString());
                    cc.onNewPlayer((Common.Player)object);
                }
//
//                if (object instanceof Common.UpdateCharacter) {
////                    ui.updateCharacter((Common.UpdateCharacter)object);
//                    return;
//                }
//
//                if (object instanceof Common.RemoveCharacter) {
//                    Common.RemoveCharacter msg = (Common.RemoveCharacter)object;
////                    ui.removeCharacter(msg.id);
//                    return;
//                }
            }

            public void disconnected (Connection connection) {

            }
        }));

        try {
            client.connect(5000, server.ip, Common.port);

            // Server communication after connection can go here, or in Listener#connected().
        } catch (IOException ex) {
            cc.onFailure(ex.toString());
        }
    }


    public interface ConnectCallback {

        void onSuccess();

        void onFailure(String fail);

        void onNewPlayer(Common.Player player);
    }
}
