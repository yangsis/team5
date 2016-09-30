/**
 * 
 */
package com.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.launcher.Constants;

/**
 * This class represents the JFrame containing user editor panel and game play
 * panel and acts as the container of whole UI.
 * 
 * @author team5
 *
 */
public class GameContainer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8659683875788012717L;
	private GameMakerPanel gameMakerPanel;
	private GamePlayPanel gamePlayPanel;
	private JPanel gameBoardPanel;

	public GameContainer() {
		this.setLayout(new BorderLayout());
		setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		gameMakerPanel = new GameMakerPanel();
		gamePlayPanel = new GamePlayPanel();
		createGameBoardPanel();
		this.add(gameMakerPanel, BorderLayout.WEST);
		this.add(gameBoardPanel, BorderLayout.EAST);
	}

	/**
	 * @return the gameMakerPanel
	 */
	public GameMakerPanel getGameMakerPanel() {
		return gameMakerPanel;
	}

	/**
	 * @param gameMakerPanel
	 *            the gameMakerPanel to set
	 */
	public void setGameMakerPanel(GameMakerPanel gameMakerPanel) {
		this.gameMakerPanel = gameMakerPanel;
	}

	/**
	 * @return the gamePlayPanel
	 */
	public GamePlayPanel getGamePlayPanel() {
		return gamePlayPanel;
	}

	/**
	 * @param gamePlayPanel
	 *            the gamePlayPanel to set
	 */
	public void setGamePlayPanel(GamePlayPanel gamePlayPanel) {
		this.gamePlayPanel = gamePlayPanel;
	}

	/**
	 * This method creates a bordered and titled placeholder for the game
	 * screen.
	 */
	private void createGameBoardPanel() {
		gameBoardPanel = new JPanel();
		gameBoardPanel.setLayout(new BorderLayout());
		gameBoardPanel.setBorder(BorderFactory.createTitledBorder("Game Panel"));
		gameBoardPanel
				.setPreferredSize(new Dimension(Constants.GAME_BOARD_PANEL_WIDTH, Constants.GAME_BOARD_PANEL_HEIGHT));
		gameBoardPanel.setBackground(new Color(245, 252, 201));
		gameBoardPanel.add(gamePlayPanel, BorderLayout.NORTH);
	}
}
