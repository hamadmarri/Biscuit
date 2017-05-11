package com.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.biscuit.commands.planner.MoveSprintToRelease;
import com.biscuit.commands.planner.MoveUserStoryToSprint;
import com.biscuit.commands.planner.UnplanUserStory;
import com.biscuit.commands.release.ListReleases;
import com.biscuit.commands.sprint.ListSprints;
import com.biscuit.commands.userStory.ListUserStories;
import com.biscuit.factories.PlannerCompleterFactory;
import com.biscuit.models.Project;
import com.biscuit.models.Release;
import com.biscuit.models.Sprint;
import com.biscuit.models.UserStory;

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
		} else if (words.length == 4) {
			return execute4Keywords(words);
		}
		return false;
	}

	private boolean execute4Keywords(String[] words) throws IOException {
		if (words[0].equals("move")) {
			if (UserStory.getUserStories(project.backlog).contains(words[1]) && Sprint.getSprints(project).contains(words[3])) {
				if ((new MoveUserStoryToSprint(reader, project, words[1], words[3])).execute()) {
					resetCompleters();
					return true;
				} else {
					return false;
				}
			} else if (Sprint.getSprints(project).contains(words[1]) && Release.getReleases(project).contains(words[3])) {
				if ((new MoveSprintToRelease(reader, project, words[1], words[3])).execute()) {
					resetCompleters();
					return true;
				} else {
					return false;
				}
			}
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
		} else if (words[0].equals("unplan")) {
			if (project.getPlannedUserStoriesNames().contains(words[1])) {
				if ((new UnplanUserStory(reader, project, words[1])).execute()) {
					resetCompleters();
					return true;
				} else {
					return false;
				}
			}
		}

		return false;
	}
}
