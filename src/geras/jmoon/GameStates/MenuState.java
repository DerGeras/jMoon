package geras.jmoon.GameStates;

import geras.jmoon.main.JMoonGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import TWLSlick.BasicTWLGameState;

/**
 * 
 * @author Geras
 *
 */
public class MenuState extends BasicTWLGameState  {

	private static final int id = JMoonGame.GameStates.MENU.ordinal();

	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int timeSinceLastFrame) throws SlickException {
		handleInput(container.getInput(), game);

	}
	
	/**
	 * handle input on update
	 * @param input
	 */
	public void handleInput(Input input, StateBasedGame game){
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			game.enterState(JMoonGame.GameStates.WORLD.ordinal());
		}
	}

	@Override
	public int getID() {
		return id;
	}

}
