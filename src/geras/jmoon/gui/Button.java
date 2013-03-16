package geras.jmoon.gui;

import geras.jmoon.entites.PlayerEntity;

import java.util.LinkedList;

import org.newdawn.slick.Input;

public class Button extends BasicPane{

	private LinkedList<ButtonListener> listeners = new LinkedList<ButtonListener>();
	
	public Button(int relativeX, int relativeY, int width, int height,
			String imageFile) {
		super(relativeX, relativeY, width, height, imageFile);
	}
	
	@Override
	public void handleInput(Input input, PlayerEntity player){
		if(visible){
			childrenInput(input, player);
			if(isHit(input.getAbsoluteMouseX(), input.getAbsoluteMouseY())){
				if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
					if(parent != null){
						parent.prioritise(this);
					}
					for(ButtonListener listener : listeners){
						listener.buttonClicked();
					}
					//input.clearMousePressedRecord();
				}
			}
		}
	}
	
	/**
	 * add a listener for buttonclicks
	 * @param listener
	 */
	public void addButtonListener(ButtonListener listener){
		listeners.add(listener);
	}
	
	/**
	 * remove a listener
	 * @param listener
	 * @return whether the listener was removed or not
	 */
	public boolean removeButtonListener(ButtonListener listener){
		return listeners.remove(listener);
	}

}
