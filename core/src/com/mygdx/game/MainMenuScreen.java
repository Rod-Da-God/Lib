package com.mygdx.game;

import com.badlogic.gdx.Game;


public class MainMenuScreen extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }


    }
