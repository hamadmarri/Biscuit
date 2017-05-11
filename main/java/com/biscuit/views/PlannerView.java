package com.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.biscuit.commands.release.ListReleases;
import com.biscuit.commands.sprint.AddSprint;
import com.biscuit.commands.sprint.ListSprints;
import com.biscuit.commands.userStory.ListUserStories;
import com.biscuit.factories.PlannerCompleterFactory;
import com.biscuit.factories.SprintsCompleterFactory;
import com.biscuit.models.Project;
import com.biscuit.models.Sprint;

import jline.console.completer.Completer;

public class PlannerView extends View {

	Project project = null;

	public PlannerView(View previousView, Project p) {
		super(previousView, "planner");
		this.project = p;
	}

	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(PlannerCompleterFactory.getPlannerCompleters(project));
	}

	@Override
	boolean executeCommand(String[] words) throws IOException {
		if (words.length == 1) {
			return execute1Keywords(words);
		} else if (words.length == 2) {
			return execute2Keywords(words);
		}
		return false;
	}

	private boolean execute1Keywords(String[] words) throws IOException {
		if (words[0].equals("releases")) {
			(new ListReleases(project, "Releases")).execute();
			return true;
		} else if (words[0].equals("sprints")) {
			(new ListSprints(project, "Sprints")).execute();
			return true;
		} else if (words[0].equals("backlog") || words[0].equals("user_stories")) {
			(new ListUserStories(project.backlog, "Backlog (User Stories)")).execute();
			return true;
		}

		return false;
	}

	private boolean execute2Keywords(String[] words) throws IOException {
		if (words[0].equals("show")) {
			if (words[1].equals("releases")) {
				(new ListReleases(project, "Releases")).execute();
				return true;
			} else if (words[1].equals("sprints")) {
				(new ListSprints(project, "Sprints")).execute();
				return true;
			} else if (words[1].equals("backlog") || words[1].equals("user_stories")) {
				(new ListUserStories(project.backlog, "Backlog (User Stories)")).execute();
				return true;
			}
		}

		return false;
	}
}
