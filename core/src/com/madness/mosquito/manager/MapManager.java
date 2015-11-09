package com.madness.mosquito.manager;

import com.artemis.Manager;

/**
 * Created by david on 11/9/15.
 */
public class MapManager extends Manager {
    public static final int VERTNUM = 100;
    public float[] verts = new float[VERTNUM];

    @Override
    protected void initialize() {
        for (int i = 0; i < VERTNUM; i++)
            verts[i] = (float) (Math.random() * 100 - 50);
    }
}