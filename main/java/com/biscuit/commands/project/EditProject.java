package com.biscuit.commands.project;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Dashboard;
import com.biscuit.models.Project;

import jline.console.ConsoleReader;

public class EditProject implements Command {

	ConsoleReader reader = null;
	Project p = new Project();


	public EditProject(ConsoleReader reader, Project p) {
		super();
		this.reader = reader;
		this.p = p;
	}


	public boolean execute() throws IOException {
		String prompt = reader.getPrompt();
		String oldName;

		oldName = p.name;

		setName();
		setDescription();

		try {
			Dashboard.getInstance().renameProject(oldName, p.name);
			Dashboard.getInstance().save();
		} catch (IOException e) {
			System.out.println("project with name \"" + p.name + "\" is already existed!");
			p.name = oldName;
		}

		p.save();
		reader.setPrompt(prompt);

		return true;
	}


	private void setDescription() throws IOException {
		StringBuilder description = new StringBuilder();
		String line;
		String prompt = ColorCodes.BLUE + "description: " + ColorCodes.YELLOW + "(\\q to end writing) " + ColorCodes.RESET;
		String preload = p.description.replace("\n", "<newline>").replace("!", "<exclamation-mark>");

		reader.resetPromptLine(prompt, preload, 0);
		reader.print("\r");

		while ((line = reader.readLine()) != null) {
			if (line.equals("\\q")) {
				break;
			}
			description.append(line).append("\n");
			reader.setPrompt("");
		}

		p.description = description.toString().replace("<newline>", "\n").replace("<exclamation-mark>", "!");
	}


	private void setName() throws IOException {
		String prompt = ColorCodes.BLUE + "name: " + ColorCodes.RESET;
		String preload = p.name;

		reader.resetPromptLine(prompt, preload, 0);
		reader.print("\r");

		p.name = reader.readLine();
	}
}
