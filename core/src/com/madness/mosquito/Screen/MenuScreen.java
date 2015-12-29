package com.madness.mosquito.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.madness.mosquito.MosquitoGame;
import com.madness.mosquito.lib.MenuAction;
import com.madness.mosquito.lib.MenuBuilder;
import com.madness.mosquito.systems.ClientSystem;
import com.madness.mosquito.systems.ServerSystem;

/**
 * Created by juanolon on 08/11/15.
 */
public class MenuScreen extends AbstractScreen {

    Stage stage;

    public MenuScreen(final MosquitoGame game) {
        super(game);

        boolean debug = true;

        stage = new Stage();

        // TODO check controller support
        InputProcessor input = Gdx.input.getInputProcessor();
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        if (input != null)
            inputMultiplexer.addProcessor(input);
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        MenuBuilder menu = new MenuBuilder(stage, debug);
        menu
                .addButton("List Servers", new MenuAction() {
                    @Override
                    public void act() {
                        // open list screen
                        //game.client.connect();
                        game.isServer = false;
                        game.artemis.getSystem(ServerSystem.class).setEnabled(false);
                        game.setScreen(new ListRoomsScreen(game));
                    }
                }, false)
                .addButton("Start Server", new MenuAction() {
                    @Override
                    public void act() {
                        // start server
                        game.isServer = true;
                        game.artemis.getSystem(ClientSystem.class).setEnabled(false);
                        game.server.start();
                        game.setScreen(new GameScreen(game));
                    }
                }, debug)
                .addButton("Exit", new MenuAction() {
                    @Override
                    public void act() {
//                        game.server.stop();
//                        game.client.stop();
                        closeMenu();
                    }
                }, false)
        ;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {

    }

    private void closeMenu() {
        Gdx.app.exit();
    }

}