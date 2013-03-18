package geras.jmoon.items;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;
import geras.jmoon.world.WorldElements;

public class WaterBucketItem extends UsableItem {

	public WaterBucketItem(int stackSize){
		super("Water Bucket", Settings.maxStackSize, stackSize, 100, 1);
	}
	
	public WaterBucketItem(){
		super("Water Bucket", Settings.maxStackSize, 1, 100, 51);
	}
	
	@Override
	public void useWorld(int x, int y, Map map, PlayerEntity player) {
		//fill a hole with water
		if(stackSize > 0){
			int fieldX = x / Settings.tileWidth;
			int fieldY = y / Settings.tileHeight;
	
			if(map.getField("Ground", fieldX, fieldY) == WorldElements.HOLE_VALUE){
				map.setField("Ground", fieldX, fieldY, WorldElements.WATER_VALUE);
				player.getInventory().addItem("Bucket",1);
				--stackSize;
			}
		}

	}

}
