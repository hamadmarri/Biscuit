package com.hamad.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.hamad.biscuit.commands.userstory.ChangeStatusUserStory;
import com.hamad.biscuit.commands.userstory.EditUserStory;
import com.hamad.biscuit.commands.userstory.ShowUserStory;
import com.hamad.biscuit.factories.UserStoryCompleterFactory;
import com.hamad.biscuit.models.UserStory;
import com.hamad.biscuit.models.enums.State;

import jline.console.completer.Completer;

public class UserStroryView extends View {

	UserStory userStory = null;


	public UserStroryView(View previousView, UserStory userStory) {
		super(previousView, userStory.title);
		this.userStory = userStory;
	}


	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(UserStoryCompleterFactory.getUserStoryCompleters(userStory));
	}


	@Override
	boolean executeCommand(String[] words) throws IOException {
		if (words.length == 1) {
			return execute1Keyword(words);
		} else if (words.length == 2) {
			return execute2Keywords(words);
		}
		return false;
	}


	private boolean execute2Keywords(String[] words) throws IOException {
		if (words[0].equals("change_status_to")) {
			if (State.values.contains(words[1])) {
				(new ChangeStatusUserStory(userStory, State.valueOf(words[1].toUpperCase()))).execute();
				return true;
			}
		}

		return false;
	}


	private boolean execute1Keyword(String[] words) throws IOException {
		if (words[0].equals("show")) {
			(new ShowUserStory(userStory)).execute();
			return true;
		} else if (words[0].equals("edit")) {
			(new EditUserStory(reader, userStory)).execute();
			this.name = userStory.title;
			updatePromptViews();

			return true;
		}

		return false;
	}

}
