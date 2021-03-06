package geras.jmoon.items;

import java.io.BufferedWriter;
import java.io.IOException;

import org.xml.sax.Attributes;

import geras.jmoon.entites.PlayerEntity;
import geras.jmoon.world.Map;

public abstract class ToolItem extends UsableItem {
	
	public ToolItem(String name, int maxDurability, int durability, int sellingPrice) {
		super(name, 1, 1, sellingPrice);
		this.maxDurability = maxDurability;
		this.durability = durability;
	}
	
	@Override
	public int getSellingPrice(){
		return  (int)((float)durability / (float)maxDurability * (float)sellingPrice);
	}

	@Override
	public abstract void useWorld(int x, int y, Map map, PlayerEntity player);
	
	@Override
	public String toString(){
		return name + " D:" + durability;
	}
	
	@Override
	public void saveToXML(BufferedWriter out){
		try {
			out.append("<tool name=\"" + name + "\" maxDurability=\"" + maxDurability + "\" durability=\"" + durability + "\">");
			out.append("</tool>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void readFromAttributes(Attributes attributes) {
		String maxDurabilityS = attributes.getValue("maxDurability");
		String durabilityS = attributes.getValue("durability");
		
		if(maxDurabilityS != null){
			maxDurability = Integer.parseInt(maxDurabilityS);
		}
		if(durabilityS != null){
			durability = Integer.parseInt(durabilityS);
		}
	}

}
