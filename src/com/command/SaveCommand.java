/**
 * 
 */
package com.command;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;

import com.model.SavableGameObject;

/**
 * This com.command saves the current state of user activity in a local file.
 * 
 * @author team5
 *
 */
public class SaveCommand implements Command {

	private static final Logger LOGGER = Logger.getLogger(SaveCommand.class);
	private SavableGameObject savableGameObject;

	/**
	 * Parameterized Constructor
	 * 
	 * @param savableGameObject
	 */
	public SaveCommand(SavableGameObject savableGameObject) {
		this.savableGameObject = savableGameObject;
	}

	@Override
	public void executeCommand() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Game State File", "gsf"));
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
				out.writeObject(savableGameObject);
				out.close();
			} catch (IOException e) {
				LOGGER.error(e);
			}
		}
	}

	/**
	 * @return the savableGameObject
	 */
	public SavableGameObject getSavableGameObject() {
		return savableGameObject;
	}

	/**
	 * @param savableGameObject
	 *            the savableGameObject to set
	 */
	public void setSavableGameObject(SavableGameObject savableGameObject) {
		this.savableGameObject = savableGameObject;
	}
}
