package com.biscuit.commands.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Task;
import com.biscuit.models.UserStory;
import com.biscuit.models.services.DateService;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestLine;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

public class ListTasks implements Command {

	UserStory userStory = null;
	String title = "";
	boolean isFilter = false;
	boolean isSort = false;
	static boolean isReverse = false;
	private String filterBy;
	private String sortBy;
	private static String lastSortBy = "";


	public ListTasks(UserStory userStory, String title) {
		super();
		this.userStory = userStory;
		this.title = title;
	}


	public ListTasks(UserStory userStory, String title, boolean isFilter, String filterBy, boolean isSort, String sortBy) {
		super();
		this.userStory = userStory;
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

		List<Task> tasks = new ArrayList<>();
		tasks.addAll(userStory.tasks);

		if (isFilter) {
			doFilter(tasks);
		}

		if (isSort) {
			doSort(tasks);
		}

		at.addRule();
		if (!this.title.isEmpty()) {
			at.addRow(null, null, null, null, null, null, this.title).setAlignment(new char[] { 'c', 'c', 'c', 'c', 'c', 'c', 'c' });
			at.addRule();
		}
		at.addRow("Title", "Description", "State", "Initiated Date", "Planned Date", "Due Date", "Estimated Time")
				.setAlignment(new char[] { 'l', 'l', 'c', 'c', 'c', 'c', 'c' });

		if (tasks.size() == 0) {
			String message;
			if (!isFilter) {
				message = "There are no tasks!";
			} else {
				message = "No results";
			}
			at.addRule();
			at.addRow(null, null, null, null, null, null, message);
		} else {
			for (Task t : tasks) {
				at.addRule();

				at.addRow(t.title, t.description, t.state, DateService.getDateAsString(t.initiatedDate), DateService.getDateAsString(t.plannedDate),
						DateService.getDateAsString(t.dueDate), t.estimatedTime).setAlignment(new char[] { 'l', 'l', 'c', 'c', 'c', 'c', 'c' });
			} // for
		}

		at.addRule();
		at.addRow(null, null, null, null, null, null, "Total: " + tasks.size());
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


	private void doSort(List<Task> tasks) {

		Comparator<Task> byTitle = (t1, t2) -> t1.title.compareTo(t2.title);
		Comparator<Task> byDescription = (t1, t2) -> t1.description.compareTo(t2.description);
		Comparator<Task> byState = (t1, t2) -> Integer.compare(t1.state.getValue(), t2.state.getValue());
		Comparator<Task> byInitiatedDate = (t1, t2) -> t1.initiatedDate.compareTo(t2.initiatedDate);
		Comparator<Task> byPlannedDate = (t1, t2) -> t1.plannedDate.compareTo(t2.plannedDate);
		Comparator<Task> byDueDate = (t1, t2) -> t1.dueDate.compareTo(t2.dueDate);
		Comparator<Task> byTime = (t1, t2) -> Float.compare(t1.estimatedTime, t2.estimatedTime);
		Comparator<Task> byFiled = null;

		if (sortBy.equals(Task.fields[0])) {
			byFiled = byTitle;
		} else if (sortBy.equals(Task.fields[1])) {
			byFiled = byDescription;
		} else if (sortBy.equals(Task.fields[2])) {
			byFiled = byState;
		} else if (sortBy.equals(Task.fields[3])) {
			byFiled = byInitiatedDate;
		} else if (sortBy.equals(Task.fields[4])) {
			byFiled = byPlannedDate;
		} else if (sortBy.equals(Task.fields[5])) {
			byFiled = byDueDate;
		} else if (sortBy.equals(Task.fields[6])) {
			byFiled = byTime;
		} else {
			return;
		}

		List<Task> sorted = tasks.stream().sorted(byFiled).collect(Collectors.toList());

		if (sortBy.equals(lastSortBy)) {
			isReverse = !isReverse;
			if (isReverse) {
				Collections.reverse(sorted);
			}
		} else {
			lastSortBy = sortBy;
			isReverse = false;
		}

		tasks.clear();
		tasks.addAll(sorted);
	}


	private void doFilter(List<Task> tasks) {
		List<Task> filtered = tasks.stream()
				.filter(us -> us.title.toLowerCase().contains(filterBy) || us.description.toLowerCase().contains(filterBy)
						|| us.state.toString().toLowerCase().contains(filterBy) || String.valueOf(us.estimatedTime).contains(filterBy)
						|| DateService.getDateAsString(us.initiatedDate).toLowerCase().contains(filterBy)
						|| DateService.getDateAsString(us.plannedDate).toLowerCase().contains(filterBy)
						|| DateService.getDateAsString(us.dueDate).toLowerCase().contains(filterBy))
				.collect(Collectors.toList());
		tasks.clear();
		tasks.addAll(filtered);
	}


	private String colorize(String tableString) {
		tableString = tableString.replaceFirst("Title", ColorCodes.BLUE + "Title" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Description", ColorCodes.BLUE + "Description" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("State", ColorCodes.BLUE + "State" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Initiated Date", ColorCodes.BLUE + "Initiated Date" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Planned Date", ColorCodes.BLUE + "Planned Date" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Due Date", ColorCodes.BLUE + "Due Date" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Estimated Time", ColorCodes.BLUE + "Estimated Time" + ColorCodes.RESET);

		return tableString;
		// return tableString.replaceAll("MUST_HAVE", ColorCodes.YELLOW +
		// "MUST_HAVE" + ColorCodes.RESET);
	}

}
