/**
 * Carnage Studios
 *
 * File: Jawn.java
 * Authors: Sidney Nguyen (creator), Vishu Yellisetty
 * Date Created: January 13, 2016
 * Date Modified: January 20, 2016
 */

package com.carnagestudios.projectjawn.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.carnagestudios.projectjawn.Driver;

/**
 * This class represents the Jawn sprite.
 */
public class Jawn extends com.badlogic.gdx.graphics.g2d.Sprite {

    //JAWN fields.
    private float velocityX;
    private float velocityY;

    /**
     * This constructor creates a Jawn at a given position.
     * @param x position X position at which to place Jawn.
     * @param y position Y position at which to place Jawn
     */
    public Jawn (Texture texture, int x, int y) {
        super(texture);

        //Initialization
        setPosition (x, y);
        setVelocityX (0);
        setVelocityY (0);
    }

    /**
     * Scales velocity to delta time and adds to position and relocates hitbox.
     * @param dt Delta Time: The time interval between updates
     */
    public void update (float dt) {
        setVelocityX (getVelocityX() * dt);
        setVelocityY (getVelocityY() * dt);
        setX (getX() + getVelocityX());
        setY (getY() + getVelocityY());
        setVelocityX (getVelocityX() / dt);
        setVelocityY (getVelocityY() / dt);
    }

    /**
     *  Splat is for when Jawn hits a wall; it loses all its velocity.
     * @param x: X is the x position to reset to.
     */
    public void splat (float x) {
        //Zero out velocity
        setVelocityX (0);
        //Reset x position.
        setX(x);
    }

    public void die ()
    {
        //Falls down with zero X velocity.
        setVelocityY( 0);
        setVelocityX(0);
        //Should end game
    }

    /**
     * Adds X velocity only if it won't exceed the maximum velocity otherwise sets to max velocity.
     * @param dx Delta X: the X velocity increment.
     */
    public void setVelocityX (float dx) {
            velocityX = dx;
    }

    /**
     * Adds y velocity only if it won't exceed the maximum velocity otherwise sets to max velocity.
     * @param dy Delta Y: the Y velocity increment.
     */
    public void setVelocityY (float dy) {
            velocityY = dy;
    }

    public float getVelocityX () { return velocityX; }
    public float getVelocityY () { return velocityY; }

}
