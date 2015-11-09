package com.madness.mosquito.Screen;

import com.badlogic.gdx.Gdx;
import com.madness.mosquito.MosquitoGame;

/**
 * Created by juanolon on 08/11/15.
 */
public class LoadingScreen extends AbstractScreen{

    public LoadingScreen(MosquitoGame game) {
        super(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }
}
