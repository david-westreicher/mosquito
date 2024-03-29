package com.madness.mosquito.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.madness.mosquito.Network.Action;
import com.madness.mosquito.Network.ClientNetwork;
import com.madness.mosquito.Network.Common;
import com.madness.mosquito.components.Position;
import com.madness.mosquito.components.RemotePlayer;
import com.madness.mosquito.manager.GameManager;
import com.madness.mosquito.manager.UberFactory;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by juanolon on 29/12/15.
 */
@Wire
public class RemoteSystem extends IteratingSystem {
    protected ComponentMapper<Position> mPosition;
    protected ComponentMapper<RemotePlayer> mRemote;

    HashMap<Integer,Common.PositionAction> states;

    protected ClientNetwork client;


    public RemoteSystem() {
        super(Aspect.all(RemotePlayer.class, Position.class));
    }

    @Override
    protected void initialize() {
        states = new HashMap<Integer, Common.PositionAction>();
    }

    @Override
    protected void begin() {
        LinkedBlockingDeque<Action> in;

        if (world.getSystem(GameManager.class).getGame().isServer) {
            in = world.getSystem(GameManager.class).getGame().server.in;
        } else {
            in = world.getSystem(GameManager.class).getGame().client.in;
        }


        for (Action action : in){
            if (action instanceof Common.PositionAction) {
                Common.PositionAction act = (Common.PositionAction)action;
                if (!states.containsKey(act.id)) {
                    world.getSystem(UberFactory.class).createRemotePlayer(world, act.id);
                }
                states.put(act.id, act);
            }
        }
        in.clear();
    }

    @Override
    protected void process(int e) {
        Position position = mPosition.get(e);
        RemotePlayer remote = mRemote.get(e);

        Common.PositionAction action = states.get(remote.id);
        position.x = action.x;
        position.y = action.y;
    }
}
