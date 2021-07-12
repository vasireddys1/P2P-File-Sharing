
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;


public class MessageReader 
{

	public synchronized byte[] readTCPBitfieldPayload(InputStream inputStream) {
		byte[] clientBitField = new byte[0];
		try {
			byte[] messageLength = new byte[4];
			inputStream.read(messageLength);
			clientBitField = readBitfieldPayload(inputStream, ByteArrayHelper.byteArrayToInteger(messageLength) - 1);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return clientBitField;
	}
	

	public int indexOfPieceToReq(byte[] peerProcessBitfield, byte[] peerClientBitField,
			AtomicBoolean[] neededBitfield) {
		byte[] need = new byte[peerProcessBitfield.length];
		byte[] temp = new byte[peerProcessBitfield.length];
		Arrays.fill(temp, (byte)0);
		byte[] reqBitFieldByte = ByteArrayHelper.booleanArraytoByteArray(neededBitfield, temp);
		byte[] available = new byte[peerProcessBitfield.length];
		List<Integer> list = new ArrayList<Integer>();
		int i = 0;
		while (i < peerProcessBitfield.length) {
			available[i] = (byte) (peerProcessBitfield[i] & reqBitFieldByte[i]);
			need[i] = (byte) ((available[i] ^ peerClientBitField[i]) & ~available[i]);

			if (need[i] != 0)
				list.add(i);
			i++;
		}
		return getPieceIndex(list, need);
	}
		
	public byte[] readMessagePayload(InputStream ins, int payloadLength) {
		byte[] result = new byte[0];
		int lengthTobeRead = payloadLength;
		try {
			while (lengthTobeRead != 0) {
				int bytesAvailable = ins.available();
				int read = 0;
				if (payloadLength > bytesAvailable) {
					read = bytesAvailable;
				} else {
					read = payloadLength;
				}

				byte[] r = new byte[read];
				if (read != 0) {
					ins.read(r);
					result = ByteArrayHelper.mergeByteArrays(result, r);
					lengthTobeRead = lengthTobeRead - read;
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return result;
	}
	
	public String readTCPHSMessage(InputStream in) {
		try {
			isValidHandshakeHeader(in);
			in.read(new byte[10]);
			byte[] peerId = new byte[4];
			in.read(peerId);
			return new String(peerId);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return "";
	}
	
	public void isValidHandshakeHeader(InputStream inputStrem) throws IOException {
		byte[] inputHeader = new byte[18];
		inputStrem.read(inputHeader);
		if (!(new String(inputHeader).equals("P2PFILESHARINGPROJ")))
			throw new RuntimeException("Header Mismatch");
	}

	public boolean shouldSendIntrMessage(byte[] peerProcessBitField, byte[] peerClientBitField) {
		byte isByteSet;
		int startInd = 0;
		while (startInd < peerProcessBitField.length) {
			isByteSet = (byte) (~peerProcessBitField[startInd] & peerClientBitField[startInd]);
			if (isByteSet != 0) {
				return true;
			}
			startInd++;
		}
		return false;
	}
	
	public int getRandomInteger(int high) {
		
		return new Random().nextInt(high);
	}
	
	public int getrandonSetBit(byte msg) {
		int bitInd = getRandomInteger(8);
		int i = 0;
		while (i < 8) {
			if ((msg & (1 << i)) != 0) {
				bitInd = i;
				break;
			}
			i++;
		}
		return bitInd;
	}
	
	public int getPieceIndex(List<Integer> list, byte[] need) {
		if(list.isEmpty())
			return -1;
		int byteInd = list.get(getRandomInteger(list.size()));
		byte rand = need[byteInd];
		int bitInd = getrandonSetBit(rand);				
		return (byteInd*8) + (7-bitInd);	
	}
	
	public byte[] readBitfieldPayload(InputStream ins,int length) throws IOException {
		
		byte[] clientBitField = new byte[length];
		byte[] type = new byte[1];			
		ins.read(type);
		byte val = ByteArrayHelper.integerToByteArray(MessageTypes.bitfield.ordinal())[3];
		if(type[0] == val) 
		{				
			ins.read(clientBitField);				
		}
		return clientBitField;
	}

}