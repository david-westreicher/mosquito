package com.madness.mosquito.systems;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.madness.mosquito.manager.CameraManager;
import com.madness.mosquito.manager.MapManager;

/**
 * Created by david on 11/8/15.
 */
public class DrawMap extends BaseSystem {

    private SpriteBatch batch;
    private Texture img;
    private Mesh m;
    private ShaderProgram shader;
    private OrthographicCamera cam;

    private String createVertex() {
        return ""//
                + "uniform mat4 mvp;\n"//
                + "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"//
                + "void main(){\n"//
                + "    gl_Position=mvp*" + ShaderProgram.POSITION_ATTRIBUTE + ";\n "//
                + "}\n"//
                ;
    }

    private String createFragment() {
        return ""//
                + "void main(){\n"//
                + "    gl_FragColor = vec4(1.0,1.0,1.0,1.0);"//
                + "}\n"//
                ;
    }

    @Override
    protected void initialize() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        float[] mapdata = world.getSystem(MapManager.class).verts;
        m = new Mesh(Mesh.VertexDataType.VertexBufferObjectSubData, false, 100, 0, VertexAttribute.Position());
        float[] verts = new float[100 * 3];
        for (int i = 0; i < 100; i++) {
            verts[i * 3 + 0] = i * 80;
            verts[i * 3 + 1] = mapdata[i];
            verts[i * 3 + 2] = 0;
        }
        verts[0 * 3 + 1] = -50;
        verts[99 * 3 + 1] = -50;
        m.setVertices(verts);
        shader = new ShaderProgram(createVertex(), createFragment());
        if (!shader.isCompiled())
            Gdx.app.log("shader not compiled: ", "" + shader.getLog());
        cam = world.getSystem(CameraManager.class).cam;
    }

    @Override
    protected void processSystem() {
        shader.begin();
        shader.setUniformMatrix("mvp", cam.combined);
        m.render(shader, GL20.GL_LINE_LOOP);
        shader.end();
        //batch.begin();
        //batch.draw(img, 0, 0);
        //batch.end();
    }
}
