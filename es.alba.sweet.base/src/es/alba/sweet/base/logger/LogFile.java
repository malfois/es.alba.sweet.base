package es.alba.sweet.base.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.alba.sweet.base.constant.Directory;
import es.alba.sweet.base.output.Output;

public class LogFile {

	public static Logger		LOG;
	public static Directory		USER_HOME;

	private FileHandler			fileTxt;

	private final static int	SIZE_LIMIT	= 1024 * 1024 * 5;

	public LogFile() {

	}

	public static void create(String className, Directory userHome) {
		USER_HOME = userHome;

		LOG = Logger.getLogger(className);

		// suppress the logging output to the console
		Logger rootLogger = Logger.getLogger("");
		Handler[] handlers = rootLogger.getHandlers();
		if (handlers[0] instanceof ConsoleHandler) {
			rootLogger.removeHandler(handlers[0]);
		}

		LOG.setLevel(Level.INFO);

		String userDirectory = userHome.get().toString() + File.separator;

		Output.MESSAGE.info("es.alba.sweet.base.logger.LogFile.create", "Logging file will be in " + userDirectory + "Logging_n.txt (n being a number from 0 to 4)");

		FileHandler fileTxt;
		try {

			// fileTxt = new FileHandler("Z:\\github\\SERVER\\Logging_%g.txt", SIZE_LIMIT, 5);
			fileTxt = new FileHandler(userDirectory + "logging_%g.log", SIZE_LIMIT, 5, true);
			// create a TXT formatter
			MessageFormatter formatterTxt = new MessageFormatter();
			fileTxt.setFormatter(formatterTxt);
			fileTxt.setLevel(Level.INFO);
			LOG.addHandler(fileTxt);

		} catch (SecurityException | IOException e) {
			e.printStackTrace();

		}
	}

	public static void close() {
		Handler[] handlers = LOG.getHandlers();
		for (int i = 0; i < handlers.length; i++) {
			handlers[i].close();
		}
	}
}
