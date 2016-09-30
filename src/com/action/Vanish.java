/**
 * 
 */
package com.action;

import com.event.Events;
import com.model.Sprite;

/**
 * This class implements vanish action on specified sprite so that the sprite
 * will not appear in the game panel on canvas repaint.
 * 
 * @author team5
 *
 */
public class Vanish implements Action {

	@Override
	public void performAction(int data, Sprite... spriteArray) {
		for (Sprite sprite : spriteArray) {
			if (sprite.getEventActionMap().get(Events.COLLISION).contains(Actions.VANISH)) {
				sprite.setDestroyed(true);
			}
		}
	}

}
