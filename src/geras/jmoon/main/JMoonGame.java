package geras.jmoon.main;

import geras.jmoon.GameStates.MenuState;
import geras.jmoon.GameStates.MainMenuGameState;
import geras.jmoon.GameStates.WorldGameState;
import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.settings.Settings;

import java.net.URL;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import TWLSlick.TWLStateBasedGame;

/**
 * 
 * @author Geras
 *
 */
public class JMoonGame extends TWLStateBasedGame  {
	
	public static PlayerEntity player;
	
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
		addState(new WorldGameState());
		addState(new MenuState());
	}
	
	
	
	/**
	 * start here
	 * @param argv - unused
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new JMoonGame());
			container.setDisplayMode(Settings.resolutionX,Settings.resolutionY,false);
			container.setTargetFrameRate(300);
			container.setMinimumLogicUpdateInterval(20);
			container.setMaximumLogicUpdateInterval(25);
			container.start();
	    } catch (SlickException e) {
	    	e.printStackTrace();
	    }
	}


	@Override
	protected URL getThemeURL() {
		return JMoonGame.class.getResource("/ui/JMoonTheme.xml");
	}

}
