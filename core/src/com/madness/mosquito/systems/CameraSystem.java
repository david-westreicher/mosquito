package com.madness.mosquito.systems;

import com.artemis.Manager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by david on 11/8/15.
 */
public class CameraSystem extends Manager {
    private OrthographicCamera cam;
    private ScreenViewport viewport;

    @Override
    protected void initialize() {
        cam = new OrthographicCamera();
        viewport = new ScreenViewport(cam);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
