/**
 * Carnage Studios
 *
 * File: Play.java
 * Authors: Sidney Nguyen (creator), Vishu Yellisetty
 * Date Created: January 13, 2016
 * Date Modified: January 20, 2016
 */

package com.carnagestudios.projectjawn.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.carnagestudios.projectjawn.Driver;
import com.carnagestudios.projectjawn.sprites.Background;
import com.carnagestudios.projectjawn.sprites.BackgroundList;
import com.carnagestudios.projectjawn.sprites.Germ;
import com.carnagestudios.projectjawn.sprites.GermList;
import com.carnagestudios.projectjawn.sprites.Jawn;
import com.carnagestudios.projectjawn.sprites.Obstacle;
import com.carnagestudios.projectjawn.sprites.Wall;
import com.carnagestudios.projectjawn.sprites.Water;

import java.util.ArrayList;

/**
 * This class represents the Play Screen of the game application.
 * It controls the interactions between users and the sprites.
 */
public class Play implements Screen, GestureDetector.GestureListener {
    //Score constants
    private static final int SCORE_TEXT_SCALE = 2;
    private static final int SCORE_TEXT_X = -500;
    private static final int SCORE_TEXT_Y = 900;
    // background constants
    private static final int BACKGROUND_X = -540;
    private static final int BACKGROUND_Y = - 960;

    // JAWN Start location
    private static final int JAWN_X = -530;
    private static final int JAWN_Y = 0;

    //Obstacle Constants
    private static final int NUMBER_GERMS = 100;
    private static final int TOP_OF_SCREEN = 960;
    private static final int BOTTOM_OF_SCREEN = -960;
    private static final int SPAWN_GAP = 40;
    private static final long SPAWN_WATER_X = -560;

    // wall starting locations
    private static final int LEFT_WALL_X = -540;
    private static final int LEFT_WALL_Y = -960;
    private static final int RIGHT_WALL_X = 530;
    private static final int RIGHT_WALL_Y = -960;

    // gravity and velocity constants
    private static final int OPEN_GRAVITY = 50;
    private static final int WALL_GRAVITY = 4;

    // velocity constraints
    private static final int MAX_VELOCITY_X = 1500;
    private static final int MAX_VELOCITY_Y = 2000;
    private static final int MIN_VELOCITY_X = 250;
    private static final int MIN_VELOCITY_Y = 500;
    private static final int MAX_DOWNWARD_VELOCITY_Y = -3000;

    // when collision with wall
    private static final float FRICTION_MULTIPLIER = 0.1f;

    // jawn movement boundaries
    private static final int TOP_BOUND = 100;
    private static final int BOTTOM_BOUND = -200;

    // background looping constants
    private static final int TOP_BACKGROUND_BOUND = 2880;
    private static final int BOTTOM_BACKGROUND_BOUND = -2880;
    private static final int BACKGROUND_LOOP_DISTANCE = 5760;

    private int bound;  // boundary Jawn is in (0 neutral, 1 top, 2 bottom)

    private Driver driver;           // used to set game screens
    private SpriteBatch batch;       // renders graphics to Screen
    private OrthographicCamera cam;  // user's view of Screen
    private Viewport viewport;       // camera properties

    private Background background0;  //
    private Background background1;  // dynamic backgrounds
    private Background background2;  //
    private Jawn jawn;              // player
    private Wall leftWall;          // left boundary
    private Wall rightWall;         // right boundary

    private Water water;
    private GermList germs;     // Holds all the obstacles.
    private BackgroundList backgrounds; // creates illusion of player movement

    // Texture assets
    private Texture backgroundTexture;
    private Texture jawnTexture;
    private Texture wallTexture;
    private Texture germTexture;
    private Texture waterTexture;

    private float gravity;     // controls gravity
    private float velocity;  // controls velocity

    private int germDeltaY; // Moving obstacles

    private int score = 0; //Players score.
    private BitmapFont scoreText;

