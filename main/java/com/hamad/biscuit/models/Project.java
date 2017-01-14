/*
	Author: Hamad Al Marri;
 */

package com.hamad.biscuit.models;

import java.util.ArrayList;
import java.util.List;

public class Project {
	public String name;
	public String description;
	public Backlog backlog = new Backlog();
	public List<Release> releases = new ArrayList<>();
	public List<Sprint> sprints = new ArrayList<>();


	public void save() {
		ModelHelper.save(this, name);
	}


	static public Project load(String name) {
		return ModelHelper.loadProject(name);
	}


	public void addRelease(Release r) {
		releases.add(r);
	}


	public void addSprint(Sprint s) {
		sprints.add(s);
	}


	@Override
	public String toString() {
		return "project name: " + name + "\n\ndescription:-\n" + description;
	}

}
