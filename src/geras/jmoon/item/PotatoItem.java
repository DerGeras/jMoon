package geras.jmoon.item;

import geras.jmoon.entity.LivingEntity;
import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.plants.PotatoPlant;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.World;
import geras.jmoon.world.WorldElements;

public class PotatoItem extends UsableItem {

	public PotatoItem(int stackSize) {
		super("Potato", Settings.maxStackSize, stackSize, 2);
	}
	
	@Override
	public void useWorld(int x, int y, World map, PlayerEntity player) {
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
	
	@Override
	public void eat(LivingEntity entity){
		if(stackSize > 0 && entity.getHunger() > 2.0f){
			entity.setHunger(entity.getHunger() - 2.0f);
			stackSize--;
		}
	}

}
