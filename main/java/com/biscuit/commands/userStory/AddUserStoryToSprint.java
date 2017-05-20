package com.biscuit.commands.userStory;

import java.io.IOException;
import java.util.Date;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Sprint;
import com.biscuit.models.UserStory;
import com.biscuit.models.enums.BusinessValue;
import com.biscuit.models.enums.Points;
import com.biscuit.models.enums.Status;

import jline.console.ConsoleReader;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class AddUserStoryToSprint implements Command {

	ConsoleReader reader = null;
	Sprint sprint = null;
	UserStory userStory = new UserStory();


	public AddUserStoryToSprint(ConsoleReader reader, Sprint sprint) {
		super();
		this.reader = reader;
		this.sprint = sprint;
	}


	public boolean execute() throws IOException {
		StringBuilder description = new StringBuilder();
		String prompt = reader.getPrompt();

		userStory.project = sprint.project;
		setTitle();

		setDescription(description);

		userStory.state = Status.OPEN;
		setBusinessValue();
		setPoints();
		userStory.initiatedDate = new Date();
		userStory.plannedDate = new Date(0);
		userStory.dueDate = new Date(0);

		reader.setPrompt(prompt);

		sprint.addUserStory(userStory);
		sprint.save();

		reader.println();
		reader.println(ColorCodes.GREEN + "User Story \"" + userStory.title + "\" has been added to sprint " + sprint.name + "!" + ColorCodes.RESET);

		return false;
	}


	private void setPoints() throws IOException {
		// List<String> points = new ArrayList<String>();
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];

		// for (Points p : Points.values()) {
		// points.add(p.name().substring(1, p.name().length() - 2));
		// }

		Completer pointsCompleter = new ArgumentCompleter(new StringsCompleter(Points.values), new NullCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(pointsCompleter);

		reader.setPrompt(ColorCodes.BLUE + "\npoints:\n" + ColorCodes.YELLOW + "(hit Tab to see an example)\n" + ColorCodes.RESET);

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
		// List<String> businessValues = new ArrayList<String>();
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];

		// for (BusinessValue bv : BusinessValue.values()) {
		// businessValues.add(bv.name().toLowerCase());
		// }

		Completer businessValuesCompleter = new ArgumentCompleter(new StringsCompleter(BusinessValue.values), new NullCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(businessValuesCompleter);

		reader.setPrompt(ColorCodes.BLUE + "\nbusiness value:\n" + ColorCodes.YELLOW + "(hit Tab to see valid values)\n" + ColorCodes.RESET);

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
		reader.setPrompt(ColorCodes.BLUE + "\ndescription:\n" + ColorCodes.YELLOW + "(\\q to end writing)\n" + ColorCodes.RESET);

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
		userStory.title = reader.readLine();
	}

}
