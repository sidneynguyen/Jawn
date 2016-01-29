/**
 * Carnage Studios
 *
 * File: ObstacleList.java
 * Authors: Vishu Yellisetty (creator), Sidney Nguyen
 * Date Created: January 23, 2016
 *
 */

package com.carnagestudios.projectjawn.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.carnagestudios.projectjawn.sprites.Obstacle;

import java.util.ArrayList;


/**
 * Class containing list of the Obstacles.
 */
public class GermList {
    //Constants
    public static final int numberObstacles = 6;

    private ArrayList<Germ> germs;  // stores all Obstacle sprites

    /**
     * This constructor creates a list data structure for obstacles.
     */
    public GermList () {
        germs = new ArrayList<Germ>();

    }

    /**
     * This method adds a germ to the list.
     * @param germ
     */
    public void add (Germ germ) {
        germs.add(germ);
    }

    /**
     * Updates all positions of objects.
     * @param deltaX Amount to move the obstacle objects in X
     * @param deltaY Amount to move the obstacle objects in Y
     * @return Number of obstacles that passed of screen below.
     */
    public int update(int deltaX, int deltaY)
    {
       int numberGermsPassed = 0;
        for (Germ g: germs) {
            numberGermsPassed+=g.updateGerm(deltaX, deltaY);

        }
        return numberGermsPassed;
    }

    /**
     * This method renders all on screen obstacles.
     * @param batch
     */
    public void draw (SpriteBatch batch) {
        for (Germ g: germs) {
            if (g.isOnScreen()) {
                g.draw(batch);
            }
        }
    }

    /**
     * Return a germ from the array list given an index.
     * @param indexOf The germ to return.
     * @return A germ at indexOf.
     */
    public Obstacle getGerm ( int indexOf )
    {
        return germs.get(indexOf);
    }
}
