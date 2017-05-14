package com.biscuit.commands.planner;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Project;
import com.biscuit.models.Release;
import com.biscuit.models.Sprint;
import com.biscuit.models.UserStory;

public class ShowPlan implements Command {

	Project project;


	public ShowPlan(Project project) {
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
		System.out.println("Backlog");
		for (Iterator<UserStory> usItr = project.backlog.userStories.iterator(); usItr.hasNext();) {
			UserStory us = usItr.next();
			boolean isLastUserStory = !usItr.hasNext();
			printUserStoryTree(us, isLastUserStory, " ");
		}

		System.out.println();
		System.out.println("Sprints");
		for (Iterator<Sprint> sItr = project.sprints.iterator(); sItr.hasNext();) {
			Sprint s = sItr.next();
			boolean isLastSprint = (!sItr.hasNext() && s.userStories.isEmpty());
			printSprintTree(s, isLastSprint, " ");
		}
	}


	private void printPlanned() {
		Comparator<Release> byStartDate = (r1, r2) -> r1.startDate.compareTo(r2.startDate);
		List<Release> sortedByStartDate = project.releases.stream().sorted(byStartDate).collect(Collectors.toList());

		System.out.println("########## PLAN ##########");
		System.out.println(project.name);
		for (Iterator<Release> rItr = sortedByStartDate.iterator(); rItr.hasNext();) {
			Release r = rItr.next();
			boolean isLastRelease = (!rItr.hasNext() && r.sprints.isEmpty());
			printReleaseTree(r, isLastRelease, " ");
		}
	}


	private void printReleaseTree(Release r, boolean isLastRelease, String indent) {

		Comparator<Sprint> byStartDate = (s1, s2) -> s1.startDate.compareTo(s2.startDate);
		List<Sprint> sortedByStartDate = r.sprints.stream().sorted(byStartDate).collect(Collectors.toList());

		// print release name
		if (isLastRelease) {
			System.out.println(indent + "└ " + ColorCodes.PURPLE + r.name + ColorCodes.RESET);
		} else {
			System.out.println(indent + "├ " + ColorCodes.PURPLE + r.name + ColorCodes.RESET);
		}

		for (Iterator<Sprint> sItr = sortedByStartDate.iterator(); sItr.hasNext();) {
			Sprint s = sItr.next();
			boolean isLastSprint = (!sItr.hasNext() && s.userStories.isEmpty());
			printSprintTree(s, isLastSprint, indent + "| ");
		}
	}


	private void printSprintTree(Sprint s, boolean isLastSprint, String indent) {

		Comparator<UserStory> byPlannedDate = (us1, us2) -> us1.plannedDate.compareTo(us2.plannedDate);
		List<UserStory> sortedByPlannedDate = s.userStories.stream().sorted(byPlannedDate).collect(Collectors.toList());

		// print sprint name
		if (isLastSprint) {
			System.out.println(indent + "└ " + ColorCodes.GREEN + s.name + ColorCodes.RESET);
		} else {
			System.out.println(indent + "├ " + ColorCodes.GREEN + s.name + ColorCodes.RESET);
		}

		for (Iterator<UserStory> usItr = sortedByPlannedDate.iterator(); usItr.hasNext();) {
			UserStory us = usItr.next();
			boolean isLastUserStory = !usItr.hasNext();
			printUserStoryTree(us, isLastUserStory, indent + "| ");
		}
	}


	private void printUserStoryTree(UserStory us, boolean isLastUserStory, String indent) {

		// print user story name
		if (isLastUserStory) {
			System.out.println(indent + "└ " + ColorCodes.YELLOW + us.title + ColorCodes.RESET);
		} else {
			System.out.println(indent + "├ " + ColorCodes.YELLOW + us.title + ColorCodes.RESET);
		}
	}

}
