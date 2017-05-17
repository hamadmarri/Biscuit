package com.biscuit.factories;

import java.util.ArrayList;
import java.util.List;

import com.biscuit.models.Project;
import com.biscuit.models.services.Finder.Releases;
import com.biscuit.models.services.Finder.Sprints;
import com.biscuit.models.services.Finder.UserStories;

import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class PlannerCompleterFactory {

	public static List<Completer> getPlannerCompleters(Project project) {
		List<Completer> completers = new ArrayList<Completer>();

		// TODO: planner commands
		// completers.add(new ArgumentCompleter(new StringsCompleter("add"), new
		// StringsCompleter("release", "sprint", "user_story"), new
		// NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("back", "plan", "releases", "sprints", "user_stories", "backlog", "auto_plan"),
				new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("plan"), new StringsCompleter("details"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("show"), new StringsCompleter("plan", "releases", "sprints", "user_stories", "backlog"),
				new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("show"), new StringsCompleter("plan"), new StringsCompleter("details"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("move"), new StringsCompleter(UserStories.getAllNames(project.backlog)),
				new StringsCompleter("to"), new StringsCompleter(Sprints.getAllNames(project)), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("move"), new StringsCompleter(Sprints.getUnplannedNames(project)), new StringsCompleter("to"),
				new StringsCompleter(Releases.getAllNames(project)), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("unplan"), new StringsCompleter(getUnplanOptions(project)), new NullCompleter()));

		return completers;

	}


	private static List<String> getUnplanOptions(Project project) {
		List<String> sprints_userstories_all = new ArrayList<>();

		sprints_userstories_all.addAll(Sprints.getPlannedNames(project));
		sprints_userstories_all.addAll(UserStories.getPlannedNames(project));
		sprints_userstories_all.add("all");

		return sprints_userstories_all;
	}
}
