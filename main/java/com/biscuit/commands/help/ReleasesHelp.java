package com.biscuit.commands.help;

import de.vandermeer.asciitable.v2.V2_AsciiTable;

public class ReleasesHelp extends UniversalHelp {

	@Override
	public void executeChild(V2_AsciiTable at) {

		at.addRow(null, "Releases Commands").setAlignment(new char[] { 'c', 'c' });
		at.addRule();
		at.addRow("releases", "List all releases").setAlignment(new char[] { 'l', 'l' });
		at.addRow("back", "Go back to previous view (Project)").setAlignment(new char[] { 'l', 'l' });
		at.addRow("go_to release", "Go to a release view (followed by a release name)").setAlignment(new char[] { 'l', 'l' });
		at.addRow("add release", "Add new release").setAlignment(new char[] { 'l', 'l' });
	}

}
