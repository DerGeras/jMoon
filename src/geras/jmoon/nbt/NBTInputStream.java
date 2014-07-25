package geras.jmoon.nbt;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;

public class NBTInputStream extends DataInputStream{

	public NBTInputStream(InputStream in) {
		super(in);
	}
	
	/**
	 * read the next tag
	 * @return
	 * @throws IOException 
	 */
	public ITag readTag() throws IOException{
		byte typeID = readByte();
		TagType tagType = TagType.getTagType(typeID);
		
		//check tagType for null
		if(tagType == null){
			throw new IOException("Invalid TagType id: " + typeID);
		}
		
		if(tagType == TagType.END){
			return null;
		}
		
		return this.readTag(tagType);
	}
	
	/**
	 * read the next tag and expect type
	 * @return
	 * @throws IOException 
	 */
	public ITag readTag(TagType type) throws IOException{
		
		Constructor<? extends ITag> constructor;
		//get constructor
		try{
			constructor = type.tagType.getConstructor(NBTInputStream.class);
		} catch (NoSuchMethodException e){
			throw new IOException("Could not find constructor for " + type.tagType.getName());
		}
		//create and return a new instance
		try{
			return constructor.newInstance(this);
		} catch(Exception e){
			throw new IOException("Could not instantiate Tag of type " +  type.tagType.getName());
		}
	}

}
