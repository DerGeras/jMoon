package geras.jmoon.GameStates;

import geras.jmoon.entites.CheaterNPC;
import geras.jmoon.entites.CowNPC;
import geras.jmoon.entites.Entity;
import geras.jmoon.entites.NPCEntity;
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
		
		//middle of the map
		int midX = container.getWidth() / 2;
		int midY = container.getHeight() / 2;
		
		//top left pixel of the map, if it were drawn completely
		int mapTopX = Math.max(Settings.resolutionX - Settings.mapWidth * Settings.tileWidth, Math.min(0,midX - player.getPosX()));
		int mapTopY = Math.max(Settings.resolutionY - Settings.mapHeight * Settings.tileHeight, Math.min(0, midY - player.getPosY()));
		
		//width and height to be drawn
		int width = Settings.resolutionX / Settings.tileWidth + 4;
		int height = Settings.resolutionY / Settings.tileHeight + 4;
		
		//first Field to be drawn
		int topLeftFieldX = Math.min(Settings.mapWidth - width, Math.max(0, player.getPosX() / Settings.tileWidth - (width / 2)));
		int topLeftFieldY = Math.min(Settings.mapHeight - height, Math.max(0, player.getPosY() / Settings.tileHeight - (height / 2)));
		
		//Position where the first field should be drawn
		int topLeftDrawX = mapTopX + topLeftFieldX * Settings.tileHeight;
		int topLeftDrawY = mapTopY + topLeftFieldY * Settings.tileHeight;
		
		//Render ground
		worldMap.render(topLeftDrawX, topLeftDrawY, topLeftFieldX, topLeftFieldY, width, height, "Ground");
		
		//Render Plants
		worldMap.render(topLeftDrawX, topLeftDrawY, topLeftFieldX, topLeftFieldY, width, height, "Plants");
				
		//Render Decoration
		worldMap.render(topLeftDrawX, topLeftDrawY, topLeftFieldX, topLeftFieldY, width, height, "Decoration");
	    
		// draw entites
		for(Entity entity : worldMap.entityList){
			entity.draw(g, mapTopX, mapTopY, worldMap);
		}
		
		
		//display the current Tool
		g.drawString(player.getCurrentTool().getName(), 50, 500);
		g.drawString("Number: " + player.getCurrentTool().getStackSize(), 50, 515);
		g.drawString("Durability: " + player.getCurrentTool().getDurability(), 50, 530);
		
	}
	
	/**
	 * Initialize the WorldGameState
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		System.out.println("Hi");
		
		worldMap = new Map("Sprites/MainSprites.png", Settings.mapWidth, Settings.mapHeight); //load the main map
		
		//init player
		JMoonGame.player = new PlayerEntity(inventoryModel);
		player = JMoonGame.player;
		worldMap.entityList.add(player);
		player.setPosition(100, 100);
		
		//init entities
		worldMap.entityList.add(new CheaterNPC(null, "Nox", "Cheater", 200, 200));
		worldMap.entityList.add(new CowNPC(null, "GeMoo", "The Furious", 400, 300));
		
		worldMap.initialize();
		
	}
	
	/**
	 * Update every iteration
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int timeSinceLastFrame) throws SlickException {
		
		//temporary stuff
		inventoryListBox.giveupKeyboardFocus();
		int selected = inventoryListBox.getSelected();
		if(inventoryListBox.isVisible() && selected >= 0){
			Item item = player.getInventory().getItem(selected);
			if(item.isUsable()){
				player.setCurrentTool((UsableItem) item);
			}
		}
		//end of temporary stuff
		
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
			player.useItem(worldMap);
		}
		
		//Inventory
		if(input.isKeyPressed(Input.KEY_Q)){
			inventoryListBox.setVisible(!inventoryListBox.isVisible());
			inventoryListBox.setSelected(-1);
		}
		if(input.isKeyPressed(Input.KEY_F)){
			for(Entity entity : worldMap.entityList){
				if(entity.isNPC() && Math.abs(entity.getPosX() - player.getPosX()) < 32 && Math.abs(entity.getPosY() - player.getPosY()) < 32){
					((NPCEntity)entity).interact(player, worldMap, game);
				}
			}
		}
		
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