    /**
     * This constructor creates a background and a Jawn.
     */
    public Play (Driver driver, SpriteBatch batch) {

        // initialize Play Screen
        this.driver = driver;
        this.batch = batch;
        this.cam = new OrthographicCamera();
        this.viewport = new StretchViewport(Driver.SCREEN_WIDTH, Driver.SCREEN_HEIGHT, cam);
        this.viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.scoreText = new BitmapFont(); //Score text
        Driver.add_asset("play font");
        scoreText.getData().scale(SCORE_TEXT_SCALE);

        // create texture assets
        this.backgroundTexture = new Texture("background2.png");
        Driver.add_asset("background2 texture");
        this.jawnTexture = new Texture("jawn2.png");
        Driver.add_asset("jawn texture");
        this.wallTexture = new Texture("sidewall.png");
        Driver.add_asset("wall texture");
        this.germTexture = new Texture("Bacteria3.gif");
        Driver.add_asset("Bacteria texture");
        this.waterTexture = new Texture("water.png");
        Driver.add_asset("water texture");

        // touch event handler
        Gdx.input.setInputProcessor(new GestureDetector(this));

        // instantiate sprite objects
        jawn = new Jawn (jawnTexture, JAWN_X, JAWN_Y);
        leftWall = new Wall (wallTexture, LEFT_WALL_X, LEFT_WALL_Y);
        rightWall = new Wall(wallTexture, RIGHT_WALL_X, RIGHT_WALL_Y);
        background0 = new Background(backgroundTexture, BACKGROUND_X, BACKGROUND_Y);
        background1 = new Background(backgroundTexture, BACKGROUND_X, BACKGROUND_Y + Driver.SCREEN_HEIGHT);
        background2 = new Background(backgroundTexture, BACKGROUND_X, BACKGROUND_Y - Driver.SCREEN_HEIGHT);
        water = new Water(waterTexture, SPAWN_WATER_X, BOTTOM_OF_SCREEN - waterTexture.getHeight());


        // instantiate background list for dynamic movement
        backgrounds = new BackgroundList();
        backgrounds.add(background0);
        backgrounds.add(background1);
        backgrounds.add(background2);
        // Instantiate Obstacles list
        germs = new GermList();
        for( int i = 0; i < NUMBER_GERMS; i++)
        {
            Germ newGerm = new Germ(germTexture, LEFT_WALL_X, RIGHT_WALL_X - germTexture.getWidth(), TOP_OF_SCREEN * (i+1)+ SPAWN_GAP , TOP_OF_SCREEN* (i+2) );
            newGerm.setIsOnScreen(false);
            germs.add(newGerm);
        }

        // initialize gravity
        setGravity(WALL_GRAVITY);
        setVelocity(0);

        // which bound ball is currently in
        bound = 0;
    }

    /**
     * This method is used for debugging. Space returns to Menu, A shows jawn coordinates.
     */
    public void handleInput() {

        // return to Menu
        if (Driver.debug_on && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            dispose();
            Driver.remove_asset("Play Screen");
            driver.setScreen(new Menu(driver, batch));
            Driver.add_asset("Menu Screen");
        }

        // display Jawn coordinates
        if (Driver.debug_on && Gdx.input.isKeyPressed(Input.Keys.A)) {
            Driver.print_debug("x = " + jawn.getX() + ", y = " + jawn.getY());
        }
    }

