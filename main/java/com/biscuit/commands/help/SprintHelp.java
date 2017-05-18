package com.biscuit.commands.help;

import com.biscuit.models.enums.Status;

import de.vandermeer.asciitable.v2.V2_AsciiTable;

public class SprintHelp extends UniversalHelp {

	@Override
	public void executeChild(V2_AsciiTable at) {

		at.addRow(null, "Sprint Commands").setAlignment(new char[] { 'c', 'c' });
		at.addRule();
		at.addRow("show", "Show sprint information").setAlignment(new char[] { 'l', 'l' });
		at.addRow("edit", "Edit sprint").setAlignment(new char[] { 'l', 'l' });
		at.addRow("change_status_to", "Change status of sprint (use TAB to autocomplete)\n" + "Status: " + Status.allStatus() + "\n")
				.setAlignment(new char[] { 'l', 'l' });

		at.addRow("user_stories", "List user stories planned in this sprint").setAlignment(new char[] { 'l', 'l' });

		at.addRow("list user_stories",
				"List user stories planned in this sprint\n" + "Optional: (filter) to filter out the results (ex. list user_stories filter a_string)\n"
						+ "Optional: (sort) to sort the results based on a chosen column (ex. list user_stories sort column_name)\n"
						+ "          use TAB to autocomplete column names\n"
						+ "          repeating list command with sort option toggles order between ASC and DESC\n")
				.setAlignment(new char[] { 'l', 'l' });

		at.addRow("back", "Go back to previous view").setAlignment(new char[] { 'l', 'l' });
		at.addRow("go_to user_story", "Go to a user story view (followed by a user story name)").setAlignment(new char[] { 'l', 'l' });
		at.addRow("add user_story", "Add new user story to this sprint").setAlignment(new char[] { 'l', 'l' });

	}

}
