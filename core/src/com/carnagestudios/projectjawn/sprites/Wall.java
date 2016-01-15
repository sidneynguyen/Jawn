/**
 * Carnage Studios
 *
 * File: Wall.java
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
 * Class representing the Wall Sprite.
 */
public class Wall extends Sprite{

    /**
     * Constructor that will set and initialize basic sprite fields.
     * @param x : Initial positional x.
     * @param y : Initial positional y.
     */
    public Wall (int x, int y) {
        texture = new Texture("sidewall.png");
        Driver.add_assets(1);

        position = new Vector2 (x, y);
        hitBox = new Rectangle (x, y, texture.getWidth(), texture.getHeight());
    }

    /**
     * Despose of the Wall Sprite's assets
     */
    public void dispose () {
        texture.dispose();
        Driver.print_debug("Wall disposed");
        Driver.remove_assets(1);
    }

    /**
     * Empty update method required by Sprite abstract.
     * @param dt The time interval between updates
     */
    public void update(float dt) {//Nothing to update wall has no changing fields.
    }


}
