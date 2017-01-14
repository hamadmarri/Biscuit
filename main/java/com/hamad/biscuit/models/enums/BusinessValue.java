package com.hamad.biscuit.models.enums;

public enum BusinessValue {

	MUST_HAVE(0), GREAT(1), GOOD(2), AVERAGE(3), NICE_TO_HAVE(4);

	private final int value;


	private BusinessValue(int value) {
		this.value = value;
	}


	public int getValue() {
		return value;
	}
}
