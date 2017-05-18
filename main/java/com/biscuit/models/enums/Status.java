package com.biscuit.models.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Status {

	CREATED(0), OPEN(1), PLANNED(2), UNPLANNED(3), IN_PROGRESS(4), IN_TESTING(5), DONE(6), OVERDUE(7), REMOVED(8);

	private final int value;
	public static List<String> values = new ArrayList<>(
			Arrays.asList("created", "open", "planned", "unplanned", "in_progress", "in_testing", "done", "overdue", "removed"));


	private Status(int value) {
		this.value = value;
	}


	public int getValue() {
		return value;
	}


	public static String allStatus() {
		StringBuilder sb = new StringBuilder();

		for (String s : values) {
			sb.append(s).append(", ");
		}

		return sb.substring(0, sb.length() - 2);
	}

}
