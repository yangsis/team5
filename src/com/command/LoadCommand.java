/**
 * 
 */
package com.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import org.apache.log4j.Logger;

import com.action.Actions;
import com.controller.GameController;
import com.event.Events;
import com.launcher.Constants;
import com.model.SavableGameObject;
import com.model.Sprite;

/**
 * This com.command loads the selected game maker file which stores user
 * activity.
 * 
 * @author team5
 *
 */
public class LoadCommand implements Command {
	private static final Logger LOGGER = Logger.getLogger(LoadCommand.class);

	private GameController gameController;

	public LoadCommand(GameController gameController) {
		this.gameController = gameController;
	}

	@Override
	public void executeCommand() {
		JFileChooser fileChooser = new JFileChooser();

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			SavableGameObject gameObject = loadFile(selectedFile);

			List<Sprite> spriteList = gameObject.getSpriteList();
			gameController.getGameContainer().getGamePlayPanel().setSpriteList(retrieveSpriteImages(spriteList));

			for (Sprite sprite : spriteList) {
				retrieveEventsAndActions(sprite, spriteList);
				gameController.getSpriteNames().add(sprite.getName());
			}
			gameController.getGameContainer().repaint();
			gameController.getGameContainer().getGameMakerPanel().getPlayButton().setEnabled(true);
		}
	}

	/**
	 * Loads the saved game file.
	 * 
	 * @param file
	 * @return
	 */
	private SavableGameObject loadFile(File file) {
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		SavableGameObject gameObject = null;
		try {
			fileIn = new FileInputStream(file);
			in = new ObjectInputStream(fileIn);
			gameObject = (SavableGameObject) in.readObject();

		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage() + e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage() + e);
		} catch (ClassNotFoundException e) {
			LOGGER.error(e.getMessage() + e);
		} finally {
			try {
				in.close();
				fileIn.close();
			} catch (IOException e) {
				LOGGER.error(e.getMessage() + e);
			}
		}
		return gameObject;
	}

	/**
	 * This method retrieves Image object for all the sprites from the saved
	 * files. It is needed as the Image object is not Serializable.
	 * 
	 * @param spriteList
	 * @return
	 */
	private List<Sprite> retrieveSpriteImages(List<Sprite> spriteList) {

		for (Sprite sprite : spriteList) {
			ImageIcon icon = new ImageIcon(this.getClass().getClassLoader()
					.getResource(Constants.SPRITES_IMAGE_PATH + "/" + sprite.getImageName()));
			sprite.setImage(icon.getImage());
		}
		return spriteList;
	}

	/**
	 * This method re-associates all the sprites with their respective actions
	 * and events from the loaded object.
	 * 
	 * @param sprite
	 * @param spriteList
	 */
	private void retrieveEventsAndActions(Sprite sprite, List<Sprite> spriteList) {
		Map<Events, List<Actions>> eventActionMap = sprite.getEventActionMap();

		for (Events event : eventActionMap.keySet()) {
			event.getValue().addSprite(sprite);
		}
	}
}
