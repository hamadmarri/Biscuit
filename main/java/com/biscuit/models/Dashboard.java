package com.biscuit.models;

import java.io.IOException;
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


	public void renameProject(String oldName, String newName) throws IOException {
		ModelHelper.rename(oldName, newName);
		projectsNames.remove(oldName);
		projectsNames.add(newName);
	}


	public void save() {
		ModelHelper.save(this, name);
	}


	static public Dashboard load() {
		return ModelHelper.loadDashboard("dashboard");
	}

}
