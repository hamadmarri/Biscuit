package com.biscuit.commands.planner;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Project;
import com.biscuit.models.Release;
import com.biscuit.models.Sprint;
import com.biscuit.models.UserStory;
import com.biscuit.models.services.DateService;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestLine;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

public class ShowPlanDetails implements Command {

	Project project;


	public ShowPlanDetails(Project project) {
		super();
		this.project = project;
	}


	@Override
	public boolean execute() throws IOException {

		printPlanned();
		System.out.println();
		printUnplanned();

		return true;
	}


	private void printUnplanned() {
	}


	private void printPlanned() {
		V2_AsciiTable at = new V2_AsciiTable();
		String tableString;

		addTopHeaserRow(at);
		addReleases(at);

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.PLAIN_7BIT.get());
		rend.setWidth(new WidthLongestLine());

		RenderedTable rt = rend.render(at);
		tableString = rt.toString();

		tableString = colorize(tableString);

		System.out.println();
		System.out.println(tableString);
	}


	private void addReleases(V2_AsciiTable at) {

		if (project.releases.size() == 0) {
			String message;
			message = "There are no releases!";
			at.addRow(null, null, null, null, null, null, null, message);
			at.addRule();
			return;
		}

		for (Release r : project.releases) {
			at.addRow(null, null, null, null, null, null, null, "RELEASE: " + r.name).setAlignment(new char[] { 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c' });
			at.addRule();

			at.addRow(null, null, "Name", "Description", "State", "Start Date", "Due Date", "Assigned Effort")
					.setAlignment(new char[] { 'c', 'c', 'l', 'l', 'c', 'c', 'c', 'c' });
			at.addRule();

			at.addRow(null, null, r.name, r.description, r.state, DateService.getDateAsString(r.startDate), DateService.getDateAsString(r.dueDate),
					r.assignedEffort).setAlignment(new char[] { 'l', 'l', 'l', 'l', 'c', 'c', 'c', 'c' });

			at.addRule();
			addSprints(at, r);
			at.addRule();
			at.addRule();
			at.addRule();
		}

	}


	private void addSprints(V2_AsciiTable at, Release r) {

		if (r.sprints.size() == 0) {
			String message;
			message = "There are no sprints in release: " + r.name + "!";
			at.addRow(null, null, null, null, null, null, null, message);
			at.addRule();
			return;
		}

		for (Sprint s : r.sprints) {
			at.addRow(null, null, null, null, null, null, null, r.name + " -> Sprint: " + s.name)
					.setAlignment(new char[] { 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c' });
			at.addRule();

			at.addRow(null, "Name", "Description", "State", "Start Date", "Due Date", "Assigned Effort", "Velocity")
					.setAlignment(new char[] { 'c', 'c', 'l', 'l', 'c', 'c', 'c', 'c' });
			at.addRule();

			at.addRow(null, s.name, s.description, s.state, DateService.getDateAsString(s.startDate), DateService.getDateAsString(s.dueDate), s.assignedEffort,
					s.velocity).setAlignment(new char[] { 'l', 'l', 'l', 'c', 'c', 'c', 'c', 'c' });

			at.addRule();

			addUserStories(at, s);
		}
	}


	private void addUserStories(V2_AsciiTable at, Sprint s) {
		at.addRow(null, null, null, null, null, null, null, s.name + " -> User Stories").setAlignment(new char[] { 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c' });
		at.addRule();

		at.addRow("Title", "Description", "State", "Business Value", "Initiated Date", "Planned Date", "Due Date", "points")
				.setAlignment(new char[] { 'c', 'c', 'l', 'l', 'c', 'c', 'c', 'c' });
		at.addRule();

		if (s.userStories.size() == 0) {
			String message;
			message = "There are no user stories in sprint: " + s.name + "!";
			at.addRow(null, null, null, null, null, null, null, message);
			at.addRule();
			return;
		}

		for (UserStory us : s.userStories) {

			at.addRow(us.title, us.description, us.state, us.businessValue, DateService.getDateAsString(us.initiatedDate),
					DateService.getDateAsString(us.plannedDate), DateService.getDateAsString(us.dueDate), us.points)
					.setAlignment(new char[] { 'l', 'l', 'c', 'c', 'c', 'c', 'c', 'c' });

			at.addRule();
		}
	}


	private void addTopHeaserRow(V2_AsciiTable at) {
		at.addRule();
		at.addRow(null, null, null, null, null, null, null, "PLAN -> PROJECT: " + project.name)
				.setAlignment(new char[] { 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c' });
		at.addRule();
	}


	private String colorize(String tableString) {
		String replace;
		String[] fields = new String[] { "Name", "Description", "State", "Start Date", "Due Date", "Assigned Effort", "Velocity", "Title", "Business Value",
				"Initiated Date", "Planned Date", "points" };

		tableString = tableString.replaceFirst("PLAN -> PROJECT: " + project.name, ColorCodes.BLUE + "PLAN -> PROJECT: " + project.name + ColorCodes.RESET);
		tableString = tableString.replaceFirst(project.name, ColorCodes.BLUE + project.name + ColorCodes.RESET);

		for (String header : fields) {
			tableString = tableString.replace(header, ColorCodes.BLUE + header + ColorCodes.RESET);
		}

		for (Release r : project.releases) {
			replace = "RELEASE: " + r.name;
			tableString = tableString.replaceFirst(replace, ColorCodes.PURPLE + replace + ColorCodes.RESET);

			for (Sprint s : r.sprints) {
				replace = r.name + " -> Sprint: " + s.name;
				tableString = tableString.replaceFirst(replace, ColorCodes.GREEN + replace + ColorCodes.RESET);

				replace = s.name + " -> User Stories";
				tableString = tableString.replaceFirst(replace, ColorCodes.YELLOW + replace + ColorCodes.RESET);
			}
		}

		return tableString;
	}

}
