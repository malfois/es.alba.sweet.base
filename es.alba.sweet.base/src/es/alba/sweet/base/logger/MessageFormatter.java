package es.alba.sweet.base.logger;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MessageFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		return record.getMessage();
	}

}
