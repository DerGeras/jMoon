package geras.jmoon.nbt;

import java.io.IOException;

public class TagLong extends NBTTag {

	private long value;
	
	public TagLong(String name, int value){
		super(name);
		setValue(value);
	}
	
	public TagLong(NBTInputStream in) throws IOException{
		super(in);
		setValue(in.readLong());
	}
	
	public void setValue(long value){
		this.value = value;
	}
	
	public long getValue(){
		return value;
	}

	@Override
	public byte getID() {
		return TagType.LONG.typeID;
	}
	
	@Override
	public void writeToStream(NBTOutputStream out) throws IOException{
		super.writeToStream(out);
		//write value
		out.writeLong(value);
	}

}
