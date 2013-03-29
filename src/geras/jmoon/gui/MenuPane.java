package geras.jmoon.gui;

import geras.jmoon.GameStates.WorldGameState;
import geras.jmoon.entites.PlayerEntity;

import org.newdawn.slick.Input;

public class MenuPane extends BasicPane {
	
	private WorldGameState state;

	public MenuPane(int relativeX, int relativeY, int width, int height, WorldGameState state) {
		super(relativeX, relativeY, width, height, "Sprites/GUI/MenuBackGround.png");
		this.state = state;
		
		Button continueButton = new Button(width / 2 - 64, height / 2, 128, 32, "Sprites/GUI/ContinueButton.png");
		continueButton.addButtonListener(new Button.ContinueButton(this, state));
		addChild(continueButton);
		
		Button saveAndExitButton = new Button(width / 2 - 64, height / 2 + 100, 128, 32, "Sprites/GUI/Save_and_Quit.png");
		saveAndExitButton.addButtonListener(new Button.SaveAndQuitButton());
		addChild(saveAndExitButton);
	}
	
	@Override
	public void handleInput(Input input, PlayerEntity player){
		if(visible){
			childrenInput(input, player);
			if(input.isKeyPressed(Input.KEY_ESCAPE)){
				this.setVisibility(false);
				state.unPause();
			}
		}
	}

}
