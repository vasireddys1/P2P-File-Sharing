import java.io.IOException;
import java.util.*;

import java.util.logging.*;

public class ProcessLogger {
	
	static Logger logger;
	
	public static Logger getLogger(Integer peerId) {
		logger = Logger.getLogger(ProcessLogger.class.getName());
		logger.setLevel(Level.INFO);
		
		FileHandler fhand = null;
		try {
			fhand = new FileHandler("log_peer_" + peerId + ".log");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fhand.setFormatter(new SimpleFormatter() {
            private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

            @Override
            public synchronized String format(LogRecord lr) {
                return String.format(format,
                        new Date(lr.getMillis()),
                        lr.getLevel().getLocalizedName(),
                        lr.getMessage()
                );
            }
        });
		
		logger.addHandler(fhand);
		return logger;
	}

}
