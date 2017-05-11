package com.biscuit.factories;

import java.util.ArrayList;
import java.util.List;

import com.biscuit.models.Project;
import com.biscuit.models.Release;
import com.biscuit.models.Sprint;
import com.biscuit.models.UserStory;

import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class PlannerCompleterFactory {

	public static List<Completer> getPlannerCompleters(Project project) {
		List<Completer> completers = new ArrayList<Completer>();

		completers.add(new ArgumentCompleter(
				new StringsCompleter("plan", "releases", "sprints", "user_stories", "auto_plan"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("show"),
				new StringsCompleter("plan", "releases", "sprints", "user_stories"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("add"),
				new StringsCompleter("release", "sprint", "user_story"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("move"),
				new StringsCompleter(UserStory.getUserStories(project.backlog)), new StringsCompleter("to"),
				new StringsCompleter(Sprint.getSprints(project)), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("move"),
				new StringsCompleter(Sprint.getSprints(project)), new StringsCompleter("to"),
				new StringsCompleter(Release.getReleases(project)), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("unplan"),
				new StringsCompleter(getUnplanOptions(project)), new NullCompleter()));

		return completers;

	}

	private static List<String> getUnplanOptions(Project project) {
		List<String> sprints_userstories_all = new ArrayList<>();

		sprints_userstories_all.addAll(Sprint.getSprints(project));
		sprints_userstories_all.addAll(UserStory.getUserStories(project.backlog));
		sprints_userstories_all.add("all");

		return sprints_userstories_all;
	}
}
