package com.madness.mosquito.components;

import com.artemis.PooledComponent;

/**
 * Created by david on 11/9/15.
 */
public class Speed extends PooledComponent {
    public float x;
    public float y;

    @Override
    protected void reset() {
        x = 0;
        y = 0;
    }
}
