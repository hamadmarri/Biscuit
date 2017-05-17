package com.biscuit.commands.sprint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Project;
import com.biscuit.models.Release;
import com.biscuit.models.Sprint;
import com.biscuit.models.services.DateService;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestLine;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

public class ListSprints implements Command {

	Project project = null;
	Release release = null;
	String title = "";
	boolean isFilter = false;
	boolean isSort = false;
	static boolean isReverse = false;
	private String filterBy;
	private String sortBy;
	private static String lastSortBy = "";


	public ListSprints(Project project, String title) {
		super();
		this.project = project;
		this.title = title;
	}


	public ListSprints(Project porject, String title, boolean isFilter, String filterBy, boolean isSort, String sortBy) {
		super();
		this.project = porject;
		this.title = title;
		this.isFilter = isFilter;
		this.filterBy = filterBy.toLowerCase();
		this.isSort = isSort;
		this.sortBy = sortBy.toLowerCase();
	}


	public ListSprints(Release release, String title) {
		super();
		this.release = release;
		this.title = title;
	}


	public ListSprints(Release release, String title, boolean isFilter, String filterBy, boolean isSort, String sortBy) {
		super();
		this.release = release;
		this.title = title;
		this.isFilter = isFilter;
		this.filterBy = filterBy.toLowerCase();
		this.isSort = isSort;
		this.sortBy = sortBy.toLowerCase();
	}


	@Override
	public boolean execute() throws IOException {

		V2_AsciiTable at = new V2_AsciiTable();
		String tableString;

		List<Sprint> sprints = new ArrayList<>();

		if (project != null) {
			sprints.addAll(project.sprints);
		} else if (release != null) {
			sprints.addAll(release.sprints);
		}

		if (isFilter) {
			doFilter(sprints);
		}

		if (isSort) {
			doSort(sprints);
		}

		at.addRule();
		if (!this.title.isEmpty()) {
			at.addRow(null, null, null, null, null, null, this.title).setAlignment(new char[] { 'c', 'c', 'c', 'c', 'c', 'c', 'c' });
			at.addRule();
		}
		at.addRow("Name", "Description", "State", "Start Date", "Due Date", "Assigned Effort", "Velocity")
				.setAlignment(new char[] { 'l', 'l', 'c', 'c', 'c', 'c', 'c' });

		if (sprints.size() == 0) {
			String message;
			if (!isFilter) {
				message = "There are no sprints!";
			} else {
				message = "No results";
			}
			at.addRule();
			at.addRow(null, null, null, null, null, null, message);
		} else {
			for (Sprint s : sprints) {
				at.addRule();

				at.addRow(s.name, s.description, s.state, DateService.getDateAsString(s.startDate), DateService.getDateAsString(s.dueDate), s.assignedEffort,
						s.velocity).setAlignment(new char[] { 'l', 'l', 'c', 'c', 'c', 'c', 'c' });
			} // for
		}

		at.addRule();
		at.addRow(null, null, null, null, null, null, "Total: " + sprints.size());
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.PLAIN_7BIT.get());
		rend.setWidth(new WidthLongestLine());

		RenderedTable rt = rend.render(at);
		tableString = rt.toString();

		tableString = colorize(tableString);

		System.out.println();
		System.out.println(tableString);

		return false;
	}


	private void doSort(List<Sprint> sprints) {

		Comparator<Sprint> byName = (s1, s2) -> s1.name.compareTo(s2.name);
		Comparator<Sprint> byDescription = (s1, s2) -> s1.description.compareTo(s2.description);
		Comparator<Sprint> byState = (s1, s2) -> Integer.compare(s1.state.getValue(), s2.state.getValue());
		Comparator<Sprint> byStartDate = (s1, s2) -> s1.startDate.compareTo(s2.startDate);
		Comparator<Sprint> byDueDate = (s1, s2) -> s1.dueDate.compareTo(s2.dueDate);
		Comparator<Sprint> byAssignedEffort = (s1, s2) -> Integer.compare(s1.assignedEffort, s2.assignedEffort);
		Comparator<Sprint> byVelocity = (s1, s2) -> Integer.compare(s1.velocity, s2.velocity);
		Comparator<Sprint> byFiled = null;

		if (sortBy.equals(Sprint.fields[0])) {
			byFiled = byName;
		} else if (sortBy.equals(Sprint.fields[1])) {
			byFiled = byDescription;
		} else if (sortBy.equals(Sprint.fields[2])) {
			byFiled = byState;
		} else if (sortBy.equals(Sprint.fields[3])) {
			byFiled = byStartDate;
		} else if (sortBy.equals(Sprint.fields[4])) {
			byFiled = byDueDate;
		} else if (sortBy.equals(Sprint.fields[5])) {
			byFiled = byAssignedEffort;
		} else if (sortBy.equals(Sprint.fields[6])) {
			byFiled = byVelocity;
		} else {
			return;
		}

		List<Sprint> sorted = sprints.stream().sorted(byFiled).collect(Collectors.toList());

		if (sortBy.equals(lastSortBy)) {
			isReverse = !isReverse;
			if (isReverse) {
				Collections.reverse(sorted);
			}
		} else {
			lastSortBy = sortBy;
			isReverse = false;
		}

		sprints.clear();
		sprints.addAll(sorted);
	}


	private void doFilter(List<Sprint> sprints) {
		List<Sprint> filtered = sprints.stream()
				.filter(r -> r.name.toLowerCase().contains(filterBy) || r.description.toLowerCase().contains(filterBy)
						|| r.state.toString().toLowerCase().contains(filterBy) || DateService.getDateAsString(r.startDate).toLowerCase().contains(filterBy)
						|| DateService.getDateAsString(r.dueDate).toLowerCase().contains(filterBy) || String.valueOf(r.assignedEffort).contains(filterBy)
						|| String.valueOf(r.velocity).contains(filterBy))
				.collect(Collectors.toList());
		sprints.clear();
		sprints.addAll(filtered);
	}


	private String colorize(String tableString) {
		for (String header : Sprint.fieldsAsHeader) {
			tableString = tableString.replaceFirst(header, ColorCodes.BLUE + header + ColorCodes.RESET);
		}

		return tableString;
	}

}
