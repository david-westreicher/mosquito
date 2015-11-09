package com.madness.mosquito.systems;

import com.artemis.BaseSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.madness.mosquito.manager.CameraManager;

/**
 * Created by david on 11/9/15.
 */
public class MoveCamera extends BaseSystem {
    @Override
    protected void processSystem() {
        OrthographicCamera cam = world.getSystem(CameraManager.class).cam;
        cam.position.x += 1f;
        //cam.zoom += 0.02f;
        cam.update();
    }
}
