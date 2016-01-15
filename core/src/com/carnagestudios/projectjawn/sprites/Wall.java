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

public class Wall extends sprite{


    public Wall (int x, int y) {
        texture = new Texture("sidewall.png");
        Driver.add_assets(1);

        position = new Vector2 (x, y);
        hitBox = new Rectangle (x, y, texture.getWidth(), texture.getHeight());
    }

    public void dispose () {
        texture.dispose();
        Driver.print_debug("Wall disposed");
        Driver.remove_assets(1);
    }

    public void update(float dt) {//Nothing to update wall has no changing fields.
    }


}
