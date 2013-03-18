package geras.jmoon.items;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.plants.WeedPlant;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;
import geras.jmoon.world.WorldElements;

public class SeedItem extends UsableItem {

	public SeedItem(int stackSize){
		super("Seeds", Settings.maxStackSize, stackSize, 0, 1);
	}
	
	public SeedItem(){
		super("Seeds", Settings.maxStackSize, 1, 1, 1);
	}
	
	@Override
	public void useWorld(int x, int y, Map map, PlayerEntity player) {
		//plant seeds on dirt
		if(stackSize > 0){
			int fieldX = x / Settings.tileWidth;
			int fieldY = y / Settings.tileHeight;
			int fieldValue = map.getField("Ground", fieldX, fieldY);
			if(map.getField("Decoration", fieldX, fieldY) == -1 && map.getField("Plants", fieldX, fieldY) == -1 && (fieldValue == WorldElements.DIRT_VALUE || fieldValue == WorldElements.WETDIRT_VALUE)){
				map.addPlant(new WeedPlant(fieldX, fieldY, WorldElements.WEED_MIN_VALUE, map));
				stackSize--;
			}
		}
	}

}
