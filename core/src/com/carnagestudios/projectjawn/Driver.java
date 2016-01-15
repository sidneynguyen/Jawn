/**
 * Carnage Studios
 *
 * File: Driver.java
 * Authors: Vishu Yellisetty, Sidney Nguyen
 * Date Created: January 13, 2016
 * Date Modified: January 14, 2016
 */

package com.carnagestudios.projectjawn;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.carnagestudios.projectjawn.states.GameStateManager;
import com.carnagestudios.projectjawn.states.Menu;

/**
 * This class runs the game application.
 */
public class Driver extends ApplicationAdapter {

    public static boolean debug_on = false;  // sets debug mode on or off

    SpriteBatch batch;     // used to render all images to the display
    GameStateManager gsm;  // organizes and manages all game States

    /**
     * This method initializes the game and starts a Menu State.
     */
	@Override
	public void create () {

		batch = new SpriteBatch ();
        gsm = new GameStateManager ();

        // starts a new Menu State
        gsm.push (new Menu(gsm));

		Gdx.gl.glClearColor (0, 0, 1, 1);

	}

    /**
     * This method updates and renders the currently running game State.
     */
	@Override
	public void render () {

        // wipes display with a solid blue color before each frame is rendered
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // update and render the currently running game State
        gsm.update (Gdx.graphics.getDeltaTime());
        gsm.render (batch);
	}

    /**
     * This method frees all memory used by the game application.
     */
	@Override
	public void dispose () {

        print_debug("Disposing GameStateManager");
        gsm.dispose();
        print_debug("GameStateManager disposed");

	}

    /**
     * This method prints debug messages to the console.
     * 
     * @param message debug output
     */
    public static void print_debug (String message) {
        if (debug_on)
            System.err.println (message);
    }
}
