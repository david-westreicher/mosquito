package com.madness.mosquito.manager;

import com.artemis.ComponentMapper;
import com.artemis.EntityTransmuter;
import com.artemis.EntityTransmuterFactory;
import com.artemis.Manager;
import com.artemis.World;
import com.artemis.annotations.Wire;
import com.madness.mosquito.components.Position;
import com.madness.mosquito.components.Speed;

/**
 * Created by david on 11/9/15.
 */
@Wire
public class UberFactory extends Manager {
    protected ComponentMapper<Position> mPosition;
    protected ComponentMapper<Speed> mSpeed;
    private EntityTransmuter playerCreator;

    @Override
    protected void initialize() {
        playerCreator = new EntityTransmuterFactory(world).
                add(Position.class).
                add(Speed.class).
                build();
    }

    public int createPlayer(World w) {
        int e = w.create();
        playerCreator.transmute(e);
        Position pos = mPosition.get(e);
        pos.x = (float) Math.random() * 500;
        pos.y = (float) Math.random() * 500;
        Speed speed = mSpeed.get(e);
        speed.x = 1;
        speed.y = -1;
        return e;
    }
}
