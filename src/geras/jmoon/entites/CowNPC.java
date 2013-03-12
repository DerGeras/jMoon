package geras.jmoon.entites;

import geras.jmoon.items.UsableItem;
import geras.jmoon.world.Map;

import org.newdawn.slick.Game;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.matthiasmann.twl.model.SimpleChangableListModel;

public class CowNPC extends NPCEntity {

	public CowNPC(SimpleChangableListModel<String> inventoryModel,
			String name, String title, int posX, int posY) {
		super(inventoryModel, name, title, posX, posY);
		width = 32;
		height = 32;
		try{
			this.entityImg = new Image("Sprites/Entities/Cow.png");
		}
		catch(SlickException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void interact(PlayerEntity player, Map map, Game game){
		//fill the bucket in case the player carries a bucket
		UsableItem handledItem = player.getCurrentTool();
		if(handledItem.getName() == "Bucket"){
			if(handledItem.getStackSize() > 0){
				handledItem.setStackSize(handledItem.getStackSize() - 1);
				player.getInventory().addItem("Milk Bucket", 1);
				player.getInventory().updateModel();
			}
		}
	}

}
