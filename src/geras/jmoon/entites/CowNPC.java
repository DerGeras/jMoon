package geras.jmoon.entites;

import java.io.BufferedWriter;
import java.io.IOException;

import geras.jmoon.AI.CowAI;
import geras.jmoon.GameStates.WorldGameState;
import geras.jmoon.items.UsableItem;
import geras.jmoon.world.Map;

import org.newdawn.slick.Game;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.xml.sax.Attributes;

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

	@Override
	public void saveToXML(BufferedWriter out) {
		try {
			out.append("<entity case=\"CowNPC\" name=\"" + name + "\" title=\"" + title + "\" posX=\"" + posX + "\" posY=\"" + posY);
			out.append("\" hunger=\"" + hunger + "\" thirst=\"" + thirst + "\">");
			out.flush();
			out.newLine();
			
			//output the inventory
			inventory.saveToXML(out);
			
			out.newLine();
			out.write("</entity>");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void readFromAttributes(Attributes attributes) {
		name = attributes.getValue("name");
		title = attributes.getValue("title");
		
		String posXS = attributes.getValue("posX");
		String posYS = attributes.getValue("posY");
		String hungerS = attributes.getValue("hunger");
		String thirstS = attributes.getValue("thirst");
		
		if(posXS != null){
			posX = Float.parseFloat(posXS);
		}
		if(posYS != null){
			posY = Float.parseFloat(posYS);
		}
		if(hungerS != null){
			hunger = Float.parseFloat(hungerS);
		}
		if(thirstS != null){
			thirst = Float.parseFloat(thirstS);
		}
	}

}
