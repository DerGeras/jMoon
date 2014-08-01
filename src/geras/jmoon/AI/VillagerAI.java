package geras.jmoon.AI;

import geras.jmoon.time.Clock;
import geras.jmoon.village.VillagerNPC;

public class VillagerAI{

	private int timeSinceLastBuy = timeBetweenBuys;
	public static final int timeBetweenBuys = Clock.millisecondsPerDay / 2;
	
	/**
	 * update the villager, in this case try to eat, if you can't, go to the supermarket
	 * @param villager
	 * @param timeSinceLastUpdate
	 */
	public void update(VillagerNPC villager, int timeSinceLastUpdate) {
		//TODO
	}
	
	public int getTimeSinceLastBuy(){
		return timeSinceLastBuy;
	}
	
	public void setTimeSinceLastBuy(int value){
		this.timeSinceLastBuy = value;
	}

}
