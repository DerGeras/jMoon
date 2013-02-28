package geras.jmoon.entites;

import geras.jmoon.items.FenceItem;
import geras.jmoon.items.HoeItem;
import geras.jmoon.items.ShovelItem;
import geras.jmoon.items.SickelItem;
import geras.jmoon.items.WaterBucketItem;
import geras.jmoon.world.Map;

import org.newdawn.slick.Game;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.matthiasmann.twl.model.SimpleChangableListModel;

public class Cheater extends NPCEntity {

	public Cheater(SimpleChangableListModel<String> inventoryModel,
			String name, String title, int posX, int posY) {
		super(inventoryModel, name, title, posX, posY);
		try{
			this.entityImg = new Image("Sprites/Cheater.png");
		}
		catch(SlickException e){
			e.printStackTrace();
		}
	}
	
	public void interact(PlayerEntity player, Map map, Game game){
		player.getInventory().addItem(new SickelItem());
		player.getInventory().addItem(new ShovelItem());
		player.getInventory().addItem(new WaterBucketItem());
		player.getInventory().addItem(new HoeItem());
		player.getInventory().addItem(new FenceItem(128));
	}

}
