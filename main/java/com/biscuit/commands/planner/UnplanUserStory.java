package com.biscuit.commands.planner;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Project;
import com.biscuit.models.Sprint;
import com.biscuit.models.UserStory;
import com.biscuit.models.enums.State;

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
		Sprint s = Sprint.findSprintContains(project, userStoryName);

		// get the user story
		UserStory us = UserStory.find(s, userStoryName);

		if (us == null || s == null) {
			return false;
		}

		sprintName = s.name;

		// move it back to backlog
		project.backlog.userStories.add(us);

		// remove it from sprint
		s.userStories.remove(us);

		// change state to unplanned
		us.state = State.UNPLANNED;

		// save project
		project.save();

		reader.println(
				ColorCodes.GREEN + "User Story \"" + userStoryName + "\" has been removed from sprint " + sprintName + " to the backlog!" + ColorCodes.RESET);

		return true;
	}

}
