/**
 * 
 */
package com.observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.event.Collision;
import com.event.TimeChange;

/**
 * @author team5
 *
 */
public class Clock implements GenericObservable<Integer> {
	private Timer timer;
	private BaseObservable<Integer> inputButtonObservable = new BaseObservable<Integer>();

	public Clock() {
		this.timer = new Timer(5, new TimerListener());
	}

	private class TimerListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			TimeChange.getInstance().triggerAction(0);
			Collision.getInstance().triggerAction(0);
			inputButtonObservable.notifyObserver();
		}
	}

	public void reset() {
		timer.setDelay(5);
		timer.restart();
	}

	@Override
	public void addObserver(GenericObserver<Integer> observer) {
		inputButtonObservable.addObserver(observer);
	}

	@Override
	public void removeObserver(GenericObserver<Integer> observer) {
		inputButtonObservable.removeObserver(observer);
	}

	@Override
	public void notifyObserver(Integer... data) {
		inputButtonObservable.notifyObserver(data);
	}

	@Override
	public void removeAllObservers() {
		inputButtonObservable.removeAllObservers();
	}

	public void update(Integer... data) {
	}

}
