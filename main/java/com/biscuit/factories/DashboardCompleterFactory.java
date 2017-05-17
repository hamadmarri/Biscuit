package com.biscuit.factories;

import java.util.ArrayList;
import java.util.List;

import com.biscuit.models.Dashboard;

import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class DashboardCompleterFactory {

	public static List<Completer> getDashboardCompleters() {
		List<Completer> completers = new ArrayList<Completer>();

		// TODO: dashboard commands
		// completers.add(new ArgumentCompleter(
		// new StringsCompleter("summary", "projects", "alerts", "check_alert",
		// "search"),
		// new NullCompleter()));

		// completers.add(new ArgumentCompleter(new StringsCompleter("go_to"),
		// new StringsCompleter("users", "contacts", "groups"), new
		// NullCompleter()));

		// completers.add(new ArgumentCompleter(new StringsCompleter("list"),
		// new StringsCompleter("projects"), new StringsCompleter("filter",
		// "sort"),
		// new NullCompleter()));
		//
		// completers.add(new ArgumentCompleter(new StringsCompleter("list"),
		// new StringsCompleter("alerts"), new StringsCompleter("filter",
		// "sort"),
		// new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("go_to"), new StringsCompleter("project"), new StringsCompleter(getProjectsNames()),
				new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("go_to"), new StringsCompleter(getProjectsNames()), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("add", "edit", "remove"), new StringsCompleter("project"), new NullCompleter()));

		return completers;
	}


	private static List<String> getProjectsNames() {
		return Dashboard.getInstance().projectsNames;
	}
}
