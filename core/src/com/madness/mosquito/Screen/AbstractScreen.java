package com.madness.mosquito.Screen;

import com.badlogic.gdx.Screen;
import com.madness.mosquito.MosquitoGame;

/**
 * Created by juanolon on 08/11/15.
 */
public abstract class AbstractScreen implements Screen {

    protected MosquitoGame game;

    public AbstractScreen(MosquitoGame game) {
        this.game = game;
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
