
/**
 * Carnage Studios
 *
 * File: Obstacle.java
 * Authors: Vishu Yellisetty (creator), Sidney Nguyen
 * Date Created: January 23, 2016
 *
 */
package com.carnagestudios.projectjawn.sprites;
import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

/**
 * Sprite of an obstacle of which kills JAWN on contact.
 */
public class Obstacle extends com.badlogic.gdx.graphics.g2d.Sprite{
   //Constants
    private static final int TOP_OF_SCREEN = 960;
    private static final int BOTTOM_OF_SCREEN = -960;


    //Obstacle fields. (Intitially we do not move obstacles.)
    private float velocityX;
    private float velocityY;

    private boolean isOnScreen; //Boolean for if the obstacle is on the visible screen or not.




    /**
     *
     * @param texture: The obstacles texture.
     * @param xmin: The minimum x value inclusive at which the obstacle can be placed.
     * @param xmax: The maximum x value inclusive at which the obstacle can be placed.
     * @param ymin: The minimum y value inclusive at which the obstacle can be placed.
     * @param ymax: The maximum x value inclusive at which the obstacle can be placed.
     */
    public Obstacle (Texture texture, int xmin,int xmax, int ymin, int ymax) {
        super(texture);
        //Initialization

        //Randomize position
        randomizePosition (xmin,xmax, ymin, ymax);
        //Set fields.

        setVelocityX(0);
        setVelocityY(0);
    }

    /**
     * Sets the position to a random set of values within a given range.
     * @param xmin: The minimum x value inclusive at which the obstacle can be placed.
     * @param xmax: The maximum x value inclusive at which the obstacle can be placed.
     * @param ymin: The minimum y value inclusive at which the obstacle can be placed.
     * @param ymax: The maximum x value inclusive at which the obstacle can be placed.
     */
    public void randomizePosition(int xmin,int xmax, int ymin, int ymax)
    {
        int randomX, randomY;
        randomX = randomizeValue(xmin, xmax);
        randomY = randomizeValue(ymin, ymax);
        setPosition(randomX, randomY);
    }

    /**
     * Will generate a random value from bounds given.
     * @param min The Lower bound inclusive of the random value.
     * @param max The Upper bound inclusive of the random value.
     * @return The Randomized value within the bound.
     */
    public int randomizeValue( int min, int max)
    {
        int randomNumber;
        int absoluteMin;

        Random rand = new Random();
        absoluteMin = Math.abs(min);
        randomNumber = rand.nextInt( ( max- min ) + 1 ) + min; // Random int between
        return randomNumber;
    }

    /**
     * Update the coordinates.
     * @param deltaX: The change in the X coordinate.
     * @param deltaY: The change in the Y coordinate.
     * @return The number of obstacles passed. (0 or 1)
     */
    public int update (float deltaX, float deltaY) {
       int numberPassed = 0;
        this.setPosition(this.getX() + deltaX, this.getY() + deltaY);

        if( isOnScreen() == true) {
            if (this.getY() < BOTTOM_OF_SCREEN) {

                randomizePosition(-530, 530 - (int) this.getWidth(), TOP_OF_SCREEN, 2 * TOP_OF_SCREEN);
                this.setIsOnScreen(false);
                numberPassed ++;
            }
        }

         else if (this.getY() < TOP_OF_SCREEN)
         {
             this.setIsOnScreen(true);
         }
        return numberPassed;
    }


    /**
     *
     * @return The boolean for isOnScreen
     */
    public boolean isOnScreen() {
        return isOnScreen;
    }

    /**
     *
     * @param isOnScreen The on screen boolean value.
     */
    public void setIsOnScreen(boolean isOnScreen) {
        this.isOnScreen = isOnScreen;
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
