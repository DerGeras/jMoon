package geras.jmoon.world;

import geras.jmoon.entity.Entity;
import geras.jmoon.plants.Plant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Region {
	
	public LinkedList<Entity> entityList = new LinkedList<Entity>(); //List of entities in this region
	
	private int width;
	private int height;
	
	private HashMap<String, MapLayer> layers = new HashMap<String, MapLayer>();
	private ArrayList<String> layerPos = new ArrayList<String>();
	
	private LinkedList<Plant> plants = new LinkedList<Plant>();

}
