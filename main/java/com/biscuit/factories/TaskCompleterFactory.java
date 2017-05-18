package com.biscuit.factories;

import java.util.ArrayList;
import java.util.List;

import com.biscuit.models.Task;
import com.biscuit.models.enums.Status;

import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class TaskCompleterFactory {

	public static List<Completer> getTaskCompleters(Task task) {
		List<Completer> completers = new ArrayList<Completer>();

		// TODO: Task commands
		// completers.add(new ArgumentCompleter(new StringsCompleter("summary",
		// "show", "times", "edit", "back"),
		// new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("show", "edit", "back"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("change_status_to"), new StringsCompleter(Status.values), new NullCompleter()));

		return completers;
	}

}
