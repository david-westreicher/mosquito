package com.madness.mosquito;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.madness.mosquito.Network.ClientNetwork;
import com.madness.mosquito.Network.ServerNetwork;
import com.madness.mosquito.Screen.MenuScreen;

public class MosquitoGame extends Game {

    public AssetManager manager = new AssetManager();
    public Artemis artemis;

    public ServerNetwork server;
    public ClientNetwork client;

    @Override
    public void create() {
        artemis = Artemis.init();

        server = new ServerNetwork();
        client = new ClientNetwork();

        //setScreen(new LoadingScreen(this));
//        setScreen(new GameScreen(this));
        setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        artemis.dispose();
        artemis = null;
    }
}
