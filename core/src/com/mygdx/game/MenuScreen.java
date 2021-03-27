package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.logging.Logger;

import sun.rmi.runtime.Log;

public class MenuScreen implements Screen {

    float delay = 1;


    MainMenuScreen menuScreen;

    Vector3 vector3 = new Vector3();

    Music Cyber;
    SpriteBatch sBatch;
    OrthographicCamera oCamera;

    Texture startButton;
    Texture exitButton;
    Texture background;
    Sprite S_startButton;
    Sprite S_exitButton;
    Sprite S_background;

    private static float BUTTON_RESIZE_FACTOR = 800f;
    private static float START_VERT_POSITION_FACTOR = 2.7f;
    private static float EXIT_VERT_POSITION_FACTOR = 4.2f;

    MenuScreen(MainMenuScreen menuScreen) {

        this.menuScreen = menuScreen;


        float heightY = Gdx.graphics.getHeight();
        float weightX = Gdx.graphics.getWidth();

        oCamera = new OrthographicCamera(heightY, weightX);
        oCamera.setToOrtho(false);
        sBatch = new SpriteBatch();


        startButton = new Texture("startButton.jpg");
        exitButton = new Texture("exitButton.jpg");
        background = new Texture("background.jpg");
        Cyber =  Gdx.audio.newMusic(Gdx.files.internal("Cyber.mp3"));
        Cyber.setLooping(true);
        Cyber.play();


        S_startButton = new Sprite(startButton);
        S_exitButton = new Sprite(exitButton);
        S_background = new Sprite(background);


        S_startButton.setPosition(S_startButton.getWidth() * (weightX / BUTTON_RESIZE_FACTOR), S_startButton.getHeight() * (weightX / BUTTON_RESIZE_FACTOR));
        S_exitButton.setSize(S_exitButton.getWidth() * (weightX / BUTTON_RESIZE_FACTOR), S_exitButton.getHeight() * (weightX / BUTTON_RESIZE_FACTOR));
        S_background.setSize(weightX, heightY);
        S_startButton.setPosition((weightX / 2f - S_startButton.getWidth() / 2), weightX / START_VERT_POSITION_FACTOR);
        S_exitButton.setPosition((weightX / 2f - S_exitButton.getWidth() / 2), weightX / EXIT_VERT_POSITION_FACTOR);
        S_background.setAlpha(0.8f);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sBatch.setProjectionMatrix(oCamera.combined);
        sBatch.begin();
        S_background.draw(sBatch);
        S_startButton.draw(sBatch);
        S_exitButton.draw(sBatch);
        handleTouch();
        sBatch.end();
    }


    void handleTouch(){
        if(Gdx.input.justTouched()) {
            vector3.set(Gdx.input.getX(),Gdx.input.getY(), 0);
            oCamera.unproject(vector3);
            float touchX = vector3.x;
            float touchY= vector3.y;
            if((touchX>=S_startButton.getX()) && touchX<= (S_startButton.getX()+S_startButton.getWidth()) && (touchY>=S_startButton.getY()) && touchY<=(S_startButton.getY()+S_startButton.getHeight()) ){
                Gdx.app.debug("GameScreen", "my error message");
                menuScreen.setScreen( new Vedro(this));
                Gdx.app.debug("GameScreen", "error");
            }
            else if((touchX>=S_exitButton.getX()) && touchX<= (S_exitButton.getX()+S_exitButton.getWidth()) && (touchY>=S_exitButton.getY()) && touchY<=(S_exitButton.getY()+S_exitButton.getHeight()) ){
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        startButton.dispose();
        exitButton.dispose();
        sBatch.dispose();
        Cyber.dispose();
    }
}
