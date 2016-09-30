/**
 * 
 */
package com.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.action.Actions;
import com.model.Sprite;

/**
 * This class should be used to implement the actions to be performed on
 * collision between two sprites.
 * 
 * @author team5
 *
 */
public class Collision implements Event {

	private static Collision collision;
	private List<Sprite> attachedSpriteList;

	/**
	 * Private constructor to prevent instantiation.
	 */
	private Collision() {
		attachedSpriteList = new ArrayList<Sprite>();
	}

	public static Collision getInstance() {
		if (null == collision) {
			collision = new Collision();
		}
		return collision;
	}

	@Override
	public void addSprite(Sprite sprite) {
		if (!attachedSpriteList.contains(sprite)) {
			attachedSpriteList.add(sprite);
		}
	}

	@Override
	public void triggerAction(int data) {
		for (Sprite sprite1 : attachedSpriteList) {
			for (Sprite sprite2 : attachedSpriteList) {
				if (sprite1.getName() != sprite2.getName()
						&& sprite1.getRectangle().intersects(sprite2.getRectangle())) {
					executeAction(sprite1, sprite2);
				}
			}
		}
	}

	/**
	 * This method executes all the actions on both the colliding sprites as
	 * listed in the EventActionMAp.
	 * 
	 * @param spriteArray
	 */
	private void executeAction(Sprite... spriteArray) {
		for (Sprite sprite : Arrays.asList(spriteArray)) {
			List<Actions> actionList = sprite.getEventActionMap().get(Events.COLLISION);

			for (Actions action : actionList) {
				action.getValue().performAction(0, spriteArray);
			}
		}
	}
}
