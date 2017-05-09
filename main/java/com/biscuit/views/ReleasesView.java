package com.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.biscuit.commands.release.AddRelease;
import com.biscuit.factories.ReleasesCompleterFactory;
import com.biscuit.models.Project;
import com.biscuit.models.Release;

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
		if (words.length == 2) {
			return execute2Keywords(words);
		}
		return false;
	}


	private boolean execute2Keywords(String[] words) throws IOException {
		if (words[0].equals("add")) {
			if (words[1].equals("release")) {
				(new AddRelease(reader, project)).execute();
				
				// to reset completers
				clearCompleters();
				addCompleters();
				
				return true;
			}
		} else if (words[0].equals("go_to")) {
			if (Release.getReleases(project).contains(words[1])) {
				Release r = Release.find(project, words[1]);
				if (r == null) {
					return false;
				}

				r.project = project;

				ReleaseView rv = new ReleaseView(this, r);
				rv.view();
				return true;
			}
		}

		return false;
	}
}
