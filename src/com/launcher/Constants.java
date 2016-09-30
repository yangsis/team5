/**
 * 
 */
package com.launcher;

/**
 * This class holds the constants used at various places in game. Any new
 * constant should be included in this class and used.
 * 
 * @author team5
 *
 */
public class Constants {
	public static final String ACTION_TYPE = "Action Type";
	public static final String EVENT_TYPE = "Event Type";
	public static final String NONE = "None";
	public static final String X_POSITION = "X-position";
	public static final String Y_POSITION = "Y-position";
	public static final String NAME = "Name";
	public static final String SPRITE_PANEL = "Sprite Panel";
	public static final String IMAGE_PANEL = "Image Panel";
	public static final String EVENT_PANEL = "Event Panel";
	public static final String CONTROL_PANEL = "Control Panel";
	public static final String BACKGROUND_PANEL = "Background Panel";
	public static final String ATTACH = "Attach";
	public static final String CREATE = "Create";
	public static final String DELETE = "Delete";
	public static final String SELECT = "SELECT";
	public static final String BACKGROUND_IMAGE = "Background Image";
	public static final int INITIAL_X_DIR = 1;
	public static final int INITIAL_Y_DIR = -1;
	
	public static final int FRAME_WIDTH = 1000;
	public static final int FRAME_HEIGHT = 900;
	public static final int GAME_BOARD_PANEL_WIDTH = 550;
	public static final int GAME_BOARD_PANEL_HEIGHT = 900;
	public static final int GAME_PLAY_PANEL_WIDTH = 540;
	public static final int GAME_PLAY_PANEL_HEIGHT = 850;
	public static final int GAME_MAKER_PANEL_WIDTH = 440;
	public static final int GAME_MAKER_PANEL_HEIGHT = 900;
	
	public static final String CONFIG_FILE_PATH = "resources/config/game-config.properties";
	public static final String SPRITES_IMAGE_PATH = "resources/img/sprites";
	public static final String BACKGROUND_IMAGE_PATH = "resources/img/background";
	public static final String APP_IMAGE_PATH = "resources/img/";
	
	public static final Integer EVENT_TIMER = 1;
	public static final Integer EVENT_KEY_LEFT = 2;
	public static final Integer EVENT_KEY_RIGHT = 3;
	public static final Integer EVENT_KEY_UP = 4;
	public static final Integer EVENT_KEY_DOWN = 5;
}
