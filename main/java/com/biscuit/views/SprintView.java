package com.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.biscuit.commands.sprint.ChangeStatusSprint;
import com.biscuit.commands.sprint.EditSprint;
import com.biscuit.commands.sprint.ShowSprint;
import com.biscuit.factories.SprintCompleterFactory;
import com.biscuit.models.Sprint;
import com.biscuit.models.enums.State;

import jline.console.completer.Completer;

public class SprintView extends View {

	Sprint sprint = null;


	public SprintView(View previousView, Sprint sprint) {
		super(previousView, sprint.name);
		this.sprint = sprint;
	}


	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(SprintCompleterFactory.getSprintCompleters(sprint));
	}


	@Override
	boolean executeCommand(String[] words) throws IOException {
		if (words.length == 1) {
			return execute1Keyword(words);
		} else if (words.length == 2) {
			return execute2Keywords(words);
		}
		return false;
	}


	private boolean execute2Keywords(String[] words) throws IOException {
		if (words[0].equals("change_status_to")) {
			if (State.values.contains(words[1])) {
				(new ChangeStatusSprint(sprint, State.valueOf(words[1].toUpperCase()))).execute();
				return true;
			}
		}
		return false;
	}


	private boolean execute1Keyword(String[] words) throws IOException {
		if (words[0].equals("show")) {
			(new ShowSprint(sprint)).execute();
			return true;
		} else if (words[0].equals("edit")) {
			(new EditSprint(reader, sprint)).execute();
			this.name = sprint.name;
			updatePromptViews();

			return true;
		}
		return false;
	}

}
