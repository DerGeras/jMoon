package geras.jmoon.GameStates;

import geras.jmoon.main.JMoonGame;
import geras.jmoon.settings.Settings;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.matthiasmann.twl.Button;

import TWLSlick.BasicTWLGameState;
import TWLSlick.RootPane;

public class MainMenuGameState extends BasicTWLGameState  {

	private static int id = JMoonGame.GameStates.MAIN_MENU.ordinal();
	private Image titleImage;
	
	private StateBasedGame game;
	
	private Button startButton;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		titleImage = new Image("Sprites/TitleScreen.png");
		this.game = game;
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
	
	@Override
    protected RootPane createRootPane() {
        RootPane rp = super.createRootPane();
        rp.setTheme("MainMenu");

        startButton = new Button("Start Game");
        startButton.addCallback(new Runnable() {
            public void run() {
                game.enterState(JMoonGame.GameStates.WORLD.ordinal());
            }
        });
        
        rp.add(startButton);
        return rp;
    }

    @Override
    protected void layoutRootPane() {
    	startButton.adjustSize();
    	startButton.setPosition(Settings.resolutionX / 2 - startButton.getWidth() / 2, Settings.resolutionX / 2);
    }

}
