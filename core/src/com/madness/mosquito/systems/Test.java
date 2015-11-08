package com.madness.mosquito.systems;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;

/**
 * Created by david on 11/8/15.
 */
public class Test extends BaseSystem {
    @Override
    protected void processSystem() {
        Gdx.app.log("test", "test");
    }
}
