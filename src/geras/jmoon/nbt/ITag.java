package geras.jmoon.nbt;


import java.io.IOException;
import java.nio.charset.Charset;

public interface ITag {
	
	public static final Charset CHARSET = Charset.forName ("UTF-8");
	
	/**
	 * 
	 * @return
	 */
	public void setName(String name);
	
	/**
	 * 
	 * @return the name of the tag
	 */
	public String getName();
	
	/**
	 * set the parent container
	 */
	public void setParent(ITagContainer parent);
	
	/**
	 * 
	 * @return the parent container
	 */
	public ITagContainer getParent();
	
	
	/**
	 * 
	 * @return the (static) id of this tag
	 */
	public byte getID();
	
	
	/**
	 * write this tag to a stream
	 * @param out
	 * @throws IOException 
	 */
	public void writeToStream(NBTOutputStream out) throws IOException;
	

}
