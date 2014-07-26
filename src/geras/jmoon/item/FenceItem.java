package geras.jmoon.item;

import geras.jmoon.entity.Entity;
import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.World;
import geras.jmoon.world.WorldElements;

public class FenceItem extends UsableItem {

	public FenceItem(int stackSize){
		super("Fence", Settings.maxStackSize, stackSize, 5);
	}
	
	@Override
	public void useWorld(int x, int y, World map, PlayerEntity player) {
		if(stackSize > 0){
			int fieldX = x / Settings.tileWidth;
			int fieldY = y / Settings.tileHeight;
			int fieldValue = map.getField("Ground", fieldX, fieldY);
			
			//check for obstacles on the map
			if((fieldValue == WorldElements.GRASS_VALUE || fieldValue == WorldElements.GRAVEL_VALUE || fieldValue == WorldElements.DIRT_VALUE)
				&& (map.getField("Decoration", fieldX, fieldY) == -1) && (map.getField("Plants", fieldX, fieldY) == -1)){
				if(checkNearbyEntites(fieldX, fieldY, map)){
					map.setField("Decoration", fieldX, fieldY, WorldElements.FENCE_VALUE);
					--stackSize;
				}				
			}
		}
	}
	
	private boolean checkNearbyEntites(int fieldX, int fieldY, World map){
		int fieldMidX = fieldX * Settings.tileWidth + Settings.tileWidth / 2;
		int fieldMidY = fieldY * Settings.tileHeight + Settings.tileHeight / 2;
		for(Entity entity: map.entityList){
			if(Math.abs(entity.getPosX() - fieldMidX) < Settings.tileWidth / 2 + entity.getWidth() / 2 + 2
					&& Math.abs(entity.getPosY() - fieldMidY) < Settings.tileHeight / 2 + entity.getHeight() / 2 + 2){
				return false;
			}
		}
		return true;
	}

}
