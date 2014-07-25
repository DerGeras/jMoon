package geras.jmoon.nbt;

import java.io.IOException;

public class TagDouble extends NBTTag {

	private double value;
	
	public TagDouble(String name, double value){
		super(name);
		setValue(value);
	}
	
	public TagDouble(NBTInputStream in) throws IOException{
		super(in);
		setValue(in.readDouble());
	}
	
	public void setValue(double value){
		this.value = value;
	}
	
	public double getValue(){
		return value;
	}

	@Override
	public byte getID() {
		return TagType.DOUBLE.typeID;
	}
	
	@Override
	public void writeToStream(NBTOutputStream out) throws IOException{
		super.writeToStream(out);
		//write value
		out.writeDouble(value);
	}

}
