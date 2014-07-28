package geras.jmoon.tile;

import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.item.ItemStack;
import geras.jmoon.item.TileItem;
import geras.jmoon.world.World;

import org.newdawn.slick.Image;

public class Tile {

	private int id;
	private String name;
	private String displayName;
	
	private Image img;
	
	private TileItem tileItem;
	
	public Tile(String name, String displayName, int id){
		
	}
	
	public void placeTile(PlayerEntity player, World world, ItemStack stack, int posX, int posY){
		//TODO
	}
	
	public TileItem getTileItem(){
		return tileItem;
	}
	
	public Image getImg(){
		return img;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return displayName;
	}
	
}
