package com.madness.mosquito.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madness.mosquito.MosquitoGame;

/**
 * Created by juanolon on 08/11/15.
 */
public class GameScreen extends AbstractScreen {

    public GameScreen(MosquitoGame game) {
        super(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.artemis.process();
    }

    @Override
    public void resize(int width, int height) {
        game.artemis.resize(width, height);
    }

    @Override
    public void hide() {

    }
}