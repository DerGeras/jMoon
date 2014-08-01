package geras.jmoon.village;

import geras.jmoon.AI.VillagerAI;
import geras.jmoon.GameState.WorldGameState;
import geras.jmoon.entity.Merchant;
import geras.jmoon.entity.NPCEntity;
import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.item.Inventory;
import geras.jmoon.item.Item;
import geras.jmoon.reference.Settings;
import geras.jmoon.time.Clock;
import geras.jmoon.world.Region;

import org.newdawn.slick.Game;
import org.newdawn.slick.Graphics;

public class VillagerNPC extends NPCEntity implements Merchant{
	
	private static float hungerRatio = 8.0f / Clock.millisecondsPerDay;
	
	public static int OUT_OF_FIELD_VALUE_X = -50;
	public static int OUT_OF_FIELD_VAlUE_Y = -50;
	
	private VillagerAI brain = new VillagerAI();
	
	public VillagerNPC(){
		this(0, null); //TODO!!!!
	}

	public VillagerNPC(int id, Region region){
		super(id, region, "", "Villager", OUT_OF_FIELD_VALUE_X, OUT_OF_FIELD_VAlUE_Y);
		//TODO
	}
	
	public VillagerNPC(int id, Region region, String name, String title, int posX, int posY) {
		super(id, region, name, title, posX, posY);
		//TODO
	}
	
	@Override
	public void draw(Graphics g, int mapTopX, int mapTopY){
		if(posX != OUT_OF_FIELD_VALUE_X && posY != OUT_OF_FIELD_VAlUE_Y){
			super.draw(g, mapTopX, mapTopY);
		}
	}

	@Override
	public void update(int timesincelastframe) {
		hunger += (float)timesincelastframe * hungerRatio;
		
		//check if the hunger is too big
		if(hunger > Settings.maxHunger){
			kill();
			return;
		}
		
		brain.update(this, timesincelastframe);
		move(timesincelastframe);
	}
	
	@Override
	public void move(int timesincelastframe){
		posX += timesincelastframe * move_speed * nextX;
		posY += timesincelastframe * move_speed * nextY;
	}
	
	@Override
	public void kill(){
		region.removeEntity(this);
		Village.removeVillager(this);
	}
	

	@Override
	public void sellTo(Merchant merchant, Item item, int amount) {
		// can't sell
		
	}

	@Override
	public void buyFrom(Merchant merchant, Item item, int amount) {
		// can't buy
		
	}

	@Override
	public float getSellSale() {
		return 0;
	}

	@Override
	public float getBuySale() {
		return 0;
	}

	@Override
	public boolean canBuyFrom() {
		return false;
	}

	@Override
	public boolean canSellTo() {
		return false;
	}

	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void interact(PlayerEntity player, Game game, WorldGameState state) {
		// TODO Auto-generated method stub
		
	}

}
