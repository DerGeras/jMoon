package geras.jmoon.nbt;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class NBTOutputStream extends DataOutputStream {
	
	public NBTOutputStream(OutputStream out){
		super(out);
	}
	
	/**
	 * write a tag to the stream
	 * @param tag
	 * @throws IOException
	 */
	public void writeTag(ITag tag) throws IOException{
		tag.writeToStream(this);
	}

}
