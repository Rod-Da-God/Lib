package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.Rectangle;

import java.util.Iterator;

public class Vedro extends Game implements Screen {
    OrthographicCamera camera;
    SpriteBatch batch;
    Texture backgroundTexture;
    Texture rain;
    Texture playerModel;
    Texture space_shipModel;
    Sprite Player;
    Sound dropSound;
    Sound MhitSound;
    Music rainMusic;
    Sprite spriteBackground;
    Rectangle rectPlayer;
    Rectangle rectSpace_ship;
    Vector3 vector;
    Array<Rectangle> raindrops;
    long lastDropTime;
    static Texture backTexture;
    static Sprite backSprite;
    Vector3 vector3 = new Vector3();

    Game game;



    @Override
    public void show() {

    }

    Vedro(MenuScreen game){
        float heightY = Gdx.graphics.getHeight();
        float weightX = Gdx.graphics.getWidth();


        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        batch = new SpriteBatch();

        vector = new Vector3();

        rain = new Texture("anamy-removebg-preview (1).png");
        playerModel = new Texture("sp-removebg-preview_cut-photo.ru (1).png");
        space_shipModel = new Texture("space_ship.png");
        backgroundTexture = new Texture("background_img.jpg");

        dropSound = Gdx.audio.newSound(Gdx.files.internal("waterdrop.wav"));
        MhitSound =  Gdx.audio.newSound(Gdx.files.internal("hit.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("bombastic.mp3"));

        rainMusic.setLooping(true);
        rainMusic.setVolume(0.5f);
        rainMusic.play();


        rectPlayer = new Rectangle();
        rectPlayer.x = 800 / 2 - 64 / 2;
        rectPlayer.y = 20;
        rectPlayer.width = 64;
        rectPlayer.height = 64;


        rectSpace_ship = new Rectangle();
        rectSpace_ship.x = 0;
        rectSpace_ship.y =-60;
        rectSpace_ship.width = 800;
        rectSpace_ship.height = 80;

        spriteBackground = new Sprite(backgroundTexture);
        spriteBackground.setSize(800,480);
        spriteBackground.setAlpha(1f);



        raindrops = new Array<Rectangle>();
        spawnRaindrop();

    }
    public void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0,800-64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        spriteBackground.draw(batch);
        batch.draw(space_shipModel,rectSpace_ship.x,rectSpace_ship.y);
        batch.draw(playerModel, rectPlayer.x, rectPlayer.y);
        for (Rectangle raindrop : raindrops) {
            batch.draw(rain, raindrop.x, raindrop.y);
        }
        batch.end();



        if (Gdx.input.isTouched()) {
            vector.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(vector);
            rectPlayer.x = (int) (vector.x - 64 / 2);
            rectPlayer.y = (int) (vector.y - 64 / 2);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            rectPlayer.y += 10000 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
            rectPlayer.x += 10000 * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            rectPlayer.x -= 500 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            rectPlayer.x += 500 * Gdx.graphics.getDeltaTime();


        if (Gdx.input.isKeyPressed(Input.Keys.A))
            rectPlayer.x -= 500 * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.D))
            rectPlayer.x += 500 * Gdx.graphics.getDeltaTime();


        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) rectPlayer.y -= 600 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) rectPlayer.y += 600 * Gdx.graphics.getDeltaTime();


        if (Gdx.input.isKeyPressed(Input.Keys.S)) rectPlayer.y -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) rectPlayer.y += 200 * Gdx.graphics.getDeltaTime();


        if (this.rectPlayer.x < 0) rectPlayer.x = 800 - 64;
        if (rectPlayer.x > 800 - 64) rectPlayer.x = 0;

        if (rectPlayer.y < 0) rectPlayer.y = 480 - 64;
        if (rectPlayer.y > 480 - 64) rectPlayer.y = 0;

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

        Iterator<Rectangle> iter = raindrops.iterator();
        while (iter.hasNext()) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (raindrop.y + 64 < 18) iter.remove();
            if (raindrop.overlaps(rectPlayer)) {
                MhitSound.play();
                iter.remove();
            }
            if (raindrop.overlaps(rectSpace_ship)){
                dropSound.play();
                iter.remove();
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
        rain.dispose();
        playerModel.dispose();
        space_shipModel.dispose();
        dropSound.dispose();
        rainMusic.dispose();
        batch.dispose();
    }
}
