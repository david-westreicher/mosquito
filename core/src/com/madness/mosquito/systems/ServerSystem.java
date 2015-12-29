package com.madness.mosquito.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.madness.mosquito.Network.Action;
import com.madness.mosquito.Network.Common;
import com.madness.mosquito.Network.ServerNetwork;
import com.madness.mosquito.components.LocalPlayer;
import com.madness.mosquito.components.Position;
import com.madness.mosquito.components.RemotePlayer;
import com.madness.mosquito.manager.GameManager;

import java.util.HashMap;

/**
 * Created by juanolon on 12/12/15.
 */
public class ServerSystem extends IteratingSystem
{
    protected ServerNetwork server;

    protected ComponentMapper<Position> mPosition;
    protected ComponentMapper<RemotePlayer> mRemote;

    public ServerSystem() {
        super(Aspect.all(RemotePlayer.class, Position.class));
    }

    @Override
    protected void initialize() {

        super.initialize();
    }


    @Override
    protected void begin() {
        server = world.getSystem(GameManager.class).getGame().server;
    }

    @Override
    protected void end() {
        server.flush();
    }

    @Override
    protected void process(int e) {
        Position position = mPosition.get(e);
        RemotePlayer remote = mRemote.get(e);

        Common.PositionAction positionAction = new Common.PositionAction();
        positionAction.id = remote.id;
        positionAction.x = position.x;
        positionAction.y = position.y;

        server.out.add(positionAction);
    }

}
