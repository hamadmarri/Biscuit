/*
	Author: Hamad Al Marri;
 */

package com.hamad.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.hamad.biscuit.commands.AddProject;
import com.hamad.biscuit.factories.DashboardCompleterFactory;
import com.hamad.biscuit.models.Project;
import com.hamad.biscuit.models.Root;

import jline.console.completer.Completer;

public class Dashboard extends View {

	public Dashboard() {
		super(null, "Dashboard");
	}


	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(DashboardCompleterFactory.getDashboardCompleters());
	}


	@Override
	boolean executeCommand(String line) throws IOException {

		if (line.equals("summary")) {

		} else if (line.equals("projects")) {

		} else if (line.equals("alerts")) {

		} else if (line.equals("check_alert")) {

		} else if (line.equals("search")) {

		} else if (line.equals("help")) {

		} else {
			String words[] = line.split("\\s+");

			if (words[0].equals("go_to")) {
				// "project#1", "users", "contacts", "groups"

				if (words[1].equals("project")) {
					// check if project name
					return checkIfProjectName(words[2]);
				}

				// check if project name
				return checkIfProjectName(words[1]);

			} else if (words[0].equals("list")) {
				// projects
				// "filter", "sort"
			} else if (words[0].equals("add") && words[1].equals("project")) {
				(new AddProject(reader)).execute();
				clearCompleters();
				addCompleters();
			} else {
				return false;
			}

		}

		return true;
	}


	private boolean checkIfProjectName(String word) {
		if (Root.getInstance().projectsNames.contains(word)) {
			Project p = Project.load(word);
			ProjectView pv = new ProjectView(this, p);
			pv.view();
			return true;
		}
		return false;
	}

}
