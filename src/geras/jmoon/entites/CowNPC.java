package geras.jmoon.entites;

import geras.jmoon.AI.CowAI;
import geras.jmoon.GameStates.WorldGameState;
import geras.jmoon.items.UsableItem;
import geras.jmoon.world.Map;

import org.newdawn.slick.Game;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class CowNPC extends NPCEntity {
	
	private CowAI brain = new CowAI(); //the brain of the cow

	public CowNPC(String name, String title, int posX, int posY) {
		super(name, title, posX, posY);
		width = 32;
		height = 32;
		move_speed = 0.05f;
		try{
			this.entityImg = new Image("Sprites/Entities/Cow.png");
		}
		catch(SlickException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void interact(PlayerEntity player, Map map, Game game, WorldGameState state){
		//fill the bucket in case the player carries a bucket
		UsableItem handledItem = player.getCurrentTool();
		if(handledItem.getName() == "Bucket"){
			if(handledItem.getStackSize() > 0){
				handledItem.setStackSize(handledItem.getStackSize() - 1);
				player.getInventory().addItem("Milk Bucket", 1);
			}
		}
	}

	@Override
	public void update(int timesincelastframe, Map map) {
		brain.update(this, timesincelastframe);
		move(timesincelastframe, map);		
	}

}
