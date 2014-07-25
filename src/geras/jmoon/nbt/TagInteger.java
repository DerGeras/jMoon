package geras.jmoon.nbt;

import java.io.IOException;

public class TagInteger extends NBTTag {

	private int value;
	
	public TagInteger(String name, int value){
		super(name);
		setValue(value);
	}
	
	public TagInteger(NBTInputStream in) throws IOException{
		super(in);
		setValue(in.readInt());
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}

	@Override
	public byte getID() {
		return TagType.INTEGER.typeID;
	}
	
	@Override
	public void writeToStream(NBTOutputStream out) throws IOException{
		super.writeToStream(out);
		//write value
		out.writeInt(value);
	}

}
