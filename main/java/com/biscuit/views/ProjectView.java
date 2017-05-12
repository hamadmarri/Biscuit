/*
	Author: Hamad Al Marri;
 */

package com.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.biscuit.commands.release.AddRelease;
import com.biscuit.commands.release.ListReleases;
import com.biscuit.commands.sprint.AddSprint;
import com.biscuit.commands.sprint.ListSprints;
import com.biscuit.commands.userStory.AddUserStoryToBacklog;
import com.biscuit.commands.userStory.ListUserStories;
import com.biscuit.factories.ProjectCompleterFactory;
import com.biscuit.models.Project;
import com.biscuit.models.Release;
import com.biscuit.models.Sprint;
import com.biscuit.models.services.Finder.Releases;
import com.biscuit.models.services.Finder.Sprints;

import jline.console.completer.Completer;

public class ProjectView extends View {

	Project project = null;


	public ProjectView(View previousView, Project p) {
		super(previousView, p.name);
		this.project = p;
	}


	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(ProjectCompleterFactory.getProjectCompleters(project));
	}


	@Override
	boolean executeCommand(String[] words) throws IOException {
		if (words.length == 1) {
			return execute1Keyword(words);
		} else if (words.length == 2) {
			return execute2Keywords(words);
		} else if (words.length == 3) {
			return execute3Keywords(words);
		} else if (words.length == 4) {
			return execute4Keywords(words);
		}
		return false;
	}


	private boolean execute4Keywords(String[] words) throws IOException {
		if (words[0].equals("show")) {
			if (words[1].equals("backlog")) {
				if (words[2].equals("filter")) {
					(new ListUserStories(project.backlog, "", true, words[3], false, "")).execute();
					return true;
				} else if (words[2].equals("sort")) {
					(new ListUserStories(project.backlog, "", false, "", true, words[3])).execute();
					return true;
				}
			}
		} else if (words[0].equals("list")) {
			if (words[1].equals("releases")) {
				if (words[2].equals("filter")) {
					(new ListReleases(project, "Releases", true, words[3], false, "")).execute();
					return true;
				} else if (words[2].equals("sort")) {
					(new ListReleases(project, "Releases", false, "", true, words[3])).execute();
					return true;
				}
			} else if (words[1].equals("sprints")) {
				if (words[2].equals("filter")) {
					(new ListSprints(project, "Sprints", true, words[3], false, "")).execute();
					return true;
				} else if (words[2].equals("sort")) {
					(new ListSprints(project, "Sprints", false, "", true, words[3])).execute();
					return true;
				}
			}
		}
		return false;
	}


	private boolean execute3Keywords(String[] words) {
		if (words[0].equals("go_to")) {
			if (words[1].equals("release")) {
				if (Releases.getAllNames(project).contains(words[2])) {
					Release r = Releases.find(project, words[2]);
					if (r == null) {
						return false;
					}

					r.project = project;

					ReleaseView rv = new ReleaseView(this, r);
					rv.view();
					return true;
				}
			} else if (words[1].equals("sprint")) {
				if (Sprints.getAllNames(project).contains(words[2])) {
					Sprint s = Sprints.find(project, words[2]);
					if (s == null) {
						return false;
					}

					s.project = project;

					SprintView sv = new SprintView(this, s);
					sv.view();
					return true;
				}
			}
		}
		return false;
	}


	private boolean execute2Keywords(String[] words) throws IOException {
		if (words[0].equals("add")) {
			if (words[1].equals("user_story")) {
				(new AddUserStoryToBacklog(reader, project)).execute();
				return true;
			} else if (words[1].equals("release")) {
				(new AddRelease(reader, project)).execute();
				resetCompleters();

				return true;
			} else if (words[1].equals("sprint")) {
				(new AddSprint(reader, project)).execute();
				resetCompleters();

				return true;
			}

		} else if (words[0].equals("go_to")) {
			if (words[1].equals("backlog")) {
				this.project.backlog.project = this.project;
				BacklogView bv = new BacklogView(this, this.project.backlog);
				bv.view();
				return true;
			} else if (words[1].equals("releases")) {

				ReleasesView rsv = new ReleasesView(this, project);
				rsv.view();

				return true;
			} else if (words[1].equals("sprints")) {

				SprintsView ssv = new SprintsView(this, project);
				ssv.view();

				return true;
			} else if (words[1].equals("planner")) {

				PlannerView pv = new PlannerView(this, project);
				pv.view();

				return true;
			}
		} else if (words[0].equals("show")) {
			if (words[1].equals("backlog")) {
				(new ListUserStories(project.backlog, "Backlog (User Stories)")).execute();
				return true;
			}
		} else if (words[0].equals("list")) {
			if (words[1].equals("releases")) {
				(new ListReleases(project, "Releases")).execute();
				return true;
			} else if (words[1].equals("sprints")) {
				(new ListSprints(project, "Sprints")).execute();
				return true;
			}
		}

		return false;
	}


	private boolean execute1Keyword(String[] words) throws IOException {
		if (words[0].equals("info")) {
			reader.println(project.toString());
			return true;
		} else if (words[0].equals("backlog")) {
			(new ListUserStories(project.backlog, "Backlog (User Stories)")).execute();
			return true;
		} else if (words[0].equals("releases")) {
			(new ListReleases(project, "Releases")).execute();
			return true;
		} else if (words[0].equals("sprints")) {
			(new ListSprints(project, "Sprints")).execute();
			return true;
		}

		return false;
	}

}
