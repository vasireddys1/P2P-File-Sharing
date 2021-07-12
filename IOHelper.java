import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;



public class IOHelper {

	private static BufferedReader br;

	public static void getPeerInfoFromConfig(List<RemotePeerInfo> result) throws IOException {
		FileReader fr = new FileReader("PeerInfo.cfg");
		br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			String[] values = line.split("\\s+");
			result.add(new RemotePeerInfo(values[0], values[1], values[2], values[3]));
			line = br.readLine();
		}
	}
	
	public static void parseCommonConfig() throws IOException {

		FileReader fr = new FileReader("Common.cfg");
		br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			String[] tokens = line.split("\\s+");
			switch (tokens[0]) {

			case "NumberOfPreferredNeighbors":
				Configurator.noOfPrefPeers = Integer.parseInt(tokens[1]);
				break;

			case "UnchokingInterval":
				Configurator.prefPeersUnchokingTime = Integer.parseInt(tokens[1]);
				break;

			case "OptimisticUnchokingInterval":
				Configurator.optUnchokingTime = Integer.parseInt(tokens[1]);
				break;

			case "FileName":
				Configurator.fileName = tokens[1];
				break;

			case "FileSize":
				Configurator.fileSz = Integer.parseInt(tokens[1]);
				break;

			case "PieceSize":
				Configurator.PieceSize = Integer.parseInt(tokens[1]);
				break;

			}
			line = br.readLine();
		}

	}
	
}
