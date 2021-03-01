package es.alba.sweet.base.communication.command;

public class Name extends CommandArgument {

	private String name;

	public Name() {
	}

	public Name(String json) throws JsonException {
		Name name = super.toObject(json, this.getClass());
		this.name = name.get();
	}

	public String get() {
		return this.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toJson() {
		return super.jsonConverter(this);
	}

}
