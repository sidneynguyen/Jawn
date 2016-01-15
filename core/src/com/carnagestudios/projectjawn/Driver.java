/**
 * Carnage Studios
 *
 * File: Driver.java
 * Authors: Vishu Yellisetty (creator), Sidney Nguyen
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
 * Uses a GameStateManager to organize and run all game States.
 */
public class Driver extends ApplicationAdapter {

    public static final int SCREEN_WIDTH = 1080;
    public static final int SCREEN_HEIGHT = 1920;

    public static boolean debug_on = true;  // sets debug mode on or off
    private static long asset_count = 0;    // tracks number of assets in the game application

    private SpriteBatch batch;     // used to render all images to the display
    private GameStateManager gsm;  // organizes and manages all game States

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
        print_debug("Number of undisposed assets: " + asset_count);

	}

    /**
     * This method increments the number of assets added.
     * @param count number of assets to add
     */
    public static void add_assets (long count) {
        asset_count += count;
        print_debug(count + " assets added. Total number of assets: " + asset_count);
    }

    /**
     * This method increments the number of assets removed.
     * @param count number of assets removed
     */
    public static void remove_assets (long count) {
        asset_count -= count;
        print_debug(count + " assets removed. Total number of assets: " + asset_count);
    }

    /**
     * This method prints debug messages to the console.
     * @param message debug output
     */
    public static void print_debug (String message) {
        if (debug_on)
            System.err.println (message);
    }
}
