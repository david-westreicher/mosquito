package com.madness.mosquito.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.madness.mosquito.MosquitoGame;
import com.madness.mosquito.Network.ClientNetwork;
import com.madness.mosquito.Network.Server;

/**
 * Created by juanolon on 21/11/15.
 */

public class ListRoomsScreen extends AbstractScreen {

    Stage stage;
    String status;
    boolean loginSucces = false;

    public ListRoomsScreen(MosquitoGame game) {
        super(game);
    }

    @Override
    public void show() {

        Server server = new Server();
        server.ip = "127.0.0.1";
        Gdx.app.log("succes", Thread.currentThread().toString());

        game.client.connect(server, new ClientNetwork.ConnectCallback(){

            @Override
            public void onSuccess() {
                Gdx.app.log("succes", Thread.currentThread().toString());
                loginSucces = true;
            }

            @Override
            public void onFailure(String fail) {
                status =  "connection failed";
                Gdx.app.log("network",status);
            }
        });

    }

    @Override
    public void render(float delta) {
        stage = new Stage();
        if (loginSucces) {
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {

    }
}