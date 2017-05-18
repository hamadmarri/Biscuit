package com.biscuit.commands.planner;

import java.io.IOException;
import java.util.Date;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Project;
import com.biscuit.models.Release;
import com.biscuit.models.Sprint;
import com.biscuit.models.UserStory;
import com.biscuit.models.enums.Status;
import com.biscuit.models.services.Finder.Releases;
import com.biscuit.models.services.Finder.Sprints;
import com.biscuit.models.services.Finder.UserStories;

import jline.console.ConsoleReader;

public class UnplanUserStory implements Command {

	ConsoleReader reader = null;
	Project project = null;
	private String userStoryName;
	private String sprintName;


	public UnplanUserStory(ConsoleReader reader, Project project, String userStoryName) {
		super();
		this.reader = reader;
		this.project = project;
		this.userStoryName = userStoryName;
	}


	@Override
	public boolean execute() throws IOException {

		// get sprint
		Sprint s = Sprints.findContains(project, userStoryName);

		// get the user story
		UserStory us = UserStories.find(s, userStoryName);

		// get release of this sprint if any
		Release r = Releases.findContains(project, s.name);

		if (us == null || s == null) {
			return false;
		}

		sprintName = s.name;

		// move it back to backlog
		project.backlog.userStories.add(us);

		// remove it from sprint
		s.userStories.remove(us);

		// change state to unplanned
		us.state = Status.UNPLANNED;

		// make planned date = unset
		us.plannedDate = new Date(0);

		// update sprint assigned effort
		s.assignedEffort -= us.points;

		// update release assigned effort
		if (r != null) {
			r.assignedEffort -= us.points;
		}

		// save project
		project.save();

		reader.println(
				ColorCodes.GREEN + "User Story \"" + userStoryName + "\" has been removed from sprint " + sprintName + " to the backlog!" + ColorCodes.RESET);

		return true;
	}

}
