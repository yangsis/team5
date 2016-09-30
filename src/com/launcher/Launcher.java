/**
 * 
 */
package com.launcher;

import com.controller.GameController;
import com.ui.GameContainer;

/**
 * This class launches the game maker application.
 * 
 * @author team5
 *
 */
public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameContainer gameContainer = new GameContainer();
		new GameController(gameContainer);
		gameContainer.setVisible(true);
	}
}
