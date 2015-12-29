package com.madness.mosquito.Network;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by juanolon on 29/12/15.
 */
public abstract class BufferedNetwork {

    public LinkedBlockingDeque<Action> in = new LinkedBlockingDeque<Action>();
    public LinkedBlockingDeque<Action> out = new LinkedBlockingDeque<Action>();

    abstract void close();
}
