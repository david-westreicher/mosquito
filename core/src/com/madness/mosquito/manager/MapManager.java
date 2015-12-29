package com.madness.mosquito.manager;

import com.artemis.Manager;

import java.util.Random;

/**
 * Created by david on 11/9/15.
 */
public class MapManager extends Manager {
    public static final int VERTNUM = 50;
    public static final float Y_VARIANCE = 100;
    public float[] verts = new float[VERTNUM];

    @Override
    protected void initialize() {
        Random r = new Random(123);

        for (int i = 0; i < VERTNUM; i++)
            verts[i] = (r.nextFloat() * Y_VARIANCE - Y_VARIANCE / 2);
    }
}
