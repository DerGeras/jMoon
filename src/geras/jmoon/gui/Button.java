package geras.jmoon.gui;

import geras.jmoon.GameState.WorldGameState;
import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.main.JMoonGame;

import java.util.LinkedList;

import org.newdawn.slick.Input;

public class Button extends BasicPane{

	public static class CloseButton implements ButtonListener{
		
		private BasicPane pane;
		
		public CloseButton(BasicPane pane){
			this.pane = pane;
		}
		
		@Override
		public void buttonClicked() {
			pane.setVisibility(false);		
		}
		
	}
	
	public static class SaveButton implements ButtonListener{

		@Override
		public void buttonClicked() {
			JMoonGame.saveGame();			
		}
		
	}
	
	public static class ContinueButton implements ButtonListener{

		private BasicPane pane;
		private WorldGameState state;
		
		public ContinueButton(BasicPane pane, WorldGameState state){
			this.pane = pane;
			this.state = state;
		}
		
		@Override
		public void buttonClicked() {
			pane.setVisibility(false);
			state.unPause();
		}
		
	}
	
	public static class SaveAndQuitButton implements ButtonListener{

		@Override
		public void buttonClicked() {
			JMoonGame.saveGame();
			JMoonGame.gameContainer.exit();
		}
		
	}
	
	
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
