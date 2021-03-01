package es.alba.sweet.base.output;

import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import es.alba.sweet.base.constant.Application;

public class MessageDeserializer extends JsonDeserializer<Message> {

	@Override
	public Message deserialize(JsonParser jsonParser, DeserializationContext arg1) throws IOException, JsonProcessingException {
		JsonNode node = jsonParser.getCodec().readTree(jsonParser);

		String date = node.get("formattedDateTime").asText();
		LocalDateTime dateTime = LocalDateTime.parse(date, AMessage.FORMATTER);

		String type = node.get("type").asText();
		MessageType messageType = MessageType.valueOf(type);

		String applicationName = node.get("application").asText();
		Application application = Application.valueOf(applicationName);

		String method = node.get("method").asText();
		String message = node.get("message").asText();

		return new Message(dateTime, messageType, application, method, message);
	}

}
