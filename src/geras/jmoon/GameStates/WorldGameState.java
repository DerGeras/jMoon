package geras.jmoon.GameStates;

import geras.jmoon.entites.BlackSmithNPC;
import geras.jmoon.entites.CheaterNPC;
import geras.jmoon.entites.CityMerchantNPC;
import geras.jmoon.entites.CowNPC;
import geras.jmoon.entites.Entity;
import geras.jmoon.entites.Merchant;
import geras.jmoon.entites.NPCEntity;
import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.gui.BasicGUIElement;
import geras.jmoon.gui.Button;
import geras.jmoon.gui.InventoryPane;
import geras.jmoon.gui.TradePane;
import geras.jmoon.items.HandItem;
import geras.jmoon.items.Item;
import geras.jmoon.items.UsableItem;
import geras.jmoon.main.JMoonGame;
import geras.jmoon.savegames.GameLoader;
import geras.jmoon.settings.Settings;
import geras.jmoon.time.Clock;
import geras.jmoon.world.Map;
import geras.jmoon.world.WorldElements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class WorldGameState extends BasicGameState {

	
	public Map worldMap; //the main map
	
	private static final int id = JMoonGame.GameStates.WORLD.ordinal();
	
	public BasicGUIElement gui;
	public InventoryPane inventoryPane;
	public TradePane tradePane;

	private int cursorX;
	private int cursorY;
	
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
		int mapTopX = Math.max(Settings.resolutionX - Settings.mapWidth * Settings.tileWidth, Math.min(0,midX - (int)JMoonGame.player.getPosX()));
		int mapTopY = Math.max(Settings.resolutionY - Settings.mapHeight * Settings.tileHeight, Math.min(0, midY - (int)JMoonGame.player.getPosY()));
		
		//width and height to be drawn
		int width = Settings.resolutionX / Settings.tileWidth + 4;
		int height = Settings.resolutionY / Settings.tileHeight + 4;
		
		//first Field to be drawn
		int topLeftFieldX = Math.min(Settings.mapWidth - width, Math.max(0, (int)JMoonGame.player.getPosX() / Settings.tileWidth - (width / 2)));
		int topLeftFieldY = Math.min(Settings.mapHeight - height, Math.max(0, (int)JMoonGame.player.getPosY() / Settings.tileHeight - (height / 2)));
		
		//Position where the first field should be drawn
		int topLeftDrawX = mapTopX + topLeftFieldX * Settings.tileHeight;
		int topLeftDrawY = mapTopY + topLeftFieldY * Settings.tileHeight;
		
		//Render ground
		worldMap.render(topLeftDrawX, topLeftDrawY, topLeftFieldX, topLeftFieldY, width, height, "Ground");
		
		//Render Plants
		worldMap.render(topLeftDrawX, topLeftDrawY, topLeftFieldX, topLeftFieldY, width, height, "Plants");
				
		//Render Decoration
		worldMap.render(topLeftDrawX, topLeftDrawY, topLeftFieldX, topLeftFieldY, width, height, "Decoration");
	    
		//draw Cursor
		cursorX = container.getInput().getMouseX() - mapTopX;
		cursorY = container.getInput().getMouseY() - mapTopY;
		
		if(Math.abs(cursorX - JMoonGame.player.getPosX()) < 2.5f * Settings.tileWidth && Math.abs(cursorY - JMoonGame.player.getPosY()) < 2.5f * Settings.tileHeight){
			int cursorFX = cursorX / Settings.tileWidth;
			int cursorFY = cursorY / Settings.tileHeight;
			worldMap.getWorldElement().draw(mapTopX + cursorFX * Settings.tileWidth, mapTopY + cursorFY * Settings.tileHeight, cursorFX, cursorFY, WorldElements.OVERLAY_VALUE, worldMap);
		}
		
		
		// draw entites
		for(Entity entity : worldMap.entityList){
			entity.draw(g, mapTopX, mapTopY, worldMap);
		}
		
		
		//display the current Tool
		g.setColor(Color.white);
		g.drawString(JMoonGame.player.getCurrentTool().getName(), 50, Settings.resolutionY - 100);
		g.drawString("Number: " + JMoonGame.player.getCurrentTool().getStackSize(), 50, Settings.resolutionY - 85);
		
		//draw the gui
		gui.draw(g);
		
		//draw the amount of money
		g.setColor(Color.yellow);
		g.drawString("Money: " + JMoonGame.player.getInventory().getMoney(), 50, Settings.resolutionY - 50);
		
	}
	
	/**
	 * Initialize the WorldGameState
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		File file = new File("save1.xml");
		if(file.exists()){
			GameLoader.loadGameFromFile(file);
		}
		else{
			worldMap = new Map("Sprites/MainSprites.png", Settings.mapWidth, Settings.mapHeight); //load the main map
			
			//init player
			JMoonGame.player = new PlayerEntity();
			worldMap.entityList.add(JMoonGame.player);
			int midX = Settings.mapWidth / 2 * Settings.tileWidth;
			int midY = Settings.mapHeight / 2 * Settings.tileHeight;
			JMoonGame.player.setPosition(midX, midY);
			
			//init entities
			worldMap.entityList.add(new CheaterNPC("Nox", "Cheater", midX - 200, midY + 200));
			worldMap.entityList.add(new CowNPC("GeMoo", "The Furious", midX - 100, midY - 300));
			worldMap.entityList.add(new CityMerchantNPC("SiBi", "City Merchant", midX - 300, midY));
			worldMap.entityList.add(new BlackSmithNPC("Mad-Murdoc", "Blacksmith", midX + 300, midY + 300));
			
			worldMap.initialize();
		}
		
		
		//initialize gui
		gui = new BasicGUIElement(0, 0);
		inventoryPane = new InventoryPane(Settings.resolutionX - 250, 50, JMoonGame.player.getInventory());
		gui.addChild(inventoryPane);
		
		tradePane = new TradePane(0,0,Settings.resolutionX, Settings.resolutionY, JMoonGame.player, null);
		gui.addChild(tradePane);
		tradePane.setVisibility(false);
		
		Button xButton = new Button(inventoryPane.getWidth() - 15, -15, 32, 32, "Sprites/GUI/XButton.png");
		xButton.addButtonListener(new Button.CloseButton(inventoryPane));
		inventoryPane.addChild(xButton);
	}
	
	/**
	 * Update every iteration
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int timeSinceLastFrame) throws SlickException {
		
		if(container.hasFocus()){
			//update time
			Clock.update(timeSinceLastFrame);
			
			//temporary stuff
			int selected = inventoryPane.getSelected();
			if(inventoryPane.isVisible() && selected >= 0){
				Item item = JMoonGame.player.getInventory().getItem(selected);
				if(item.isUsable()){
					JMoonGame.player.setCurrentTool((UsableItem) item);
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
	}

	/**
	 * handle input on update
	 * @param input
	 */
	public void handleInput(Input input, StateBasedGame game){
		//give the input to the gui first
		gui.handleInput(input, JMoonGame.player);
		
		//Movement
		if(input.isKeyDown(Input.KEY_A)){
			JMoonGame.player.setNextX(-1);
		}
		if(input.isKeyDown(Input.KEY_D)){
			JMoonGame.player.setNextX(1);
		}
		if(input.isKeyDown(Input.KEY_W)){
			JMoonGame.player.setNextY(-1);
		}
		if(input.isKeyDown(Input.KEY_S)){
			JMoonGame.player.setNextY(1);
		}
		
		//Switch Tools
		if(input.isKeyPressed(Input.KEY_1)){
			inventoryPane.setSelected(-1);
			JMoonGame.player.setCurrentTool(new HandItem());
		}
		
		//Interaction
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			if(Math.abs(cursorX - JMoonGame.player.getPosX()) < 2.5f * Settings.tileWidth && Math.abs(cursorY - JMoonGame.player.getPosY()) < 2.5f * Settings.tileHeight){
				JMoonGame.player.useItem(worldMap, cursorX, cursorY);
			}
		}
		
		if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)){
			for(Entity entity : worldMap.entityList){
				if(entity.isNPC() && Math.abs(cursorX - entity.getPosX()) < 32 && Math.abs(cursorY - entity.getPosY()) < 32){
					if(Math.abs(cursorX - JMoonGame.player.getPosX()) < 2.5f * Settings.tileWidth && Math.abs(cursorY - JMoonGame.player.getPosY()) < 2.5f * Settings.tileHeight){
						((NPCEntity)entity).interact(JMoonGame.player, worldMap, game, this);
					}
				}
			}
		}
		
		//Inventory
		if(input.isKeyPressed(Input.KEY_I)){
			inventoryPane.setVisibility(!inventoryPane.isVisible());
			inventoryPane.setSelected(-1);
		}
		
		//Menu
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			//game.enterState(JMoonGame.GameStates.MENU.ordinal());
			if(tradePane.isVisible()){
				tradePane.setVisibility(false);
				inventoryPane.setVisibility(true);
			}
		}
		
		//save
		//TODO better things here
		if(input.isKeyPressed(Input.KEY_K)){
			JMoonGame.saveGame();
		}
	}
	
	
	/**
	 * get the id of this Gamestate
	 */
	@Override
	public int getID() {
		return id;
	}
	
	/**
	 * start the trading window
	 * @param merchant
	 */
	public void startTrade(Merchant merchant){
		tradePane.setMerchant(merchant);
		inventoryPane.setVisibility(false);
		tradePane.setVisibility(true);
	}
	
	/**
	 * save this state to xml
	 */
	public void saveToXML(){
		try {
			//create bufferedwriter
			File file = new File("./save1.xml");
			FileWriter writer = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(writer, 100000);
			
			//start
			out.append("<game>");
			out.newLine();
			
			//save the clock
			Clock.saveToXML(out);
			out.newLine();
			
			//save the map
			worldMap.saveToXML(out);
			out.newLine();
			
			//stop
			out.append("</game>");
			out.flush();
			
			//close the file
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
