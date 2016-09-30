package com.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.apache.log4j.Logger;

import com.launcher.Constants;

/**
 * This class represents the UI for game editor panel.
 * 
 * @author team5
 *
 */
public class GameMakerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8296623610289320323L;

	private static final Logger LOGGER = Logger.getLogger(GameMakerPanel.class);

	private JPanel spritePanel = new JPanel();
	private JPanel imagePanel = new JPanel();
	private JPanel eventPanel = new JPanel();
	private JPanel controlPanel = new JPanel();
	private JPanel backgroundPanel = new JPanel();
	private JScrollPane imageScrollPane;
	private JComboBox<Object> backgroundImageComboBox;

	private final JLabel spriteNameLabel;
	private final JLabel xPosition;
	private final JLabel yPosition;
	private final JLabel eventTypeLabel;
	private final JLabel actionTypeLabel;
	private final JLabel backgroundSelectLabel;

	private Object selectedImage;
	private JTextField spriteNameTextField;
	private JTextField xPositionTextField;
	private JTextField yPositionTextField;
	private JComboBox<Object> eventTypeComboBox;
	private JList<Object> actionList;
	private JList<Object> backgroundImageList;

	private JButton attachButton = new JButton(Constants.ATTACH);
	private JButton createSpriteButton = new JButton(Constants.CREATE);
	private JButton deleteSpriteButton = new JButton(Constants.DELETE);
	private JButton saveButton;
	private JButton loadButton;
	private JButton playButton;

	private GridBagLayout gridBagLayout = new GridBagLayout();
	private GridBagConstraints gridBagConstraints;
	private Properties props;

	private Object[] imageStrings;

	ImageIcon backgroundImage;

	public GameMakerPanel() {
		setPreferredSize(new Dimension(Constants.GAME_MAKER_PANEL_WIDTH, Constants.GAME_MAKER_PANEL_HEIGHT));
		setLayout(new GridLayout(5, 0));
		props = loadConfigProperties();
		eventPanel = new JPanel();
		controlPanel = new JPanel();
		spriteNameTextField = new JTextField(10);
		spriteNameLabel = new JLabel(Constants.NAME);
		xPosition = new JLabel(Constants.X_POSITION);
		yPosition = new JLabel(Constants.Y_POSITION);
		xPositionTextField = new JTextField(5);
		yPositionTextField = new JTextField(5);
		imageScrollPane = new JScrollPane();
		backgroundSelectLabel = new JLabel(Constants.BACKGROUND_IMAGE);
		backgroundImageComboBox = new JComboBox<Object>(populatebackgroundImages());
		backgroundImageComboBox.setSelectedItem(Constants.NONE);
		eventTypeLabel = new JLabel(Constants.EVENT_TYPE);
		eventTypeComboBox = new JComboBox<Object>(populateEvents());
		eventTypeComboBox.setSelectedItem(Constants.NONE);
		actionList = new JList<Object>();
		actionTypeLabel = new JLabel(Constants.ACTION_TYPE);
		selectedImage = null;

		eventTypeComboBox.addActionListener(new EventTypeListener());
		imageStrings = populateSpriteImageNames();

		initGridBagConstraints();
		populateSpritePanel(spritePanel);
		populateImagePanel(imagePanel);
		populateEventPanel(eventPanel);
		populateControlPanel(controlPanel);
		populateBackgroundPanel(backgroundPanel);

		this.add(spritePanel);
		this.add(imagePanel);
		this.add(eventPanel);
		this.add(controlPanel);
		this.add(backgroundPanel);
	}

	/**
	 * This method creates the background panel with corresponding components
	 * like the combo box.
	 * 
	 * @param backgroundPanel
	 */
	private void populateBackgroundPanel(JPanel backgroundPanel) {
		backgroundPanel.setBorder(BorderFactory.createTitledBorder(Constants.BACKGROUND_PANEL));
		backgroundPanel.setBackground(new Color(245, 252, 201));
		backgroundPanel.setLayout(gridBagLayout);

		// attachButton.setEnabled(false);

		backgroundPanel.add(backgroundSelectLabel, setGridBagConstraints(0, 0));
		backgroundPanel.add(backgroundImageComboBox, setGridBagConstraints(1, 0));
	}

	/**
	 * This method populates the list of background images that can be applied.
	 * 
	 * @return
	 */
	private Object[] populatebackgroundImages() {
		// TODO Auto-generated method stub
		List<Object> backgroundImageList = new ArrayList<Object>();
		File dir = new File(this.getClass().getClassLoader().getResource(Constants.BACKGROUND_IMAGE_PATH).getPath());
		for (String filename : dir.list()) {
			backgroundImageList.add(filename);
		}
		return backgroundImageList.toArray();

	}

	/**
	 * This method scan the specified image package to fetch all the images that
	 * a sprite can be associated with.
	 * 
	 * @return
	 */
	private Object[] populateSpriteImageNames() {
		List<Object> imageNameList = new ArrayList<Object>();

		File dir = new File(this.getClass().getClassLoader().getResource(Constants.SPRITES_IMAGE_PATH).getPath());
		for (String filename : dir.list()) {
			imageNameList.add(filename);
		}
		return imageNameList.toArray();
	}

	/**
	 * This method creates the sprite panel with corresponding components for
	 * specifying name and position of the sprite on game screen.
	 * 
	 * @param spritePanel
	 */
	private void populateSpritePanel(JPanel spritePanel) {

		spritePanel.setLayout(gridBagLayout);
		spritePanel.setBorder(BorderFactory.createTitledBorder(Constants.SPRITE_PANEL));
		spritePanel.setBackground(new Color(245, 252, 201));
		spritePanel.add(spriteNameLabel, setGridBagConstraints(0, 0));
		spritePanel.add(spriteNameTextField, setGridBagConstraints(1, 0));

		spritePanel.add(xPosition, setGridBagConstraints(0, 1));
		spritePanel.add(xPositionTextField, setGridBagConstraints(1, 1));

		spritePanel.add(yPosition, setGridBagConstraints(0, 2));
		spritePanel.add(yPositionTextField, setGridBagConstraints(1, 2));
	}

	/**
	 * This method creates a JScrollpane to be displayed in image panel and
	 * associated with a given sprite.
	 * 
	 * @param imagePanel
	 */
	private void populateImagePanel(JPanel imagePanel) {

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(gridBagLayout);
		buttonPanel.setBackground(new Color(245, 252, 201));

		imagePanel.setLayout(new GridLayout(0, 1));
		imagePanel.setBorder(BorderFactory.createTitledBorder(Constants.IMAGE_PANEL));
		imagePanel.setBackground(new Color(245, 252, 201));

		JPanel tmpImagePanel = new JPanel();
		tmpImagePanel.setLayout(new FlowLayout());

		for (int j = 0; j < imageStrings.length; j++) {
			ImageIcon imageIcon = new ImageIcon(
					getClass().getClassLoader().getResource(Constants.SPRITES_IMAGE_PATH + "/" + imageStrings[j]));
			if ((imageIcon.getIconHeight() > 30)) {
				imageIcon = compressImage(imageIcon, 30);
			}

			imageIcon.setDescription(imageStrings[j].toString());
			JButton imageButton = new JButton(imageIcon);
			imageButton.setContentAreaFilled(false);
			imageButton.setBorderPainted(false);
			imageButton.addActionListener(new ImageSelectionListener());
			tmpImagePanel.add(imageButton);
			tmpImagePanel.repaint();
		}

		imageScrollPane = new JScrollPane(tmpImagePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		imageScrollPane.setViewportView(tmpImagePanel);
		imageScrollPane.setPreferredSize(new Dimension(400, 80));
		tmpImagePanel.setBackground(new Color(180, 220, 152));
		imagePanel.add(imageScrollPane);
		buttonPanel.add(createSpriteButton, setGridBagConstraints(0, 0));
		buttonPanel.add(deleteSpriteButton, setGridBagConstraints(1, 0));
		imagePanel.add(buttonPanel);

	}

	/**
	 * This method populates the event panel with event and action selectables.
	 * 
	 * @param eventPanel
	 */
	private void populateEventPanel(JPanel eventPanel) {
		eventPanel.setBorder(BorderFactory.createTitledBorder(Constants.EVENT_PANEL));
		eventPanel.setBackground(new Color(245, 252, 201));
		eventPanel.setLayout(gridBagLayout);

		attachButton.setEnabled(false);

		eventPanel.add(eventTypeLabel, setGridBagConstraints(0, 0));
		eventPanel.add(eventTypeComboBox, setGridBagConstraints(1, 0));
		eventPanel.add(actionTypeLabel, setGridBagConstraints(0, 2));
		eventPanel.add(actionList, setGridBagConstraints(1, 2));
		eventPanel.add(attachButton, setGridBagConstraints(1, 4));
	}

	/**
	 * This method populates the event type drop down box to be associated with
	 * a given sprite.
	 * 
	 * @return
	 */
	private Object[] populateEvents() {
		Object[] tempEvents = props.keySet().toArray();
		Arrays.sort(tempEvents);
		Object[] events = { Constants.NONE };
		events = Arrays.copyOf(events, tempEvents.length + 1);
		for (int i = 0; i < tempEvents.length; i++) {
			events[i + 1] = tempEvents[i];
		}
		return events;
	}

	/**
	 * This method initializes grid bag constraints used in formatting of
	 * components in JPanel.
	 */
	private void initGridBagConstraints() {
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(5, 5, 0, 5);
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
	}

	/**
	 * This method sets current value for grid bag constraints used in
	 * formatting of components in JPanel.
	 */
	private GridBagConstraints setGridBagConstraints(int x, int y) {
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		return gridBagConstraints;
	}

	/**
	 * This method populates the control panel with control buttons such as
	 * save, load, play etc.
	 * 
	 * @param activityPanel
	 */
	private void populateControlPanel(JPanel activityPanel) {
		activityPanel.setLayout(gridBagLayout);
		activityPanel.setBorder(BorderFactory.createTitledBorder(Constants.CONTROL_PANEL));
		activityPanel.setBackground(new Color(245, 252, 201));

		saveButton = new JButton(compressImage(
				new ImageIcon(this.getClass().getClassLoader().getResource(Constants.APP_IMAGE_PATH + "save.png")),
				25));
		loadButton = new JButton(compressImage(
				new ImageIcon(this.getClass().getClassLoader().getResource(Constants.APP_IMAGE_PATH + "load.png")),
				25));
		playButton = new JButton(compressImage(
				new ImageIcon(this.getClass().getClassLoader().getResource(Constants.APP_IMAGE_PATH + "play.png")),
				25));

		saveButton.setContentAreaFilled(false);
		loadButton.setContentAreaFilled(false);
		playButton.setContentAreaFilled(false);

		saveButton.setBorderPainted(false);
		loadButton.setBorderPainted(false);
		playButton.setBorderPainted(false);

		playButton.setEnabled(false);

		activityPanel.add(saveButton, setGridBagConstraints(0, 0));
		activityPanel.add(loadButton, setGridBagConstraints(1, 0));
		activityPanel.add(playButton, setGridBagConstraints(2, 0));
	}

	/**
	 * This method resizes the image to fit into JScrollpane.
	 * 
	 * @param imageIcon
	 * @param percentage
	 * @return
	 */
	private ImageIcon compressImage(ImageIcon imageIcon, int percentage) {
		Image resizedImage;
		double imgWidth = imageIcon.getIconWidth();
		double imgHeight = imageIcon.getIconHeight();
		double x = (percentage / imgHeight);
		resizedImage = imageIcon.getImage().getScaledInstance((int) (imgWidth * x), (int) (imgHeight * x),
				Image.SCALE_SMOOTH);
		imageIcon.setImage(resizedImage);
		return imageIcon;
	}

	/**
	 * This method provides a property map containing game configuration data
	 * such as event-action combinations.
	 * 
	 * @return
	 */
	private Properties loadConfigProperties() {
		props = new Properties();
		String propFileName = Constants.CONFIG_FILE_PATH;
		InputStream inputStream;
		try {
			inputStream = this.getClass().getClassLoader().getResourceAsStream(propFileName);

			if (null != inputStream) {
				props.load(inputStream);
			} else {
				try {
					throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				} catch (FileNotFoundException e) {
					LOGGER.warn(e);
				}
			}
		} catch (IOException e) {
			LOGGER.warn(e);
		}
		return props;
	}

	/**
	 * This method resets the values previously specified by user.
	 */
	public void resetUserInputPanel() {
		spriteNameTextField.setText(null);
		selectedImage = null;
		xPosition.setText(null);
		yPosition.setText(null);
		eventTypeComboBox.setSelectedItem(Constants.NONE);
		actionList.clearSelection();
		attachButton.setEnabled(false);
	}

	/**
	 * This method resets the event and action values previously specified by
	 * user.
	 */
	public void resetEventsPanel() {
		eventTypeComboBox.setSelectedItem(Constants.NONE);
		actionList.clearSelection();
	}

	/**
	 * @return the spriteNameTextField
	 */
	public JTextField getSpriteNameTextField() {
		return spriteNameTextField;
	}

	/**
	 * @param spriteNameTextField
	 *            the spriteNameTextField to set
	 */
	public void setSpriteNameTextField(JTextField spriteNameTextField) {
		this.spriteNameTextField = spriteNameTextField;
	}

	/**
	 * @return the xPositionTextField
	 */
	public JTextField getxPositionTextField() {
		return xPositionTextField;
	}

	/**
	 * @param xPositionTextField
	 *            the xPositionTextField to set
	 */
	public void setxPositionTextField(JTextField xPositionTextField) {
		this.xPositionTextField = xPositionTextField;
	}

	/**
	 * @return the yPositionTextField
	 */
	public JTextField getyPositionTextField() {
		return yPositionTextField;
	}

	/**
	 * @param yPositionTextField
	 *            the yPositionTextField to set
	 */
	public void setyPositionTextField(JTextField yPositionTextField) {
		this.yPositionTextField = yPositionTextField;
	}

	/**
	 * @return the eventTypeComboBox
	 */
	public JComboBox<Object> getEventTypeComboBox() {
		return eventTypeComboBox;
	}

	/**
	 * @param eventTypeComboBox
	 *            the eventTypeComboBox to set
	 */
	public void setEventTypeComboBox(JComboBox<Object> eventTypeComboBox) {
		this.eventTypeComboBox = eventTypeComboBox;
	}

	/**
	 * @return the actionList
	 */
	public JList<Object> getActionList() {
		return actionList;
	}

	/**
	 * @param actionList
	 *            the actionList to set
	 */
	public void setActionList(JList<Object> actionList) {
		this.actionList = actionList;
	}

	/**
	 * @return the attachButton
	 */
	public JButton getAttachButton() {
		return attachButton;
	}

	/**
	 * @param attachButton
	 *            the attachButton to set
	 */
	public void setAttachButton(JButton attachButton) {
		this.attachButton = attachButton;
	}

	/**
	 * @return the createSpriteButton
	 */
	public JButton getCreateSpriteButton() {
		return createSpriteButton;
	}

	/**
	 * @param createSpriteButton
	 *            the createSpriteButton to set
	 */
	public void setCreateSpriteButton(JButton createSpriteButton) {
		this.createSpriteButton = createSpriteButton;
	}

	/**
	 * @return the deleteSpriteButton
	 */
	public JButton getDeleteSpriteButton() {
		return deleteSpriteButton;
	}

	/**
	 * @param deleteSpriteButton
	 *            the deleteSpriteButton to set
	 */
	public void setDeleteSpriteButton(JButton deleteSpriteButton) {
		this.deleteSpriteButton = deleteSpriteButton;
	}

	/**
	 * @return the saveButton
	 */
	public JButton getSaveButton() {
		return saveButton;
	}

	/**
	 * @param saveButton
	 *            the saveButton to set
	 */
	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	/**
	 * @return the loadButton
	 */
	public JButton getLoadButton() {
		return loadButton;
	}

	/**
	 * @param loadButton
	 *            the loadButton to set
	 */
	public void setLoadButton(JButton loadButton) {
		this.loadButton = loadButton;
	}

	/**
	 * @return the playButton
	 */
	public JButton getPlayButton() {
		return playButton;
	}

	/**
	 * @param playButton
	 *            the playButton to set
	 */
	public void setPlayButton(JButton playButton) {
		this.playButton = playButton;
	}

	/**
	 * @return the selectedImage
	 */
	public Object getSelectedImage() {
		return selectedImage;
	}

	/**
	 * @param selectedImage
	 *            the selectedImage to set
	 */
	public void setSelectedImage(Object selectedImage) {
		this.selectedImage = selectedImage;
	}

	/**
	 * @return the backgroundImageComboBox
	 */
	public JComboBox<Object> getBackgroundImageComboBox() {
		return backgroundImageComboBox;
	}

	/**
	 * @param backgroundImageComboBox
	 *            the backgroundImageComboBox to set
	 */
	public void setBackgroundImageComboBox(JComboBox<Object> backgroundImageComboBox) {
		this.backgroundImageComboBox = backgroundImageComboBox;
	}

	/**
	 * Add Button Listeners
	 */
	public void addCreateListener(ActionListener a) {
		createSpriteButton.addActionListener(a);
	}

	public void addAttachButtonListener(ActionListener a) {
		attachButton.addActionListener(a);
	}

	public void addSaveButtonListener(ActionListener a) {
		saveButton.addActionListener(a);
	}

	public void addLoadButtonListener(ActionListener a) {
		loadButton.addActionListener(a);
	}

	public void addPlayButtonListener(ActionListener a) {
		playButton.addActionListener(a);
	}

	public void addBackgroundImageChangeListener(ActionListener a) {
		backgroundImageComboBox.addActionListener(a);
	}

	public JLabel getBackgroundSelectLabel() {
		return backgroundSelectLabel;
	}

	public JList<Object> getBackgroundImageList() {
		return backgroundImageList;
	}

	public void setBackgroundImageList(JList<Object> backgroundImageList) {
		this.backgroundImageList = backgroundImageList;
	}

	/**
	 * This class listens to clicks on images for sprites and stores the name of
	 * selected image.
	 * 
	 * @author team5
	 *
	 */
	private class ImageSelectionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			selectedImage = ((ImageIcon) button.getIcon()).getDescription();
		}
	}

	/**
	 * This class populates action list corresponding to changes in selected
	 * value of events for a given sprite.
	 * 
	 * @author team5
	 *
	 */
	private class EventTypeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String actions = ((JComboBox<?>) e.getSource()).getSelectedItem().toString();
			if (!Constants.NONE.equals(actions)) {
				actionList.setListData(props.getProperty(actions).split(","));
			} else {
				actionList.setListData(new String[0]);
			}
		}
	}
}
