package geras.jmoon.nbt;

import java.io.IOException;

public class TagByteArray extends NBTTag {

	private byte[] value;
	
	public TagByteArray(String name, byte[] value){
		super(name);
		setValue(value);
	}
	
	public TagByteArray(NBTInputStream in) throws IOException{
		super(in);
		int length = in.readInt();
		byte[] value = new byte[length];
		in.readFully(value);
		setValue(value);
	}
	
	public void setValue(byte[] value){
		this.value = value;
	}
	
	public byte[] getValue(){
		return value;
	}

	@Override
	public byte getID() {
		return TagType.BYTE_ARRAY.typeID;
	}
	
	@Override
	public void writeToStream(NBTOutputStream out) throws IOException{
		super.writeToStream(out);
		//write length
		out.writeInt(value.length);
		//write value
		out.write(value);
	}

}
