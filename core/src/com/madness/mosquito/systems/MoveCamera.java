package com.madness.mosquito.systems;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.madness.mosquito.components.LocalPlayer;
import com.madness.mosquito.components.Position;
import com.madness.mosquito.manager.CameraManager;

/**
 * Created by david on 11/9/15.
 */
public class MoveCamera extends IteratingSystem {
    protected ComponentMapper<Position> mPosition;
    private float max;
    private float maxy;

    public MoveCamera() {
        super(Aspect.all(Position.class, LocalPlayer.class));
    }

    @Override
    protected void begin() {
        max = 0;
        maxy = 0;
    }

    @Override
    protected void end() {
        OrthographicCamera cam = world.getSystem(CameraManager.class).cam;
        cam.position.x = max;
        cam.position.y = maxy;
//        cam.zoom = Math.abs((float) Math.sin(Gdx.graphics.getFrameId() / 1000.0)) + 0.1f;
        cam.zoom = 1.1f;
        cam.update();
    }

    @Override
    protected void process(int e) {
        Position pos = mPosition.get(e);
        if (pos.x > max) {
            max = pos.x;
            maxy = pos.y;
        }
    }

}
