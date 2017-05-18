package com.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.biscuit.commands.help.ReleaseHelp;
import com.biscuit.commands.release.ChangeStatusRelease;
import com.biscuit.commands.release.EditRelease;
import com.biscuit.commands.release.ShowRelease;
import com.biscuit.commands.sprint.ListSprints;
import com.biscuit.factories.ReleaseCompleterFactory;
import com.biscuit.models.Release;
import com.biscuit.models.Sprint;
import com.biscuit.models.enums.Status;
import com.biscuit.models.services.Finder.Sprints;

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
		} else if (words.length == 4) {
			return execute4Keywords(words);
		}
		return false;
	}


	private boolean execute4Keywords(String[] words) throws IOException {
		if (words[0].equals("list")) {
			if (words[1].equals("sprints")) {
				if (words[2].equals("filter")) {
					(new ListSprints(release, "Sprints", true, words[3], false, "")).execute();
					return true;
				} else if (words[2].equals("sort")) {
					(new ListSprints(release, "Sprints", false, "", true, words[3])).execute();
					return true;
				}
			}
		}
		return false;
	}


	private boolean execute2Keywords(String[] words) throws IOException {
		if (words[0].equals("change_status_to")) {
			if (Status.values.contains(words[1])) {
				(new ChangeStatusRelease(release, Status.valueOf(words[1].toUpperCase()))).execute();
				return true;
			}
		} else if (words[0].equals("list")) {
			if (words[1].equals("sprints")) {
				(new ListSprints(release, "Sprints")).execute();
				return true;
			}
		} else if (words[0].equals("go_to")) {
			if (Sprints.getAllNames(release).contains(words[1])) {
				Sprint s = Sprints.find(release, words[1]);
				if (s == null) {
					return false;
				}

				SprintView sv = new SprintView(this, s);
				sv.view();
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
		} else if (words[0].equals("sprints")) {
			(new ListSprints(release, "Sprints")).execute();
			return true;
		} else if (words[0].equals("help")) {
			return (new ReleaseHelp()).execute();
		}
		return false;
	}

}
