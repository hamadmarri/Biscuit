# Biscuit
Is a free command line interface tool for software project management. This project gives you the ability to follow Agaile approach. Current version (0.1.0) is Beta, and it has only the implementation for Scrum so far. Commands are designed to be intuitive and easy to remember. Your work will be saved as json files under your home directory (`$HOME$/biscuit/`).


# Features
* Autocompelete ability using TAB
* Coloured console output
* Dashboard view
* Adding, editing, and removing projects
* Adding and editing releases
* Adding and editing sprints
* Adding and editing user stories
* Adding and editing tasks
* Backlog view
* List releases, sprints, user stories, and tasks with filtering and sorting features
* Changing status of releases, sprints, user stories, and tasks (Status: created, open, planned, unplanned, in_progress, in_testing, done, overdue, removed)
* Planner view
* Print plan in short form as a tree, or in details in a table
* Moving user stories to sprints
* Moving sprints to releases
* Unplan sprints, user stories, or unplan all



# Requirements
Java 1.8


# Installation
## Linux
```
cd ./biscuit/binaries
chmod +x ./setup.sh
./setup.sh
```
Following files will be created
```
/usr/local/bin/biscuit.jar
/usr/local/bin/biscuit
```

## Windows
no installation file yet  
run manually:  
```
cd ./biscuit/binaries
java -jar biscuit.jar
```



# Example
[Usage Example](https://github.com/hamadmarri/Biscuit/wiki/Usage-Example)


# Commands
[Commands](https://github.com/hamadmarri/Biscuit/wiki/Commands)


# Future work
* Time logging
* Team members
  * Groups
  * Roles
* Contacts
* Redo and Undo commands
* Labelling
* Attachments
* Alerts system
* Search in projects
* Print Reports
* Plotting graphs
  * Burn down chart
  * Process control? maybe?
  * cumulative flow
  * cycle time distribution? maybe?
* Enhance listing
  * list past, future, current, and closed release, sprints etc.. 
* Adding bugs and tests
* Print summary for project, release, sprints, ect..
* Auto plan where user specifies velocity, start date, due date, sprint duration, release duration, and priority first


# Credits
The following libraries are used in this project  
* [jline 2.12](https://github.com/jline/jline2)
* [asciitable 0.2.5](https://github.com/vdmeer/asciitable)
* [gson 2.8.0](https://github.com/google/gson)
* [ColorCodes.java](https://gist.github.com/nathan-fiscaletti/9dc252d30b51df7d710a)


# Donate
[![paypal](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=RZC5CWSCTMB8E)

