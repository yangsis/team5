/**
 * 
 */
package com.event;

import com.model.Sprite;

/**
 * This interface should be implemented by all the event classes to specify
 * respective events.
 * 
 * @author team5
 *
 */
public interface Event {
	/**
	 * This method should be implemented by all events to bind the specified
	 * sprite to the event.
	 * 
	 * @param sprite
	 */
	public void addSprite(Sprite sprite);

	/**
	 * This method should be implemented by all the Event classes and should be
	 * used to invoke actions corresponding to all the events for a given
	 * sprite.
	 */
	public void triggerAction(int data);

}
