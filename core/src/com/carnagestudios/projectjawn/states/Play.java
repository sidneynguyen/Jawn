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
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.carnagestudios.projectjawn.Driver;
import com.carnagestudios.projectjawn.sprites.Jawn;
import com.carnagestudios.projectjawn.sprites.Wall;

/**
 * This class represents the Play State of the game application.
 */
public class Play extends State implements GestureDetector.GestureListener {

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
    private static final int GRAVITY = 10;

    //Sprites
    private Texture background;
    private Jawn jawn;
    private Wall leftWall;
    private Wall rightWall;

    /**
     * This constructor creates a background and a Jawn.
     * @param gsm GameStateManager
     */
    public Play (GameStateManager gsm) {
        super(gsm);
        Gdx.input.setInputProcessor(new GestureDetector(this));

        background = new Texture("background.png");
        Driver.add_assets(1);

        //Instansiate sprite objects
        jawn = new Jawn (JAWN_X, JAWN_Y);
        leftWall = new Wall (LEFT_WALL_X, LEFT_WALL_Y);
        rightWall = new Wall(RIGHT_WALL_X, RIGHT_WALL_Y);
    }

    /**
     * This method ends the Play State and returns to the Menu State when the space bar is pressed.
     * Only when debugger is active
     */
    @Override
    public void handleInput() {
        if (Driver.debug_on && Gdx.input.isKeyPressed(Input.Keys.SPACE))
            gsm.pop();
    }

    /**
     * This method checks for input and updates Jawn.
     * @param dt delta time
     */
    @Override
    public void update(float dt) {
        //Wall Collision detection
        if (jawn.getHitBox().overlaps(leftWall.getHitBox())) {
            jawn.splat(LEFT_WALL_X + GAP);
        }
        if (jawn.getHitBox().overlaps(rightWall.getHitBox())) {
            jawn.splat(RIGHT_WALL_X - jawn.getDiameter());
        }
        //Gravity
        jawn.setVelocityY(-GRAVITY);

        //Update/input
        handleInput();
        jawn.update(dt);
    }

    /**
     * This method draws the background and jawn to the display.
     * @param batch SpriteBatch
     */
    @Override
    public void render(SpriteBatch batch) {

        // set camera to a stretch camera
        batch.setProjectionMatrix(cam.combined);

        // all graphics within begin and end will be drawn to the display
        batch.begin();

        batch.draw(background, BACKGROUND_X, BACKGROUND_Y);
        batch.draw(jawn.getTexture(), jawn.getX(), jawn.getY());
        batch.draw(leftWall.getTexture(), leftWall.getX(), leftWall.getY());
        batch.draw(rightWall.getTexture(), rightWall.getX(), rightWall.getY());

        batch.end();
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

        //system info
        Driver.print_debug("fling");
        System.err.println(velocityX);
        System.err.println(velocityY);
        return true;
    }

    /**
     * This method disposes all assets of the Play State.
     */
    @Override
    public void dispose() {
        Driver.print_debug("Disposing Play");

        background.dispose();
        Driver.print_debug("background disposed");
        Driver.remove_assets(1);

        jawn.dispose();

        Driver.print_debug("Play disposed");
    }





    //Excess override methods.

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
