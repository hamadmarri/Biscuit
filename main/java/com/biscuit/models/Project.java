/*
	Author: Hamad Al Marri;
 */

package com.biscuit.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	public List<UserStory> getPlannedUserStories() {
		List<UserStory> userStories = new ArrayList<>();

		for (Sprint s : sprints) {
			userStories.addAll(s.userStories);
		}

		return userStories;
	}

	public List<String> getPlannedUserStoriesNames() {
		List<String> userStoriesNames = new ArrayList<>();

		for (Sprint s : sprints) {
			userStoriesNames.addAll(s.userStories.stream().map(us -> us.title).collect(Collectors.toList()));
		}

		return userStoriesNames;
	}

	@Override
	public String toString() {
		return "project name: " + name + "\n\ndescription:-\n" + description;
	}

}
