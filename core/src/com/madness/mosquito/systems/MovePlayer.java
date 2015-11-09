package com.madness.mosquito.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.madness.mosquito.components.Position;
import com.madness.mosquito.components.Speed;
import com.madness.mosquito.manager.MapManager;

/**
 * Created by david on 11/9/15.
 */
@Wire
public class MovePlayer extends IteratingSystem {
    protected ComponentMapper<Position> mPosition;
    protected ComponentMapper<Speed> mSpeed;

    public MovePlayer() {
        super(Aspect.all(Position.class, Speed.class));
    }

    @Override
    protected void process(int e) {
        Position pos = mPosition.get(e);
        Speed speed = mSpeed.get(e);
        pos.x += speed.x;
        pos.y += speed.y;
    }
}
