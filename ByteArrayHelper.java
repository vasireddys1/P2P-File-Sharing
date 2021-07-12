
import java.util.concurrent.atomic.AtomicBoolean;

public class ByteArrayHelper {

	static byte[] mergeByteArrays(byte[] first, byte[] second) {

		byte[] res = new byte[first.length + second.length];

		System.arraycopy(first, 0, res, 0, first.length);

		System.arraycopy(second, 0, res, first.length, second.length);

		return res;

	}

	static byte[] mergeByteArraywithByte(byte[] first, byte second) {

		byte[] res = new byte[first.length + 1];

		System.arraycopy(first, 0, res, 0, first.length);

		res[first.length] = second;

		return res;

	}

	static byte[] integerToByteArray(int id) {

		byte[] conv = new byte[4];

		conv[0] = (byte) ((id >> 24) & 0xFF);

		conv[1] = (byte) ((id >> 16) & 0xFF);

		conv[2] = (byte) ((id >> 8) & 0xFF);

		conv[3] = (byte) (id & 0xFF);

		return conv;

	}

	static int byteArrayToInteger(byte[] value) {

		int conv0 = ((value[0] & 0xFF) << 24);

		int conv1 = ((value[1] & 0xFF) << 16);

		int conv2 = ((value[2] & 0xFF) << 8);

		int conv3 = (value[3] & 0xFF);

		return conv0 | conv1 | conv2 | conv3;

	}

	static byte[] booleanArraytoByteArray(AtomicBoolean[] booleanArray, byte[] byteArray) {
		for (int ind = 0; ind < booleanArray.length; ind++) {
			if (booleanArray[ind].get()) {
				byteArray[ind / 8] |= 1 << (7 - (ind % 8));
			}
		}
		return byteArray;
	}

}