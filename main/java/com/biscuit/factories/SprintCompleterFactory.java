package com.biscuit.factories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.biscuit.models.Sprint;
import com.biscuit.models.enums.State;

import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class SprintCompleterFactory {

	public static Collection<? extends Completer> getSprintCompleters(Sprint sprint) {
		List<Completer> completers = new ArrayList<Completer>();

		completers.add(new ArgumentCompleter(new StringsCompleter("summary", "show", "times", "edit", "back"),
				new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("sprints"),
				new StringsCompleter("releases"), new StringsCompleter("filter", "sort"), new NullCompleter()));

		// completers.add(new ArgumentCompleter(new StringsCompleter("go_to"),
		// new StringsCompleter(Sprint.getSprints(release)), new
		// NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("change_status_to"),
				new StringsCompleter(State.values), new NullCompleter()));

		return completers;
	}
}
