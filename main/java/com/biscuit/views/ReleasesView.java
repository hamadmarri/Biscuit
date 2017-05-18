package com.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.biscuit.commands.help.ReleasesHelp;
import com.biscuit.commands.release.AddRelease;
import com.biscuit.commands.release.ListReleases;
import com.biscuit.factories.ReleasesCompleterFactory;
import com.biscuit.models.Project;
import com.biscuit.models.Release;
import com.biscuit.models.services.Finder.Releases;

import jline.console.completer.Completer;

public class ReleasesView extends View {

	Project project = null;


	public ReleasesView(View previousView, Project p) {
		super(previousView, "releases");
		this.project = p;
	}


	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(ReleasesCompleterFactory.getReleasesCompleters(project));
	}


	@Override
	boolean executeCommand(String[] words) throws IOException {
		if (words.length == 1) {
			return execute1Keywords(words);
		} else if (words.length == 2) {
			return execute2Keywords(words);
		}
		return false;
	}


	private boolean execute1Keywords(String[] words) throws IOException {
		if (words[0].equals("releases")) {
			(new ListReleases(project, "Releases")).execute();
			return true;
		} else if (words[0].equals("help")) {
			return (new ReleasesHelp()).execute();
		}

		return false;
	}


	private boolean execute2Keywords(String[] words) throws IOException {
		if (words[0].equals("add")) {
			if (words[1].equals("release")) {
				(new AddRelease(reader, project)).execute();
				resetCompleters();

				return true;
			}
		} else if (words[0].equals("go_to")) {
			if (Releases.getAllNames(project).contains(words[1])) {
				Release r = Releases.find(project, words[1]);
				if (r == null) {
					return false;
				}

				// r.project = project;

				ReleaseView rv = new ReleaseView(this, r);
				rv.view();
				return true;
			}
		}

		return false;
	}
}
