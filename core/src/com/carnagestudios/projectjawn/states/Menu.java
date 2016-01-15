/**
 * Carnage Studios
 *
 * File: Menu.java
 * Authors: Sidney Nguyen (creator), Vishu Yellisetty
 * Date Created: January 13, 2016
 * Date Modified: January 14, 2016
 */

package com.carnagestudios.projectjawn.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.carnagestudios.projectjawn.Driver;


/**
 * This class represents the main Menu of the game application.
 */
public class Menu extends State {

    /**
     * This constructor initializes a new Menu State.
     * @param gsm GameStateManager
     */
    public Menu (GameStateManager gsm) {
        super (gsm);
    }

    /**
     * This method handles user input.
     */
    @Override
    public void handleInput () {
    }

    /**
     * This method updates the Menu State.
     * @param dt delta time
     */
    @Override
    public void update (float dt) {
        handleInput();
    }

    /**
     * This method renders all Menu graphics to the display.
     * @param batch SpriteBatch
     */
    @Override
    public void render (SpriteBatch batch) {
        // all graphics within begin and end will be drawn to the display
        batch.begin ();

        batch.end ();
    }

    /**
     * This method disposes all assets used by the Menu State.
     */
    @Override
    public void dispose () {
        Driver.print_debug ("Disposing Menu");
        Driver.print_debug ("Menu disposed");
    }
}