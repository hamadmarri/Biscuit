package com.biscuit.commands.help;

import de.vandermeer.asciitable.v2.V2_AsciiTable;

public class SprintsHelp extends UniversalHelp {

	@Override
	public void executeChild(V2_AsciiTable at) {

		at.addRow(null, "Sprints Commands").setAlignment(new char[] { 'c', 'c' });
		at.addRule();
		at.addRow("sprints", "List all sprints").setAlignment(new char[] { 'l', 'l' });
		at.addRow("back", "Go back to previous view (Project)").setAlignment(new char[] { 'l', 'l' });
		at.addRow("go_to sprint", "Go to a sprint view (followed by a sprint name)").setAlignment(new char[] { 'l', 'l' });
		at.addRow("add sprint", "Add new sprint").setAlignment(new char[] { 'l', 'l' });
	}

}
