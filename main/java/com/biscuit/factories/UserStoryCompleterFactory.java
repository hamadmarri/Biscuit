package com.biscuit.factories;

import java.util.ArrayList;
import java.util.List;

import com.biscuit.models.UserStory;
import com.biscuit.models.enums.Status;
import com.biscuit.models.services.Finder.Tasks;

import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class UserStoryCompleterFactory {

	public static List<Completer> getUserStoryCompleters(UserStory userStory) {
		List<Completer> completers = new ArrayList<Completer>();

		// TODO: user story commands
		// "summary","times",
		// completers.add(new ArgumentCompleter(new StringsCompleter("list"),
		// new StringsCompleter("open"), new StringsCompleter("tasks"),
		// new StringsCompleter("filter", "sort"), new NullCompleter()));
		//
		// completers.add(new ArgumentCompleter(new StringsCompleter("list"),
		// new StringsCompleter("planned"), new StringsCompleter("tasks"),
		// new StringsCompleter("filter", "sort"), new NullCompleter()));
		//
		// completers.add(new ArgumentCompleter(new StringsCompleter("list"),
		// new StringsCompleter("in_progress"), new StringsCompleter("tasks"),
		// new StringsCompleter("filter", "sort"), new NullCompleter()));
		//
		// completers.add(new ArgumentCompleter(new StringsCompleter("list"),
		// new StringsCompleter("in_testing"), new StringsCompleter("tasks"),
		// new StringsCompleter("filter", "sort"), new NullCompleter()));
		//
		// completers.add(new ArgumentCompleter(new StringsCompleter("list"),
		// new StringsCompleter("done"), new StringsCompleter("tasks"),
		// new StringsCompleter("filter", "sort"), new NullCompleter()));
		//
		// completers.add(new ArgumentCompleter(new StringsCompleter("list"),
		// new StringsCompleter("tasks"), new StringsCompleter("filter"), new
		// NullCompleter()));

		// completers.add(new ArgumentCompleter(new StringsCompleter("add"), new
		// StringsCompleter("task", "bug", "test"), new NullCompleter()));

		// completers.add(new ArgumentCompleter(new StringsCompleter("go_to"),
		// new StringsCompleter("bug#", "test#"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("show", "edit", "tasks", "back"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("add"), new StringsCompleter("task"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("go_to"), new StringsCompleter("task"), new StringsCompleter(Tasks.getAllNames(userStory)),
				new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("change_status_to"), new StringsCompleter(Status.values), new NullCompleter()));

		return completers;
	}

}
