package com.madness.mosquito.Network;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.HashSet;

/**
 * Created by juanolon on 29/12/15.
 */
public class ServerNetwork extends BufferedNetwork {

    Server server;

    HashSet<Common.Player> loggedIn = new HashSet();

    public HashSet<Common.Player> getPlayers(){
        return loggedIn;
    }

    public void close(){
        if (server != null) {
            server.stop();
        }
    }

    public void flush(){
        for (Action action : out) {
            server.sendToAllTCP(action);
        }
        out.clear();
    }

    public void start() {

        // creates new server
        server = new Server() {
            protected Connection newConnection () {
                // By providing our own connection implementation, we can store per
                // connection state without a connection ID to state look up.
                return new Common.PlayerConnection();
            }
        };


        // For consistency, the classes to be sent over the network are
        // registered by the same method for both the client and server.
        Common.register(server);

        server.addListener(new ServerListener() {

            @Override
            void onAction(Action action, Common.PlayerConnection connection) {
                in.add(action);
            }

            @Override
            void onLogin(Common.Login login, Common.PlayerConnection c) {

                Common.Player player = new Common.Player();
                player.name = login.name;
                c.player = player;

                c.sendTCP(new Common.LoginSuccess());

                // Add existing characters to new logged in connection.
                for (Common.Player other : loggedIn) {
                    c.sendTCP(other);
                }

                loggedIn.add(player);

                // Add logged in player to all connections.
                server.sendToAllTCP(player);
                return;
            }

            public void disconnected(Connection c) {
                Common.PlayerConnection connection = (Common.PlayerConnection) c;
                if (connection.player != null) {
                    loggedIn.remove(connection.player);

                    Common.RemoveCharacter removeCharacter = new Common.RemoveCharacter();
                    removeCharacter.id = connection.player.id;
                    server.sendToAllTCP(removeCharacter);
                }
            }
        });

        try{
            server.bind(Common.port);
        } catch (IOException e) {
            Gdx.app.log("server", "boom"+e.toString());
        }
        server.start();
    }
}
