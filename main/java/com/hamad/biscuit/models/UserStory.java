/*
	Author: Hamad Al Marri;
 */

package com.hamad.biscuit.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.hamad.biscuit.models.enums.BusinessValue;
import com.hamad.biscuit.models.enums.State;

public class UserStory {

	public transient Project project;

	public String title;
	public String description;
	public State state;
	public BusinessValue businessValue;
	public Date initiatedDate = null;
	public Date plannedDate = null;
	public Date dueDate = null;
	public int points;

	public static String[] fields;

	public List<Task> tasks = new ArrayList<>();
	public List<Bug> bugs = new ArrayList<>();
	public List<Test> tests = new ArrayList<>();

	static {
		fields = new String[8];
		fields[0] = "title";
		fields[1] = "description";
		fields[2] = "state";
		fields[3] = "business_value";
		fields[4] = "initiated_date";
		fields[5] = "planned_date";
		fields[6] = "due_date";
		fields[7] = "points";
	}


	public static List<String> getUserStories(Backlog backlog) {
		return backlog.userStories.stream().map(us -> us.title).collect(Collectors.toList());
	}


	public static UserStory find(Backlog backlog, String title) {
		return backlog.userStories.stream().filter(us -> us.title.equals(title)).findAny().orElse(null);
	}


	public void save() {
		project.save();
	}

}
