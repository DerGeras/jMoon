package geras.jmoon.nbt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class TagCompound extends NBTTag implements ITagContainer {
	
	private HashMap<String, ITag> tags = new HashMap<String, ITag>();

	public TagCompound(String name) {
		super(name);
	}
	
	public TagCompound(NBTInputStream in) throws IOException{
		super(in);
		
		TagType type = null;
		
		while(type != TagType.END){
			//read type
			byte typeID = in.readByte();
			
			//get type
			type = TagType.getTagType(typeID);
			
			//check for null
			if(type == null){
				throw new IOException("Undefined type id:" + typeID);
			}
			
			if(type != TagType.END){
				addTag(in.readTag(type));
			}
		}
	}

	@Override
	public ITag getTag(String name) {
		return tags.get(name);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends ITag> T getTag(String name, Class<T> tagClass) throws IOException {
		ITag tag = getTag(name);
		if(tag != null && !tagClass.isInstance(tag)){
			throw new IOException("Tag " + name + "should be of type "
					+ tagClass.getSimpleName() + "but is"
					+ tag.getClass().getSimpleName());
		}
		return (T)tag;
	}
	
	//TODO getters for all the tag types

	@Override
	public Set<String> getTagNames() {
		return tags.keySet();
	}

	@Override
	public void addTag(ITag tag) {
		//delete existing tag of that name if present
		if(tags.containsKey(tag.getName())){
			removeTag(tag.getName());
		}
		
		tags.put(tag.getName(), tag);
		tag.setParent(this);
	}

	@Override
	public void removeTag(String name) {
		removeTag(tags.get(name));
	}

	@Override
	public void removeTag(ITag tag) {
		if(tag != null){
			tag.setParent(null);
			tags.remove(tag.getName());
		}
	}

	@Override
	public byte getID() {
		return TagType.COMPOUND.typeID;
	}

	@Override
	public boolean hasTag(String name) {
		return tags.containsKey(name);
	}
	
	@Override
	public void writeToStream(NBTOutputStream out) throws IOException{
		super.writeToStream(out);
		for(ITag tag : tags.values()){
			tag.writeToStream(out);
		}
		//write END byte
		out.writeByte(TagType.END.typeID);
	}

}
