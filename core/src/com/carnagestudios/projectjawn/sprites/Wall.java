/**
 * Carnage Studios
 *
 * File: Wall.java
 * Authors: Sidney Nguyen (creator), Vishu Yellisetty
 * Date Created: January 13, 2016
 * Date Modified: January 20, 2016
 */

package com.carnagestudios.projectjawn.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.carnagestudios.projectjawn.Driver;

/**
 * Class representing the Wall Sprite.
 */
public class Wall extends com.badlogic.gdx.graphics.g2d.Sprite {

    /**
     * Constructor that will set and initialize basic sprite fields.
     * @param x : Initial positional x.
     * @param y : Initial positional y.
     */
    public Wall (Texture texture, int x, int y) {
        super (texture);
        setPosition(x, y);
    }

}