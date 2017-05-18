package com.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.biscuit.commands.help.SprintHelp;
import com.biscuit.commands.sprint.ChangeStatusSprint;
import com.biscuit.commands.sprint.EditSprint;
import com.biscuit.commands.sprint.ShowSprint;
import com.biscuit.commands.userStory.AddUserStoryToSprint;
import com.biscuit.commands.userStory.ListUserStories;
import com.biscuit.factories.SprintCompleterFactory;
import com.biscuit.models.Sprint;
import com.biscuit.models.UserStory;
import com.biscuit.models.enums.Status;
import com.biscuit.models.services.Finder.UserStories;

import jline.console.completer.Completer;

public class SprintView extends View {

	Sprint sprint = null;


	public SprintView(View previousView, Sprint sprint) {
		super(previousView, sprint.name);
		this.sprint = sprint;
	}


	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(SprintCompleterFactory.getSprintCompleters(sprint));
	}


	@Override
	boolean executeCommand(String[] words) throws IOException {
		if (words.length == 1) {
			return execute1Keyword(words);
		} else if (words.length == 2) {
			return execute2Keywords(words);
		} else if (words.length == 4) {
			return execute4Keyword(words);
		}

		return false;
	}


	private boolean execute4Keyword(String[] words) throws IOException {
		if (words[0].equals("list")) {
			if (words[1].equals("user_stories")) {
				if (words[2].equals("filter")) {
					(new ListUserStories(sprint, sprint.name + " (User Stories)", true, words[3], false, "")).execute();
					return true;
				} else if (words[2].equals("sort")) {
					(new ListUserStories(sprint, sprint.name + " (User Stories)", false, "", true, words[3])).execute();
					return true;
				}
			}
		}

		return false;
	}


	private boolean execute2Keywords(String[] words) throws IOException {
		if (words[0].equals("change_status_to")) {
			if (Status.values.contains(words[1])) {
				(new ChangeStatusSprint(sprint, Status.valueOf(words[1].toUpperCase()))).execute();
				return true;
			}
		} else if (words[0].equals("list")) {
			if (words[1].equals("user_stories")) {
				(new ListUserStories(sprint, sprint.name + " (User Stories)")).execute();
				return true;
			}
		} else if (words[0].equals("add")) {
			if (words[1].equals("user_story")) {
				(new AddUserStoryToSprint(reader, sprint)).execute();
				resetCompleters();

				return true;
			}
		} else if (words[0].equals("go_to")) {
			if (UserStories.getAllNames(sprint).contains(words[1])) {
				UserStory us = UserStories.find(sprint, words[1]);
				if (us == null) {
					return false;
				}

				UserStroryView usv = new UserStroryView(this, us);
				usv.view();
				return true;
			}
		}
		return false;
	}


	private boolean execute1Keyword(String[] words) throws IOException {
		if (words[0].equals("show")) {
			(new ShowSprint(sprint)).execute();
			return true;
		} else if (words[0].equals("edit")) {
			(new EditSprint(reader, sprint)).execute();
			this.name = sprint.name;
			updatePromptViews();

			return true;
		} else if (words[0].equals("user_stories")) {
			(new ListUserStories(sprint, sprint.name + " (User Stories)")).execute();
			return true;
		} else if (words[0].equals("help")) {
			return (new SprintHelp()).execute();
		}
		return false;
	}

}
