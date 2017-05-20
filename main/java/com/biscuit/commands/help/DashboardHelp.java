package com.biscuit.commands.help;

import de.vandermeer.asciitable.v2.V2_AsciiTable;

public class DashboardHelp extends UniversalHelp {

	@Override
	public void executeChild(V2_AsciiTable at) {

		at.addRow(null, "Dashboard Commands").setAlignment(new char[] { 'c', 'c' });
		at.addRule();

		at.addRow("go_to", "Go to a project and open the project view (followed by a project name)").setAlignment(new char[] { 'l', 'l' });
		at.addRow("go_to project", "Similar to 'go_to', it goes to a project and open the project view (followed by a project name)")
				.setAlignment(new char[] { 'l', 'l' });
		at.addRow("add project", "Create a new project").setAlignment(new char[] { 'l', 'l' });
		at.addRow("edit project", "Edit project (followed by a project name)").setAlignment(new char[] { 'l', 'l' });
		at.addRow("remove project", "Remove or delete project (followed by a project name)").setAlignment(new char[] { 'l', 'l' });

	}

}
