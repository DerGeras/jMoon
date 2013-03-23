package geras.jmoon.time;

import geras.jmoon.main.JMoonGame;

import java.io.BufferedWriter;
import java.io.IOException;

import org.xml.sax.Attributes;

/**
 * A clock, what else?
 * @author Geras
 *
 */
public class Clock {

	public static final int saveInterval = 60 * 1000; //interval to autosave
	public static final int millisecondsPerDay = 10 * 60 * 1000; // duration of a day
	
	private static long milliSecondsSinceStart = 0;
	private static int timeSinceLastSave = 0;
	
	/**
	 * update the clock
	 * @param timeSinceLastFrame - time since the last update
	 */
	public static void update(int timeSinceLastFrame){
		setTimeSinceStart(getTimeSinceStart() + timeSinceLastFrame);
		timeSinceLastSave += timeSinceLastFrame;
		if(timeSinceLastSave >= saveInterval){
			JMoonGame.saveGame();
			timeSinceLastSave -= saveInterval;
		}
	}

	/**
	 * save to XML
	 * @param out
	 */
	public static void saveToXML(BufferedWriter out){
		try {
			out.append("<clock timeSinceStart=\"" + milliSecondsSinceStart + "\">");
			out.append("</clock>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * read from xml attributes
	 * @param attributes
	 */
	public static void readFromAttributes(Attributes attributes){
		String timeSinceStartS = attributes.getValue("timeSinceStart");
		
		if(timeSinceStartS != null){
			milliSecondsSinceStart = Long.parseLong(timeSinceStartS);
		}
	}
	
	/**
	 * get the time since start (in ms)
	 * @return
	 */
	public static long getTimeSinceStart() {
		return milliSecondsSinceStart;
	}

	/**
	 * Note: Set to 0 if value < 0
	 * @param value
	 */
	public static void setTimeSinceStart(long value) {
		Clock.milliSecondsSinceStart = Math.max(0, value);
	}
	
	/**
	 * 
	 * @return the current day (since game start)
	 */
	public static long getDay(){
		return milliSecondsSinceStart / (long)millisecondsPerDay;
	}
	
	/**
	 * 
	 * 
	 * @return the current hour (of this day)
	 */
	public static long getHour(){
		return (milliSecondsSinceStart / (long)(millisecondsPerDay / 24)) % 24l;
	}
	
	/**
	 * 
	 * @return the current minute (of this hour)
	 */
	public static long getMinute(){
		return (milliSecondsSinceStart / (long)(millisecondsPerDay / 24 / 60)) % 60l;
	}
	
}
