package com.biscuit.models.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.biscuit.models.Backlog;
import com.biscuit.models.Dashboard;
import com.biscuit.models.Project;
import com.biscuit.models.Release;
import com.biscuit.models.Sprint;
import com.biscuit.models.Task;
import com.biscuit.models.UserStory;

public class Finder {

	public static class Projects {

		public static Project getProject(String projectName) {
			Project p = null;

			if (Dashboard.getInstance().projectsNames.contains(projectName)) {
				p = Project.load(projectName);
				p.updateChildrenReferences();
			}

			return p;
		}
	}

	public static class UserStories {

		public static List<UserStory> getAll(Project p) {
			List<UserStory> all = new ArrayList<>();
			all.addAll(getAll(p.backlog)); // unplanned
			all.addAll(getPlanned(p));
			return all;
		}


		public static List<UserStory> getAll(Backlog backlog) {
			return backlog.userStories;
		}


		public static List<UserStory> getPlanned(Project p) {
			List<UserStory> userStories = new ArrayList<>();
			List<Sprint> plannedSprints;

			// go thru unplanned sprints
			for (Sprint s : p.sprints) {
				userStories.addAll(s.userStories);
			}

			// also check planned sprints
			plannedSprints = Sprints.getPlanned(p);
			for (Sprint s : plannedSprints) {
				userStories.addAll(s.userStories);
			}

			return userStories;
		}


		public static List<String> getAllNames(Project p) {
			List<String> all = new ArrayList<>();
			all.addAll(getAllNames(p.backlog));
			all.addAll(getPlannedNames(p));
			return all;
		}


		public static List<String> getAllNames(Backlog backlog) {
			return backlog.userStories.stream().map(us -> us.title).collect(Collectors.toList());
		}


		public static List<String> getAllNames(Sprint sprint) {
			return sprint.userStories.stream().map(us -> us.title).collect(Collectors.toList());
		}


		public static UserStory find(Project p, String title) {
			return getAll(p).stream().filter(us -> us.title.equals(title)).findAny().orElse(null);
		}


		public static UserStory find(Backlog backlog, String title) {
			return backlog.userStories.stream().filter(us -> us.title.equals(title)).findAny().orElse(null);
		}


		public static UserStory find(Sprint sprint, String title) {
			return sprint.userStories.stream().filter(us -> us.title.equals(title)).findAny().orElse(null);
		}


		public static List<String> getPlannedNames(Project p) {
			List<String> userStoriesNames = new ArrayList<>();
			List<Sprint> plannedSprints;

			// go thru unplanned sprints
			for (Sprint s : p.sprints) {
				userStoriesNames.addAll(s.userStories.stream().map(us -> us.title).collect(Collectors.toList()));
			}

			// also check planned sprints
			plannedSprints = Sprints.getPlanned(p);
			for (Sprint s : plannedSprints) {
				userStoriesNames.addAll(s.userStories.stream().map(us -> us.title).collect(Collectors.toList()));
			}

			return userStoriesNames;
		}
	}

	public static class Releases {

		public static List<String> getAllNames(Project p) {
			return p.releases.stream().map(r -> r.name).collect(Collectors.toList());
		}


		public static Release find(Project p, String name) {
			return p.releases.stream().filter(r -> r.name.equals(name)).findAny().orElse(null);
		}


		public static Release findContains(Project p, String sprintName) {
			for (Release r : p.releases) {
				for (Sprint s : r.sprints) {
					if (s.name.equals(sprintName)) {
						return r;
					}
				}
			}

			return null;
		}
	}

	public static class Sprints {

		public static List<Sprint> getAll(Project p) {
			List<Sprint> sprints = new ArrayList<>();

			sprints.addAll(getUnplanned(p));
			sprints.addAll(getPlanned(p));

			return sprints;
		}


		public static List<Sprint> getUnplanned(Project p) {
			return p.sprints;
		}


		public static List<Sprint> getPlanned(Project p) {
			List<Sprint> sprints = new ArrayList<>();

			for (Release r : p.releases) {
				sprints.addAll(r.sprints);
			}

			return sprints;
		}


		public static List<String> getAllNames(Project p) {
			List<String> all = new ArrayList<>();
			all.addAll(getUnplannedNames(p));
			all.addAll(getPlannedNames(p));
			return all;
		}


		public static List<String> getUnplannedNames(Project p) {
			return p.sprints.stream().map(s -> s.name).collect(Collectors.toList());
		}


		public static List<String> getPlannedNames(Project p) {
			List<String> sprintsNames = new ArrayList<>();

			for (Release r : p.releases) {
				sprintsNames.addAll(r.sprints.stream().map(s -> s.name).collect(Collectors.toList()));
			}

			return sprintsNames;
		}


		public static List<String> getAllNames(Release release) {
			return release.sprints.stream().map(s -> s.name).collect(Collectors.toList());
		}


		public static Sprint find(Project p, String name) {
			return getAll(p).stream().filter(s -> s.name.equals(name)).findAny().orElse(null);
		}


		public static Sprint find(Release r, String name) {
			return r.sprints.stream().filter(s -> s.name.equals(name)).findAny().orElse(null);
		}


		public static Sprint findContains(Project p, String userStoryName) {

			// check generic sprints in p list
			for (Sprint s : p.sprints) {
				for (UserStory us : s.userStories) {
					if (us.title.equals(userStoryName)) {
						return s;
					}
				}
			}

			// if not found yet, then check sprints in releases
			for (Release r : p.releases) {
				for (Sprint s : r.sprints) {
					for (UserStory us : s.userStories) {
						if (us.title.equals(userStoryName)) {
							return s;
						}
					}
				}
			}

			return null;
		}

	}

	public static class Tasks {

		public static List<String> getAllNames(UserStory us) {
			return us.tasks.stream().map(t -> t.title).collect(Collectors.toList());
		}


		public static Task find(UserStory us, String title) {
			return us.tasks.stream().filter(t -> t.title.equals(title)).findAny().orElse(null);
		}
	}

}
