/*
	Author: Hamad Al Marri;
 */

package com.biscuit.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.biscuit.ColorCodes;
import com.biscuit.factories.UniversalCompleterFactory;

import jline.console.ConsoleReader;
import jline.console.completer.AggregateCompleter;
import jline.console.completer.Completer;

public abstract class View {

	static ConsoleReader reader;
	static List<String> promptViews;
	static List<Completer> universalCompleters;
	static Completer completer;

	String name;
	View previousView = null;
	boolean isViewed = false;

	static {
		promptViews = new ArrayList<String>();
		universalCompleters = new ArrayList<Completer>();
		completer = null;

		try {
			reader = new ConsoleReader();
			addUniversalCompleters();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public View(View previousView, String name) {
		this.previousView = previousView;
		this.name = name;
	}

	public void view() {
		if (!isViewed) {
			addPromptViews();
			isViewed = true;
		}

		setPrompt();

		clearCompleters();

		addCompleters();

		read();
	}

	protected void clearCompleters() {
		if (completer != null)
			reader.removeCompleter(completer);
	}

	private static void addUniversalCompleters() {
		universalCompleters.addAll(UniversalCompleterFactory.getUniversalCompleters());
		completer = new AggregateCompleter(universalCompleters);
		reader.addCompleter(completer);
	}

	protected void addCompleters() {
		List<Completer> completers = new ArrayList<Completer>();

		completers.addAll(universalCompleters);
		addSpecificCompleters(completers);

		completer = new AggregateCompleter(completers);
		reader.addCompleter(completer);
	}

	abstract void addSpecificCompleters(List<Completer> completers);

	protected void resetCompleters() {
		clearCompleters();
		addCompleters();
	}

	private void read() {
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				line = line.trim();

				if (line.isEmpty()) {
					continue;
				}

				String words[] = line.split("\\s+");

				if (checkIfUnivesalCommand(words)) {
					continue;
				}

				if (!executeCommand(words)) {
					System.out.println("invalid command!");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean checkIfUnivesalCommand(String[] words) throws IOException {

		if (words.length == 1) {
			if (words[0].equals("clear")) {
				reader.clearScreen();
				return true;
			} else if (words[0].equals("exit")) {
				System.out.println(ColorCodes.BLUE + "See ya!\n" + ColorCodes.RESET);
				reader.shutdown();
				System.exit(0);
			} else if (words[0].equals("dashboard")) {
				gotoDashboard();
				return true;
			} else if (words[0].equals("back")) {
				this.close();
				return true;
			}
		} else if (words.length == 2) {
			if (words[0].equals("go_to") && words[1].equals("dashboard")) {
				gotoDashboard();
				return true;
			}
		}

		return false;
	}

	private void gotoDashboard() throws IOException {
		if (this.name.equals("Dashboard")) {
			reader.println("you are in the dashboard already!");
		} else {
			promptViews.remove(name);
			View v = this.previousView;
			while (!v.name.equals("Dashboard")) {
				promptViews.remove(v.name);
				v = v.previousView;
			}
			v.view();
		}
	}

	abstract boolean executeCommand(String[] words) throws IOException;

	void addPromptViews() {
		promptViews.add(name);
	}

	void updatePromptViews() {
		promptViews.remove(promptViews.size() - 1);
		promptViews.add(name);
		setPrompt();
	}

	static void setPrompt() {
		StringBuilder prompt = new StringBuilder();

		for (int i = 0; i < promptViews.size(); i++) {
			String pv = promptViews.get(i);
			prompt.append(ColorCodes.PURPLE + pv);

			if (i < promptViews.size() - 1)
				prompt.append(ColorCodes.CYAN + ">");
		}

		prompt.append(ColorCodes.YELLOW + "~" + ColorCodes.GREEN + "$ " + ColorCodes.RESET);

		reader.setPrompt(prompt.toString());
	}

	public void close() {
		if (previousView != null) {
			promptViews.remove(name);
			previousView.view();
		}
	}

}
