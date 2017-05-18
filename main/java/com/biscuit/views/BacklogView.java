package com.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.biscuit.commands.help.BacklogHelp;
import com.biscuit.commands.userStory.AddUserStoryToBacklog;
import com.biscuit.commands.userStory.ListUserStories;
import com.biscuit.factories.BacklogCompleterFactory;
import com.biscuit.models.Backlog;
import com.biscuit.models.UserStory;
import com.biscuit.models.services.Finder.UserStories;

import jline.console.completer.Completer;

public class BacklogView extends View {

	Backlog backlog = null;


	public BacklogView(View previousView, Backlog backlog) {
		super(previousView, "backlog");
		this.backlog = backlog;
	}


	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(BacklogCompleterFactory.getBacklogCompleters(backlog));
	}


	@Override
	boolean executeCommand(String[] words) throws IOException {
		if (words.length == 1) {
			return execute1Keyword(words);
		} else if (words.length == 2) {
			return execute2Keyword(words);
		} else if (words.length == 4) {
			return execute4Keyword(words);
		}

		return false;
	}


	private boolean execute4Keyword(String[] words) throws IOException {
		if (words[0].equals("list")) {
			if (words[1].equals("user_stories")) {
				if (words[2].equals("filter")) {
					(new ListUserStories(backlog, "Backlog (User Stories)", true, words[3], false, "")).execute();
					return true;
				} else if (words[2].equals("sort")) {
					(new ListUserStories(backlog, "Backlog (User Stories)", false, "", true, words[3])).execute();
					return true;
				}
			}
		}

		return false;
	}


	private boolean execute2Keyword(String[] words) throws IOException {
		if (words[0].equals("add")) {
			if (words[1].equals("user_story")) {
				(new AddUserStoryToBacklog(reader, this.backlog.project)).execute();
				resetCompleters();

				return true;
			}
		} else if (words[0].equals("list")) {
			if (words[1].equals("user_stories")) {
				(new ListUserStories(backlog, "Backlog (User Stories)")).execute();
				return true;
			}
		} else if (words[0].equals("go_to")) {
			if (UserStories.getAllNames(backlog).contains(words[1])) {
				UserStory us = UserStories.find(backlog, words[1]);
				if (us == null) {
					return false;
				}

				us.project = backlog.project;

				UserStroryView usv = new UserStroryView(this, us);
				usv.view();
				return true;
			}
		}

		return false;
	}


	private boolean execute1Keyword(String[] words) throws IOException {
		if (words[0].equals("user_stories")) {
			(new ListUserStories(backlog, "Backlog (User Stories)")).execute();
			return true;
		} else if (words[0].equals("help")) {
			return (new BacklogHelp()).execute();
		}

		return false;
	}

}
