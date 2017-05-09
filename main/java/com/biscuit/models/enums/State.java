package com.biscuit.models.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum State {

	CREATED(0), OPEN(1), PLANNED(2), IN_PROGRESS(3), IN_TESTING(4), DONE(5), OVERDUE(6), REMOVED(7);

	private final int value;
	public static List<String> values = new ArrayList<>(
			Arrays.asList("created", "open", "planned", "in_progress", "in_testing", "done", "overdue", "removed"));


	private State(int value) {
		this.value = value;
	}


	public int getValue() {
		return value;
	}

}
