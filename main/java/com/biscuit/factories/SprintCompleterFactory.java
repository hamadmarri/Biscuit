package com.biscuit.factories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.biscuit.models.Sprint;
import com.biscuit.models.UserStory;
import com.biscuit.models.enums.Status;
import com.biscuit.models.services.Finder.UserStories;

import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class SprintCompleterFactory {

	public static Collection<? extends Completer> getSprintCompleters(Sprint sprint) {
		List<Completer> completers = new ArrayList<Completer>();

		// TODO: sprint commands
		// completers.add(new ArgumentCompleter(
		// new StringsCompleter("summary", "show", "times", "edit", "back",
		// "user_stories"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("show", "edit", "back", "user_stories"), new NullCompleter()));

		completers.add(
				new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("user_stories"), new StringsCompleter("filter"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("user_stories"), new StringsCompleter("sort"),
				new StringsCompleter(UserStory.fields), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("add"), new StringsCompleter("user_story"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("change_status_to"), new StringsCompleter(Status.values), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("go_to"), new StringsCompleter(UserStories.getAllNames(sprint)), new NullCompleter()));

		return completers;
	}
}
