package com.biscuit.commands.help;

import de.vandermeer.asciitable.v2.V2_AsciiTable;

public class BacklogHelp extends UniversalHelp {

	@Override
	public void executeChild(V2_AsciiTable at) {

		at.addRow(null, "Backlog Commands").setAlignment(new char[] { 'c', 'c' });
		at.addRule();

		at.addRow("user_stories", "List all user stories").setAlignment(new char[] { 'l', 'l' });

		at.addRow("list user_stories", "List all user stories\n" + "Optional: (filter) to filter out the results (ex. list user_stories filter a_string)\n"
				+ "Optional: (sort) to sort the results based on a chosen column (ex. list user_stories sort column_name)\n"
				+ "          use TAB to autocomplete column names\n" + "          repeating list command with sort option toggles order between ASC and DESC\n")
				.setAlignment(new char[] { 'l', 'l' });

		at.addRow("back", "Go back to previous view (Project)").setAlignment(new char[] { 'l', 'l' });
		at.addRow("go_to user_story", "Go to a user story view (followed by a user story name)").setAlignment(new char[] { 'l', 'l' });
		at.addRow("add user_story", "Add new user story to the backlog").setAlignment(new char[] { 'l', 'l' });

	}

}
