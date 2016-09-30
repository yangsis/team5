/**
 * 
 */
package com.command;

import com.controller.GameController;
import com.event.KeyPress;
import com.observer.Clock;

/**
 * This command launches the game created by user.
 * 
 * @author team5
 *
 */
public class PlayCommand implements Command {
	private GameController gameController;

	public PlayCommand(GameController gameController) {
		this.gameController = gameController;
	}

	@Override
	public void executeCommand() {
		Clock clock = gameController.getClock();
		clock = new Clock();
		KeyPress keyPress = new KeyPress();
		gameController.getGameContainer().getGamePlayPanel().addObserver(keyPress);
		gameController.getGameContainer().getGamePlayPanel().requestFocusInWindow();
		clock.addObserver(gameController.getGameContainer().getGamePlayPanel());
		clock.reset();
		
		gameController.getGameContainer().getGamePlayPanel().requestFocus();
		
		
	}

}
