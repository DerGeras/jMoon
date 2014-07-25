package geras.jmoon.nbt;

import java.util.HashMap;

public enum TagType {
	
	END(0, null),
	COMPOUND(1, TagCompound.class),
	BYTE(1, TagByte.class),
	BYTE_ARRAY(2, TagByteArray.class),
	INTEGER(3, TagInteger.class),
	INTEGER_ARRAY(4, TagIntegerArray.class),
	FLOAT(5, TagFloat.class),
	LONG(6,TagLong.class),
	SHORT(7, TagShort.class),
	STRING(8, TagString.class),
	DOUBLE(9, TagDouble.class);
	
	
	
	/**
	 * a map of all types
	 */
	private static final HashMap<Byte, TagType> types = new HashMap<Byte, TagType>();
	
	static{
		//add all tag types to the types map
		for(TagType t : values()){
			types.put(t.typeID, t);
		}
	}
	
	
	public final Class<? extends ITag> tagType;
	
	
	public final byte typeID;
	
	/**
	 * 
	 * @param typeID
	 * @param tagType
	 */
	private TagType(int typeID, Class<? extends ITag> tagType){
		this.typeID = (byte) typeID;
		this.tagType = tagType;
	}
	
	/**
	 * return the TagType for a given ID
	 * @param typeID
	 * @return
	 */
	public static TagType getTagType(byte typeID){
		return types.get(typeID);
	}

}
