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

    SpriteBatch batch;
    Texture img;


    public GameScreen(MosquitoGame game) {
        super(game);

        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.artemis.process();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        game.artemis.resize(width, height);
    }

    @Override
    public void hide() {

    }
}