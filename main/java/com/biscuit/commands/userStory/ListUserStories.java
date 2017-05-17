package com.biscuit.commands.userStory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Backlog;
import com.biscuit.models.Sprint;
import com.biscuit.models.UserStory;
import com.biscuit.models.services.DateService;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestLine;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

public class ListUserStories implements Command {

	Backlog backlog = null;
	Sprint sprint = null;
	List<UserStory> userStories = null;
	String title = "";
	boolean isFilter = false;
	boolean isSort = false;
	static boolean isReverse = false;
	private String filterBy;
	private String sortBy;
	private static String lastSortBy = "";


	public ListUserStories(Backlog backlog, String title) {
		super();
		this.backlog = backlog;
		this.title = title;
	}


	public ListUserStories(Sprint sprint, String title) {
		super();
		this.sprint = sprint;
		this.title = title;
	}


	public ListUserStories(List<UserStory> userStories, String title) {
		super();
		this.userStories = userStories;
		this.title = title;
	}


	public ListUserStories(Backlog backlog, String title, boolean isFilter, String filterBy, boolean isSort, String sortBy) {
		super();
		this.backlog = backlog;
		this.title = title;
		this.isFilter = isFilter;
		this.filterBy = filterBy.toLowerCase();
		this.isSort = isSort;
		this.sortBy = sortBy.toLowerCase();
	}


	public ListUserStories(Sprint sprint, String title, boolean isFilter, String filterBy, boolean isSort, String sortBy) {
		super();
		this.sprint = sprint;
		this.title = title;
		this.isFilter = isFilter;
		this.filterBy = filterBy.toLowerCase();
		this.isSort = isSort;
		this.sortBy = sortBy.toLowerCase();
	}


