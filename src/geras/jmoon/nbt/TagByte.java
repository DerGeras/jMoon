package geras.jmoon.nbt;

import java.io.IOException;

public class TagByte extends NBTTag {
	
	private byte value;
	
	public TagByte(String name, byte value){
		super(name);
		setValue(value);
	}
	
	public TagByte(NBTInputStream in) throws IOException{
		super(in);
		setValue(in.readByte());
	}
	
	public void setValue(byte value){
		this.value = value;
	}
	
	public byte getValue(){
		return value;
	}

	@Override
	public byte getID() {
		return TagType.BYTE.typeID;
	}
	
	@Override
	public void writeToStream(NBTOutputStream out) throws IOException{
		super.writeToStream(out);
		//write value
		out.write(value);
	}

}
