package com.carnagestudios.projectjawn.sprites;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by vishu on 1/28/2016.
 */
public class Water extends Obstacle {
    private static final float MAX_Y_SPEED = 300;
    private static final float MIN_Y = -1920-960;

    public Water(Texture texture, float x, float y)
    {
        super(texture);
        this.setPosition(x, y);
        this.setVelocityX(0);
        this.setVelocityY(MAX_Y_SPEED);
    }

    public void shiftDown(float shiftY)
    {
        this.setY(this.getY() - shiftY);
    }

    public void updateWater(float dt)
    {

        setVelocityY (getVelocityY() * dt);

        if(this.getY() + getVelocityY() < MIN_Y)
            this.setY(MIN_Y);
        else
            setY (getY() + getVelocityY());

        setVelocityY (getVelocityY() / dt);
    }
}
