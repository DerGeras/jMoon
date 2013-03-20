package geras.jmoon.main;

import geras.jmoon.GameStates.MainMenuGameState;
import geras.jmoon.GameStates.MenuState;
import geras.jmoon.GameStates.WorldGameState;
import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.settings.Settings;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 * @author Geras
 *
 */
public class JMoonGame extends StateBasedGame  {
	
	public static PlayerEntity player;
	
	public static WorldGameState worldGameState;
	
	public enum GameStates{
		MAIN_MENU,
		WORLD,
		MENU,
		INVENTORY_SCREEN
	}
	
	
	public JMoonGame() {
		super("JMoon");
	}
	
	
	/**
	 * Initialize all the states
	 */
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new MainMenuGameState());
		addState(worldGameState = new WorldGameState());
		addState(new MenuState());
	}
	
	
	/**
	 * start here
	 * Set up the game container and start the game
	 * @param argv - unused
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new JMoonGame());
			container.setDisplayMode(Settings.resolutionX,Settings.resolutionY,false);
			container.setTargetFrameRate(300);
			container.setMinimumLogicUpdateInterval(20);
			container.setMaximumLogicUpdateInterval(20);
			container.start();
	    } catch (SlickException e) {
	    	e.printStackTrace();
	    }
	}

}
