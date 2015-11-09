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
import com.madness.mosquito.Config;
import com.madness.mosquito.manager.CameraManager;
import com.madness.mosquito.manager.MapManager;

/**
 * Created by david on 11/8/15.
 */
public class DrawMap extends BaseSystem {

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
        float[] mapdata = world.getSystem(MapManager.class).verts;
        m = new Mesh(Mesh.VertexDataType.VertexBufferObjectSubData, false, (MapManager.VERTNUM + 2), 0, VertexAttribute.Position());
        float[] verts = new float[(MapManager.VERTNUM + 2) * 3];
        verts[0] = 0;
        verts[1] = -MapManager.Y_VARIANCE;
        verts[2] = 0;

        for (int i = 1; i <= MapManager.VERTNUM; i++) {
            verts[i * 3 + 0] = (i - 1) * Config.MAP_XDIST;
            verts[i * 3 + 1] = mapdata[i - 1];
            verts[i * 3 + 2] = 0;
        }
        verts[(MapManager.VERTNUM + 1) * 3 + 0] = Config.MAP_XDIST * (MapManager.VERTNUM - 1);
        verts[(MapManager.VERTNUM + 1) * 3 + 1] = -MapManager.Y_VARIANCE;
        verts[(MapManager.VERTNUM + 1) * 3 + 2] = 0;
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
    }
}
