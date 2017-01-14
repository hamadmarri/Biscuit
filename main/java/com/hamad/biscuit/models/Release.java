/*
	Author: Hamad Al Marri;
 */

package com.hamad.biscuit.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hamad.biscuit.models.enums.State;

public class Release {

	// info
	public String name;
	public String description;
	public State state;
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

}
