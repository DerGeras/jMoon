package geras.jmoon.nbt;

import java.io.IOException;

public class TagFloat extends NBTTag {

	private float value;
	
	public TagFloat(String name, float value){
		super(name);
		setValue(value);
	}
	
	public TagFloat(NBTInputStream in) throws IOException{
		super(in);
		setValue(in.readFloat());
	}
	
	public void setValue(float value){
		this.value = value;
	}
	
	public float getValue(){
		return value;
	}

	@Override
	public byte getID() {
		return TagType.FLOAT.typeID;
	}
	
	@Override
	public void writeToStream(NBTOutputStream out) throws IOException{
		super.writeToStream(out);
		//write value
		out.writeFloat(value);
	}

}
