package com.biscuit.commands.release;

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
import com.biscuit.models.services.DateService;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestLine;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

public class ListReleases implements Command {

	Project project = null;
	String title = "";
	boolean isFilter = false;
	boolean isSort = false;
	static boolean isReverse = false;
	private String filterBy;
	private String sortBy;
	private static String lastSortBy = "";


	public ListReleases(Project project, String title) {
		super();
		this.project = project;
		this.title = title;
	}


	public ListReleases(Project porject, String title, boolean isFilter, String filterBy, boolean isSort, String sortBy) {
		super();
		this.project = porject;
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

		List<Release> releases = new ArrayList<>();
		releases.addAll(project.releases);

		if (isFilter) {
			doFilter(releases);
		}

		if (isSort) {
			doSort(releases);
		}

		at.addRule();
		if (!this.title.isEmpty()) {
			at.addRow(null, null, null, null, null, this.title).setAlignment(new char[] { 'c', 'c', 'c', 'c', 'c', 'c' });
			at.addRule();
		}
		at.addRow("Name", "Description", "State", "Start Date", "Due Date", "Assigned Effort").setAlignment(new char[] { 'l', 'l', 'c', 'c', 'c', 'c' });

		if (releases.size() == 0) {
			String message;
			if (!isFilter) {
				message = "There are no releases!";
			} else {
				message = "No results";
			}
			at.addRule();
			at.addRow(null, null, null, null, null, message);
		} else {
			for (Release r : releases) {
				at.addRule();

				at.addRow(r.name, r.description, r.state, DateService.getDateAsString(r.startDate), DateService.getDateAsString(r.dueDate), r.assignedEffort)
						.setAlignment(new char[] { 'l', 'l', 'c', 'c', 'c', 'c' });
			} // for
		}

		at.addRule();
		at.addRow(null, null, null, null, null, "Total: " + releases.size());
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


	private void doSort(List<Release> releases) {

		Comparator<Release> byName = (r1, r2) -> r1.name.compareTo(r2.name);
		Comparator<Release> byDescription = (r1, r2) -> r1.description.compareTo(r2.description);
		Comparator<Release> byState = (r1, r2) -> Integer.compare(r1.state.getValue(), r2.state.getValue());
		Comparator<Release> byStartDate = (r1, r2) -> r1.startDate.compareTo(r2.startDate);
		Comparator<Release> byDueDate = (r1, r2) -> r1.dueDate.compareTo(r2.dueDate);
		Comparator<Release> byAssignedEffort = (r1, r2) -> Integer.compare(r1.assignedEffort, r2.assignedEffort);
		Comparator<Release> byFiled = null;

		if (sortBy.equals(Release.fields[0])) {
			byFiled = byName;
		} else if (sortBy.equals(Release.fields[1])) {
			byFiled = byDescription;
		} else if (sortBy.equals(Release.fields[2])) {
			byFiled = byState;
		} else if (sortBy.equals(Release.fields[3])) {
			byFiled = byStartDate;
		} else if (sortBy.equals(Release.fields[4])) {
			byFiled = byDueDate;
		} else if (sortBy.equals(Release.fields[5])) {
			byFiled = byAssignedEffort;
		} else {
			return;
		}

		List<Release> sorted = releases.stream().sorted(byFiled).collect(Collectors.toList());

		if (sortBy.equals(lastSortBy)) {
			isReverse = !isReverse;
			if (isReverse) {
				Collections.reverse(sorted);
			}
		} else {
			lastSortBy = sortBy;
			isReverse = false;
		}

		releases.clear();
		releases.addAll(sorted);
	}


	private void doFilter(List<Release> releases) {
		List<Release> filtered = releases.stream()
				.filter(r -> r.name.toLowerCase().contains(filterBy) || r.description.toLowerCase().contains(filterBy)
						|| r.state.toString().toLowerCase().contains(filterBy) || DateService.getDateAsString(r.startDate).toLowerCase().contains(filterBy)
						|| DateService.getDateAsString(r.dueDate).toLowerCase().contains(filterBy) || String.valueOf(r.assignedEffort).contains(filterBy))
				.collect(Collectors.toList());
		releases.clear();
		releases.addAll(filtered);
	}


	private String colorize(String tableString) {
		for (String header : Release.fieldsAsHeader) {
			tableString = tableString.replaceFirst(header, ColorCodes.BLUE + header + ColorCodes.RESET);
		}

		return tableString;
	}

}
