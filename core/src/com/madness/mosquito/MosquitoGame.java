package com.madness.mosquito;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.madness.mosquito.Screen.GameScreen;
import com.madness.mosquito.Screen.LoadingScreen;

public class MosquitoGame extends Game {

    public AssetManager manager = new AssetManager();
    public Artemis artemis;


    @Override
    public void create() {
        artemis = Artemis.init();
        //setScreen(new LoadingScreen(this));
        setScreen(new GameScreen(this));
    }

    @Override
    public void dispose() {
        artemis.dispose();
        artemis = null;
    }
}
