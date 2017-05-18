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

public class MoveSprintToRelease implements Command {

	ConsoleReader reader = null;
	Project project = null;
	private String sprintName;
	private String releaseNam;


	public MoveSprintToRelease(ConsoleReader reader, Project project, String sprintName, String releaseName) {
		super();
		this.reader = reader;
		this.project = project;
		this.sprintName = sprintName;
		this.releaseNam = releaseName;
	}


	@Override
	public boolean execute() throws IOException {

		// get sprint
		Sprint s = Sprints.find(project, sprintName);

		// get release
		Release r = Releases.find(project, releaseNam);

		if (s == null || r == null) {
			return false;
		}

		// remove it from geneic list in project
		project.sprints.remove(s);

		// add it to release
		r.sprints.add(s);

		// change state to planned
		s.state = Status.PLANNED;

		// update release assigned effort
		r.assignedEffort += s.assignedEffort;

		// save project
		project.save();

		reader.println(ColorCodes.GREEN + "Sprint \"" + sprintName + "\" has been moved to release " + releaseNam + "!" + ColorCodes.RESET);

		return true;
	}

}
