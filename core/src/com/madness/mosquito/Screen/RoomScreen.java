package com.madness.mosquito.Screen;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.madness.mosquito.MosquitoGame;

/**
 * Created by juanolon on 21/11/15.
 */

public class RoomScreen extends AbstractScreen {

    Stage stage;

    public RoomScreen(MosquitoGame game) {
        super(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage = new Stage();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {

    }
}