	public ListUserStories(List<UserStory> userStories, String title, boolean isFilter, String filterBy, boolean isSort, String sortBy) {
		super();
		this.userStories = userStories;
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

		List<UserStory> userStories = new ArrayList<>();

		if (backlog != null) {
			userStories.addAll(backlog.userStories);
		} else if (sprint != null) {
			userStories.addAll(sprint.userStories);
		} else if (this.userStories != null) {
			userStories = this.userStories;
		} else {
			System.err.println("error: backlog, sprint, and userStories are null");
			return false;
		}

		if (isFilter) {
			doFilter(userStories);
		}

		if (isSort) {
			doSort(userStories);
		}

		at.addRule();
		if (!this.title.isEmpty()) {
			at.addRow(null, null, null, null, null, null, null, null, this.title).setAlignment(new char[] { 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c' });
			at.addRule();
		}
		at.addRow("Title", "Description", "State", "Business Value", "Initiated Date", "Planned Date", "Due Date", "Tasks #", "Points")
				.setAlignment(new char[] { 'l', 'l', 'c', 'c', 'c', 'c', 'c', 'c', 'c' });

		if (userStories.size() == 0) {
			String message;
			if (!isFilter) {
				message = "There are no user stories!";
			} else {
				message = "No results";
			}
			at.addRule();
			at.addRow(null, null, null, null, null, null, null, null, message);
		} else {
			for (UserStory us : userStories) {
				at.addRule();

				at.addRow(us.title, us.description, us.state, us.businessValue, DateService.getDateAsString(us.initiatedDate),
						DateService.getDateAsString(us.plannedDate), DateService.getDateAsString(us.dueDate), us.tasks.size(), us.points)
						.setAlignment(new char[] { 'l', 'l', 'c', 'c', 'c', 'c', 'c', 'c', 'c' });
			} // for
		}

		at.addRule();
		at.addRow(null, null, null, null, null, null, null, null, "Total: " + userStories.size());
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


	private void doSort(List<UserStory> userStories) {

		Comparator<UserStory> byTitle = (us1, us2) -> us1.title.compareTo(us2.title);
		Comparator<UserStory> byDescription = (us1, us2) -> us1.description.compareTo(us2.description);
		Comparator<UserStory> byState = (us1, us2) -> Integer.compare(us1.state.getValue(), us2.state.getValue());
		Comparator<UserStory> byBusinessValue = (us1, us2) -> Integer.compare(us1.businessValue.getValue(), us2.businessValue.getValue());
		Comparator<UserStory> byInitiatedDate = (us1, us2) -> us1.initiatedDate.compareTo(us2.initiatedDate);
		Comparator<UserStory> byPlannedDate = (us1, us2) -> us1.plannedDate.compareTo(us2.plannedDate);
		Comparator<UserStory> byDueDate = (us1, us2) -> us1.dueDate.compareTo(us2.dueDate);
		Comparator<UserStory> byTasks = (us1, us2) -> Integer.compare(us1.tasks.size(), us2.tasks.size());
		Comparator<UserStory> byPoints = (us1, us2) -> Integer.compare(us1.points, us2.points);
		Comparator<UserStory> byFiled = null;

		if (sortBy.equals(UserStory.fields[0])) {
			byFiled = byTitle;
		} else if (sortBy.equals(UserStory.fields[1])) {
			byFiled = byDescription;
		} else if (sortBy.equals(UserStory.fields[2])) {
			byFiled = byState;
		} else if (sortBy.equals(UserStory.fields[3])) {
			byFiled = byBusinessValue;
		} else if (sortBy.equals(UserStory.fields[4])) {
			byFiled = byInitiatedDate;
		} else if (sortBy.equals(UserStory.fields[5])) {
			byFiled = byPlannedDate;
		} else if (sortBy.equals(UserStory.fields[6])) {
			byFiled = byDueDate;
		} else if (sortBy.equals(UserStory.fields[7])) {
			byFiled = byTasks;
		} else if (sortBy.equals(UserStory.fields[8])) {
			byFiled = byPoints;
		} else {
			return;
		}

		List<UserStory> sorted = userStories.stream().sorted(byFiled).collect(Collectors.toList());

		if (sortBy.equals(lastSortBy)) {
			isReverse = !isReverse;
			if (isReverse) {
				Collections.reverse(sorted);
			}
		} else {
			lastSortBy = sortBy;
			isReverse = false;
		}

		userStories.clear();
		userStories.addAll(sorted);
	}


	private void doFilter(List<UserStory> userStories) {
		List<UserStory> filtered = userStories.stream()
				.filter(us -> us.title.toLowerCase().contains(filterBy) || us.description.toLowerCase().contains(filterBy)
						|| us.state.toString().toLowerCase().contains(filterBy) || us.businessValue.toString().toLowerCase().contains(filterBy)
						|| String.valueOf(us.points).contains(filterBy) || DateService.getDateAsString(us.initiatedDate).toLowerCase().contains(filterBy)
						|| DateService.getDateAsString(us.plannedDate).toLowerCase().contains(filterBy)
						|| DateService.getDateAsString(us.dueDate).toLowerCase().contains(filterBy))
				.collect(Collectors.toList());
		userStories.clear();
		userStories.addAll(filtered);
	}


	private String colorize(String tableString) {
		tableString = tableString.replaceFirst("Title", ColorCodes.BLUE + "Title" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Description", ColorCodes.BLUE + "Description" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("State", ColorCodes.BLUE + "State" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Business Value", ColorCodes.BLUE + "Business Value" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Initiated Date", ColorCodes.BLUE + "Initiated Date" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Planned Date", ColorCodes.BLUE + "Planned Date" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Due Date", ColorCodes.BLUE + "Due Date" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Tasks #", ColorCodes.BLUE + "Tasks #" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Points", ColorCodes.BLUE + "Points" + ColorCodes.RESET);
		return tableString.replaceAll("MUST_HAVE", ColorCodes.YELLOW + "MUST_HAVE" + ColorCodes.RESET);
	}

}
