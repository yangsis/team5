package com.action;

import java.awt.event.KeyEvent;


import com.event.Events;
import com.launcher.Constants;
import com.model.Sprite;

public class MoveVertical implements Action {

	public MoveVertical() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void performAction(int data, Sprite... spriteArray) {
		// TODO Auto-generated method stub
		if (data == Constants.EVENT_KEY_UP) {
			for (Sprite sprite : spriteArray) {
				sprite.setX(sprite.getY() + 30);
			}
		}
		if (data == Constants.EVENT_KEY_DOWN) {
			for (Sprite sprite : spriteArray) {
				sprite.setX(sprite.getY() - 30);
			}
		}

	}

}
