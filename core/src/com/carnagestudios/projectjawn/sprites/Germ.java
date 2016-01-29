package com.carnagestudios.projectjawn.sprites;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by vishu on 1/28/2016.
 */
public class Germ extends Obstacle {

/**
 *
 * @param texture: The obstacles texture.
 * @param xmin: The minimum x value inclusive at which the obstacle can be placed.
 * @param xmax: The maximum x value inclusive at which the obstacle can be placed.
 * @param ymin: The minimum y value inclusive at which the obstacle can be placed.
 * @param ymax: The maximum x value inclusive at which the obstacle can be placed.
 **/
    public Germ (Texture texture, int xmin,int xmax, int ymin, int ymax) {
        super(texture);
        //Initialization

        //Randomize position
        randomizePosition (xmin,xmax, ymin, ymax);
        //Set fields.

        setVelocityX(0);
        setVelocityY(0);
    }

    /**
     * Update the coordinates.
     * @param deltaX: The change in the X coordinate.
     * @param deltaY: The change in the Y coordinate.
     * @return The number of obstacles passed. (0 or 1)
     */

    public int updateGerm (float deltaX, float deltaY) {
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

}
