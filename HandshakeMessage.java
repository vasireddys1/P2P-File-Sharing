

public class HandshakeMessage {
	String handshakeHeader;
	byte[] zeroBits;
	String peerID;
	
	public HandshakeMessage(String peerID) {
		this.handshakeHeader = "P2PFILESHARINGPROJ";
		this.zeroBits = new byte[]{0,0,0,0,0,0,0,0,0,0};
		this.peerID = peerID;
	}
	
	
	public String getHandshakeHeader() {
		return handshakeHeader;
	}
	
	public void setHandshakeHeader(String handshakeHeader) {
		this.handshakeHeader = handshakeHeader;
	}
	
	public String getPeerID() {
		return peerID;
	}
	
	public void setPeerID(String peerID) {
		this.peerID = peerID;
	}
	
	public byte[] getZeroBits() {
		return zeroBits;
	}
	
	public void setZeroBits(byte[] zeroBits) {
		this.zeroBits = zeroBits;
	}
	
	public byte[] constructHandshakeMessage() 
	{	
		byte[] header_and_zerobits = ByteArrayHelper.mergeByteArrays(getHandshakeHeader().getBytes(), getZeroBits());
		byte[] handshakeMsg = ByteArrayHelper.mergeByteArrays(header_and_zerobits, getPeerID().getBytes());
		return handshakeMsg;
		
	}
}
