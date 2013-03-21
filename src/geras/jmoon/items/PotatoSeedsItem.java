package geras.jmoon.items;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.plants.PotatoPlant;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;
import geras.jmoon.world.WorldElements;

public class PotatoSeedsItem extends UsableItem {

	public PotatoSeedsItem(int stackSize) {
		super("Potato Seeds", Settings.maxStackSize, stackSize, 1);
	}

	@Override
	public void useWorld(int x, int y, Map map, PlayerEntity player) {
		//plant seeds on dirt
		if(stackSize > 0){
			int fieldX = x / Settings.tileWidth;
			int fieldY = y / Settings.tileHeight;
			int fieldValue = map.getField("Ground", fieldX, fieldY);
			if(map.getField("Decoration", fieldX, fieldY) == -1 && map.getField("Plants", fieldX, fieldY) == -1 && (fieldValue == WorldElements.DIRT_VALUE || fieldValue == WorldElements.WETDIRT_VALUE)){
				map.addPlant(new PotatoPlant(fieldX, fieldY, WorldElements.POTATO_MIN_VALUE, map));
				stackSize--;
			}
		}
	}

}
