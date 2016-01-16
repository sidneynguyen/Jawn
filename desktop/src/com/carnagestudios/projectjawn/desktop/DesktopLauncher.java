package com.carnagestudios.projectjawn.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.carnagestudios.projectjawn.Driver;

/**
 * Manages the launching of the application implemented in Driver to run on a Desktop.
 */
public class DesktopLauncher {

	//Fields for the application configuration
    private static final int SCREEN_WIDTH = 450;
    private static final int SCREEN_HEIGHT = 800;
	private static final String TITLE = "JAWN";

	/**
	 * This main method starts the desktop application and its configuration.
	 * @param arg Not Used
	 */
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//Configure fields
		config.width = SCREEN_WIDTH;
        config.height = SCREEN_HEIGHT;
		config.title = TITLE;
		//Launch application.
		new LwjglApplication(new Driver(), config);
	}
}
