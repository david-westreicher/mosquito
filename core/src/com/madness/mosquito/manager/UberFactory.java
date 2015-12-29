package com.madness.mosquito.manager;

import com.artemis.ComponentMapper;
import com.artemis.EntityTransmuter;
import com.artemis.EntityTransmuterFactory;
import com.artemis.Manager;
import com.artemis.World;
import com.artemis.annotations.Wire;
import com.madness.mosquito.components.LocalPlayer;
import com.madness.mosquito.components.Position;
import com.madness.mosquito.components.RemotePlayer;
import com.madness.mosquito.components.Speed;

/**
 * Created by david on 11/9/15.
 */
@Wire
public class UberFactory extends Manager {
    protected ComponentMapper<RemotePlayer> mRemotePlayer;
    protected ComponentMapper<Position> mPosition;
    protected ComponentMapper<Speed> mSpeed;
    private EntityTransmuter playerCreator;
    private EntityTransmuter playerRemoteCreator;

    @Override
    protected void initialize() {
        playerCreator = new EntityTransmuterFactory(world).
                add(Position.class).
                add(Speed.class).
                add(LocalPlayer.class).
                build();

        playerRemoteCreator = new EntityTransmuterFactory(world).
                add(Position.class).
                add(RemotePlayer.class).
                build();
    }

    public int createPlayer(World w) {
        int e = w.create();
        playerCreator.transmute(e);
        Position pos = mPosition.get(e);
        pos.x = (float) 300;
        pos.y = (float) 300;
        Speed speed = mSpeed.get(e);
        speed.x = (float) 0.1f;
        speed.y = 0;
        return e;
    }

    public int createRemotePlayer(World w, int id) {
        int e = w.create();
        playerRemoteCreator.transmute(e);
        Position pos = mPosition.get(e);
        RemotePlayer player = mRemotePlayer.get(e);

        player.id = id;
        pos.x = (float) Math.random() * 500;
        pos.y = (float) Math.random() * 500;
        return e;
    }
}
