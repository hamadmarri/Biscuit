package com.biscuit.commands.help;

import de.vandermeer.asciitable.v2.V2_AsciiTable;

public class ProjectHelp extends UniversalHelp {

	@Override
	public void executeChild(V2_AsciiTable at) {

		at.addRow(null, "Project Commands").setAlignment(new char[] { 'c', 'c' });
		at.addRule();

		at.addRow("show", "Show project information").setAlignment(new char[] { 'l', 'l' });
		at.addRow("releases", "List all releases").setAlignment(new char[] { 'l', 'l' });
		at.addRow("sprints", "List all sprints").setAlignment(new char[] { 'l', 'l' });
		at.addRow("user_stories", "List all user stories").setAlignment(new char[] { 'l', 'l' });
		at.addRow("tasks", "List all tasks").setAlignment(new char[] { 'l', 'l' });
		at.addRow("plan", "Show plan in short form as a tree").setAlignment(new char[] { 'l', 'l' });
		at.addRow("plan details", "Show plan in details in a table").setAlignment(new char[] { 'l', 'l' });
		at.addRow("backlog", "List user stories in the backlog").setAlignment(new char[] { 'l', 'l' });
		at.addRow("show backlog",
				"Similar to backlog, list user stories in the backlog\n" + "Optional: (filter) to filter out the results (ex. show backlog filter a_string)\n"
						+ "Optional: (sort) to sort the results based on a chosen column (ex. show backlog sort column_name)\n"
						+ "          use TAB to autocomplete column names\n"
						+ "          repeating show backlog command with sort option toggles order between ASC and DESC\n")
				.setAlignment(new char[] { 'l', 'l' });

		at.addRow("list releases", "List all releases\n" + "Optional: (filter) to filter out the results (ex. list releases filter a_string)\n"
				+ "Optional: (sort) to sort the results based on a chosen column (ex. list releases sort column_name)\n"
				+ "          use TAB to autocomplete column names\n" + "          repeating list command with sort option toggles order between ASC and DESC\n")
				.setAlignment(new char[] { 'l', 'l' });

		at.addRow("list sprints", "List all sprints\n" + "Optional: (filter) to filter out the results (ex. list sprints filter a_string)\n"
				+ "Optional: (sort) to sort the results based on a chosen column (ex. list sprints sort column_name)\n"
				+ "          use TAB to autocomplete column names\n" + "          repeating list command with sort option toggles order between ASC and DESC\n")
				.setAlignment(new char[] { 'l', 'l' });

		at.addRow("list user_stories", "List all user stories\n" + "Optional: (filter) to filter out the results (ex. list user_stories filter a_string)\n"
				+ "Optional: (sort) to sort the results based on a chosen column (ex. list user_stories sort column_name)\n"
				+ "          use TAB to autocomplete column names\n" + "          repeating list command with sort option toggles order between ASC and DESC\n")
				.setAlignment(new char[] { 'l', 'l' });

		at.addRow("back", "Go back to previous view (Dashboard)").setAlignment(new char[] { 'l', 'l' });
		at.addRow("go_to backlog", "Go to backlog view").setAlignment(new char[] { 'l', 'l' });
		at.addRow("go_to release", "Go to a release view (followed by a release name)").setAlignment(new char[] { 'l', 'l' });
		at.addRow("go_to sprint", "Go to a sprint view (followed by a sprint name)").setAlignment(new char[] { 'l', 'l' });
		at.addRow("go_to user_story", "Go to a user story view (followed by a user story name)").setAlignment(new char[] { 'l', 'l' });
		at.addRow("go_to releases", "Go to all releases view").setAlignment(new char[] { 'l', 'l' });
		at.addRow("go_to sprints", "Go to all sprints view").setAlignment(new char[] { 'l', 'l' });
		at.addRow("go_to planner", "Go to planner view").setAlignment(new char[] { 'l', 'l' });
		at.addRow("add release", "Add new release").setAlignment(new char[] { 'l', 'l' });
		at.addRow("add sprint", "Add new sprint").setAlignment(new char[] { 'l', 'l' });
		at.addRow("add user_story", "Add new user story to the backlog").setAlignment(new char[] { 'l', 'l' });
	}

}
