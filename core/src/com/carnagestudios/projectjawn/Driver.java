package com.carnagestudios.projectjawn;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.carnagestudios.projectjawn.states.GameStateManager;
import com.carnagestudios.projectjawn.states.Menu;

/**
 * Driver class from which the application is run.
 */
public class Driver extends ApplicationAdapter {
	SpriteBatch batch;
    GameStateManager gsm;

	@Override
	public void create () {
		batch = new SpriteBatch();
        gsm = new GameStateManager();
        gsm.push(new Menu(gsm));

		Gdx.gl.glClearColor(0, 0, 1, 1);
	}

	@Override
	public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
	}

	@Override
	public void dispose() {
        System.err.println("Disposing GameStateManager");
        gsm.dispose();
        System.err.println("GameStateManager disposed");
	}
}
