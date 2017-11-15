package logic;

public class QuestionPacket {
	private int pointer;
	private int length;
	private byte[] body;
	private int transactionId;
	private int flags;
	private int questions;
	private int ans_rr;
	private int auth_rr;
	private int add_rr;
	private String data;
	private int type;
	private int typeClass;
	
	public QuestionPacket(byte[] body) {
		this.pointer = 0;
		this.body = body;
		this.length = body.length;
		this.transactionId = this.get2Bytes();
		this.flags = this.get2Bytes();
		this.questions = this.get2Bytes();
		this.ans_rr = this.get2Bytes();
		this.auth_rr = this.get2Bytes();
		this.add_rr = this.get2Bytes();
		
		int name_length = this.getByte();
		
		this.data = "";
		while (name_length != 0) {
			for (int i = 0; i < name_length; i++) {
				this.data += (char) this.getByte();
			}
			name_length = this.getByte();
			if (name_length > 0) {
				this.data += ".";
			}
		}
		
		this.type = this.get2Bytes();
		this.typeClass = this.get2Bytes();
	}
	
	public int getDifference() {
		return this.length - this.pointer;
	}
	
	public byte getByte() {
		return this.body[this.pointer++];
	}
	
	public byte[] getBytes(int len) {
		byte[] array = new byte[len];
		for (int i = 0; i < len; i++) {
			array[i] = this.getByte();
		}
		return array;
	}
	
	public int get4Bytes() {
		byte[] v = this.getBytes(4);
		return (((v[0] & 0xFF) << 24) + ((v[1] & 0xFF) << 16) + ((v[2] & 0xFF) << 8) + ((v[3] & 0xFF) << 0));
	}
	
	public int get2Bytes() {
		byte[] v = this.getBytes(2);
		return ((v[0] & 0xFF) << 8) + ((v[1] & 0xFF) << 0);
	}
	
	public int getPointer() {
		return this.pointer;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public byte[] getBody() {
		return this.body;
	}
	
	public int getTransactionId() {
		return this.transactionId;
	}
	
	public int getFlags() {
		return this.flags;
	}
	
	public int getQuestions() {
		return this.questions;
	}
	
	public int getAns_rr() {
		return this.ans_rr;
	}
	
	public int getAuth_rr() {
		return this.auth_rr;
	}
	
	public int getAdd_rr() {
		return this.add_rr;
	}
	
	public String getData() {
		return this.data;
	}
	
	public int getType() {
		return this.type;
	}
	
	public int getTypeClass() {
		return this.typeClass;
	}
}
