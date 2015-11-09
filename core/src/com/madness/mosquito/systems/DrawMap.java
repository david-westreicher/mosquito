package com.madness.mosquito.systems;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by david on 11/8/15.
 */
public class DrawMap extends BaseSystem {

    private SpriteBatch batch;
    private Texture img;
    private Mesh m;

    @Override
    protected void initialize() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        //m = new Mesh(Mesh.VertexDataType.VertexBufferObjectSubData,false,100,0,)
    }

    @Override
    protected void processSystem() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }
}
