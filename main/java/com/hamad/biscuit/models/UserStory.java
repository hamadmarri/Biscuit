/*
	Author: Hamad Al Marri;
 */

package com.hamad.biscuit.models;

import java.util.Date;
import java.util.List;

import com.hamad.biscuit.models.enums.BusinessValue;
import com.hamad.biscuit.models.enums.State;

public class UserStory {

	public String name;
	public String description;
	public State state;
	public BusinessValue businessValue;
	public Date initiatedDate = null;
	public Date plannedDate = null;
	public Date dueDate = null;
	public int points;

	List<Task> tasks;
	List<Bug> bugs;
	List<Test> tests;

}
