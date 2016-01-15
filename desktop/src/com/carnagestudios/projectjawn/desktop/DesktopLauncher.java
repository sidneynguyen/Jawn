package com.carnagestudios.projectjawn.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.carnagestudios.projectjawn.Driver;

public class DesktopLauncher {
    private static final int VISHU_DUMB_W = 480;
    private static final int VISHU_DUMB_H = 800;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = VISHU_DUMB_W;
        config.height = VISHU_DUMB_H;
		new LwjglApplication(new Driver(), config);
	}
}
