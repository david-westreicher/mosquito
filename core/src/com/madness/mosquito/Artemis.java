package com.madness.mosquito;

import com.artemis.BaseSystem;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.badlogic.gdx.Game;
import com.madness.mosquito.manager.CameraManager;
import com.madness.mosquito.manager.NetworkManager;
import com.madness.mosquito.manager.UberFactory;
import com.madness.mosquito.systems.ClientSystem;
import com.madness.mosquito.systems.CollisionSystem;
import com.madness.mosquito.systems.DrawMap;
import com.madness.mosquito.manager.MapManager;
import com.madness.mosquito.systems.DrawPlayer;
import com.madness.mosquito.systems.MoveCamera;
import com.madness.mosquito.systems.MovePlayer;
import com.madness.mosquito.systems.RemoteSystem;
import com.madness.mosquito.systems.ServerSystem;
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

    public static Artemis init(MosquitoGame game) {
        WorldConfiguration config = new WorldConfiguration();
        //MANAGER
        config.setSystem(CameraManager.class);
        config.setSystem(MapManager.class);
        config.setSystem(UberFactory.class);

        config.setSystem(NetworkManager.class);

        //LOGIC
        addLogic(config, MoveCamera.class);
        addLogic(config, MovePlayer.class);
        addLogic(config, CollisionSystem.class);

        if (game.isServer) {
            addLogic(config, ServerSystem.class);
        } else {
            addLogic(config, ClientSystem.class);
            addLogic(config, RemoteSystem.class);
        }

        //RENDERING
        config.setSystem(StartRendering.class);
        config.setSystem(DrawMap.class);
        config.setSystem(DrawPlayer.class);

        Artemis a = new Artemis(config);
        a.setInvocationStrategy(new FixedTimestepStrategy(a));
        a.getSystem(NetworkManager.class).setGame(game);
        addPlayer(a);
        return a;
    }

    private static void addPlayer(Artemis a) {
//        for (int i = 0; i < 1; i++)
            a.getSystem(UberFactory.class).createPlayer(a);
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
