package com.madness.mosquito;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MosquitoMain extends ApplicationAdapter {

    private Artemis artemis;

    @Override
    public void create() {
        artemis = Artemis.init();
    }

    @Override
    public void render() {
        artemis.process();
    }

    @Override
    public void resize(int width, int height) {
        artemis.resize(width, height);
    }

    @Override
    public void dispose() {
        artemis.dispose();
        artemis = null;
    }
}
