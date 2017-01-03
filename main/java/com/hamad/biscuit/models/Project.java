/*
	Author: Hamad Al Marri;
 */

package com.hamad.biscuit.models;

import java.util.List;

public class Project {
	public String name;
	public String description;
	Backlog backlog;
	List<Release> releases;
	List<Sprint> sprints;


	public void save() {
		ModelHelper.save(this, name);
	}


	static public Project load(String name) {
		return ModelHelper.loadProject(name);
	}


	@Override
	public String toString() {
		return "project name: " + name + "\n\ndescription:-\n" + description;
	}

}
