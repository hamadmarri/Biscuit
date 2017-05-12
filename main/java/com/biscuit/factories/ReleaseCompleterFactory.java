package com.biscuit.factories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.biscuit.models.Release;
import com.biscuit.models.enums.State;
import com.biscuit.models.services.Finder.Sprints;

import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class ReleaseCompleterFactory {

	public static Collection<? extends Completer> getReleaseCompleters(Release release) {
		List<Completer> completers = new ArrayList<Completer>();

		completers.add(new ArgumentCompleter(new StringsCompleter("summary", "show", "times", "edit", "back"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("sprints"), new StringsCompleter("releases"),
				new StringsCompleter("filter", "sort"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("go_to"), new StringsCompleter(Sprints.getAllNames(release)), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("change_status_to"), new StringsCompleter(State.values), new NullCompleter()));

		return completers;
	}

}
