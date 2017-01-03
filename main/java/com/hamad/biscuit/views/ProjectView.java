/*
	Author: Hamad Al Marri;
 */

package com.hamad.biscuit.views;

import java.io.IOException;
import java.util.List;

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
	boolean executeCommand(String line) throws IOException {
		if (line.equals("info")) {
			reader.println(project.toString());
		} else if (line.equals("back")) {
			this.close();
		}
		
		return true;
	}

}
