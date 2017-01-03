package com.hamad.biscuit.commands;

import java.io.IOException;

import com.hamad.biscuit.ColorCodes;
import com.hamad.biscuit.models.Project;
import com.hamad.biscuit.models.Root;

import jline.console.ConsoleReader;

public class AddProject implements Command {

	Root root = Root.getInstance();
	Project project = new Project();
	ConsoleReader reader = null;


	public AddProject(ConsoleReader reader) {
		super();
		this.reader = reader;
	}


	public boolean execute() throws IOException {

		StringBuilder description = new StringBuilder();
		String line;
		String prompt = reader.getPrompt();
		reader.setPrompt(ColorCodes.BLUE + "project name: " + ColorCodes.RESET);
		project.name = reader.readLine();
		reader.setPrompt(ColorCodes.BLUE + "\ndescription: " + ColorCodes.YELLOW + "\n(\\q to end writing)\n" + ColorCodes.RESET);

		while ((line = reader.readLine()) != null) {
			if (line.equals("\\q")) {
				break;
			}
			description.append(line).append("\n");
			reader.setPrompt("");
		}

		project.description = description.toString();

		reader.setPrompt(prompt);

		root.addProject(project);

		root.save();
		project.save();

		reader.println();
		reader.println(ColorCodes.GREEN + "Project \"" + project.name + "\" has been added!" + ColorCodes.RESET);

		return false;
	}

}
