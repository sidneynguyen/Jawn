/**
 * Carnage Studios
 *
 * File: State.java
 * Authors: Sidney Nguyen (creator), Vishu Yellisetty
 * Date Created: January 13, 2016
 * Date Modified: January 14, 2016
 */

package com.carnagestudios.projectjawn.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.carnagestudios.projectjawn.Driver;

/**
 * This abstract class is to be implemented by any of our game states.
 */
public abstract class State {

    protected GameStateManager gsm;
    protected OrthographicCamera cam;
    protected Viewport viewport;

    /**
     * This constructor initializes a camera to be used by a GameState.
     * @param gsm GameStateManager
     */
    public State(GameStateManager gsm) {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        viewport = new StretchViewport(Driver.SCREEN_WIDTH, Driver.SCREEN_HEIGHT, cam);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    //Method abstracts
    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

}