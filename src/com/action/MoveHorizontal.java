/**
 * 
 */
package com.action;

import com.launcher.Constants;
import com.model.Sprite;

/**
 * This class should be used to implement horizontal movement action for the
 * specified sprite.
 * 
 * @author team5
 *
 */
public class MoveHorizontal implements Action {

	@Override
	public void performAction(int data, Sprite... spriteArray) {
		if (data == Constants.EVENT_KEY_LEFT) {
			for (Sprite sprite : spriteArray) {
				sprite.setX(sprite.getX() - 30);
				System.out.println("performAction reach");
			}
		}
		if (data == Constants.EVENT_KEY_RIGHT) {
			for (Sprite sprite : spriteArray) {
				sprite.setX(sprite.getX() + 30);
				System.out.println("performAction reach");
			}
		}

	}

}
