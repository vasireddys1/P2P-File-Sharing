

public class ActualMessage {
	int messageLength;//4Bytes
	MessageTypes messageType;//1Byte
	byte[] messagePayload;//VariableLength
	
	public ActualMessage(MessageTypes messageType, byte[] payLoad) {
		this.messageLength = payLoad.length;
		this.messageType = messageType;
		this.messagePayload = payLoad;
		
		
	}
	
	public int getMessageLength() {
		return messageLength;
	}
	public void setMessageLength(int messageLength) {
		this.messageLength = messageLength;
	}
	public MessageTypes getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageTypes messageType) {
		this.messageType = messageType;
	}
	public byte[] getMessagePayload() {
		return messagePayload;
	}
	public void setMessagePayload(byte[] messagePayload) {
		this.messagePayload = messagePayload;
	}
	
	public byte[] getActualMessageInBytes() {
		Integer msgLength = getMessageLength() + 1;
		byte[] len = ByteArrayHelper.integerToByteArray(msgLength);
		byte byt = ByteArrayHelper.integerToByteArray(getMessageType().ordinal())[3];
		byte[] res = ByteArrayHelper.mergeByteArrays(ByteArrayHelper.mergeByteArraywithByte(len, byt), getMessagePayload());
		return res;
	}
}
