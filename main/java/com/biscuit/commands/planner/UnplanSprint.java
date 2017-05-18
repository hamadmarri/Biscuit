package com.biscuit.commands.planner;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Project;
import com.biscuit.models.Release;
import com.biscuit.models.Sprint;
import com.biscuit.models.enums.Status;
import com.biscuit.models.services.Finder.Releases;
import com.biscuit.models.services.Finder.Sprints;

import jline.console.ConsoleReader;

public class UnplanSprint implements Command {

	ConsoleReader reader = null;
	Project project = null;
	private String sprintName;


	public UnplanSprint(ConsoleReader reader, Project project, String sprintName) {
		super();
		this.reader = reader;
		this.project = project;
		this.sprintName = sprintName;
	}


	@Override
	public boolean execute() throws IOException {

		// get the release
		Release r = Releases.findContains(project, sprintName);

		// get sprint
		Sprint s = Sprints.find(r, sprintName);

		if (s == null || r == null) {
			return false;
		}

		// add it to geneic list in project
		project.sprints.add(s);

		// remove it from release
		r.sprints.remove(s);

		// change state to unplanned
		s.state = Status.UNPLANNED;

		// update release assigned effort
		r.assignedEffort -= s.assignedEffort;

		// save project
		project.save();

		reader.println(ColorCodes.GREEN + "Sprint \"" + sprintName + "\" has been removed from release " + r.name + "!" + ColorCodes.RESET);

		return true;
	}

}
