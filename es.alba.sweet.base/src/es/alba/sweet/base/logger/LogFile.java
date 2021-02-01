package es.alba.sweet.base.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.alba.sweet.base.constant.UserHome;
import es.alba.sweet.base.output.Output;

public class LogFile {

	public static Logger		LOG;
	public static UserHome		USER_HOME;

	private final static int	SIZE_LIMIT	= 1024 * 1024 * 5;

	public LogFile() {

	}

	public static void create(String className, UserHome userHome) {
		USER_HOME = userHome;

		LOG = Logger.getLogger(className);

		// suppress the logging output to the console
		Logger rootLogger = Logger.getLogger("");
		Handler[] handlers = rootLogger.getHandlers();
		if (handlers[0] instanceof ConsoleHandler) {
			rootLogger.removeHandler(handlers[0]);
		}

		// Output.MESSAGE.setLogger(LOG);
		// Output.DEBUG.setLogger(LOG);

		LOG.setLevel(Level.INFO);

		String userDirectory = userHome.get().toString() + File.separator;

		Output.MESSAGE.info("es.alba.sweet.base.logger.LogFile.create", "Logging file will be in " + userDirectory + "Logging_n.txt (n being a number from 0 to 4)");

		FileHandler fileTxt;
		try {

			fileTxt = new FileHandler(userHome + "Logging_%g.txt", SIZE_LIMIT, 5);
			// create a TXT formatter
			MessageFormatter formatterTxt = new MessageFormatter();
			fileTxt.setFormatter(formatterTxt);
			LOG.addHandler(fileTxt);

		} catch (SecurityException | IOException e) {
			e.printStackTrace();

		}
	}

}
