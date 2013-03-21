package geras.jmoon.time;

import java.io.BufferedWriter;
import java.io.IOException;

import org.xml.sax.Attributes;

/**
 * A clock, what else?
 * @author Geras
 *
 */
public class Clock {

	private static long timeSinceStart = 0;
	
	/**
	 * update the clock
	 * @param timeSinceLastFrame - time since the last update
	 */
	public static void update(int timeSinceLastFrame){
		setTimeSinceStart(getTimeSinceStart() + timeSinceLastFrame);
	}

	/**
	 * save to XML
	 * @param out
	 */
	public static void saveToXML(BufferedWriter out){
		try {
			out.append("<clock timeSinceStart=\"" + timeSinceStart + "\">");
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
			timeSinceStart = Long.parseLong(timeSinceStartS);
		}
	}
	
	public static long getTimeSinceStart() {
		return timeSinceStart;
	}

	/**
	 * Note: Set to note if value < 0
	 * @param value
	 */
	public static void setTimeSinceStart(long value) {
		Clock.timeSinceStart = Math.max(0, value);
	}
	
}
