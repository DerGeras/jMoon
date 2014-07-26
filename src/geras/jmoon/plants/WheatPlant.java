package geras.jmoon.plants;

import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.world.World;
import geras.jmoon.world.WorldElements;

/**
 * 
 * @author Geras
 *
 */
public class WheatPlant extends Plant {

	public WheatPlant(int x, int y, int tileValue, World map) {
		super(x, y, tileValue, map);
	}

	@Override
	public void update(int timeSinceLastFrame) {
		//check if the ground is still alright
		if(map.getField("Ground", posX, posY) != WorldElements.DIRT_VALUE && map.getField("Ground", posX, posY) != WorldElements.WETDIRT_VALUE){
			map.removePlant(this);
			return;
		}
		//only do something the plant isn't fully grown
		if(tileValue < WorldElements.WHEAT_MAX_VALUE){
			timeSinceLastGrowth += timeSinceLastFrame;
			//time to grow?
			if(timeSinceLastGrowth > growthInterval){
				//time to grow!
				timeSinceLastGrowth -= growthInterval;
				grow();
			}
		}

	}

	@Override
	public boolean grow() {
		boolean res = tileValue < WorldElements.WHEAT_MAX_VALUE && map.getField("Ground", posX, posY) == WorldElements.WETDIRT_VALUE;
		if(res){
			map.setField("Plants", posX, posY, ++tileValue);
		}
		return res;
	}

	@Override
	public void harvest(PlayerEntity player, World map) {
		int growth = tileValue - WorldElements.WHEAT_MIN_VALUE;
		if(growth >= 0){
			player.getInventory().addItem("Seeds", 1);
		}
		if(growth == 5){
			player.getInventory().addItem("Seeds", 1);
			player.getInventory().addItem("Wheat", 2);
		}
		map.removePlant(this);
	}

}
