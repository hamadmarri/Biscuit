/*
	Author: Hamad Al Marri;
 */

package com.biscuit.models;

import java.util.Date;
import java.util.List;

import com.biscuit.models.enums.Status;

public class Task {

	public transient Project project;

	public String title;
	public String description;
	public Status state;
	public Date initiatedDate = null;
	public Date plannedDate = null;
	public Date dueDate = null;
	public float estimatedTime;

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


	public void save() {
		project.save();
	}
}
