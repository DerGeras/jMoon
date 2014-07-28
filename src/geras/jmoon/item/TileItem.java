package geras.jmoon.item;

import geras.jmoon.entity.PlayerEntity;
import geras.jmoon.tile.Tile;
import geras.jmoon.world.World;


public class TileItem extends Item {
	
	private Tile tile;

	public TileItem(int id, String name, String displayName,
			int maxStackSize, int sellingPrice) {
		super(id, name, displayName, 0, maxStackSize, sellingPrice);
	}
	
	@Override
	public void use(PlayerEntity player, World world, ItemStack stack, int posX, int posY){
		tile.placeTile(player, world, stack, posX, posY);
	}
	
	public Tile getTile(){
		return tile;
	}

}
