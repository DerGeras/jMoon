package geras.jmoon.GameStates;

import geras.jmoon.entites.Entity;
import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.items.Item;
import geras.jmoon.items.UsableItem;
import geras.jmoon.main.JMoonGame;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import TWLSlick.BasicTWLGameState;
import TWLSlick.RootPane;
import de.matthiasmann.twl.ListBox;
import de.matthiasmann.twl.model.SimpleChangableListModel;

public class WorldGameState extends BasicTWLGameState {

	private Map worldMap; //the main map
	
	private PlayerEntity player;
	
	private static final int id = JMoonGame.GameStates.WORLD.ordinal();
	
	private ListBox<String> inventoryListBox;
	
	private SimpleChangableListModel<String> inventoryModel = new SimpleChangableListModel<String>();

	
	public WorldGameState(){
		super();
	}
	
	/**
	 * Render the world in each iteration
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		int midX = container.getWidth() / 2;
		int midY = container.getHeight() / 2;
		
		int mapTopX = midX - player.getPosX();
		int mapTopY = midY - player.getPosY();
		
		worldMap.render(-(Settings.tileWidth / 2)-(player.getPosX() % Settings.tileWidth),
						-(Settings.tileHeight / 2)-(player.getPosY() % Settings.tileHeight),
						(int)Math.floor((player.getPosX() / Settings.tileWidth - Settings.resolutionX / (float)Settings.tileWidth / 2.0f)),
						(int)Math.floor((player.getPosY() / Settings.tileHeight - Settings.resolutionY / (float)Settings.tileHeight / 2.0f)),
						Settings.resolutionX / Settings.tileWidth + 3,
						Settings.resolutionY / Settings.tileHeight + 3,
						"Ground");
		
		//TODO Refactor this shit
		worldMap.render(-(Settings.tileWidth / 2)-(player.getPosX() % Settings.tileWidth),
				-(Settings.tileHeight / 2)-(player.getPosY() % Settings.tileHeight),
				(int)Math.floor((player.getPosX() / Settings.tileWidth - Settings.resolutionX / (float)Settings.tileWidth / 2.0f)),
				(int)Math.floor((player.getPosY() / Settings.tileHeight - Settings.resolutionY / (float)Settings.tileHeight / 2.0f)),
				Settings.resolutionX / Settings.tileWidth + 3,
				Settings.resolutionY / Settings.tileHeight + 3,
				"Plants");
		
		// draw entites
		for(Entity entity : worldMap.entityList){
			entity.draw(g, mapTopX, mapTopY);
		}
		
		
		//TODO Refactor this shit
		worldMap.render(-(Settings.tileWidth / 2)-(player.getPosX() % Settings.tileWidth),
				-(Settings.tileHeight / 2)-(player.getPosY() % Settings.tileHeight),
				(int)Math.floor((player.getPosX() / Settings.tileWidth - Settings.resolutionX / (float)Settings.tileWidth / 2.0f)),
				(int)Math.floor((player.getPosY() / Settings.tileHeight - Settings.resolutionY / (float)Settings.tileHeight / 2.0f)),
				Settings.resolutionX / Settings.tileWidth + 3,
				Settings.resolutionY / Settings.tileHeight + 3,
				"Decoration");
	    
		
		
		//display the current Tool
		g.drawString(player.getCurrentTool().getName(), 50, 500);
		g.drawString("Number: " + player.getCurrentTool().getStackSize(), 50, 515);
		g.drawString("Durability: " + player.getCurrentTool().getDurability(), 50, 530);
		
		//"Minimap"
//	    g.scale(0.1f,0.1f);
//	    worldMap.render(5800, 150);
//	    g.resetTransform();
		
	}
	
	/**
	 * Initialize the WorldGameState
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		worldMap = new Map("Sprites/MainSprites.png", Settings.mapWidth, Settings.mapHeight); //load the main map
		
		JMoonGame.player = new PlayerEntity(inventoryModel);
		player = JMoonGame.player;
		worldMap.entityList.add(player);
		player.setPosition(100, 100);
		
		worldMap.initialize();
		
	}
	
	/**
	 * Update every iteration
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int timeSinceLastFrame) throws SlickException {
		inventoryListBox.giveupKeyboardFocus();
		handleInput(container.getInput(), game);
		worldMap.updatePlants(timeSinceLastFrame);
		//Update all entities
		for(Entity entity : worldMap.entityList){
			entity.update(timeSinceLastFrame,worldMap);
		}
	}

	/**
	 * handle input on update
	 * @param input
	 */
	public void handleInput(Input input, StateBasedGame game){
		//Movement
		if(input.isKeyDown(Input.KEY_A)){
			player.setNextX(-1);
		}
		if(input.isKeyDown(Input.KEY_D)){
			player.setNextX(1);
		}
		if(input.isKeyDown(Input.KEY_W)){
			player.setNextY(-1);
		}
		if(input.isKeyDown(Input.KEY_S)){
			player.setNextY(1);
		}
		
		//Switch Tools
		if(input.isKeyPressed(Input.KEY_1)){
			inventoryListBox.setSelected(-1);
			player.setTool(0);
		}
		if(input.isKeyPressed(Input.KEY_2)){
			inventoryListBox.setSelected(-1);
			player.setTool(1);
		}
		if(input.isKeyPressed(Input.KEY_3)){
			inventoryListBox.setSelected(-1);
			player.setTool(2);
		}
		if(input.isKeyPressed(Input.KEY_4)){
			inventoryListBox.setSelected(-1);
			player.setTool(3);
		}
		if(input.isKeyPressed(Input.KEY_5)){
			inventoryListBox.setSelected(-1);
			player.setTool(4);
		}
		
		//Interaction
		if(input.isKeyPressed(Input.KEY_E)){
			int selected = inventoryListBox.getSelected();
			if(inventoryListBox.isVisible() && selected >= 0){
				Item item = player.getInventory().getItem(selected);
				if(item.isUsable()){
					player.setCurrentTool((UsableItem) item);
				}
			}
			player.useItem(worldMap);
		}
		
		//Inventory
		if(input.isKeyPressed(Input.KEY_Q)){
			inventoryListBox.setVisible(!inventoryListBox.isVisible());
			inventoryListBox.setSelected(-1);
		}
//		if(input.isKeyPressed(Input.KEY_F)){
//			int selected = inventoryListBox.getSelected();
//			if(inventoryListBox.isVisible() && selected >= 0){
//				Item item = player.getInventory().getItem(selected);
//				if(item.isUsable()){
//					player.setCurrentTool((UsableItem)item);
//				}
//			}
//		}
		
		//Menu
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			game.enterState(JMoonGame.GameStates.MENU.ordinal());
		}
	}
	
	
	@Override
    protected RootPane createRootPane() {
        RootPane rp = super.createRootPane();
        rp.setTheme("WorldTheme");
        
        inventoryListBox = new ListBox<String>(inventoryModel);
        
        rp.add(inventoryListBox);
        
		return rp;
	}
	
	@Override
    protected void layoutRootPane() {
    	inventoryListBox.adjustSize();
    	inventoryListBox.setPosition(600, 100);
    }
	
	
	/**
	 * get the id of this Gamestate
	 */
	@Override
	public int getID() {
		return id;
	}
	
}
