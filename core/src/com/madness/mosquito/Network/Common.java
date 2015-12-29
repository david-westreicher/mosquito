package com.madness.mosquito.Network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;

// This class is a convenient place to keep things common to both the client and server.
public class Common {
    static public final int port = 54555;

    // This registers objects that are going to be sent over the network.
    static public void register (EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(Login.class);
        kryo.register(LoginSuccess.class);
        kryo.register(RegistrationRequired.class);
        kryo.register(Register.class);
        kryo.register(UpdateCharacter.class);
        kryo.register(RemoveCharacter.class);
        kryo.register(Player.class);
        kryo.register(MoveCharacter.class);
    }

    static class PlayerConnection extends Connection {
        public Player player;
    }


    static public class Login {
        public String name;
    }

    static public class RegistrationRequired {
    }

    static public class Register {
        public String name;
    }

    static public class Player {
        public String name;
        public int id, x, y;
    }



    // deprecated
    static public class UpdateCharacter {
        public int id, x, y;
    }

    static public class RemoveCharacter {
        public int id;
    }

    static public class MoveCharacter {
        public int x, y;
    }

    static public class LoginSuccess {
        public boolean status;
    }
}