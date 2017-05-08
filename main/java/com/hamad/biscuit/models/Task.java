/*
	Author: Hamad Al Marri;
 */

package com.hamad.biscuit.models;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.hamad.biscuit.models.enums.State;

public class Task {

	public transient Project project;

	public String title;
	public String description;
	public State state;
	public Date initiatedDate = null;
	public Date plannedDate = null;
	public Date dueDate = null;
	public int estimatedTime;

	public static String[] fields;

	List<Bug> bugs;
	List<Test> tests;

	static {
		fields = new String[8];
		fields[0] = "title";
		fields[1] = "description";
		fields[2] = "state";
		fields[3] = "initiated_date";
		fields[4] = "planned_date";
		fields[5] = "due_date";
		fields[6] = "estimated_time";
	}


	public static List<String> getTasks(UserStory us) {
		return us.tasks.stream().map(t -> t.title).collect(Collectors.toList());
	}


	public static Task find(UserStory us, String title) {
		return us.tasks.stream().filter(t -> t.title.equals(title)).findAny().orElse(null);
	}


	public void save() {
		project.save();
	}
}
