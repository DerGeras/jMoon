package geras.jmoon.GameStates;

import geras.jmoon.gui.BasicGUIElement;
import geras.jmoon.gui.Button;
import geras.jmoon.gui.ButtonListener;
import geras.jmoon.main.JMoonGame;
import geras.jmoon.settings.Settings;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuGameState extends BasicGameState  {

	private class GameStateSwitcher implements ButtonListener{
		
		private StateBasedGame game;
		private int id;
		
		public GameStateSwitcher(StateBasedGame game, int id){
			this.game = game;
			this.id = id;
		}

		@Override
		public void buttonClicked() {
			game.enterState(id);
		}
		
	}
	
	private static int id = JMoonGame.GameStates.MAIN_MENU.ordinal();
	private Image titleImage;
	
	private BasicGUIElement gui;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		titleImage = new Image("Sprites/TitleScreen.png");
		gui = new BasicGUIElement(0, 0);
		Button button = new Button(Settings.resolutionX / 2 - 64, Settings.resolutionY / 2 + 64, 128, 32, "Sprites/GUI/Start_Game.png");
		button.addButtonListener(new GameStateSwitcher(game, JMoonGame.GameStates.WORLD.ordinal()));
		gui.addChild(button);
	
	}

	@Override
	public void render(GameContainer conainer, StateBasedGame game, Graphics g) throws SlickException {
		titleImage.draw(0,0);
		gui.draw(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int timeSinceLastFrame) throws SlickException {
		handleInput(container.getInput(),game);

	}

	/**
	 * handle input on update
	 * @param input
	 */
	public void handleInput(Input input, StateBasedGame game){
		gui.handleInput(input, JMoonGame.player);
		if(input.isKeyPressed(Input.KEY_ENTER)){
			game.enterState(JMoonGame.GameStates.WORLD.ordinal());
		}
	}
	
	@Override
	public int getID() {
		return id;
	}

}
