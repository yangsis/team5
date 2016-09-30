/**
 * 
 */
package com.model;

import java.io.Serializable;
import java.util.List;

import com.ui.GameContainer;

/**
 * This object is used to save the game maker state and stores the list of
 * sprites created by user.
 * 
 * @author team5
 *
 */
public class SavableGameObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8175288920817406356L;
	private List<Sprite> spriteList;

	/**
	 * Parameterized Constructor
	 * 
	 * @param gameContainer
	 */
	public SavableGameObject(GameContainer gameContainer) {
		this.spriteList = gameContainer.getGamePlayPanel().getSpriteList();
	}

	/**
	 * @return the spriteList
	 */
	public List<Sprite> getSpriteList() {
		return spriteList;
	}

	/**
	 * @param spriteList
	 *            the spriteList to set
	 */
	public void setSpriteList(List<Sprite> spriteList) {
		this.spriteList = spriteList;
	}
}
