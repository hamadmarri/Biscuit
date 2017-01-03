package com.hamad.biscuit.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hamad.biscuit.ColorCodes;
import com.hamad.biscuit.models.Project;
import com.hamad.biscuit.models.UserStory;
import com.hamad.biscuit.models.enums.BusinessValue;
import com.hamad.biscuit.models.enums.Points;
import com.hamad.biscuit.models.enums.State;

import jline.console.ConsoleReader;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class AddUserStoryToBacklog implements Command {

	ConsoleReader reader = null;
	Project project = null;
	UserStory userStory = new UserStory();


	public AddUserStoryToBacklog(ConsoleReader reader, Project project) {
		super();
		this.reader = reader;
		this.project = project;
	}


	public boolean execute() throws IOException {
		StringBuilder description = new StringBuilder();
		String prompt = reader.getPrompt();

		setTitle();

		setDescription(description);

		userStory.state = State.OPEN;

		setBusinessValue();

		setPoints();

		userStory.initiatedDate = new Date();

		reader.setPrompt(prompt);

		project.backlog.addUserStory(userStory);
		project.save();

		reader.println();
		reader.println(ColorCodes.GREEN + "User Story \"" + userStory.name + "\" has been added!" + ColorCodes.RESET);

		return false;
	}


	private void setPoints() throws IOException {
		List<String> points = new ArrayList<String>();
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];

		for (Points p : Points.values()) {
			points.add(p.name().substring(1, p.name().length() - 2));
		}

		Completer pointsCompleter = new ArgumentCompleter(new StringsCompleter(points), new NullCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(pointsCompleter);

		reader.setPrompt(ColorCodes.BLUE + "\npoints:\n" + ColorCodes.YELLOW + "(hit Tab to see an example)\n"
				+ ColorCodes.RESET);

		while ((line = reader.readLine()) != null) {
			line = line.trim();

			try {
				userStory.points = Integer.valueOf(line);
				break;
			} catch (NumberFormatException e) {
				System.out.println(ColorCodes.RED + "invalid value: must be an integer value!" + ColorCodes.RESET);
			}
		}

		reader.removeCompleter(pointsCompleter);
		reader.addCompleter(oldCompleter);
	}


	private void setBusinessValue() throws IOException {
		List<String> businessValues = new ArrayList<String>();
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];

		for (BusinessValue bv : BusinessValue.values()) {
			businessValues.add(bv.name().toLowerCase());
		}

		Completer businessValuesCompleter = new ArgumentCompleter(new StringsCompleter(businessValues),
				new NullCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(businessValuesCompleter);

		reader.setPrompt(ColorCodes.BLUE + "\nbusiness value:\n" + ColorCodes.YELLOW + "(hit Tab to see valid values)\n"
				+ ColorCodes.RESET);

		while ((line = reader.readLine()) != null) {
			line = line.trim().toUpperCase();

			try {
				userStory.businessValue = BusinessValue.valueOf(line);
			} catch (IllegalArgumentException e) {
				System.out.println(ColorCodes.RED + "invalid value" + ColorCodes.RESET);
				continue;
			}

			reader.removeCompleter(businessValuesCompleter);
			reader.addCompleter(oldCompleter);
			break;
		}

	}


	private void setDescription(StringBuilder description) throws IOException {
		String line;
		reader.setPrompt(
				ColorCodes.BLUE + "\ndescription:\n" + ColorCodes.YELLOW + "(\\q to end writing)\n" + ColorCodes.RESET);

		while ((line = reader.readLine()) != null) {
			if (line.equals("\\q")) {
				break;
			}
			description.append(line).append("\n");
			reader.setPrompt("");
		}

		userStory.description = description.toString();
	}


	private void setTitle() throws IOException {
		reader.setPrompt(ColorCodes.BLUE + "title: " + ColorCodes.RESET);
		userStory.name = reader.readLine();
	}

}
