package com.biscuit.models;

import java.util.ArrayList;
import java.util.List;

public class Dashboard {

	// users
	// contacts
	// groups

	private static transient Dashboard thisInstance = null;


	public static Dashboard getInstance() {
		return thisInstance;
	}


	public static void setInstance(Dashboard r) {
		thisInstance = r;
	}

	private transient String name = "dashboard";

	public List<String> projectsNames = new ArrayList<String>();


	public void addProject(Project p) {
		projectsNames.add(p.name);
	}


	public void removeProject(Project p) {
		projectsNames.remove(p.name);
	}


	public void save() {
		ModelHelper.save(this, name);
	}


	static public Dashboard load() {
		return ModelHelper.loadDashboard("dashboard");
	}

}
