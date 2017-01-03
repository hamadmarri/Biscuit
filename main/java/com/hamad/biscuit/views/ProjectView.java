/*
	Author: Hamad Al Marri;
 */

package com.hamad.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.hamad.biscuit.commands.AddUserStoryToBacklog;
import com.hamad.biscuit.factories.ProjectCompleterFactory;
import com.hamad.biscuit.models.Project;

import jline.console.completer.Completer;

public class ProjectView extends View {

	Project project = null;


	public ProjectView(View previousView, Project p) {
		super(previousView, p.name);
		this.project = p;
	}


	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(ProjectCompleterFactory.getProjectCompleters());
	}


	@Override
	boolean executeCommand(String[] words) throws IOException {
		if (words[0].equals("info")) {
			reader.println(project.toString());
		} else if (words[0].equals("back")) {
			this.close();
		} else if (words[0].equals("add")) {
			if (words[1].equals("user_story")) {
				(new AddUserStoryToBacklog(reader, project)).execute();
			}
		}

		return true;
	}

}
