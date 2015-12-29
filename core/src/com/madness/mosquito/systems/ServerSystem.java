package com.madness.mosquito.systems;

import com.artemis.Aspect;
import com.artemis.systems.IteratingSystem;

/**
 * Created by juanolon on 12/12/15.
 */
public class ServerSystem extends IteratingSystem
{

    /**
     * Creates a new EntityProcessingSystem.
     *
     * @param aspect the aspect to match entities
     */
    public ServerSystem(Aspect.Builder aspect) {
        super(aspect);
    }

    @Override
    protected void initialize() {

        super.initialize();
    }

    @Override
    protected void process(int entityId) {

//        if (com.madness.mosquito.ServerSystem.ServerSystem.hasPlayer(entityId)) {
//            com.madness.mosquito.ServerSystem.ServerSystem.Player player = com.madness.mosquito.ServerSystem.ServerSystem.getPlayer(entityId);
//            // coponent = getComponent(entityId)
//            // componsent.baba = player.baba;
//            // ...
//        }

    }

}
