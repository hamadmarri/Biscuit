package com.biscuit.commands.planner;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Project;
import com.biscuit.models.Sprint;
import com.biscuit.models.UserStory;
import com.biscuit.models.enums.State;
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

		if (us == null || s == null) {
			return false;
		}

		// remove it from backlog
		project.backlog.userStories.remove(us);

		// add it to sprint
		s.userStories.add(us);

		// change state to planned
		us.state = State.PLANNED;

		// save project
		project.save();

		reader.println(ColorCodes.GREEN + "User Story \"" + userStoryName + "\" has been moved to sprint " + sprintName + "!" + ColorCodes.RESET);

		return true;
	}

}
