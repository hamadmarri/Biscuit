package com.biscuit.commands.help;

import com.biscuit.models.enums.Status;

import de.vandermeer.asciitable.v2.V2_AsciiTable;

public class ReleaseHelp extends UniversalHelp {

	@Override
	public void executeChild(V2_AsciiTable at) {

		at.addRow(null, "Release Commands").setAlignment(new char[] { 'c', 'c' });
		at.addRule();
		at.addRow("show", "Show release information").setAlignment(new char[] { 'l', 'l' });
		at.addRow("edit", "Edit release").setAlignment(new char[] { 'l', 'l' });
		at.addRow("change_status_to", "Change status of release (use TAB to autocomplete)\n" + "Status: " + Status.allStatus() + "\n")
				.setAlignment(new char[] { 'l', 'l' });

		at.addRow("sprints", "List sprints planned in this release").setAlignment(new char[] { 'l', 'l' });

		at.addRow("list sprints", "List sprints planned in this release\n" + "Optional: (filter) to filter out the results (ex. list sprints filter a_string)\n"
				+ "Optional: (sort) to sort the results based on a chosen column (ex. list sprints sort column_name)\n"
				+ "          use TAB to autocomplete column names\n" + "          repeating list command with sort option toggles order between ASC and DESC\n")
				.setAlignment(new char[] { 'l', 'l' });

		at.addRow("back", "Go back to previous view").setAlignment(new char[] { 'l', 'l' });
		at.addRow("go_to sprint", "Go to a sprint view (followed by a sprint name)").setAlignment(new char[] { 'l', 'l' });

	}

}
