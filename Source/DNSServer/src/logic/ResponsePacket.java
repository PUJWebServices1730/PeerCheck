package logic;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ResponsePacket {
	private List<Byte> data;
	
	public ResponsePacket(int transactionId, String data, int type, int typeClass, InetAddress address) {
		this.data = new ArrayList<>();
		this.appendShort(transactionId);
		this.appendShort(0x8180);
		this.appendShort(1);
		this.appendShort(1);
		this.appendShort(0);
		this.appendShort(0);
		
		StringTokenizer tkn = new StringTokenizer(data, ".");
		while (tkn.hasMoreTokens()) {
			String current_label = tkn.nextToken();
			this.appendByte(current_label.length());
			this.appendBytes(current_label.getBytes());
		}
		this.appendByte(0);
		
		this.appendShort(type);
		this.appendShort(typeClass);
		this.appendShort(0xc00c);
		this.appendShort(type);
		this.appendShort(0x0001);
		this.appendInt(41);
		this.appendShort(4);
		
		this.appendBytes(address.getAddress());
	}
	
	public void appendByte(byte b) {
		this.data.add(b);
	}
	
	public void appendByte(int i) {
		this.appendByte((byte) i);
	}
	
	public void appendBytes(byte[] bytes) {
		for (byte b : bytes) {
			this.appendByte(b);
		}
	}
	
	public void appendInt(int value) {
		this.appendBytes(new byte[] { (byte) (value >>> 24), (byte) ((value >> 16) & 0xff), (byte) ((value >> 8) & 0xff), (byte) (value & 0xff) });
	}
	
	public void appendShort(int value) {
		this.appendShort((short) value);
	}
	
	public void appendShort(short value) {
		this.appendBytes(new byte[] { (byte) ((value >> 8) & 0xff), (byte) (value & 0xff) });
	}
	
	public int getLength() {
		return this.data.size();
	}
	
	public byte[] getBytes() {
		int lenghtData = this.getLength();
		byte[] array = new byte[lenghtData];
		for (int i = 0; i < lenghtData; i++) {
			array[i] = this.data.get(i);
		}
		return array;
	}
}
