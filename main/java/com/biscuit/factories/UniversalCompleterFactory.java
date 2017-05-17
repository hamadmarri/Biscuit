package com.biscuit.factories;

import java.util.ArrayList;
import java.util.List;

import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class UniversalCompleterFactory {

	public static List<Completer> getUniversalCompleters() {
		List<Completer> completers = new ArrayList<Completer>();

		// TODO: Universal commands
		// completers.add(new ArgumentCompleter(new StringsCompleter("clear",
		// "exit", "users", "contacts", "groups",
		// "dashboard", "toggle_prompt", "undo", "redo", "help"), new
		// NullCompleter()));

		// completers.add(new ArgumentCompleter(new StringsCompleter("list"),
		// new StringsCompleter("users"), new StringsCompleter("filter",
		// "sort"),
		// new NullCompleter()));
		//
		// completers.add(new ArgumentCompleter(new StringsCompleter("list"),
		// new StringsCompleter("groups"), new StringsCompleter("filter",
		// "sort"),
		// new NullCompleter()));
		//
		// completers.add(new ArgumentCompleter(new StringsCompleter("list"),
		// new StringsCompleter("contacts"), new StringsCompleter("filter",
		// "sort"),
		// new NullCompleter()));

		// completers.add(new ArgumentCompleter(new StringsCompleter("add"), new
		// StringsCompleter("user", "group", "contact"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("clear", "exit", "dashboard", "help"), new NullCompleter()));
		completers.add(new ArgumentCompleter(new StringsCompleter("go_to"), new StringsCompleter("dashboard"), new NullCompleter()));

		return completers;
	}
}
