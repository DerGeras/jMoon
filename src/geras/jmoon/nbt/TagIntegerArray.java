package geras.jmoon.nbt;

import java.io.IOException;

public class TagIntegerArray extends NBTTag {

	private int[] value;
	
	public TagIntegerArray(String name, int[] value){
		super(name);
		setValue(value);
	}
	
	public TagIntegerArray(NBTInputStream in) throws IOException{
		super(in);
		int length = in.readInt();
		int[] value = new int[length];
		//read values
		for(int i = 0; i < length; i++){
			value[i] = in.readInt();
		}
			
		setValue(value);
	}
	
	public void setValue(int[] value){
		this.value = value;
	}
	
	public int[] getValue(){
		return value;
	}

	@Override
	public byte getID() {
		return TagType.INTEGER_ARRAY.typeID;
	}
	
	@Override
	public void writeToStream(NBTOutputStream out) throws IOException{
		super.writeToStream(out);
		//write length
		out.writeInt(value.length);
		//write value
		for(int v : this.value){
			out.writeInt(v);
		}
	}

}
