package com.biscuit.commands.help;

import com.biscuit.models.enums.Status;

import de.vandermeer.asciitable.v2.V2_AsciiTable;

public class TaskHelp extends UniversalHelp {

	@Override
	public void executeChild(V2_AsciiTable at) {

		at.addRow(null, "Task Commands").setAlignment(new char[] { 'c', 'c' });
		at.addRule();

		at.addRow("show", "Show task information").setAlignment(new char[] { 'l', 'l' });
		at.addRow("edit", "Edit task").setAlignment(new char[] { 'l', 'l' });
		at.addRow("change_status_to", "Change status of task (use TAB to autocomplete)\n" + "Status: " + Status.allStatus() + "\n")
				.setAlignment(new char[] { 'l', 'l' });

		at.addRow("back", "Go back to previous view").setAlignment(new char[] { 'l', 'l' });

	}

}
