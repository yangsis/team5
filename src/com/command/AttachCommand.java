/**
 * 
 */
package com.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.action.Actions;
import com.controller.GameController;
import com.event.Events;
import com.ui.GameMakerPanel;

/**
 * This com.command attaches the selected event/action to a sprite.
 * 
 * @author team5
 *
 */
public class AttachCommand implements Command {
	private GameController gameController;

	public AttachCommand(GameController gameController) {
		this.gameController = gameController;
	}

	@Override
	public void executeCommand() {
		Map<Events, List<Actions>> eventActionMap = gameController.getSprite().getEventActionMap();
		GameMakerPanel gameMakerPanel = gameController.getGameContainer().getGameMakerPanel();
		List<Actions> actionList = new ArrayList<Actions>();
		if ("None".equals(gameMakerPanel.getEventTypeComboBox().getSelectedItem().toString())) {
			JOptionPane.showMessageDialog(null, "Please select a valid Event");
		} else if (gameMakerPanel.getActionList().isSelectionEmpty()) {
			JOptionPane.showMessageDialog(null, "Please select an Action");
		} else {
			Events event = Events
					.valueOf(gameMakerPanel.getEventTypeComboBox().getSelectedItem().toString().toUpperCase());

			if (eventActionMap.containsKey(event)) {
				actionList = eventActionMap.get(event);
			}
			actionList.add(Actions.valueOf(gameMakerPanel.getActionList().getSelectedValue().toString().toUpperCase()));
			eventActionMap.put(event, actionList);
			gameController.getSprite().setEventActionMap(eventActionMap);

			event.getValue().addSprite(gameController.getSprite());

			gameMakerPanel.getPlayButton().setEnabled(true);
			gameMakerPanel.resetEventsPanel();
		}
	}

}
