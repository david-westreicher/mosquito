package com.madness.mosquito;

import com.artemis.BaseSystem;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.madness.mosquito.manager.CameraManager;
import com.madness.mosquito.systems.DrawMap;
import com.madness.mosquito.manager.MapManager;
import com.madness.mosquito.systems.MoveCamera;
import com.madness.mosquito.systems.StartRendering;

import java.util.ArrayList;

/**
 * Created by david on 11/8/15.
 */
public class Artemis extends World {
    public static final ArrayList<Class> LOGIC_SYSTEMS = new ArrayList<Class>();

    public Artemis(WorldConfiguration config) {
        super(config);
    }

    public static Artemis init() {
        WorldConfiguration config = new WorldConfiguration();
        //MANAGER
        config.setSystem(CameraManager.class);
        config.setSystem(MapManager.class);

        //LOGIC
        addLogic(config, MoveCamera.class);

        //RENDERING
        config.setSystem(StartRendering.class);
        config.setSystem(DrawMap.class);

        Artemis a = new Artemis(config);
        a.setInvocationStrategy(new FixedTimestepStrategy(a));
        return a;
    }

    private static void addLogic(WorldConfiguration config, Class<? extends BaseSystem> clss) {
        LOGIC_SYSTEMS.add(clss);
        config.setSystem(clss);
    }


    public void resize(int width, int height) {
        getSystem(CameraManager.class).resize(width, height);
    }

    public void dispose() {
        super.dispose();
        LOGIC_SYSTEMS.clear();
    }
}
