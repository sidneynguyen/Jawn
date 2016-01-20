/**
 * Carnage Studios
 *
 * File: Menu.java
 * Authors: Sidney Nguyen (creator), Vishu Yellisetty
 * Date Created: January 13, 2016
 * Date Modified: January 19, 2016
 */

package com.carnagestudios.projectjawn.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

    private Driver driver;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Viewport viewport;

    //Menu's fields.
    private static final int PLAY_TEXT_X = -240;
    private static final int PLAY_TEXT_Y = 100;
    private static final int PLAY_TEXT_SCALE = 3;

    private BitmapFont playText;

    /**
     * This constructor initializes a new Menu State.
     */
    public Menu (Driver driver, SpriteBatch batch) {

        this.driver = driver;
        this.batch = batch;
        this.cam = new OrthographicCamera();
        this.viewport = new StretchViewport(Driver.SCREEN_WIDTH, Driver.SCREEN_HEIGHT, cam);
        this.viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        playText = new BitmapFont();
        Driver.add_asset("play font");

        playText.getData().scale(PLAY_TEXT_SCALE);
    }

    public void handleInput () {
        if (Gdx.input.justTouched()) {
            dispose ();
            driver.setScreen(new Play(driver, batch));
        }
    }

    public void update (float dt) {
        handleInput();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update( delta );

        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // set camera to a stretch camera
        batch.setProjectionMatrix(cam.combined);
        // all graphics within begin and end will be drawn to the display

        batch.begin ();
        playText.draw(batch, "TOUCH TO PLAY", PLAY_TEXT_X, PLAY_TEXT_Y);
        batch.end ();
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

    /**
     * This method disposes all assets used by the Menu State.
     */
    @Override
    public void dispose () {
        Driver.print_debug("Disposing Menu");
        playText.dispose();
        Driver.remove_asset("play font");
        Driver.print_debug("playText disposed");
        Driver.print_debug("Menu disposed");
    }
}