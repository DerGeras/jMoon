package geras.jmoon.savegames;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class GameLoader {
	
	public static void loadGameFromFile(File file){
		if(file != null && file.exists()){
			try {
				//create a parser
				SAXParserFactory saxFactory = SAXParserFactory.newInstance();
				SAXParser parser = saxFactory.newSAXParser();
				
				//launch parser
				parser.parse(file, new SaveGameHandler());
				
				
			//Sir Catch-a-lot
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
