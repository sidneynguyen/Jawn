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
import com.badlogic.gdx.math.Vector2;
import com.carnagestudios.projectjawn.Driver;

/**
 * This class represents the Jawn sprite.
 */
public class Jawn {

    private Texture texture;
    private Vector2 position;
    private Vector2 velocity;

    /**
     * This constructor creates a Jawn at a given position.
     * @param x position
     * @param y position
     */
    public Jawn (int x, int y) {
        texture = new Texture("jawn.png");
        Driver.add_assets(1);

        position = new Vector2 (x, y);
        velocity = new Vector2 (0, 0);
    }

    public void update (float dt) {
        velocity.scl(dt);
        position.add(velocity);
        velocity.scl(1/dt);
    }

    /**
     * This method disposes Jawn assets.
     */
    public void dispose () {
        texture.dispose();
        Driver.remove_assets(1);
        Driver.print_debug("Jawn disposed");
    }

    public Texture getTexture () {
        return texture;
    }

    public float getX () {
        return position.x;
    }

    public float getY () {
        return position.y;
    }

    public void setVelocity (float dx) {
        velocity.x += dx;
    }

}
