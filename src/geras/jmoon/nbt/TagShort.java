package geras.jmoon.nbt;

import java.io.IOException;

public class TagShort extends NBTTag {

	private short value;
	
	public TagShort(String name, short value){
		super(name);
		setValue(value);
	}
	
	public TagShort(NBTInputStream in) throws IOException{
		super(in);
		setValue(in.readShort());
	}
	
	public void setValue(short value){
		this.value = value;
	}
	
	public short getValue(){
		return value;
	}

	@Override
	public byte getID() {
		return TagType.SHORT.typeID;
	}
	
	@Override
	public void writeToStream(NBTOutputStream out) throws IOException{
		super.writeToStream(out);
		//write value
		out.writeShort(value);
	}

}
