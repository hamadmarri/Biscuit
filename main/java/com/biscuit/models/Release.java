/*
	Author: Hamad Al Marri;
 */

package com.biscuit.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.biscuit.models.enums.Status;

public class Release {

	public transient Project project;

	// info
	public String name;
	public String description;
	public Status state;
	public Date startDate;
	public Date dueDate;
	public int assignedEffort;

	public List<Sprint> sprints = new ArrayList<>();
	public List<Bug> bugs;
	public List<Test> tests;

	// Completed 0pt 0% ToDo 8pt

	public static String[] fields;
	public static String[] fieldsAsHeader;
	static {
		fields = new String[] { "name", "description", "state", "start_date", "due_date", "assigned_effort" };
		fieldsAsHeader = new String[] { "Name", "Description", "State", "Start Date", "Due Date", "Assigned Effort" };
	}

	public void save() {
		project.save();
	}
}
