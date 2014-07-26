package geras.jmoon.item;

import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.World;
import geras.jmoon.world.WorldElements;

public class AxeItem extends ToolItem {

	public AxeItem(int durability) {
		super("Axe", Settings.maxDurability, durability, 150);
	}

	@Override
	public void useWorld(int x, int y, World map, PlayerEntity player) {
		if(durability > 0 && stackSize > 0){
			int fieldX = x / Settings.tileWidth;
			int fieldY = y / Settings.tileHeight;
			
			if(map.getField("Decoration", fieldX, fieldY) == WorldElements.FENCE_VALUE){
				map.setField("Decoration", fieldX, fieldY, -1);
				player.getInventory().addItem("Fence", 1);
				if(--durability <= 0){
					--stackSize;
					durability = maxDurability; //reset durability for the next item
				}
			}
		}
	}

}
