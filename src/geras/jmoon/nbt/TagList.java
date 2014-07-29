package geras.jmoon.nbt;

import java.io.IOException;
import java.util.LinkedList;

public class TagList extends NBTTag{
	
	private LinkedList<ITag> tags;
	
	public TagList(String name, LinkedList<ITag> value){
		super(name);
		tags = value;
	}

	public TagList(NBTInputStream in) throws IOException {
		super(in);
		tags = new LinkedList<ITag>();
		//read the type of the list elements
		byte type = in.readByte();
		TagType tagType = TagType.getTagType(type);
		if(tagType == TagType.END){
			return; //no data stored
		}
		//get length
		int length = in.readInt();
		//read in tags
		for(int i = 0; i < length; i++){
			addTag(in.readTag(tagType));
		}
	}
	
	@Override
	public void writeToStream(NBTOutputStream out) throws IOException{
		super.writeToStream(out);
		if(tags.size() == 0){
			out.writeByte(TagType.END.typeID);
			return;
		}
		out.writeByte(tags.getFirst().getID());
		out.writeInt(tags.size());
		for(ITag tag : tags){
			tag.writeToStream(out);
		}
	}

	@Override
	public byte getID() {
		return TagType.LIST.typeID;
	}
	
	
	public LinkedList<ITag> getTags() {
		return tags;
	}

	
	@SuppressWarnings("unchecked")
	public <T extends ITag> LinkedList<T> getTags(Class<T> tagClass)
			throws IOException {
		for(ITag tag : tags){
			if(!tagClass.isInstance(tag)){
				throw new IOException("Tag " + tag.getName() + "should be of type "
						+ tagClass.getSimpleName() + "but is"
						+ tag.getClass().getSimpleName());
			}
		}
		return (LinkedList<T>) tags;
	}

	public void addTag(ITag tag) {
		tags.add(tag);
		
	}

	public void removeTag(ITag tag) {
		tags.remove(tag);
		
	}

	public void setList(LinkedList<ITag> list) {
		this.tags = list;
		
	}

}