    /**
     * This method checks for collision and updates the Sprites accordingly.
     * @param delta seconds per frame
     */
    public void update(float delta) {
        germDeltaY = 0;
        // collision detection
        if (jawn.getBoundingRectangle().overlaps(leftWall.getBoundingRectangle())) {
            jawn.splat (LEFT_WALL_X + leftWall.getWidth() + 1);
            setVelocity(getVelocity() * FRICTION_MULTIPLIER);
            setGravity (WALL_GRAVITY);
        }
        if (jawn.getBoundingRectangle().overlaps(rightWall.getBoundingRectangle())) {
            jawn.splat (RIGHT_WALL_X - jawn.getWidth() - 1);
            setVelocity(getVelocity() * FRICTION_MULTIPLIER);
            setGravity(WALL_GRAVITY);
        }
        if( jawn.getBoundingRectangle().overlaps(water.getBoundingRectangle()))
        {
            jawn.die();
            driver.setScreen(new Menu(driver, batch)); //lose

        }
        for(int i = 0; i<NUMBER_GERMS; i++)
        {
            if(germs.getGerm(i).isOnScreen()) { //Collision with on screen obstacles.
                if (germs.getGerm(i).getBoundingRectangle().overlaps(jawn.getBoundingRectangle())) {
                    driver.setScreen(new Menu(driver, batch)); //lose
                    break;
                }
            }
        }

        // update ball's current bound
        if (bound == 0 && jawn.getY() > TOP_BOUND) {
            jawn.setY(TOP_BOUND);
            bound = 1;
        }
        else if (bound == 1 && getVelocity() < 0) {
            bound = 0;
        }
        /*
        else if (bound == 0 && jawn.getY() < BOTTOM_BOUND) {
            jawn.setY(BOTTOM_BOUND);
            bound = 2;
        }
        else if (bound == 2 && getVelocity() > 0) {
            bound = 0;
        }*/

        // stabilize time
        setVelocity(getVelocity() * delta);

        // move dynamic sprites
        if (bound == 0) {
            jawn.setY (jawn.getY() + getVelocity());

        }
        else {
            backgrounds.moveY(-getVelocity());
            germDeltaY = (int) -getVelocity(); //If we move background we move obstacles with it.
            if ( getVelocity() > 0)
                water.shiftDown(getVelocity());
        }
        // finish using time
        setVelocity(getVelocity() / delta);

        // add gravity
        setVelocity(getVelocity() - getGravity() );
       if (getVelocity() < MAX_DOWNWARD_VELOCITY_Y)
            setVelocity(MAX_DOWNWARD_VELOCITY_Y);

        // update sprites
        score+=germs.update(0, germDeltaY);
        jawn.update(delta);
        backgrounds.update(delta, TOP_BACKGROUND_BOUND, BOTTOM_BACKGROUND_BOUND, BACKGROUND_LOOP_DISTANCE);
        water.updateWater(delta);
        handleInput();

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

        // check for minimum velocities
        if (velocityX > MIN_VELOCITY_X || velocityX < -MIN_VELOCITY_X || velocityY > MIN_VELOCITY_Y ||
                velocityY < -MIN_VELOCITY_Y) {

            // set max velocities
            if (velocityX > MAX_VELOCITY_X)
                velocityX = MAX_VELOCITY_X;
            else if (velocityX < -MAX_VELOCITY_X)
                velocityX = -MAX_VELOCITY_X;

            if (velocityY > MAX_VELOCITY_Y)
                velocityY = MAX_VELOCITY_Y;
            else if (velocityY < -MAX_VELOCITY_Y)
                velocityY = -MAX_VELOCITY_Y;

            // move jawn right, set global velocity
            jawn.setVelocityX(velocityX);
            setVelocity(-velocityY);  // sign needs to be flipped

            // change type of gravity
            setGravity(OPEN_GRAVITY);

        }

        return true;
    }

    /**
     * This method renders the graphics onto the Screen.
     * @param delta
     */
    @Override
    public void render(float delta) {
        update(delta);

        // clear screen with solid blue color before every render
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // set camera to a stretch camera
        batch.setProjectionMatrix(cam.combined);

        // all graphics within begin and end will be drawn to the display
        batch.begin();

        backgrounds.draw(batch);
        germs.draw(batch);
        leftWall.draw(batch);
        rightWall.draw(batch);
        jawn.draw(batch);
        water.draw(batch);

        scoreText.draw(batch, "Score: "+score, SCORE_TEXT_X, SCORE_TEXT_Y);

        batch.end();

    }

    /**
     * This method disposes all assets of the Play State.
     */
    @Override
    public void dispose() {
        backgroundTexture.dispose();
        Driver.remove_asset("background texture");
        jawnTexture.dispose();
        Driver.remove_asset("jawn texture");
        wallTexture.dispose();
        Driver.remove_asset("wall texture");
        germTexture.dispose();
        Driver.remove_asset("obstacle texture");
        waterTexture.dispose();
        Driver.remove_asset("Water texture");
        scoreText.dispose();
        Driver.remove_asset("score text");

    }

    /**
     * This method sets the gravity.
     * @param gravity new gravity
     */
    public void setGravity (int gravity) { this.gravity = gravity; }

    public float getGravity () {
        return gravity;
    }

    public void setVelocity (float velocity) {
        this.velocity = velocity;
    }

    public float getVelocity () {
        return velocity;
    }

    // excess implemented methods.

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
