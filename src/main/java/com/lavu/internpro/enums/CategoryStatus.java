package com.lavu.internpro.enums;

public enum CategoryStatus {

	ACTICE("Active"), HIDE("Hide");

	private final String name;

	public String getName() {
		return name;
	}

	CategoryStatus(String name) {
		this.name = name;
	}

}
