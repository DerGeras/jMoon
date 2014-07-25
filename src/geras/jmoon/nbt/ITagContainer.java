package geras.jmoon.nbt;

import java.io.IOException;
import java.util.Set;

public interface ITagContainer {
	
	/**
	 * 
	 * @param name - name of the tag to be retrieved (might be null)
	 * @return
	 */
	public ITag getTag(String name);
	
	/**
	 * Returns a tag with the given name, checking for the given type
	 * @param name
	 * @param tagClass
	 * @return
	 * @throws IOException 
	 */
	public <T extends ITag> T getTag(String name, Class<T> tagClass) throws IOException;
	
	
	/**
	 * 
	 * @return a set of all the tag names
	 */
	public Set<String> getTagNames();
	
	
	/**
	 * adds a tag to the container
	 * @param tag - the tag to be added
	 */
	public void addTag(ITag tag);

	
	/**
	 * remove the tag with the given name from the container
	 * @param name
	 */
	public void removeTag(String name);
	
	/**
	 * remove the given tag
	 * @param tag
	 */
	public void removeTag(ITag tag);
	
	/**
	 * check whether a tag with the given name exists
	 * @param name
	 * @return
	 */
	public boolean hasTag(String name);
	
}
