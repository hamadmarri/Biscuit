package com.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.biscuit.commands.sprint.AddSprint;
import com.biscuit.factories.SprintsCompleterFactory;
import com.biscuit.models.Project;
import com.biscuit.models.Sprint;

import jline.console.completer.Completer;

public class SprintsView extends View {

	Project project = null;

	public SprintsView(View previousView, Project p) {
		super(previousView, "sprints");
		this.project = p;
	}

	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(SprintsCompleterFactory.getSprintsCompleters(project));
	}

	@Override
	boolean executeCommand(String[] words) throws IOException {
		if (words.length == 2) {
			return execute2Keywords(words);
		}
		return false;
	}

	private boolean execute2Keywords(String[] words) throws IOException {
		if (words[0].equals("add")) {
			if (words[1].equals("sprint")) {
				(new AddSprint(reader, project)).execute();

				// to reset completers
				clearCompleters();
				addCompleters();

				return true;
			}
		} else if (words[0].equals("go_to")) {
			if (Sprint.getSprints(project).contains(words[1])) {
				Sprint s = Sprint.find(project, words[1]);
				if (s == null) {
					return false;
				}

				s.project = project;

				SprintView sv = new SprintView(this, s);
				sv.view();
				return true;
			}
		}

		return false;
	}
}
