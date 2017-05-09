package com.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.biscuit.commands.release.ChangeStatusRelease;
import com.biscuit.commands.release.EditRelease;
import com.biscuit.commands.release.ShowRelease;
import com.biscuit.factories.ReleaseCompleterFactory;
import com.biscuit.models.Release;
import com.biscuit.models.enums.State;

import jline.console.completer.Completer;

public class ReleaseView extends View {

	Release release = null;


	public ReleaseView(View previousView, Release release) {
		super(previousView, release.name);
		this.release = release;
	}


	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(ReleaseCompleterFactory.getReleaseCompleters(release));
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
				(new ChangeStatusRelease(release, State.valueOf(words[1].toUpperCase()))).execute();
				return true;
			}
		}
		return false;
	}


	private boolean execute1Keyword(String[] words) throws IOException {
		if (words[0].equals("show")) {
			(new ShowRelease(release)).execute();
			return true;
		} else if (words[0].equals("edit")) {
			(new EditRelease(reader, release)).execute();
			this.name = release.name;
			updatePromptViews();

			return true;
		}
		return false;
	}

}
