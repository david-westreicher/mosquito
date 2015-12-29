package com.madness.mosquito.manager;

import com.artemis.Manager;
import com.madness.mosquito.MosquitoGame;

/**
 * Created by juanolon on 29/12/15.
 */
public class GameManager extends Manager {

    MosquitoGame game;

    public void setGame(MosquitoGame game) {
        this.game = game;
    }

    public MosquitoGame getGame() {
        return game;
    }
}
