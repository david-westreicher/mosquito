package com.madness.mosquito;

import com.artemis.BaseSystem;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.madness.mosquito.systems.Test;

import java.util.ArrayList;

/**
 * Created by david on 11/8/15.
 */
public class Artemis extends World {
    public static final ArrayList<Class> LOGIC_SYSTEMS = new ArrayList<Class>();

    public static Artemis init() {
        WorldConfiguration config = new WorldConfiguration();
        //LOGIC
        addLogic(config, Test.class);

        //RENDERING
        //config.setSystem();

        Artemis a = new Artemis();
        a.setInvocationStrategy(new FixedTimestepStrategy(a));
        return a;
    }

    private static void addLogic(WorldConfiguration config, Class<? extends BaseSystem> clss) {
        LOGIC_SYSTEMS.add(clss);
        config.setSystem(clss);
    }


    public void resize(int width, int height) {
    }

    public void dispose() {
        super.dispose();
        LOGIC_SYSTEMS.clear();
    }
}