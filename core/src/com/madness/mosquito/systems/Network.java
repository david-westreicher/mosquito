package com.madness.mosquito.systems;

import com.artemis.Aspect;
import com.artemis.systems.IteratingSystem;

/**
 * Created by juanolon on 12/12/15.
 */
public class Network extends IteratingSystem
{

    /**
     * Creates a new EntityProcessingSystem.
     *
     * @param aspect the aspect to match entities
     */
    public Network(Aspect.Builder aspect) {
        super(aspect);
    }

    @Override
    protected void initialize() {

        super.initialize();
    }

    @Override
    protected void process(int entityId) {
//        if (com.madness.mosquito.Network.Network.hasPlayer(entityId)) {
//            com.madness.mosquito.Network.Network.Player player = com.madness.mosquito.Network.Network.getPlayer(entityId);
//            // coponent = getComponent(entityId)
//            // componsent.baba = player.baba;
//            // ...
//        }

    }

}
