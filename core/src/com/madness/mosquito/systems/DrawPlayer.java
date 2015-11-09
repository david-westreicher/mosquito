package com.madness.mosquito.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madness.mosquito.manager.CameraManager;
import com.madness.mosquito.components.Position;

/**
 * Created by david on 11/9/15.
 */
@Wire
public class DrawPlayer extends IteratingSystem {
    protected ComponentMapper<Position> mPosition;
    private SpriteBatch batch;
    private Texture img;


    public DrawPlayer() {
        super(Aspect.all(Position.class));
    }

    @Override
    protected void initialize() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    @Override
    protected void begin() {
        OrthographicCamera cam = world.getSystem(CameraManager.class).cam;
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
    }

    @Override
    protected void process(int e) {
        Position pos = mPosition.get(e);
        Gdx.app.log("pos", pos.x + "," + pos.y);
        batch.draw(img, pos.x, pos.y, 10, 10);
    }

    @Override
    protected void end() {
        batch.end();
    }

}
