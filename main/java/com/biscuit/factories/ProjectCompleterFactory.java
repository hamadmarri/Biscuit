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

public class ProjectCompleterFactory {

	public static List<Completer> getProjectCompleters(Project project) {
		List<Completer> completers = new ArrayList<Completer>();

		completers.add(new ArgumentCompleter(new StringsCompleter("summary", "info", "releases", "sprints", "alerts",
				"check_alert", "tasks", "tests", "times", "bugs", "backlog", "back"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("show"), new StringsCompleter("backlog"),
				new StringsCompleter("filter"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("show"), new StringsCompleter("backlog"),
				new StringsCompleter("sort"), new StringsCompleter(UserStory.fields), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("show"), new StringsCompleter("report"),
				new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("show"), new StringsCompleter("chart"),
				new StringsCompleter("process_control", "burn_down", "cumulative_flow", "cycle_time_distribution"),
				new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("past"),
				new StringsCompleter("releases"), new StringsCompleter("filter", "sort"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("past"),
				new StringsCompleter("sprints"), new StringsCompleter("filter", "sort"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("future"),
				new StringsCompleter("releases"), new StringsCompleter("filter", "sort"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("future"),
				new StringsCompleter("sprints"), new StringsCompleter("filter", "sort"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("current"),
				new StringsCompleter("sprints"), new StringsCompleter("filter", "sort"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("closed"),
				new StringsCompleter("alerts"), new StringsCompleter("filter", "sort"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("closed"),
				new StringsCompleter("bugs"), new StringsCompleter("filter", "sort"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("releases"),
				new StringsCompleter("filter"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("releases"),
				new StringsCompleter("sort"), new StringsCompleter(Release.fields), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("sprints"),
				new StringsCompleter("filter"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("sprints"),
				new StringsCompleter("sort"), new StringsCompleter(Sprint.fields), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("alerts"),
				new StringsCompleter("filter", "sort"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("tasks"),
				new StringsCompleter("filter", "sort"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("tests"),
				new StringsCompleter("filter", "sort"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("times"),
				new StringsCompleter("filter", "sort"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("bugs"),
				new StringsCompleter("filter", "sort"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("add"),
				new StringsCompleter("user_story", "release", "sprint"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("go_to"),
				new StringsCompleter("backlog", "releases", "sprints", "tasks", "tests", "bugs", "planner"),
				new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("go_to"), new StringsCompleter("release"),
				new StringsCompleter(Release.getReleases(project)), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("go_to"), new StringsCompleter("sprint"),
				new StringsCompleter(Sprint.getSprints(project)), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("go_to"), new StringsCompleter("sprint"),
				new StringsCompleter(Sprint.getSprints(project)), new NullCompleter()));

		return completers;

	}

}
