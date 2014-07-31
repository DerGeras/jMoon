package geras.jmoon.main;

import geras.jmoon.GameState.MainMenuGameState;
import geras.jmoon.GameState.MenuState;
import geras.jmoon.GameState.WorldGameState;
import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.reference.Settings;

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
	public static AppGameContainer gameContainer;
	
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
	
	public static void saveGame(){
		worldGameState.saveToXML();
	}
	
	
	/**
	 * start here
	 * Set up the game container and start the game
	 * @param argv - unused
	 */
	public static void main(String[] argv) {
		try {
			gameContainer = new AppGameContainer(new JMoonGame());
			int resX = Math.min(gameContainer.getScreenWidth(), Settings.initResolutionX);
			int resY = Math.min(gameContainer.getScreenHeight(), Settings.initResolutionY);
			gameContainer.setDisplayMode(resX,resY,false);
			gameContainer.setTargetFrameRate(300);
			gameContainer.setMinimumLogicUpdateInterval(20);
			gameContainer.setMaximumLogicUpdateInterval(20);
			gameContainer.start();
	    } catch (SlickException e) {
	    	e.printStackTrace();
	    }
	}

}
