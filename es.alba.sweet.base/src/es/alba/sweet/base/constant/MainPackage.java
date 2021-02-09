package es.alba.sweet.base.constant;

public enum MainPackage {

	NAME("es.alba.sweet");

	private String name;

	private MainPackage(String packageName) {
		this.name = packageName;
	}

	public String get() {
		return this.name;
	}
}
