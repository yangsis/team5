/**
 * 
 */
package com.event;

import java.util.ArrayList;
import java.util.List;

import com.action.Actions;
import com.model.Sprite;

/**
 * This class should be used to implement actions that need to be performed as
 * time changes.
 * 
 * @author team5
 *
 */
public class TimeChange implements Event {

	private static TimeChange timeChange;
	private List<Sprite> attachedSpriteList;

	/**
	 * Private constructor to prevent instantiation.
	 */
	private TimeChange() {
		attachedSpriteList = new ArrayList<Sprite>();
	}

	public static TimeChange getInstance() {
		if (null == timeChange) {
			timeChange = new TimeChange();
		}
		return timeChange;
	}

	@Override
	public void addSprite(Sprite sprite) {
		if (!attachedSpriteList.contains(sprite)) {
			attachedSpriteList.add(sprite);
		}
	}

	@Override
	public void triggerAction(int data) {
		for (Sprite sprite : attachedSpriteList) {
			for (Actions action : sprite.getEventActionMap().get(Events.TIMECHANGE)) {
				action.getValue().performAction(0, sprite);
			}
		}
	}
}
