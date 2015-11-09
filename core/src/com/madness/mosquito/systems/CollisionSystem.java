package com.madness.mosquito.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.madness.mosquito.Config;
import com.madness.mosquito.components.Position;
import com.madness.mosquito.components.Speed;
import com.madness.mosquito.manager.MapManager;

/**
 * Created by david on 11/9/15.
 */
@Wire
public class CollisionSystem extends IteratingSystem {
    protected ComponentMapper<Position> mPosition;
    protected ComponentMapper<Speed> mSpeed;
    private float[] map;

    public CollisionSystem() {
        super(Aspect.all(Position.class, Speed.class));
    }

    @Override
    protected void begin() {
        map = world.getSystem(MapManager.class).verts;
    }

    private float getHeight(float pos) {
        if (pos < 0) return -MapManager.Y_VARIANCE;
        int mapindex = (int) (pos / Config.MAP_XDIST);
        if (mapindex + 1 >= map.length) return -MapManager.Y_VARIANCE;
        float heighta = map[mapindex];
        float heightb = map[mapindex + 1];
        float interp = (pos % Config.MAP_XDIST) / (Config.MAP_XDIST);
        return heightb * interp + heighta * (1f - interp);
    }

    @Override
    protected void process(int e) {
        Position pos = mPosition.get(e);
        Speed speed = mSpeed.get(e);

        float mapheight = getHeight(pos.x);
        if (pos.y < mapheight) {
            speed.y = -mapheight + getHeight(pos.x + speed.x);
        }
        pos.y = Math.max(mapheight, pos.y);
    }

}
