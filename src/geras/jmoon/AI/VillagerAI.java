package geras.jmoon.AI;

import village.VillagerNPC;
import geras.jmoon.GameStates.WorldGameState;
import geras.jmoon.items.Item;
import geras.jmoon.settings.Settings;
import geras.jmoon.time.Clock;

public class VillagerAI{

	private int timeSinceLastBuy = timeBetweenBuys;
	public static final int timeBetweenBuys = Clock.millisecondsPerDay / 2;
	
	/**
	 * update the villager, in this case try to eat, if you can't, go to the supermarket
	 * @param villager
	 * @param timeSinceLastUpdate
	 */
	public void update(VillagerNPC villager, int timeSinceLastUpdate) {
		timeSinceLastBuy += timeSinceLastUpdate;
		//try to eat
		Item potato = villager.getInventory().getItem("Potato");
		Item bread = villager.getInventory().getItem("Bread");
		if(villager.getHunger() > 3.0f){
			if(potato != null){
				potato.eat(villager);
			}
			else{
				if(bread != null){
					bread.eat(villager);
				}
			} 
		}
		
		//go to the stall
		if(villager.getHunger() > 6.0f && timeSinceLastBuy > timeBetweenBuys && (villager.getPosX() == VillagerNPC.OUT_OF_FIELD_VALUE_X || villager.getPosY() == VillagerNPC.OUT_OF_FIELD_VAlUE_Y)){
			villager.setPosition(42, Settings.mapHeight / 2 * Settings.tileHeight);
		}
		
		//move towards the stall if not outside the village
		if(villager.getPosX() != VillagerNPC.OUT_OF_FIELD_VALUE_X && villager.getPosY() != VillagerNPC.OUT_OF_FIELD_VAlUE_Y){
			float delta = 2.0f; // delta to avoid strange behavior
			//near Stall?
			if(Math.abs(villager.getPosX() - WorldGameState.stallX) < delta && Math.abs(villager.getPosY() - (WorldGameState.stallY + 64)) < delta){
				//try to buy bread
				if(bread == null || bread.getStackSize() < 3){
					Item stallBread = WorldGameState.stall.getInventory().getItem("Bread");
					if(stallBread != null){
						WorldGameState.stall.buyFrom(villager, stallBread, 3);
					}
				}
				//try to buy potatoes
				if(potato == null || potato.getStackSize() < 3){
					Item stallPotato = WorldGameState.stall.getInventory().getItem("Potato");
					if(stallPotato != null){
						WorldGameState.stall.buyFrom(villager, stallPotato, 3);
					}
				}
				timeSinceLastBuy = 0;
			}
			else{ // move towards stall
				if(timeSinceLastBuy > timeBetweenBuys){
					villager.setNextX(WorldGameState.stallX - (int)villager.getPosX());
					villager.setNextY(WorldGameState.stallY + 64 - (int)villager.getPosY());
				}
				else{
					villager.setPosition(VillagerNPC.OUT_OF_FIELD_VALUE_X, VillagerNPC.OUT_OF_FIELD_VAlUE_Y);
				}
			}
		}
	}
	
	public int getTimeSinceLastBuy(){
		return timeSinceLastBuy;
	}
	
	public void setTimeSinceLastBuy(int value){
		this.timeSinceLastBuy = value;
	}

}
