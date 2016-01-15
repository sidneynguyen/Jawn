package com.carnagestudios.projectjawn.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu extends State {

    public Menu(GameStateManager gsm) {
        super(gsm);
    }


    @Override
    public void handleInput() {
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.end();
    }


    @Override
    public void dispose() {
        System.err.println("Disposing Menu");
        System.err.println("Menu disposed");
    }
}