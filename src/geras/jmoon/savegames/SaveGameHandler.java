package geras.jmoon.savegames;

import geras.jmoon.entites.Entity;
import geras.jmoon.entites.EntityFactory;
import geras.jmoon.items.Inventory;
import geras.jmoon.items.Item;
import geras.jmoon.items.ItemFactory;
import geras.jmoon.main.JMoonGame;
import geras.jmoon.plants.Plant;
import geras.jmoon.plants.PlantFactory;
import geras.jmoon.time.Clock;
import geras.jmoon.world.Map;
import geras.jmoon.world.MapLayer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaveGameHandler extends DefaultHandler {
	
	Map currMap = null;
	
	MapLayer currentLayer = null;
	boolean inLayerLine = false;
	
	Entity currEntity = null;
	
	Inventory currInventory = null;
	
	
	public void startElement(String uri, String localName,String qName, 
            Attributes attributes) throws SAXException {
		
		//load clock
		if(qName.equalsIgnoreCase("clock")){
			Clock.readFromAttributes(attributes);
		}
		
		//create a map
		if(qName.equalsIgnoreCase("map")){
			createMap(attributes);
		}
		
		//add layers
		if(qName.equalsIgnoreCase("layer")){
			addLayer(attributes);
		}
		
		//layerLine
		if(qName.equalsIgnoreCase("line") && currentLayer != null){
			inLayerLine = true;
		}
		
		//plants
		if(qName.equalsIgnoreCase("plant") && currMap != null){
			Plant plant = PlantFactory.getPlant(attributes, currMap);
			if(plant != null){
				currMap.addPlant(plant);
			}
		}
		
		//entitis
		if(qName.equalsIgnoreCase("entity") && currMap != null){
			currEntity = EntityFactory.getEntity(attributes);
			currMap.entityList.add(currEntity);
		}
		
		//inventory
		if(qName.equalsIgnoreCase("inventory") && currEntity != null){
			if(currEntity != null){ //yes, I saw it, and I hate it
				currInventory = currEntity.getInventory();
				currInventory.clear();
				currInventory.setMoney(Integer.parseInt(attributes.getValue("money")));
			}
		}
		
		//items
		if(qName.equalsIgnoreCase("item") && currInventory != null){
			Item item = ItemFactory.getItem(attributes.getValue("name"), 1, 0);
			item.readFromAttributes(attributes);
			currInventory.addStrictItem(item);
		}
		
		//items
		if(qName.equalsIgnoreCase("tool") && currInventory != null){
			Item item = ItemFactory.getItem(attributes.getValue("name"), 1, 0);
			item.readFromAttributes(attributes);
			currInventory.addStrictItem(item);
		}
	}
	
	public void endElement(String uri, String localName,
			String qName) throws SAXException {
		
		//stop map
		if(qName.equalsIgnoreCase("map")){
			currMap = null;
		}
		
		//stopLayer
		if(qName.equalsIgnoreCase("layer")){
			currentLayer = null;
			inLayerLine = false;
		}
		
		// stop layerLine
		if(qName.equalsIgnoreCase("line")){
			inLayerLine = false;
		}
		
		//stop entitis
		if(qName.equalsIgnoreCase("entity")){
			currEntity = null;
		}
		
		//stop inventory
		if(qName.equals("inventory")){
			currInventory = null;
		}
		
	}
	
	public void characters(char ch[], int start, int length) throws SAXException {
		
		//read layerContent
		if(inLayerLine){
			currentLayer.readLine(ch, start, length);
		}
		
	}
	
	/**
	 * create a map with the given attributes
	 * @param attributes
	 */
	private void createMap(Attributes attributes){
		int width = Integer.parseInt(attributes.getValue("width"));
		int height = Integer.parseInt(attributes.getValue("height"));
		currMap = JMoonGame.worldGameState.worldMap = new Map("Sprites/MainSprites.png", width, height);
	}
	
	/**
	 * add a layer with the given attributes
	 * @param attributes
	 */
	private void addLayer(Attributes attributes){
		String name = attributes.getValue("name");
		if(attributes.getValue("name") != "Plants"){
			currentLayer = JMoonGame.worldGameState.worldMap.addLayer(name);
		}
	}
	
}
