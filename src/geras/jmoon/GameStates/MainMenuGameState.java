package geras.jmoon.GameStates;

import geras.jmoon.main.JMoonGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuGameState extends BasicGameState  {

	private static int id = JMoonGame.GameStates.MAIN_MENU.ordinal();
	private Image titleImage;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		titleImage = new Image("Sprites/TitleScreen.png");
	}

	@Override
	public void render(GameContainer conainer, StateBasedGame game, Graphics g) throws SlickException {
		titleImage.draw(0,0);

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
		if(input.isKeyPressed(Input.KEY_ENTER)){
			game.enterState(JMoonGame.GameStates.WORLD.ordinal());
		}
	}
	
	@Override
	public int getID() {
		return id;
	}

}
