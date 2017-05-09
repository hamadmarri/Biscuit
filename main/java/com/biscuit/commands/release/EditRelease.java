package com.biscuit.commands.release;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.factories.DateCompleter;
import com.biscuit.models.Release;
import com.biscuit.models.enums.State;
import com.biscuit.models.services.DateService;

import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class EditRelease implements Command {
	ConsoleReader reader = null;
	Release r = new Release();


	public EditRelease(ConsoleReader reader, Release r) {
		super();
		this.reader = reader;
		this.r = r;
	}


	public boolean execute() throws IOException {
		String prompt = reader.getPrompt();

		setName();
		setDescription();
		setState();
		setStartDate();
		setDueDate();

		reader.setPrompt(prompt);

		r.save();

		return true;
	}


	private void setDueDate() throws IOException {
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];

		Completer dateCompleter = new AggregateCompleter(DateCompleter.getDateCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(dateCompleter);

		reader.setPrompt(ColorCodes.BLUE + "\ndue date:\n" + ColorCodes.YELLOW
				+ "(hit Tab to see examples)\n(optional: leave it blank for unchange, or unset to unset)\n"
				+ ColorCodes.RESET + "current value: " + DateService.getDateAsString(r.dueDate) + "\n");

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
				r.dueDate = new Date(0);
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

				if (DateService.isSet(r.dueDate) && cal.getTime().compareTo(r.startDate) <= 0) {
					System.out.println(ColorCodes.RED + "due date must be after start date" + ColorCodes.RESET);
					continue;
				}

				r.dueDate = cal.getTime();

			} catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
				System.out.println(ColorCodes.RED + "invalid value" + ColorCodes.RESET);
				continue;
			}

			reader.removeCompleter(dateCompleter);
			reader.addCompleter(oldCompleter);
			break;
		} // while

	}


	private void setStartDate() throws IOException {
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];

		Completer dateCompleter = new AggregateCompleter(DateCompleter.getDateCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(dateCompleter);

		reader.setPrompt(ColorCodes.BLUE + "\nstartDate date:\n" + ColorCodes.YELLOW
				+ "(hit Tab to see examples)\n(optional: leave it blank for unchange, or unset to unset)\n"
				+ ColorCodes.RESET + "current value: " + DateService.getDateAsString(r.startDate) + "\n");

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
				r.startDate = new Date(0);
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

				r.startDate = cal.getTime();

			} catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
				System.out.println(ColorCodes.RED + "invalid value" + ColorCodes.RESET);
				continue;
			}

			reader.removeCompleter(dateCompleter);
			reader.addCompleter(oldCompleter);
			break;
		} // while

	}


	private void setState() throws IOException {
		String prompt = ColorCodes.BLUE + "state: " + ColorCodes.RESET;
		String preload = r.state.toString().toLowerCase();
		String state;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];
		Completer stateCompleter = new ArgumentCompleter(new StringsCompleter(State.values), new NullCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(stateCompleter);

		reader.resetPromptLine(prompt, preload, 0);
		reader.print("\r");

		state = reader.readLine().trim();

		while (!State.values.contains(state)) {
			System.out.println(ColorCodes.RED + "invalid state, hit tab for auto-complete" + ColorCodes.RESET);
			state = reader.readLine().trim();
		}

		r.state = State.valueOf(state.toUpperCase());

		reader.removeCompleter(stateCompleter);
		reader.addCompleter(oldCompleter);
	}


	private void setDescription() throws IOException {
		StringBuilder description = new StringBuilder();
		String line;
		String prompt = ColorCodes.BLUE + "description: " + ColorCodes.YELLOW + "(\\q to end writing) "
				+ ColorCodes.RESET;
		String preload = r.description.replace("\n", "<newline>").replace("!", "<exclamation-mark>");

		reader.resetPromptLine(prompt, preload, 0);
		reader.print("\r");

		while ((line = reader.readLine()) != null) {
			if (line.equals("\\q")) {
				break;
			}
			description.append(line).append("\n");
			reader.setPrompt("");
		}

		r.description = description.toString().replace("<newline>", "\n").replace("<exclamation-mark>", "!");
	}


	private void setName() throws IOException {
		String prompt = ColorCodes.BLUE + "name: " + ColorCodes.RESET;
		String preload = r.name;

		reader.resetPromptLine(prompt, preload, 0);
		reader.print("\r");

		r.name = reader.readLine();
	}
}
