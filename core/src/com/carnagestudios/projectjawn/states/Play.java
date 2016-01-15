/**
 * Carnage Studios
 *
 * File: Play.java
 * Authors: Sidney Nguyen (creator), Vishu Yellisetty
 * Date Created: January 13, 2016
 * Date Modified: January 14, 2016
 */

package com.carnagestudios.projectjawn.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.carnagestudios.projectjawn.Driver;

/**
 * This class represents the Play State of the game application.
 */
public class Play extends State {

    private static final int BACKGROUND_X = -540;
    private static final int BACKGROUND_Y = - 960;

    private Texture background;

    public Play (GameStateManager gsm) {
        super(gsm);

        background = new Texture("background.png");
        Driver.add_assets(1);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched())
            gsm.pop();
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {

        // set camera to a stretch camera
        batch.setProjectionMatrix(cam.combined);

        // all graphics within begin and end will be drawn to the display
        batch.begin();
        batch.draw(background, BACKGROUND_X, BACKGROUND_Y);
        batch.end ();
    }

    @Override
    public void dispose() {
        Driver.print_debug("Disposing Play");
        background.dispose();
        Driver.print_debug("background disposed");
        Driver.remove_assets(1);
        Driver.print_debug("Play disposed");
    }
}
