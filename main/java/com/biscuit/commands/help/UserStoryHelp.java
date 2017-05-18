package com.biscuit.commands.help;

import com.biscuit.models.enums.Status;

import de.vandermeer.asciitable.v2.V2_AsciiTable;

public class UserStoryHelp extends UniversalHelp {

	@Override
	public void executeChild(V2_AsciiTable at) {

		at.addRow(null, "User Story Commands").setAlignment(new char[] { 'c', 'c' });
		at.addRule();

		at.addRow("show", "Show user story information").setAlignment(new char[] { 'l', 'l' });
		at.addRow("edit", "Edit user story").setAlignment(new char[] { 'l', 'l' });
		at.addRow("change_status_to", "Change status of user story (use TAB to autocomplete)\n" + "Status: " + Status.allStatus() + "\n")
				.setAlignment(new char[] { 'l', 'l' });

		at.addRow("tasks", "List tasks planned to this user story").setAlignment(new char[] { 'l', 'l' });
		

		at.addRow("back", "Go back to previous view").setAlignment(new char[] { 'l', 'l' });
		
		at.addRow("go_to task", "Go to a task view (followed by a task name)").setAlignment(new char[] { 'l', 'l' });
		
		at.addRow("add task", "Add new task to this user story").setAlignment(new char[] { 'l', 'l' });
		
	}

}
