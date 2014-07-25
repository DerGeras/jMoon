package geras.jmoon.nbt;

import java.io.IOException;

public abstract class NBTTag implements ITag {
	
	private String name;
	
	private ITagContainer parent;
	
	/**
	 * simple constructor
	 * @param name
	 */
	public NBTTag(String name){
		setName(name);
	}
	
	/**
	 * constructor from given inputstream
	 * @param in
	 * @throws IOException 
	 */
	public NBTTag(NBTInputStream in) throws IOException{
		int nameLength = in.readShort();
		byte[] name = new byte[nameLength];
		in.readFully(name);
		setName(new String(name, CHARSET));
	}

	@Override
	public void setName(String name) {
		if(parent != null){
			parent.removeTag(this);
		}
		this.name = name;
		if(parent != null){
			parent.addTag(this);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setParent(ITagContainer parent) {
		if(parent != null){
			parent.removeTag(this);
		}
		this.parent = parent;
	}

	@Override
	public ITagContainer getParent() {
		return parent;
	}

	@Override
	public abstract byte getID();
	
	@Override
	public void writeToStream(NBTOutputStream out) throws IOException{
		//write the id
		out.writeByte(getID());
		//write the name
		byte[] name = this.name.getBytes(CHARSET);
		out.writeShort(name.length);
		out.write(name);
	}

}
