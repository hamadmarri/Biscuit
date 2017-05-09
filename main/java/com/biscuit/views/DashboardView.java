/*
	Author: Hamad Al Marri;
 */

package com.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.biscuit.commands.AddProject;
import com.biscuit.factories.DashboardCompleterFactory;
import com.biscuit.models.Project;
import com.biscuit.models.Dashboard;

import jline.console.completer.Completer;

public class DashboardView extends View {

	public DashboardView() {
		super(null, "Dashboard");
	}


	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(DashboardCompleterFactory.getDashboardCompleters());
	}


	@Override
	boolean executeCommand(String[] words) throws IOException {

		if (words.length == 1) {
			if (words[0].equals("summary")) {
			} else if (words[0].equals("projects")) {
			} else if (words[0].equals("alerts")) {
			} else if (words[0].equals("check_alert")) {
			} else if (words[0].equals("search")) {
			} else if (words[0].equals("help")) {
			}
		} else if (words.length == 2) {

			if (words[0].equals("go_to")) {
				// "project#1", "users", "contacts", "groups"

				// check if project name
				return checkIfProjectName(words[1]);

			} else if (words[0].equals("list")) {
				// projects
				// "filter", "sort"
			} else if (words[0].equals("add") && words[1].equals("project")) {
				(new AddProject(reader)).execute();
				
				// to reset completers
				clearCompleters();
				addCompleters();
				
				return true;
			}

		} else if (words.length == 3) {
			if (words[0].equals("go_to")) {
				// "project#1", "users", "contacts", "groups"

				if (words[1].equals("project")) {
					// check if project name
					return checkIfProjectName(words[2]);
				}
			}
		}

		return false;
	}


	private boolean checkIfProjectName(String word) {
		if (Dashboard.getInstance().projectsNames.contains(word)) {
			Project p = Project.load(word);
			ProjectView pv = new ProjectView(this, p);
			pv.view();
			return true;
		}
		return false;
	}

}