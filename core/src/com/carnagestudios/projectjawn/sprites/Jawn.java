/**
 * Carnage Studios
 *
 * File: Jawn.java
 * Authors: Sidney Nguyen (creator), Vishu Yellisetty
 * Date Created: January 13, 2016
 * Date Modified: January 14, 2016
 */

package com.carnagestudios.projectjawn.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.carnagestudios.projectjawn.Driver;

/**
 * This class represents the Jawn sprite.
 */
public class Jawn extends Sprite{

    private static final int MAX_VELOCITY = 1000;

    //JAWN fields.
    private Vector2 velocity;


    /**
     * This constructor creates a Jawn at a given position.
     * @param x position X position at which to place Jawn.
     * @param y position Y position at which to place Jawn
     */
    public Jawn (int x, int y) {
        texture = new Texture("jawn.png");
        Driver.add_assets(1);

        //Initialization
        position = new Vector2 (x, y);
        velocity = new Vector2 (0, 0);
        hitBox = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    /**
     * Scales velocity to delta time and adds to position and relocates hitbox.
     * @param dt Delta Time: The time interval between updates
     */
    public void update (float dt) {
        velocity.scl(dt);
        position.add(velocity);
        velocity.scl(1 / dt);
        hitBox.setPosition(position);
    }

    /**
     *  Splat is for when Jawn hits a wall; it loses all its velocity.
     * @param x: X is the x position to reset to.
     */
    public void splat (float x) {
        //Zero out velocity
        velocity.x = 0;
        velocity.y = 0;
        //Reset x position.
        position.x = x;
    }

    /**
     * This method disposes Jawn assets.
     */
    public void dispose () {
        texture.dispose();
        Driver.remove_assets(1);
        Driver.print_debug("Jawn disposed");
    }

    /**
     * Adds X velocity only if it won't exceed the maximum velocity otherwise sets to max velocity.
     * @param dx Delta X: the X velocity increment.
     */
    public void setVelocityX (float dx) {
        if ((velocity.x+dx) > MAX_VELOCITY)
            velocity.x = MAX_VELOCITY;
        else if ((velocity.x+dx)<-MAX_VELOCITY)
            velocity.x = -MAX_VELOCITY;
        else
            velocity.x += dx;
    }

    /**
     * Adds y velocity only if it won't exceed the maximum velocity otherwise sets to max velocity.
     * @param dy Delta Y: the Y velocity increment.
     */
    public void setVelocityY (float dy) {
        if ((velocity.y+dy) > MAX_VELOCITY)
            velocity.y = MAX_VELOCITY;
        else if ((velocity.y+dy)<-MAX_VELOCITY)
            velocity.y = -MAX_VELOCITY;
        else
            velocity.y += dy;
    }





}
