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

public class MoveUserStoryToSprint implements Command {

	ConsoleReader reader = null;
	Project project = null;
	private String userStoryName;
	private String sprintName;


	public MoveUserStoryToSprint(ConsoleReader reader, Project project, String userStoryName, String sprintName) {
		super();
		this.reader = reader;
		this.project = project;
		this.userStoryName = userStoryName;
		this.sprintName = sprintName;
	}


	@Override
	public boolean execute() throws IOException {

		// get the user story
		UserStory us = UserStories.find(project.backlog, userStoryName);

		// get sprint
		Sprint s = Sprints.find(project, sprintName);

		// get release of this sprint if any
		Release r = Releases.findContains(project, s.name);

		if (us == null || s == null) {
			return false;
		}

		// remove it from backlog
		project.backlog.userStories.remove(us);

		// add it to sprint
		s.userStories.add(us);

		// change state to planned
		us.state = Status.PLANNED;

		// make planned date = today
		us.plannedDate = new Date();

		// update sprint assigned effort
		s.assignedEffort += us.points;

		// update release assigned effort
		if (r != null) {
			r.assignedEffort += us.points;
		}

		// save project
		project.save();

		reader.println(ColorCodes.GREEN + "User Story \"" + userStoryName + "\" has been moved to sprint " + sprintName + "!" + ColorCodes.RESET);

		return true;
	}

}
