package com.madness.mosquito.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.madness.mosquito.Config;
import com.madness.mosquito.components.Position;
import com.madness.mosquito.manager.MapManager;

/**
 * Created by david on 11/9/15.
 */
@Wire
public class CollisionSystem extends IteratingSystem {
    protected ComponentMapper<Position> mPosition;
    private float[] map;

    public CollisionSystem() {
        super(Aspect.all(Position.class));
    }

    @Override
    protected void begin() {
        map = world.getSystem(MapManager.class).verts;
    }

    @Override
    protected void process(int e) {
        Position pos = mPosition.get(e);
        if (pos.x < 0) return;
        int mapindex = (int) (pos.x / Config.MAP_XDIST);
        if (mapindex + 1 >= map.length) return;
        float heighta = map[mapindex];
        float heightb = map[mapindex + 1];
        float interp = (pos.x % Config.MAP_XDIST) / (Config.MAP_XDIST);
        float mapheight = heightb * interp + heighta * (1f - interp);
        pos.y = Math.max(mapheight, pos.y);
    }

}
