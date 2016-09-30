/**
 * 
 */
package com.command;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.controller.GameController;
import com.launcher.Constants;
import com.ui.GameMakerPanel;

/**
 * This com.command attaches the selected background image to the panel.
 * 
 * @author team5
 *
 */
public class BackgroundChangeCommand implements Command {

	private GameController gameController;

	public BackgroundChangeCommand(GameController gameController) {
		this.gameController = gameController;
	}

	@Override
	public void executeCommand() {

		GameMakerPanel gameMakerPanel = gameController.getGameContainer().getGameMakerPanel();

		String selectedbackgroundImage = gameMakerPanel.getBackgroundImageComboBox().getSelectedItem().toString();

		if (!Constants.NONE.equals(selectedbackgroundImage)) {
			ImageIcon backgroundImage = new ImageIcon(getClass().getClassLoader()
					.getResource(Constants.BACKGROUND_IMAGE_PATH + "/" + selectedbackgroundImage));

			gameController.getGameContainer().getGamePlayPanel().setBackgroudImage((backgroundImage.getImage()));
			gameController.getGameContainer().getGamePlayPanel().repaint();
		}

	}

	/**
	 * This method scales the image to fit game panel window.
	 * 
	 * @return
	 */
	private Image scaleImage(Image image) {
		Image i = image.getScaledInstance(450, 850, Image.SCALE_SMOOTH);
		return i;
	}

}
