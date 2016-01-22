/**
 * Carnage Studios
 *
 * File: Background.java
 * Authors: Sidney Nguyen (creator), Vishu Yellisetty
 * Date Created: January 20, 2016
 * Date Modified: January 20, 2016
 */

package com.carnagestudios.projectjawn.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * This class represents a background entity.
 */
public class Background extends Sprite {

    private float velocityX;
    private float velocityY;

    /**
     * This constructor initializes a background entity.
     * @param texture asset
     * @param x position
     * @param y position
     */
    public Background (Texture texture, int x, int y) {
        super (texture);
        setPosition (x, y);
    }

    /**
     *
     * @param delta
     */
    public void update (float delta) {
        setVelocityX (getVelocityX() * delta);
        setVelocityY (getVelocityY() * delta);
        setX (getX() + getVelocityX());
        setY (getY() + getVelocityY());
        setVelocityX (getVelocityX() / delta);
        setVelocityY (getVelocityY() / delta);
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }
}
