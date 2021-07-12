
public class MessageHelper {

	public byte[] constructChokeMessage() {
		peerProcess.objLogger.info("Constructing CHOKE Message");
		byte[] len = ByteArrayHelper.integerToByteArray(1);
		byte byt = ByteArrayHelper.integerToByteArray(MessageTypes.choke.ordinal())[3];
		byte[] res = ByteArrayHelper.mergeByteArraywithByte(len, byt);
		return res;
	}

	public byte[] constructUnChokeMessage() {
		peerProcess.objLogger.info("Constructing UNCHOKE Message");
		byte[] len = ByteArrayHelper.integerToByteArray(1);
		byte byt = ByteArrayHelper.integerToByteArray(MessageTypes.unchoke.ordinal())[3];
		byte[] res = ByteArrayHelper.mergeByteArraywithByte(len, byt);
		return res;
	}

	public byte[] constructInterestedMessage() {
		peerProcess.objLogger.info("Constructing INTERESTED Message");
		byte[] len = ByteArrayHelper.integerToByteArray(1);
		byte byt = ByteArrayHelper.integerToByteArray(MessageTypes.interested.ordinal())[3];
		byte[] res = ByteArrayHelper.mergeByteArraywithByte(len, byt);
		return res;
	}

	public byte[] constructNotInterestedMessage() {
		peerProcess.objLogger.info("Constructing NOTINTERESTED Message");
		byte[] len = ByteArrayHelper.integerToByteArray(1);
		byte byt = ByteArrayHelper.integerToByteArray(MessageTypes.not_interested.ordinal())[3];
		byte[] res = ByteArrayHelper.mergeByteArraywithByte(len, byt);
		return res;
	}

	public byte[] constructHaveMessage(byte[] pieceIndex) {
		peerProcess.objLogger.info("Constructing HAVE message");
		byte[] len = ByteArrayHelper.integerToByteArray(5);
		byte byt = ByteArrayHelper.integerToByteArray(MessageTypes.have.ordinal())[3];
		byte[] res = ByteArrayHelper.mergeByteArrays(ByteArrayHelper.mergeByteArraywithByte(len, byt), pieceIndex);
		return res;
	}

	public byte[] constructBitFieldMessage(byte[] payload) {
		peerProcess.objLogger.info("Constructing BITFIELD Message");
		ActualMessage actlMessage = new ActualMessage(MessageTypes.bitfield, payload);
		return actlMessage.getActualMessageInBytes();
	}

	public byte[] constructRequestMessage(int index) {
		peerProcess.objLogger.info("Constructing REQUEST Message");
		ActualMessage actlMessage = new ActualMessage(MessageTypes.request, ByteArrayHelper.integerToByteArray(index));
		return actlMessage.getActualMessageInBytes();
	}

	public byte[] constructPieceMessage(int idx, byte[] payload) {
		peerProcess.objLogger.info("Constructing PIECE Message");
		ActualMessage actlMessage = new ActualMessage(MessageTypes.piece,
				ByteArrayHelper.mergeByteArrays(ByteArrayHelper.integerToByteArray(idx), payload));
		return actlMessage.getActualMessageInBytes();
	}

}
