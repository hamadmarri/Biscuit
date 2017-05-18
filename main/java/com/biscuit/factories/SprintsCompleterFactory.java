package com.biscuit.factories;

import java.util.ArrayList;
import java.util.List;

import com.biscuit.models.Project;
import com.biscuit.models.services.Finder;

import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class SprintsCompleterFactory {

	public static List<Completer> getSprintsCompleters(Project project) {
		List<Completer> completers = new ArrayList<Completer>();

		// TODO: sprints commands
		// completers.add(new ArgumentCompleter(new StringsCompleter("summary",
		// "back"), new NullCompleter()));

		// completers.add(
		// new ArgumentCompleter(new StringsCompleter("list"), new
		// StringsCompleter("past"), new StringsCompleter("filter", "sort"), new
		// NullCompleter()));
		//
		// completers.add(new ArgumentCompleter(new StringsCompleter("list"),
		// new StringsCompleter("future"), new StringsCompleter("filter",
		// "sort"),
		// new NullCompleter()));
		//
		// completers.add(new ArgumentCompleter(new StringsCompleter("list"),
		// new StringsCompleter("current"), new NullCompleter()));
		//
		// completers.add(new ArgumentCompleter(new StringsCompleter("list"),
		// new StringsCompleter("all"), new StringsCompleter("filter"), new
		// NullCompleter()));
		//
		// completers.add(new ArgumentCompleter(new StringsCompleter("list"),
		// new StringsCompleter("all"), new StringsCompleter("sort"),
		// new StringsCompleter(Release.fields), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("back", "sprints"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("add"), new StringsCompleter("sprint"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("go_to"), new StringsCompleter(Finder.Sprints.getAllNames(project)), new NullCompleter()));

		return completers;

	}

}
