/*
	Author: Hamad Al Marri;
 */

package com.biscuit.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.biscuit.models.enums.State;

public class Sprint {

	public transient Project project;

	// info
	public String name;
	public String description;
	public State state;
	public Date startDate;
	public Date dueDate;
	public int assignedEffort;
	public int velocity;

	public List<UserStory> userStories = new ArrayList<>();
	public List<Bug> bugs;
	public List<Test> tests;

	// Completed 0pt 0% ToDo 8pt

	public static String[] fields;
	public static String[] fieldsAsHeader;

	static {
		fields = new String[] { "name", "description", "state", "start_date", "due_date", "assigned_effort",
				"velocity" };
		fieldsAsHeader = new String[] { "Name", "Description", "State", "Start Date", "Due Date", "Assigned Effort",
				"Velocity" };
	}


	public static List<String> getSprints(Project project) {
		return project.sprints.stream().map(s -> s.name).collect(Collectors.toList());
	}


	public static List<String> getSprints(Release release) {
		return release.sprints.stream().map(s -> s.name).collect(Collectors.toList());
	}


	public static Sprint find(Project project, String name) {
		return project.sprints.stream().filter(s -> s.name.equals(name)).findAny().orElse(null);
	}


	public void addUserStory(UserStory userStory) {
		this.userStories.add(userStory);
	}


	public void save() {
		project.save();
	}

}
