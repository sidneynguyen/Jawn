package com.carnagestudios.projectjawn.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class BackgroundList {

    private ArrayList<Background> backgrounds;

    public BackgroundList () {
        backgrounds = new ArrayList<Background>();
    }

    public void add (Background background) {
        backgrounds.add(background);
    }

    public void moveY (float dy) {
        for (Background b: backgrounds) {
            b.setY(dy + b.getY());
        }
    }

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

    public void draw (SpriteBatch batch) {
        for (Background b: backgrounds) {
            b.draw (batch);
        }
    }
}
