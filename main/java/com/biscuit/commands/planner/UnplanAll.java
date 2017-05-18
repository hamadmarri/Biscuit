package com.biscuit.commands.planner;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Project;
import com.biscuit.models.Release;
import com.biscuit.models.Sprint;
import com.biscuit.models.UserStory;
import com.biscuit.models.enums.Status;
import com.biscuit.models.services.Finder.Sprints;

import jline.console.ConsoleReader;

public class UnplanAll implements Command {

	ConsoleReader reader = null;
	Project project = null;


	public UnplanAll(ConsoleReader reader, Project project) {
		super();
		this.reader = reader;
		this.project = project;
	}


	@Override
	public boolean execute() throws IOException {

		unplanSprints();
		unplanUserStories();

		return true;
	}


	private void unplanUserStories() throws IOException {
		List<Sprint> unplannedSprints = Sprints.getUnplanned(project);

		for (Sprint s : unplannedSprints) {
			for (Iterator<UserStory> usItr = s.userStories.iterator(); usItr.hasNext();) {
				UserStory us = usItr.next();

				// move it back to backlog
				project.backlog.userStories.add(us);

				// remove it from sprint
				usItr.remove();

				// change state to unplanned
				us.state = Status.UNPLANNED;

				// update sprint assigned effort
				s.assignedEffort -= us.points;

				// save project
				project.save();

				reader.println(
						ColorCodes.GREEN + "User Story \"" + us.title + "\" has been removed from sprint " + s.name + " to the backlog!" + ColorCodes.RESET);

			}
		}
	}


	private void unplanSprints() throws IOException {

		for (Release r : project.releases) {
			for (Iterator<Sprint> sItr = r.sprints.iterator(); sItr.hasNext();) {
				Sprint s = sItr.next();

				// add it to geneic list in project
				project.sprints.add(s);

				// remove it from release
				sItr.remove();

				// change state to unplanned
				s.state = Status.UNPLANNED;

				// update release assigned effort
				r.assignedEffort -= s.assignedEffort;

				// save project
				project.save();

				reader.println(ColorCodes.GREEN + "Sprint \"" + s.name + "\" has been removed from release " + r.name + "!" + ColorCodes.RESET);
			}
		}
	}

}
