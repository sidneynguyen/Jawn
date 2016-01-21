/**
 * Carnage Studios
 *
 * File: Driver.java
 * Authors: Vishu Yellisetty (creator), Sidney Nguyen
 * Date Created: January 13, 2016
 * Date Modified: January 20, 2016
 */

package com.carnagestudios.projectjawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.carnagestudios.projectjawn.screens.Menu;

/**
 * This class runs the game application.
 * Uses a GameStateManager to organize and run all game States.
 */
public class Driver extends Game {

    public static final int SCREEN_WIDTH = 1080;   // base screen width
    public static final int SCREEN_HEIGHT = 1920;  // base screen height

    public static boolean debug_on = true;  // sets debug mode on or off
    private static long asset_count = 0;    // tracks number of assets in the game application

    private SpriteBatch batch;     // used to render all images to the display

    /**
     * This method initializes the game and starts a Menu Screen.
     */
	@Override
	public void create () {

		batch = new SpriteBatch ();
        add_asset("SpriteBatch");

        setScreen (new Menu (this, batch));
        add_asset("Menu Screen");

	}

    /**
     * This method frees all memory used by the game application.
     */
	@Override
	public void dispose () {
        super.dispose();

        batch.dispose();
        remove_asset("SpriteBatch");

        getScreen().dispose();
        remove_asset("Current Screen");

        Driver.print_debug("Game disposed");
	}

    /**
     * This method increments the number of assets added.
     */
    public static void add_asset (String name) {
        asset_count++;
        print_debug(name + " asset added. Total number of assets: " + asset_count);
    }

    /**
     * This method increments the number of assets removed.
     */
    public static void remove_asset (String name) {
        asset_count --;
        print_debug(name + " asset removed. Total number of assets: " + asset_count);
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
