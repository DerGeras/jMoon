package geras.jmoon.entites;

import geras.jmoon.main.JMoonGame;

import org.xml.sax.Attributes;

public class EntityFactory {

	public static Entity getEntity(Attributes attributes){
		Entity entity = null;
		
		//create the entity
		switch(attributes.getValue("case")){
		case "PlayerEntity":
			entity = JMoonGame.player = new PlayerEntity();
			break;
		case "CheaterNPC": entity = new CheaterNPC("", "", 50, 50);break;
		case "CityMerchantNPC": entity = new CityMerchantNPC("", "", 50, 50);break;
		case "CowNPC": entity = new CowNPC("", "", 50, 50);break;
		}
		
		//read the attributes
		if(entity != null){
			entity.readFromAttributes(attributes);
		}
		
		return entity;
	}
	
}
