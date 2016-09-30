/**
 * 
 */
package com.action;

import com.model.Sprite;

/**
 * This interface should be implemented by all the action classes to specify
 * respective action.
 * 
 * @author team5
 *
 */
public interface Action {
	/**
	 * This method should be implemented by all the Action classes to specify
	 * respective action implementation.
	 * 
	 * @param spriteName
	 */
	public void performAction(int data, Sprite... sprite);
}
