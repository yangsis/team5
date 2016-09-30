/**
 * 
 */
package com.event;

import java.util.ArrayList;
import java.util.List;

import com.action.Actions;
import com.model.Sprite;
import com.observer.GenericObserver;

/**
 * This class should be used to implement actions to be performed when Keyboard
 * events are captured.
 * 
 * @author team5
 *
 */
public class KeyPress implements Event, GenericObserver<Integer> {

	private static KeyPress keyPress;
	private List<Sprite> attachedSpriteList;

	/**
	 * Private constructor to prevent instantiation.
	 */
	public KeyPress() {
		attachedSpriteList = new ArrayList<Sprite>();
	}

	public static KeyPress getInstance() {
		if (null == keyPress) {
			keyPress = new KeyPress();
		}
		return keyPress;
	}

	@Override
	public void addSprite(Sprite sprite) {
		if (!attachedSpriteList.contains(sprite)) {
			attachedSpriteList.add(sprite);
		}
	}

	@Override
	public void triggerAction(int data) {
		System.out.println("trigger: " + data);
		for (Sprite sprite : attachedSpriteList) {
			for (Actions action : sprite.getEventActionMap().get(Events.KEYPRESS)) {
				action.getValue().performAction(data, sprite);
				System.out.println("triggerAction reach");
			}
		}
	}

	@Override
	public void update(Integer... data) {
		// TODO Auto-generated method stub
		System.out.println("update reach");
		System.out.println(data[0]);
		triggerAction(data[0]);
	}
	
	
}
