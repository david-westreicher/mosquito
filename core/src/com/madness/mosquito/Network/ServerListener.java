package com.madness.mosquito.Network;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.*;
import com.esotericsoftware.kryonet.Connection;

/**
 * Created by juanolon on 27/12/15.
 */
abstract public class ServerListener extends Listener {

    @Override
    public void received(Connection c, Object object) {
        super.received(c, object);
        Gdx.app.log("server", "object: " + object.toString());
        // We know all connections for this server are actually CharacterConnections.
        Common.PlayerConnection connection = (Common.PlayerConnection)c;
        Common.Player player = connection.player;

        if (object instanceof Common.Login) {
            onLogin((Common.Login)object, connection);
        } else if (object instanceof Action){
            onAction((Action) object, connection);
        }

    }

    abstract void onAction(Action login, Common.PlayerConnection connection);

    abstract void onLogin(Common.Login login, Common.PlayerConnection connection);
}
