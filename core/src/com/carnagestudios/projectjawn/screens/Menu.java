/**
 * Carnage Studios
 *
 * File: Menu.java
 * Authors: Sidney Nguyen (creator), Vishu Yellisetty
 * Date Created: January 13, 2016
 * Date Modified: January 20, 2016
 */

package com.carnagestudios.projectjawn.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.carnagestudios.projectjawn.Driver;

/**
 * This class represents the main Menu of the game application.
 * It displays text and waits for the user to touch before starting a Play State.
 */
public class Menu implements Screen {

    // playText constants
    private static final int PLAY_TEXT_X = -240;
    private static final int PLAY_TEXT_Y = 100;
    private static final int PLAY_TEXT_SCALE = 3;

    private Driver driver;           // used to set new screens
    private SpriteBatch batch;       // used to render graphics
    private OrthographicCamera cam;  // area viewed by player
    private Viewport viewport;       // camera properties

    private Texture background;
    private Texture wall;

    private BitmapFont playText;  // text displayed to screen

    private BitmapFont highScore;
    private int hScore;

    /**
     * This constructor initializes a new Menu Screen.
     */
    public Menu (Driver driver, SpriteBatch batch) {

        // initialize Menu Screen
        this.driver = driver;
        this.batch = batch;
        this.cam = new OrthographicCamera();
        this.viewport = new StretchViewport(Driver.SCREEN_WIDTH, Driver.SCREEN_HEIGHT, cam);
        this.viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.background = new Texture("background2.png");
        this.wall = new Texture("sidewall.png");

        // create playText
        this.playText = new BitmapFont();
        Driver.add_asset("play font");
        playText.getData().scale(PLAY_TEXT_SCALE);

        Preferences pref = Gdx.app.getPreferences("Preferences");
        hScore = pref.getInteger("hs", 0);
        highScore = new BitmapFont();
        highScore.setColor(Color.WHITE);
        highScore.getData().scale(2);
    }

    /**
     * This method starts a Play Screen when the screen is tapped
     */
    public void handleInput () {

        // check for tap
        if (Gdx.input.justTouched()) {
            dispose ();
            Driver.remove_asset("Menu Screen");

            // start new Play Screen
            driver.setScreen(new Play(driver, batch));
            Driver.add_asset("Play Screen");
        }
    }

    /**
     * This method updates the Menu and handles input.
     * @param delta seconds per frame
     */
    public void update (float delta) {
        handleInput();
    }

    /**
     * This method updates the Menu and renders the graphics.
     * @param delta seconds per frame
     */
    @Override
    public void render(float delta) {
        update(delta);

        // clear Screen with solid blue color before rendering each frame
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // set camera to a stretch camera
        batch.setProjectionMatrix(cam.combined);

        // all graphics within begin and end will be drawn to the display
        batch.begin();

        batch.draw(background, -540, -960);
        batch.draw(wall, -540, -960);
        batch.draw(wall, 530, -960);

        playText.draw(batch, "TOUCH TO PLAY", PLAY_TEXT_X, PLAY_TEXT_Y);
        highScore.draw(batch, "High Score " + hScore, 200, 900);

        batch.end();
    }

    /**
     * This method disposes all assets used by the Menu State.
     */
    @Override
    public void dispose () {
        background.dispose();
        wall.dispose();
        playText.dispose();
        highScore.dispose();
        Driver.remove_asset("playText");
    }

    // excess implemented methods

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

}