package com.madness.mosquito.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.madness.mosquito.Network.ClientNetwork;
import com.madness.mosquito.Network.Common;
import com.madness.mosquito.components.LocalPlayer;
import com.madness.mosquito.components.Position;
import com.madness.mosquito.manager.GameManager;

/**
 * Created by juanolon on 29/12/15.
 */
@Wire
public class ClientSystem extends IteratingSystem {
    protected ComponentMapper<Position> mPosition;
    protected ComponentMapper<LocalPlayer> mLocal;

    protected ClientNetwork client;

    public ClientSystem() {
        super(Aspect.all(LocalPlayer.class, Position.class));
    }

    @Override
    protected void begin() {
        client = world.getSystem(GameManager.class).getGame().client;
    }

    @Override
    protected void end() {
        client.flush();
    }

    @Override
    protected void process(int e) {
        Position position = mPosition.get(e);
        LocalPlayer local = mLocal.get(e);

        Common.PositionAction positionAction = new Common.PositionAction();
        positionAction.id = local.id;
        positionAction.x = position.x;
        positionAction.y = position.y;

        client.out.add(positionAction);
    }
}
