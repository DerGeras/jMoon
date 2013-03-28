package village;

import java.util.LinkedList;

public class Village {
	
	private static LinkedList<VillagerNPC> villagers = new LinkedList<VillagerNPC>();
	
	/**
	 * add a villager
	 * @param villager
	 */
	public static void addVillager(VillagerNPC villager){
		if(villager != null){
			villagers.add(villager);
		}
	}
	
	/**
	 * remove a villager
	 * @param villager
	 */
	public static void removeVillager(VillagerNPC villager){
		if(villager != null){
			villagers.remove(villager);
		}
	}
	
	public static int getVillagerCount(){
		return villagers.size();
	}
	
	/**
	 * return the average hunger value
	 * @return
	 */
	public static float getAverageHungerValue(){
		float totalValue = 0.0f;
		for(VillagerNPC villager : villagers){
			totalValue += villager.getHunger();
		}
		return totalValue / villagers.size();
	}
	
}
