/**
 * Carnage Studios
 *
 * File: Play.java
 * Authors: Sidney Nguyen (creator), Vishu Yellisetty
 * Date Created: January 13, 2016
 * Date Modified: January 19, 2016
 */

package com.carnagestudios.projectjawn.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.carnagestudios.projectjawn.Driver;
import com.carnagestudios.projectjawn.sprites.Jawn;
import com.carnagestudios.projectjawn.sprites.Wall;

/**
 * This class represents the Play State of the game application.
 */
public class Play implements Screen, GestureDetector.GestureListener {

    private static final int BACKGROUND_X = -540;
    private static final int BACKGROUND_Y = - 960;

    //JAWN Start location
    private static final int JAWN_X = -530;
    private static final int JAWN_Y = 0;

    //Wall starting locations
    private static final int LEFT_WALL_X = -540;
    private static final int LEFT_WALL_Y = -960;
    private static final int RIGHT_WALL_X = 530;
    private static final int RIGHT_WALL_Y = -960;

    private static final int GAP = 10;
    private static final int OPEN_GRAVITY = 15;
    private static final int WALL_GRAVITY = 5;

    private Driver driver;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Viewport viewport;

    //Sprites
    private Texture background;
    private Jawn jawn;
    private Wall leftWall;
    private Wall rightWall;

    private Texture jawnTexture;
    private Texture wallTexture;

    private int gravity;

    /**
     * This constructor creates a background and a Jawn.
     */
    public Play (Driver driver, SpriteBatch batch) {

        this.driver = driver;
        this.batch = batch;
        this.cam = new OrthographicCamera();
        this.viewport = new StretchViewport(Driver.SCREEN_WIDTH, Driver.SCREEN_HEIGHT, cam);
        this.viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        jawnTexture = new Texture("jawn.png");
        Driver.add_asset("jawn texture");
        wallTexture = new Texture("sidewall.png");
        Driver.add_asset("wall texture");
        background = new Texture("background.png");
        Driver.add_asset("background texture");

        Gdx.input.setInputProcessor(new GestureDetector(this));

        //Instansiate sprite objects
        jawn = new Jawn (JAWN_X, JAWN_Y, jawnTexture);
        leftWall = new Wall (LEFT_WALL_X, LEFT_WALL_Y, wallTexture);
        rightWall = new Wall(RIGHT_WALL_X, RIGHT_WALL_Y, wallTexture);

        gravity = WALL_GRAVITY;
    }

    public void handleInput() {
        if (Driver.debug_on && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            dispose ();
            driver.setScreen(new Menu(driver, batch));
        }
        else if (Driver.debug_on && Gdx.input.isKeyPressed(Input.Keys.A)) {
            Driver.print_debug("x = " + jawn.getX() + ", y = " + jawn.getY());
        }
    }


    public void update(float dt) {
        //Wall Collision detection
        if (jawn.getBoundingRectangle().overlaps(leftWall.getBoundingRectangle())) {
            jawn.splat(LEFT_WALL_X + GAP + 1);
            gravity = WALL_GRAVITY;
        }
        if (jawn.getBoundingRectangle().overlaps(rightWall.getBoundingRectangle())) {
            jawn.splat(RIGHT_WALL_X - jawn.getWidth() - 1);
            gravity = WALL_GRAVITY;
        }
        //Gravity
        jawn.setVelocityY(jawn.getVelocityY() - gravity);

        //Update/input
        handleInput();
        jawn.update(dt);
    }

    /**
     * This method listens for a fling and flings Jawn relative to the fling's velocity.
     * @param velocityX Velocity in the X direction of the fling.
     * @param velocityY Velocity in the Y direction of the fling.
     * @param button
     * @return true if method is handled
     */
    @Override
    public boolean fling(float velocityX, float velocityY, int button) {

        jawn.setVelocityX(velocityX);
        jawn.setVelocityY(-velocityY);//sign needs to be flipped
        gravity = OPEN_GRAVITY;

        return true;
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // set camera to a stretch camera
        batch.setProjectionMatrix(cam.combined);

        // all graphics within begin and end will be drawn to the display
        batch.begin();

        batch.draw(background, BACKGROUND_X, BACKGROUND_Y);
        jawn.draw(batch);
        leftWall.draw(batch);
        rightWall.draw(batch);

        batch.end();

    }

    /**
     * This method disposes all assets of the Play State.
     */
    @Override
    public void dispose() {
        Driver.print_debug("Disposing Play");

        background.dispose();
        Driver.print_debug("background disposed");
        Driver.remove_asset("background texture");

        jawnTexture.dispose();
        Driver.remove_asset("jawn texture");
        wallTexture.dispose();
        Driver.remove_asset("wall texture");

        Driver.print_debug("Play disposed");
    }

    //Excess override methods.

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

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) { return false; }

    @Override
    public boolean tap(float x, float y, int count, int button) { return false; }

    @Override
    public boolean longPress(float x, float y) { return false; }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) { return false; }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) { return false; }

    @Override
    public boolean zoom(float initialDistance, float distance) { return false; }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) { return false; }
}
