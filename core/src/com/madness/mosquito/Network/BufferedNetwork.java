package com.madness.mosquito.Network;

import java.util.ArrayList;

/**
 * Created by juanolon on 29/12/15.
 */
public abstract class BufferedNetwork {
    ArrayList<Action> in;
    ArrayList<Action> out;

    public void push(Action action){
        in.add(action);
    }

    public void pop(Action action){
        out.add(action);
    }

    abstract void close();
}
