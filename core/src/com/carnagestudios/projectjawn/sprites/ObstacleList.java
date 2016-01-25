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
public class ObstacleList {
    //Constants
    public static final int numberObstacles = 6;

    private ArrayList<Obstacle> obstacles;  // stores all Obstacle sprites

    /**
     * This constructor creates a list data structure for obstacles.
     */
    public ObstacleList () {
        obstacles = new ArrayList<Obstacle>();

    }

    /**
     * This method adds an obstacle to the list.
     * @param obstacle
     */
    public void add (Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    /**
     * Updates all positions of objects.
     * @param deltaX Amount to move the obstacle objects in X
     * @param deltaY Amount to move the obstacle objects in Y
     * @return Number of obstacles that passed of screen below.
     */
    public int update(int deltaX, int deltaY)
    {
       int numberObstaclesPassed = 0;
        for (Obstacle o: obstacles) {
            numberObstaclesPassed+=o.update(deltaX, deltaY);

        }
        return numberObstaclesPassed;
    }

    /**
     * This method renders all on screen obstacles.
     * @param batch
     */
    public void draw (SpriteBatch batch) {
        for (Obstacle o: obstacles) {
            if (o.isOnScreen()) {
                o.draw(batch);
            }
        }
    }

    /**
     * Return an obstacle from the array list given an index.
     * @param indexOf The obstacle to return.
     * @return An obstacle at indexOf.
     */
    public Obstacle getObstacle ( int indexOf )
    {
        return obstacles.get(indexOf);
    }
}
