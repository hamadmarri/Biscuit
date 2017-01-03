package com.hamad.biscuit.models;

import java.util.ArrayList;
import java.util.List;

public class Root {

	// users
	// contacts
	// groups

	private static transient Root thisInstance = null;


	public static Root getInstance() {
		return thisInstance;
	}


	public static void setInstance(Root r) {
		thisInstance = r;
	}

	private transient String name = "root";

	public List<String> projectsNames = new ArrayList<String>();


	public void addProject(Project p) {
		projectsNames.add(p.name);
	}


	public void save() {
		ModelHelper.save(this, name);
	}


	static public Root load() {
		return ModelHelper.loadRoot("root");
	}

}
