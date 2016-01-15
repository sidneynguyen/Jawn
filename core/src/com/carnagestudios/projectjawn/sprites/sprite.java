package com.carnagestudios.projectjawn.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Sprite abstract to be extended by any of our in game sprites.
 * Created by vishu on 1/14/2016.
 */
public abstract class Sprite {
    //Sprite Fields
    protected Texture texture;
    protected Vector2 position;
    protected Rectangle hitBox;

    //Abstract methods to be implemented.
    /**
     * Update values of the sprite.
     * @param dt The time interval between updates
     */
    public abstract void update(float dt);
    /**
     * Dispose sprite assets
     */
    public abstract void dispose();

    //Sprite accessor methods.
    /**
     * Accessor to Sprite's texture.
     * @return the sprite's texture.
     */
    public Texture getTexture () {
        return texture;
    }

    /**
     * Accessor to Sprite's getX()
     * @return The sprites x position
     */
    public float getX () {
        return position.x;
    }

    /**
     * Accessor to Sprite's Y coordinate
     * @return The sprite's Y coordinate
     */
    public float getY () {
        return position.y;
    }

    /**
     * Accessor to Sprite's hitbox
     * @return The sprite's hitbox.
     */
    public Rectangle getHitBox () {
        return hitBox;
    }


}
