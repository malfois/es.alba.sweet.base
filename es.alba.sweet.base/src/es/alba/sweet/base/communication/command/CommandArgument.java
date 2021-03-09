package es.alba.sweet.base.communication.command;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.alba.sweet.base.output.Output;

public abstract class CommandArgument {

	public abstract String toJson();

	private static ObjectMapper ObjectMapper = new ObjectMapper();

	protected CommandArgument() {
	}

	protected <T extends CommandArgument> T toObject(String json, Class<T> clazz) throws JsonException {
		byte[] jsonData = json.getBytes();
		try {
			return (T) ObjectMapper.readValue(jsonData, clazz);
		} catch (JsonParseException e) {
			throw new JsonException("Error parsing " + json + " to object " + clazz);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new JsonException("Error parsing " + json + " to object " + clazz);
		} catch (IOException e) {
			throw new JsonException("Error parsing " + json + " to object " + clazz);
		}
	}

	protected <T extends CommandArgument> String jsonConverter(T argument) {
		StringWriter text = new StringWriter();
		try {
			ObjectMapper.writeValue(text, argument);
			return text.toString();
		} catch (IOException e) {
			Output.DEBUG.info("es.alba.sweet.communication.command.JsonText.toString", "Error reading configuration ");
			return e.getMessage();
		}
	}
}
