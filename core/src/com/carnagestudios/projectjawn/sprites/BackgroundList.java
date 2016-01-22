/**
 * Carnage Studios
 *
 * File: BackgroundList.java
 * Authors: Sidney Nguyen (creator), Vishu Yellisetty
 * Date Created: January 20, 2016
 * Date Modified: January 21, 2016
 */

package com.carnagestudios.projectjawn.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * This class organizes all Background Sprites into an ArrayList.
 */
public class BackgroundList {

    private ArrayList<Background> backgrounds;  // stores all background sprites

    /**
     * This constructor creates a data structure for backgrounds.
     */
    public BackgroundList () {
        backgrounds = new ArrayList<Background>();
    }

    /**
     * This method adds a background to the list.
     * @param background
     */
    public void add (Background background) {
        backgrounds.add(background);
    }

    /**
     * This method moves all backgrounds a certain distance.
     * @param dy
     */
    public void moveY (float dy) {
        for (Background b: backgrounds) {
            b.setY(dy + b.getY());
        }
    }

    /**
     * This method loops the backgrounds.
     * @param delta  seconds per frame
     * @param top    highest point before moving top background to bottom
     * @param bottom lowest point before moving bottom background to top
     * @param length distance to move the backgrounds
     */
    public void update (float delta, int top, int bottom, int length) {
        for (Background b: backgrounds) {
            b.update(delta);
        }
        for (Background b: backgrounds) {
            if (b.getY() > top) {
                b.setY(b.getY() - length);
            }
            if (b.getY() < bottom) {
                b.setY(b.getY() + length);
            }
        }
    }

    /**
     * This method renders all backgrounds.
     * @param batch
     */
    public void draw (SpriteBatch batch) {
        for (Background b: backgrounds) {
            b.draw (batch);
        }
    }
}
