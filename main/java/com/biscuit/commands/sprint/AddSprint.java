package com.biscuit.commands.sprint;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.factories.DateCompleter;
import com.biscuit.models.Project;
import com.biscuit.models.Sprint;
import com.biscuit.models.enums.Status;

import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.Completer;

public class AddSprint implements Command {

	ConsoleReader reader = null;
	Project project = null;
	Sprint sprint = new Sprint();


	public AddSprint(ConsoleReader reader, Project project) {
		super();
		this.reader = reader;
		this.project = project;
	}


	public boolean execute() throws IOException {
		StringBuilder description = new StringBuilder();
		String prompt = reader.getPrompt();

		sprint.project = project;
		setName();

		setDescription(description);

		sprint.state = Status.CREATED;
		sprint.startDate = new Date(0);
		sprint.dueDate = new Date(0);

		if (setStartDate()) {
			if (!setDuration()) {
				setDueDate();
			}
		}

		sprint.assignedEffort = 0;
		setVelocity();

		reader.setPrompt(prompt);

		project.addSprint(sprint);
		project.save();

		reader.println();
		reader.println(ColorCodes.GREEN + "Sprint \"" + sprint.name + "\" has been added!" + ColorCodes.RESET);

		return false;
	}


	private void setDueDate() throws IOException {
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];

		Completer dateCompleter = new AggregateCompleter(DateCompleter.getDateCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(dateCompleter);

		reader.setPrompt(ColorCodes.BLUE + "\ndue date:\n" + ColorCodes.YELLOW + "(hit Tab to see examples)\n(optional: leave it blank and hit enter)\n"
				+ ColorCodes.RESET);

		while ((line = reader.readLine()) != null) {
			line = line.trim();
			String words[] = line.split("\\s+");

			if (line.isEmpty()) {
				reader.removeCompleter(dateCompleter);
				reader.addCompleter(oldCompleter);
				break;
			}

			try {
				int month = DateCompleter.months.indexOf(words[0]);
				int day = Integer.parseInt(words[1]);
				int year = Integer.parseInt(words[2]);

				Calendar cal = new GregorianCalendar();
				cal.clear();
				cal.set(year, month, 1);

				if (day > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
					throw new NullPointerException();
				}

				cal.set(year, month, day);

				if (cal.getTime().compareTo(sprint.startDate) <= 0) {
					System.out.println(ColorCodes.RED + "due date must be after start date" + ColorCodes.RESET);
					continue;
				}

				sprint.dueDate = cal.getTime();

			} catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
				System.out.println(ColorCodes.RED + "invalid value" + ColorCodes.RESET);
				continue;
			}

			reader.removeCompleter(dateCompleter);
			reader.addCompleter(oldCompleter);
			break;
		}
	}


	private boolean setDuration() throws IOException {
		String line;
		int duration;
		reader.setPrompt(ColorCodes.BLUE + "duration: " + ColorCodes.YELLOW + "(optional: leave it blank and hit enter)\n" + ColorCodes.RESET);

		while ((line = reader.readLine()) != null) {
			line = line.trim();

			if (line.isEmpty()) {
				return false;
			}

			try {
				duration = Integer.valueOf(line);
				if (duration <= 0) {
					throw new IllegalArgumentException();
				}

				Calendar cal = new GregorianCalendar();
				cal.setTime(sprint.startDate);
				cal.add(Calendar.DAY_OF_YEAR, duration - 1);
				sprint.dueDate = cal.getTime();
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(ColorCodes.RED + "invalid value: must be a positive integer value!" + ColorCodes.RESET);
			}
		}

		return true;
	}


	private boolean setStartDate() throws IOException {
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];

		Completer dateCompleter = new AggregateCompleter(DateCompleter.getDateCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(dateCompleter);

		reader.setPrompt(ColorCodes.BLUE + "\nstart date:\n" + ColorCodes.YELLOW + "(hit Tab to see examples)\n(optional: leave it blank and hit enter)\n"
				+ ColorCodes.RESET);

		while ((line = reader.readLine()) != null) {
			line = line.trim();
			String words[] = line.split("\\s+");

			if (line.isEmpty()) {
				reader.removeCompleter(dateCompleter);
				reader.addCompleter(oldCompleter);
				return false;
			}

			try {
				int month = DateCompleter.months.indexOf(words[0]);
				int day = Integer.parseInt(words[1]);
				int year = Integer.parseInt(words[2]);

				Calendar cal = new GregorianCalendar();
				cal.clear();
				cal.set(year, month, 1);

				if (day > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
					throw new NullPointerException();
				}

				cal.set(year, month, day);

				sprint.startDate = cal.getTime();

			} catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
				System.out.println(ColorCodes.RED + "invalid value" + ColorCodes.RESET);
				continue;
			}

			reader.removeCompleter(dateCompleter);
			reader.addCompleter(oldCompleter);
			break;
		}
		return true;
	}


	private void setVelocity() throws IOException {
		String line;
		reader.setPrompt(ColorCodes.BLUE + "veloctiy: " + ColorCodes.RESET);

		while ((line = reader.readLine()) != null) {
			line = line.trim();

			try {
				sprint.velocity = Integer.valueOf(line);
				break;
			} catch (NumberFormatException e) {
				System.out.println(ColorCodes.RED + "invalid value: must be an integer value!" + ColorCodes.RESET);
			}
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

		sprint.description = description.toString();
	}


	private void setName() throws IOException {
		reader.setPrompt(ColorCodes.BLUE + "name: " + ColorCodes.RESET);
		sprint.name = reader.readLine();
	}

}
