package geras.jmoon.item;

import geras.jmoon.entity.LivingEntity;
import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.settings.Settings;
import geras.jmoon.world.World;

public class BreadItem extends UsableItem {

	public BreadItem(int stackSize) {
		super("Bread", Settings.maxStackSize, stackSize, 3);
	}

	@Override
	public void useWorld(int x, int y, World map, PlayerEntity player) {
		// do nothing
	}
	
	@Override
	public void eat(LivingEntity entity){
		if(stackSize > 0 && entity.getHunger() > 3.0f){
			entity.setHunger(entity.getHunger() - 3.0f);
			stackSize--;
		}
	}

}
