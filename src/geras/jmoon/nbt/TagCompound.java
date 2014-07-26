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
	
	/*
	 * Getters for different typed tags
	 */
	
	public TagCompound getCompound(String name) throws IOException{
		return getTag(name, TagCompound.class);
	}
	
	public byte getByte(String name) throws IOException{
		TagByte b = getTag(name, TagByte.class);
		return b == null ? 0 : b.getValue();
	}
	
	public byte[] getByteArray(String name) throws IOException{
		TagByteArray b = getTag(name, TagByteArray.class);
		byte[] ba = new byte[0];
		return b == null ? ba : b.getValue();
	}
	
	public double getDouble(String name) throws IOException{
		TagDouble b = getTag(name, TagDouble.class);
		return b == null ? 0.0d : b.getValue();
	}
	
	public float getFloat(String name) throws IOException{
		TagFloat b = getTag(name, TagFloat.class);
		return b == null ? 0.0f : b.getValue();
	}
	
	public int getInt(String name) throws IOException{
		TagInteger b = getTag(name, TagInteger.class);
		return b == null ? 0 : b.getValue();
	}
	
	public int[] getIntegerArray(String name) throws IOException{
		TagIntegerArray b = getTag(name, TagIntegerArray.class);
		int[] ba = new int[0];
		return b == null ? ba : b.getValue();
	}
	
	public long getLong(String name) throws IOException{
		TagLong b = getTag(name, TagLong.class);
		return b == null ? 0l : b.getValue();
	}
	
	public short getShort(String name) throws IOException{
		TagShort b = getTag(name, TagShort.class);
		return b == null ? 0 : b.getValue();
	}
	
	public String getString(String name) throws IOException{
		TagString b = getTag(name, TagString.class);
		return b == null ? "" : b.getValue();
	}
	
	/*
	 * getOrCreate for all tag types
	 */
	
	public TagCompound getOrCreateCompound(String name, TagCompound value) throws IOException{
		TagCompound b = getTag(name, TagCompound.class);
		if(b == null){
			b = value;
			addTag(b);
		}
		return b;
	}
	
	public byte getOrCreateByte(String name, byte value) throws IOException{
		TagByte b = getTag(name, TagByte.class);
		if(b == null){
			b = new TagByte(name, value);
			addTag(b);
		}
		return b.getValue();
	}
	
	public byte[] getOrCreateByteArray(String name, byte[] value) throws IOException{
		TagByteArray b = getTag(name, TagByteArray.class);
		if(b == null){
			b = new TagByteArray(name, value);
			addTag(b);
		}
		return b.getValue();
	}
	
	public double getOrCreateDouble(String name, double value) throws IOException{
		TagDouble b = getTag(name, TagDouble.class);
		if(b == null){
			b = new TagDouble(name, value);
			addTag(b);
		}
		return b.getValue();
	}
	
	public float getOrCreateFloat(String name, float value) throws IOException{
		TagFloat b = getTag(name, TagFloat.class);
		if(b == null){
			b = new TagFloat(name, value);
			addTag(b);
		}
		return b.getValue();
	}
	
	public int getOrCreateInt(String name, int value) throws IOException{
		TagInteger b = getTag(name, TagInteger.class);
		if(b == null){
			b = new TagInteger(name, value);
			addTag(b);
		}
		return b.getValue();
	}
	
	public int[] getOrCreateIntegerArray(String name, int[] value) throws IOException{
		TagIntegerArray b = getTag(name, TagIntegerArray.class);
		if(b == null){
			b = new TagIntegerArray(name, value);
			addTag(b);
		}
		return b.getValue();
	}
	
	public long getOrCreateLong(String name, long value) throws IOException{
		TagLong b = getTag(name, TagLong.class);
		if(b == null){
			b = new TagLong(name, value);
			addTag(b);
		}
		return b.getValue();
	}
	
	public short getOrCreateShort(String name, short value) throws IOException{
		TagShort b = getTag(name, TagShort.class);
		if(b == null){
			b = new TagShort(name, value);
			addTag(b);
		}
		return b.getValue();
	}
	
	public String getOrCreateString(String name, String value) throws IOException{
		TagString b = getTag(name, TagString.class);
		if(b == null){
			b = new TagString(name, value);
			addTag(b);
		}
		return b.getValue();
	}

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
