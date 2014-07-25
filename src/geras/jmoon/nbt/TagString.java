package geras.jmoon.nbt;

import java.io.IOException;

	public class TagString extends NBTTag {

	private String value;
	
	public TagString(String name, String value){
		super(name);
		setValue(value);
	}
	
	public TagString(NBTInputStream in) throws IOException{
		super(in);
		int length = in.readShort();
		byte[] s = new byte[length];
		in.readFully(s);
		setValue(new String(s, CHARSET));
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}

	@Override
	public byte getID() {
		return TagType.STRING.typeID;
	}
	
	@Override
	public void writeToStream(NBTOutputStream out) throws IOException{
		super.writeToStream(out);
		//write value
		byte[] v = value.getBytes(CHARSET);
		out.writeShort(v.length);
		out.write(v);
	}

}
