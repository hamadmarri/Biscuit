package com.biscuit.commands.help;

import de.vandermeer.asciitable.v2.V2_AsciiTable;

public class PlannerHelp extends UniversalHelp {

	@Override
	public void executeChild(V2_AsciiTable at) {

		at.addRow(null, "Planner Commands").setAlignment(new char[] { 'c', 'c' });
		at.addRule();

		at.addRow("releases", "List all releases").setAlignment(new char[] { 'l', 'l' });
		at.addRow("sprints", "List all sprints").setAlignment(new char[] { 'l', 'l' });
		at.addRow("user_stories", "List all user stories").setAlignment(new char[] { 'l', 'l' });
		at.addRow("backlog", "List user stories in the backlog").setAlignment(new char[] { 'l', 'l' });

		at.addRow("plan", "Show plan in short form as a tree").setAlignment(new char[] { 'l', 'l' });
		at.addRow("plan details", "Show plan in details in a table").setAlignment(new char[] { 'l', 'l' });

		at.addRow("show releases", "Similar to releases, list all releases").setAlignment(new char[] { 'l', 'l' });
		at.addRow("show sprints", "Similar to sprints, list all sprints").setAlignment(new char[] { 'l', 'l' });
		at.addRow("show user_stories", "Similar to user_stories, list all user stories").setAlignment(new char[] { 'l', 'l' });
		at.addRow("show backlog", "Similar to backlog, list user stories in the backlog").setAlignment(new char[] { 'l', 'l' });
		at.addRow("show plan", "Similar to plan, show plan in short form as a tree").setAlignment(new char[] { 'l', 'l' });
		at.addRow("show plan details", "Similar to plan details, show plan in details in a table").setAlignment(new char[] { 'l', 'l' });

		at.addRow("move sprint# to release#", "Move (or plan) sprint to release where sprint# is a sprint name and release# is a release name\n"
				+ "use TAB to autocomplete sprint and release names\n").setAlignment(new char[] { 'l', 'l' });

		at.addRow("move user_story# to sprint#", "Move (or plan) user story to sprint where user_story# is a user story name and sprint# is a sprint name\n"
				+ "use TAB to autocomplete user story and sprint names\n").setAlignment(new char[] { 'l', 'l' });

		at.addRow("unplan sprint#", "Unplan sprint where sprint# is a sprint name\n" + "use TAB to autocomplete sprint names\n")
				.setAlignment(new char[] { 'l', 'l' });

		at.addRow("unplan user_story#",
				"Unplan user story and move it back to the backlog where user_story# is a user story name\n" + "use TAB to autocomplete user story names\n")
				.setAlignment(new char[] { 'l', 'l' });

		at.addRow("unplan all", "Unplan all user stories and sprints").setAlignment(new char[] { 'l', 'l' });

		at.addRow("back", "Go back to previous view (Project)").setAlignment(new char[] { 'l', 'l' });

	}

}
