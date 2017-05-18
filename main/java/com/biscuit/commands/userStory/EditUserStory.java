package com.biscuit.commands.userStory;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.factories.DateCompleter;
import com.biscuit.models.UserStory;
import com.biscuit.models.enums.BusinessValue;
import com.biscuit.models.enums.Points;
import com.biscuit.models.enums.Status;
import com.biscuit.models.services.DateService;

import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class EditUserStory implements Command {

	ConsoleReader reader = null;
	UserStory userStory = new UserStory();


	public EditUserStory(ConsoleReader reader, UserStory us) {
		super();
		this.reader = reader;
		this.userStory = us;
	}


	public boolean execute() throws IOException {
		String prompt = reader.getPrompt();

		setTitle();
		setDescription();
		setState();
		setBusinessValue();
		setInitiatedDate();
		setPlannedDate();
		setDueDate();
		setPoints();

		reader.setPrompt(prompt);

		userStory.save();

		return true;
	}


	private void setPoints() throws IOException {

		String prompt = ColorCodes.BLUE + "points:" + ColorCodes.YELLOW + "(hit Tab to see an example) "
				+ ColorCodes.RESET;
		String preload = String.valueOf(userStory.points);	
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];
		Completer pointsCompleter = new ArgumentCompleter(new StringsCompleter(Points.values), new NullCompleter());
		reader.removeCompleter(oldCompleter);
		reader.addCompleter(pointsCompleter);

		reader.resetPromptLine(prompt, preload, 0);
		reader.print("\r");

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


	private void setDueDate() throws IOException {
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];

		Completer dateCompleter = new AggregateCompleter(DateCompleter.getDateCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(dateCompleter);

		reader.setPrompt(ColorCodes.BLUE + "\ndue date:\n" + ColorCodes.YELLOW
				+ "(hit Tab to see examples)\n(optional: leave it blank for unchange, or unset to unset)\n"
				+ ColorCodes.RESET + "current value: " + DateService.getDateAsString(userStory.dueDate) + "\n");

		while ((line = reader.readLine()) != null) {
			line = line.trim();
			String words[] = line.split("\\s+");

			if (line.isEmpty()) {
				reader.removeCompleter(dateCompleter);
				reader.addCompleter(oldCompleter);
				break;
			} else if (words[0].equals("unset")) {
				reader.removeCompleter(dateCompleter);
				reader.addCompleter(oldCompleter);
				userStory.dueDate = new Date(0);
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

				if (DateService.isSet(userStory.plannedDate) && cal.getTime().compareTo(userStory.plannedDate) <= 0) {
					System.out.println(ColorCodes.RED + "due date must be after planned date" + ColorCodes.RESET);
					continue;
				}

				userStory.dueDate = cal.getTime();

			} catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
				System.out.println(ColorCodes.RED + "invalid value" + ColorCodes.RESET);
				continue;
			}

			reader.removeCompleter(dateCompleter);
			reader.addCompleter(oldCompleter);
			break;
		} // while

	}


	private void setPlannedDate() throws IOException {
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];

		Completer dateCompleter = new AggregateCompleter(DateCompleter.getDateCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(dateCompleter);

		reader.setPrompt(ColorCodes.BLUE + "\nplanned date:\n" + ColorCodes.YELLOW
				+ "(hit Tab to see examples)\n(optional: leave it blank for unchange, or unset to unset)\n"
				+ ColorCodes.RESET + "current value: " + DateService.getDateAsString(userStory.plannedDate) + "\n");

		while ((line = reader.readLine()) != null) {
			line = line.trim();
			String words[] = line.split("\\s+");

			if (line.isEmpty()) {
				reader.removeCompleter(dateCompleter);
				reader.addCompleter(oldCompleter);
				break;
			} else if (words[0].equals("unset")) {
				reader.removeCompleter(dateCompleter);
				reader.addCompleter(oldCompleter);
				userStory.plannedDate = new Date(0);
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

				if (DateService.isSet(userStory.dueDate) && cal.getTime().compareTo(userStory.dueDate) >= 0) {
					System.out.println(ColorCodes.RED + "planned date must be before due date" + ColorCodes.RESET);
					continue;
				}

				userStory.plannedDate = cal.getTime();

			} catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
				System.out.println(ColorCodes.RED + "invalid value" + ColorCodes.RESET);
				continue;
			}

			reader.removeCompleter(dateCompleter);
			reader.addCompleter(oldCompleter);
			break;
		} // while

	}


	private void setInitiatedDate() throws IOException {
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];

		Completer dateCompleter = new AggregateCompleter(DateCompleter.getDateCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(dateCompleter);

		reader.setPrompt(ColorCodes.BLUE + "\ninitiated date:\n" + ColorCodes.YELLOW
				+ "(hit Tab to see examples)\n(optional: leave it blank for unchange, or unset to unset)\n"
				+ ColorCodes.RESET + "current value: " + DateService.getDateAsString(userStory.initiatedDate) + "\n");

		while ((line = reader.readLine()) != null) {
			line = line.trim();
			String words[] = line.split("\\s+");

			if (line.isEmpty()) {
				reader.removeCompleter(dateCompleter);
				reader.addCompleter(oldCompleter);
				break;
			} else if (words[0].equals("unset")) {
				reader.removeCompleter(dateCompleter);
				reader.addCompleter(oldCompleter);
				userStory.initiatedDate = new Date(0);
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

				// if (cal.getTime().compareTo(sprint.startDate) <= 0) {
				// System.out.println(ColorCodes.RED + "due date must be after
				// start date" + ColorCodes.RESET);
				// continue;
				// }

				userStory.initiatedDate = cal.getTime();

			} catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
				System.out.println(ColorCodes.RED + "invalid value" + ColorCodes.RESET);
				continue;
			}

			reader.removeCompleter(dateCompleter);
			reader.addCompleter(oldCompleter);
			break;
		} // while

	}


	private void setBusinessValue() throws IOException {

		String prompt = ColorCodes.BLUE + "business value: " + ColorCodes.RESET;
		String preload = userStory.businessValue.toString().toLowerCase();
		String businussValue;

		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];
		Completer businessValuesCompleter = new ArgumentCompleter(new StringsCompleter(BusinessValue.values),
				new NullCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(businessValuesCompleter);

		reader.resetPromptLine(prompt, preload, 0);
		reader.print("\r");

		businussValue = reader.readLine().trim();
		while (!BusinessValue.values.contains(businussValue)) {
			System.out.println(ColorCodes.RED + "invalid business value, hit tab for auto-complete" + ColorCodes.RESET);
			businussValue = reader.readLine().trim();
		}

		userStory.businessValue = BusinessValue.valueOf(businussValue.toUpperCase());

		reader.removeCompleter(businessValuesCompleter);
		reader.addCompleter(oldCompleter);

	}


	private void setState() throws IOException {
		String prompt = ColorCodes.BLUE + "state: " + ColorCodes.RESET;
		String preload = userStory.state.toString().toLowerCase();
		String state;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];
		Completer stateCompleter = new ArgumentCompleter(new StringsCompleter(Status.values), new NullCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(stateCompleter);

		reader.resetPromptLine(prompt, preload, 0);
		reader.print("\r");

		state = reader.readLine().trim();

		while (!Status.values.contains(state)) {
			System.out.println(ColorCodes.RED + "invalid state, hit tab for auto-complete" + ColorCodes.RESET);
			state = reader.readLine().trim();
		}

		userStory.state = Status.valueOf(state.toUpperCase());

		reader.removeCompleter(stateCompleter);
		reader.addCompleter(oldCompleter);
	}


	private void setDescription() throws IOException {
		StringBuilder description = new StringBuilder();
		String line;
		String prompt = ColorCodes.BLUE + "description: " + ColorCodes.YELLOW + "(\\q to end writing) "
				+ ColorCodes.RESET;
		String preload = userStory.description.replace("\n", "<newline>").replace("!", "<exclamation-mark>");

		reader.resetPromptLine(prompt, preload, 0);
		reader.print("\r");

		while ((line = reader.readLine()) != null) {
			if (line.equals("\\q")) {
				break;
			}
			description.append(line).append("\n");
			reader.setPrompt("");
		}

		userStory.description = description.toString().replace("<newline>", "\n").replace("<exclamation-mark>", "!");
	}


	private void setTitle() throws IOException {
		String prompt = ColorCodes.BLUE + "title: " + ColorCodes.RESET;
		String preload = userStory.title;

		reader.resetPromptLine(prompt, preload, 0);
		reader.print("\r");

		userStory.title = reader.readLine();
	}

}
