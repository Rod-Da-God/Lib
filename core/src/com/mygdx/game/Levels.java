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

import javax.crypto.Cipher;

public class Levels extends Game implements Screen {
    Vector3 vector3 = new Vector3();
    MenuScreen levels;
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
    SpriteBatch Batch;
    OrthographicCamera Camera;

    Levels(MenuScreen levels){
        this.levels = levels;

        float heightY = Gdx.graphics.getHeight();
        float weightX = Gdx.graphics.getWidth();
        oCamera = new OrthographicCamera(heightY, weightX);
        oCamera.setToOrtho(false);
        sBatch = new SpriteBatch();
        startButton = new Texture("startButton.jpg");
        Cyber =  Gdx.audio.newMusic(Gdx.files.internal("Cyber.mp3"));
        Cyber.setLooping(true);
        Cyber.play();
        S_startButton = new Sprite(startButton);
        S_startButton.setPosition(S_startButton.getWidth() * (weightX / BUTTON_RESIZE_FACTOR), S_startButton.getHeight() * (weightX / BUTTON_RESIZE_FACTOR));
        S_startButton.setPosition((weightX / 2f - S_startButton.getWidth() / 2), weightX / START_VERT_POSITION_FACTOR);
    }




    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sBatch.setProjectionMatrix(oCamera.combined);
        sBatch.begin();
        S_startButton.draw(sBatch);
        handleTouch();
    }
    void handleTouch(){
        if(Gdx.input.justTouched()) {
            vector3.set(Gdx.input.getX(),Gdx.input.getY(), 0);
            oCamera.unproject(vector3);
            float touchX = vector3.x;
            float touchY= vector3.y;
            if((touchX>=S_startButton.getX()) && touchX<= (S_startButton.getX()+S_startButton.getWidth()) && (touchY>=S_startButton.getY()) && touchY<=(S_startButton.getY()+S_startButton.getHeight()) ){

             //   levels.setScreen( new Levels(levels));

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

    }

    @Override
    public void dispose() {

    }
}
