package geras.jmoon.items;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.Map;

public class MilkBucketItem extends UsableItem {

	public MilkBucketItem(int stackSize) {
		super("Milk Bucket", Settings.maxStackSize, stackSize, 70);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void useWorld(int x, int y, Map map, PlayerEntity player) {
		//Currently: do nothing

	}

}
