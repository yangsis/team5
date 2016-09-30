/**
 * 
 */
package com.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


import com.launcher.Constants;
import com.model.Sprite;
import com.observer.BaseObservable;
import com.observer.GenericObservable;
import com.observer.GenericObserver;

/**
 * This class contains components on game play screen.
 * 
 * @author team5
 *
 */
public class GamePlayPanel extends JPanel implements GenericObserver<Integer>, GenericObservable<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8842878779462323972L;

	private List<Sprite> spriteList;
	private Image backgroudImage;
	public BaseObservable<Integer> inputKeyObservable = new BaseObservable<Integer>();
	public GamePlayPanel() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder());
		setPreferredSize(new Dimension(Constants.GAME_PLAY_PANEL_WIDTH, Constants.GAME_PLAY_PANEL_HEIGHT));
		this.spriteList = new ArrayList<Sprite>();
		this.addKeyListener(new KeyBoardListener());// for the key board input
		
		}
		
		// this is the event handle for the paddle movement
		private class KeyBoardListener extends KeyAdapter {
	        
			public void keyPressed(KeyEvent event) {
				int key = event.getKeyCode();
				if (key == KeyEvent.VK_RIGHT ) {
					inputKeyObservable.notifyObserver(Constants.EVENT_KEY_RIGHT);
					System.out.println("game play right");
				}
				if (key == KeyEvent.VK_LEFT) {
					inputKeyObservable.notifyObserver(Constants.EVENT_KEY_LEFT);
					System.out.println("game play left");
				}
				if (key == KeyEvent.VK_DOWN) {
					inputKeyObservable.notifyObserver(Constants.EVENT_KEY_DOWN);
					System.out.println("game play down");
				}
				if (key == KeyEvent.VK_UP) {
					inputKeyObservable.notifyObserver(Constants.EVENT_KEY_UP);
					System.out.println("game play up");
				}
				repaint();
		}
	        
	}


	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (null != backgroudImage)
			g.drawImage(backgroudImage, 0, 0, null);
		for (Sprite sprite : spriteList) {
			if (!sprite.isDestroyed()) {
				g.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(), this);
			}
		}

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

	@Override
	public void update(Integer... data) {
		repaint();
	}

	/**
	 * @return the backgroudImage
	 */
	public Image getBackgroudImage() {
		return backgroudImage;
	}

	/**
	 * @param backgroudImage
	 *            the backgroudImage to set
	 */
	public void setBackgroudImage(Image backgroudImage) {
		this.backgroudImage = backgroudImage;
	}

	@Override
	public void addObserver(GenericObserver<Integer> observer) {
		inputKeyObservable.addObserver(observer);
	}

	@Override
	public void removeObserver(GenericObserver<Integer> observer) {
		inputKeyObservable.removeObserver(observer);
	}

	@Override
	public void notifyObserver(Integer... data) {
		inputKeyObservable.notifyObserver(data);
	}

	@Override
	public void removeAllObservers() {
		inputKeyObservable.removeAllObservers();
	}


}
