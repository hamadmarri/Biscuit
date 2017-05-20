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
to run:  
`biscuit`  
or run manually:  
`java -jar biscuit.jar`  

Biscuit will create a folder under your home directory named _biscuit_.  
* dashboard.json file will be created as the root file that has all projects information under _biscuit_ folder
* for each new project, _projectName_.json file will be created as well under _biscuit_ folder


```
Dashboard~$ add project 
project name: firstProject

description: 
(\q to end writing)
That is my first project
\q

Project "firstProject" has been added!
Dashboard~$ go_to 
go_to    
Dashboard~$ go_to project firstProject 
Dashboard>firstProject~$ go_to 
go_to    
Dashboard>firstProject~$ go_to backlog 
Dashboard>firstProject>backlog~$ add user_story 
title: us1

description:
(\q to end writing)
as developer, I have to show a demonstration
\q

business value:
(hit Tab to see valid values)

average        good           great          must_have      nice_to_have   

business value:
(hit Tab to see valid values)
must_have 

points:
(hit Tab to see an example)

0    1    13   2    21   3    5    8    

points:
(hit Tab to see an example)
5

User Story "us1" has been added to the backlog!
Dashboard>firstProject>backlog~$ add user_story 
title: us2

description:
(\q to end writing)
another one
\q

business value:
(hit Tab to see valid values)
great 

points:
(hit Tab to see an example)
2

User Story "us2" has been added to the backlog!
Dashboard>firstProject>backlog~$ list user_stories 
user_stories    
Dashboard>firstProject>backlog~$ list user_stories 

+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|                                                                     Backlog (User Stories)                                                                     |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------------+
| Title | Description                                  | State | Business Value | Initiated Date | Planned Date |  Due Date   | Tasks # |         Points         |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------------+
| us1   | as developer, I have to show a demonstration | OPEN  |   MUST_HAVE    | May. 19, 2017  | Not set yet  | Not set yet |    0    |           5            |
|       |                                              |       |                |                |              |             |         |                        |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------------+
| us2   | another one                                  | OPEN  |     GREAT      | May. 19, 2017  | Not set yet  | Not set yet |    0    |           2            |
|       |                                              |       |                |                |              |             |         |                        |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------------+
| Total: 2                                                                                                                                                       |
+----------------------------------------------------------------------------------------------------------------------------------------------------------------+

Dashboard>firstProject>backlog~$ list user_stories filter must

+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|                                                                     Backlog (User Stories)                                                                     |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------------+
| Title | Description                                  | State | Business Value | Initiated Date | Planned Date |  Due Date   | Tasks # |         Points         |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------------+
| us1   | as developer, I have to show a demonstration | OPEN  |   MUST_HAVE    | May. 19, 2017  | Not set yet  | Not set yet |    0    |           5            |
|       |                                              |       |                |                |              |             |         |                        |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------------+
| Total: 1                                                                                                                                                       |
+----------------------------------------------------------------------------------------------------------------------------------------------------------------+

Dashboard>firstProject>backlog~$ list user_stories sort 
business_value   description      due_date         initiated_date   planned_date     points           state            tasks            title            
Dashboard>firstProject>backlog~$ list user_stories sort points 

+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|                                                                     Backlog (User Stories)                                                                     |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------------+
| Title | Description                                  | State | Business Value | Initiated Date | Planned Date |  Due Date   | Tasks # |         Points         |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------------+
| us2   | another one                                  | OPEN  |     GREAT      | May. 19, 2017  | Not set yet  | Not set yet |    0    |           2            |
|       |                                              |       |                |                |              |             |         |                        |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------------+
| us1   | as developer, I have to show a demonstration | OPEN  |   MUST_HAVE    | May. 19, 2017  | Not set yet  | Not set yet |    0    |           5            |
|       |                                              |       |                |                |              |             |         |                        |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------------+
| Total: 2                                                                                                                                                       |
+----------------------------------------------------------------------------------------------------------------------------------------------------------------+

Dashboard>firstProject>backlog~$ list user_stories sort points 

+----------------------------------------------------------------------------------------------------------------------------------------------------------------+
|                                                                     Backlog (User Stories)                                                                     |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------------+
| Title | Description                                  | State | Business Value | Initiated Date | Planned Date |  Due Date   | Tasks # |         Points         |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------------+
| us1   | as developer, I have to show a demonstration | OPEN  |   MUST_HAVE    | May. 19, 2017  | Not set yet  | Not set yet |    0    |           5            |
|       |                                              |       |                |                |              |             |         |                        |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------------+
| us2   | another one                                  | OPEN  |     GREAT      | May. 19, 2017  | Not set yet  | Not set yet |    0    |           2            |
|       |                                              |       |                |                |              |             |         |                        |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------------+
| Total: 2                                                                                                                                                       |
+----------------------------------------------------------------------------------------------------------------------------------------------------------------+

Dashboard>firstProject>backlog~$ back
Dashboard>firstProject~$ add 
release      sprint       user_story   
Dashboard>firstProject~$ add sprint 
name: s1

description:
(\q to end writing)
first sprint
\q

start date:
(hit Tab to see examples)
(optional: leave it blank and hit enter)

jan      feb      march    april    may      jun      jul      aug      sep      oct      nov      dec      

start date:
(hit Tab to see examples)
(optional: leave it blank and hit enter)
may 
1    10   11   12   13   14   15   16   17   18   19   2    20   21   22   23   24   25   26   27   28   29   3    4    5    6    7    8    9    

start date:
(hit Tab to see examples)
(optional: leave it blank and hit enter)
may 19 201
2015   2016   2017   2018   2019   

start date:
(hit Tab to see examples)
(optional: leave it blank and hit enter)
may 19 2017
duration: (optional: leave it blank and hit enter)
14
veloctiy: 8

Sprint "s1" has been added!
Dashboard>firstProject~$ sprints

+-----------------------------------------------------------------------------------------------------+
|                                          Unplanned Sprints                                          |
+------+--------------+---------+---------------+---------------+-----------------+-------------------+
| Name | Description  |  State  |  Start Date   |   Due Date    | Assigned Effort |     Velocity      |
+------+--------------+---------+---------------+---------------+-----------------+-------------------+
| s1   | first sprint | CREATED | May. 19, 2017 | Jun. 01, 2017 |        0        |         8         |
|      |              |         |               |               |                 |                   |
+------+--------------+---------+---------------+---------------+-----------------+-------------------+
| Total: 1                                                                                            |
+-----------------------------------------------------------------------------------------------------+

Dashboard>firstProject~$ add release
name: r1

description:
(\q to end writing)
first release
\q

start date:
(hit Tab to see examples)
(optional: leave it blank and hit enter)
may 20 2017

due date:
(hit Tab to see examples)
(optional: leave it blank and hit enter)
jan 1 2017
due date must be after start date

due date:
(hit Tab to see examples)
(optional: leave it blank and hit enter)
dec 15 2017

Release "r1" has been added!
Dashboard>firstProject~$ releases 

+----------------------------------------------------------------------------------+
|                                     Releases                                     |
+------+---------------+---------+---------------+---------------+-----------------+
| Name | Description   |  State  |  Start Date   |   Due Date    | Assigned Effort |
+------+---------------+---------+---------------+---------------+-----------------+
| r1   | first release | CREATED | May. 20, 2017 | Dec. 15, 2017 |        0        |
|      |               |         |               |               |                 |
+------+---------------+---------+---------------+---------------+-----------------+
| Total: 1                                                                         |
+----------------------------------------------------------------------------------+

Dashboard>firstProject~$ go_to 
go_to    
Dashboard>firstProject~$ go_to user_story us
us1   us2   
Dashboard>firstProject~$ go_to user_story us1
Dashboard>firstProject>us1~$ add task 
title: t1

description:
(\q to end writing)
first task
\q

estimated time (in hours):
(hit Tab to see an example)

1      1.5    2      2.25   3      

estimated time (in hours):
(hit Tab to see an example)
1.5

Task "t1" has been added!
Dashboard>firstProject>us1~$ tasks

+-------+-------------+-------+----------------+--------------+-------------+----------------+
| Title | Description | State | Initiated Date | Planned Date |  Due Date   | Estimated Time |
+-------+-------------+-------+----------------+--------------+-------------+----------------+
| t1    | first task  | OPEN  | May. 19, 2017  | Not set yet  | Not set yet |      1.5       |
|       |             |       |                |              |             |                |
+-------+-------------+-------+----------------+--------------+-------------+----------------+
| Total: 1                                                                                   |
+--------------------------------------------------------------------------------------------+

Dashboard>firstProject>us1~$ back
Dashboard>firstProject~$ go_to 
go_to    
Dashboard>firstProject~$ go_to planner 
Dashboard>firstProject>planner~$ user_stories 

+----------------------------------------------------------------------------------------------------------------------------------------------------------+
|                                                                     All User Stories                                                                     |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------+
| Title | Description                                  | State | Business Value | Initiated Date | Planned Date |  Due Date   | Tasks # |      Points      |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------+
| us1   | as developer, I have to show a demonstration | OPEN  |   MUST_HAVE    | May. 19, 2017  | Not set yet  | Not set yet |    1    |        5         |
|       |                                              |       |                |                |              |             |         |                  |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------+
| us2   | another one                                  | OPEN  |     GREAT      | May. 19, 2017  | Not set yet  | Not set yet |    0    |        2         |
|       |                                              |       |                |                |              |             |         |                  |
+-------+----------------------------------------------+-------+----------------+----------------+--------------+-------------+---------+------------------+
| Total: 2                                                                                                                                                 |
+----------------------------------------------------------------------------------------------------------------------------------------------------------+

Dashboard>firstProject>planner~$ sprints 

+--------------------------------------------------------------------------------------------+
|                                          Sprints                                           |
+------+--------------+---------+---------------+---------------+-----------------+----------+
| Name | Description  |  State  |  Start Date   |   Due Date    | Assigned Effort | Velocity |
+------+--------------+---------+---------------+---------------+-----------------+----------+
| s1   | first sprint | CREATED | May. 19, 2017 | Jun. 01, 2017 |        0        |    8     |
|      |              |         |               |               |                 |          |
+------+--------------+---------+---------------+---------------+-----------------+----------+
| Total: 1                                                                                   |
+--------------------------------------------------------------------------------------------+

Dashboard>firstProject>planner~$ move us
us1   us2   
Dashboard>firstProject>planner~$ move us1 to s1
User Story "us1" has been moved to sprint s1!
Dashboard>firstProject>planner~$ move us2 to s1
User Story "us2" has been moved to sprint s1!
Dashboard>firstProject>planner~$ plan 
plan    
Dashboard>firstProject>planner~$ plan 
########## PLAN ##########
firstProject
 └ r1

Backlog

Sprints
 ├ s1
 | ├ us1
 | └ us2
Dashboard>firstProject>planner~$ move s1 to r1
Sprint "s1" has been moved to release r1!
Dashboard>firstProject>planner~$ plan 
plan    
Dashboard>firstProject>planner~$ plan 
########## PLAN ##########
firstProject
 ├ r1
 | ├ s1
 | | ├ us1
 | | └ us2

Backlog

Sprints
Dashboard>firstProject>planner~$ plan 
plan    
Dashboard>firstProject>planner~$ plan details 

+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|                                                                          PLAN -> PROJECT: firstProject                                                                           |
+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|                                                                                  RELEASE: r1                                                                                   |
+---------------------------------------------------------------------------------+----------------+---------------+---------------+---------------+-------------------------------+
| Name                                                                            |  Description   |     State     |  Start Date   |   Due Date    |        Assigned Effort        |
+---------------------------------------------------------------------------------+----------------+---------------+---------------+---------------+-------------------------------+
| r1                                                                              | first release  |    CREATED    | May. 20, 2017 | Dec. 15, 2017 |               7               |
|                                                                                 |                |               |               |               |                               |
+---------------------------------------------------------------------------------+----------------+---------------+---------------+---------------+-------------------------------+
|                                                                                 r1 -> Sprint: s1                                                                                 |
+----------------------------------------------------------------+----------------+----------------+---------------+---------------+---------------+-------------------------------+
| Name                                                           | Description    |     State      |   Velocity    |  Start Date   |   Due Date    |        Assigned Effort        |
+----------------------------------------------------------------+----------------+----------------+---------------+---------------+---------------+-------------------------------+
| s1                                                             |  first sprint  |    PLANNED     |       8       | May. 19, 2017 | Jun. 01, 2017 |               7               |
|                                                                |                |                |               |               |               |                               |
+----------------------------------------------------------------+----------------+----------------+---------------+---------------+---------------+-------------------------------+
|                                                                                s1 -> User Stories                                                                                |
+-------+----------------------------------------------+---------+----------------+----------------+---------------+---------------+---------------+-------------------------------+
| Title |                 Description                  | State   | Business Value | Initiated Date | Planned Date  |   Due Date    |    Tasks #    |            points             |
+-------+----------------------------------------------+---------+----------------+----------------+---------------+---------------+---------------+-------------------------------+
| us1   | as developer, I have to show a demonstration | PLANNED |   MUST_HAVE    | May. 19, 2017  | May. 19, 2017 |  Not set yet  |       1       |               5               |
|       |                                              |         |                |                |               |               |               |                               |
+-------+----------------------------------------------+---------+----------------+----------------+---------------+---------------+---------------+-------------------------------+
| us2   | another one                                  | PLANNED |     GREAT      | May. 19, 2017  | May. 19, 2017 |  Not set yet  |       0       |               2               |
|       |                                              |         |                |                |               |               |               |                               |
+-------+----------------------------------------------+---------+----------------+----------------+---------------+---------------+---------------+-------------------------------+
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


Dashboard>firstProject>planner~$ exit
See ya!

```


# Commands
## Universal Commands
Commands are available in all views
### clear
Clear the terminal scree 
### exit
Exit/terminate the program
### dashboard
Go to dashboard
### go_to dashboard
Go to dashboard
### help
Show help
## Dashboard Commands
Commands are available in Dashboard view
### go_to
Go to a project and open the project view (followed by a project name)
### go_to project
Similar to 'go_to', it goes to a project and open the project view (followed by a project name)
### add project
Create a new project
### edit project
Edit project (followed by a project name)
### remove project
Remove or delete project (followed by a project name)

## Project Commands
Commands are available in Project view
### show
Show project information
### releases
List all releases
### sprints
List all sprints
### user_stories
List all user stories
### tasks
List all tasks
### plan
Show plan in short form as a tree
### plan details
Show plan in details in a table
### backlog
List user stories in the backlog
### show backlog
Similar to backlog, list user stories in the backlog  
Optional: (filter) to filter out the results (ex. show backlog filter a_string)  
Optional: (sort) to sort the results based on a chosen column (ex. show backlog sort column_name)  
use TAB to autocomplete column names  
repeating show backlog command with sort option toggles order between ASC and DESC  
### list releases
List all releases  
Optional: (filter) to filter out the results (ex. list releases filter a_string)  
Optional: (sort) to sort the results based on a chosen column (ex. list releases sort column_name)  
use TAB to autocomplete column names  
repeating list command with sort option toggles order between ASC and DESC  
### list sprints
List all sprints  
Optional: (filter) to filter out the results (ex. list sprints filter a_string)  
Optional: (sort) to sort the results based on a chosen column (ex. list sprints sort column_name)  
use TAB to autocomplete column names  
repeating list command with sort option toggles order between ASC and DESC  
### list user_stories
List all user stories  
Optional: (filter) to filter out the results (ex. list user_stories filter a_string)  
Optional: (sort) to sort the results based on a chosen column (ex. list user_stories sort column_name)  
use TAB to autocomplete column names  
repeating list command with sort option toggles order between ASC and DESC  
### back
Go back to previous view (Dashboard)
### go_to backlog
Go to backlog view
### go_to release
Go to a release view (followed by a release name)
### go_to sprint
Go to a sprint view (followed by a sprint name)
### go_to user_story
Go to a user story view (followed by a user story name)
### go_to releases
Go to all releases view
### go_to sprints
Go to all sprints view
### go_to planner
Go to planner view
### add release
Add new release
### add sprint
Add new sprint
### add user_story
Add new user story to the backlog

## Backlog Commands
Commands are available in Backlog view
### user_stories
List all user stories
### list user_stories
List all user stories  
 Optional: (filter) to filter out the results (ex. list user_stories filter a_string)  
Optional: (sort) to sort the results based on a chosen column (ex. list user_stories sort column_name)  
use TAB to autocomplete column names  
repeating list command with sort option toggles order between ASC and DESC
### back
Go back to previous view (Project)
### go_to user_story
Go to a user story view (followed by a user story name)
### add user_story
Add new user story to the backlog

## Releases Commands
Commands are available in Releases view
### releases
List all releases
### back
Go back to previous view (Project)
### go_to release
Go to a release view (followed by a release name)
### add release
Add new release

## Release Commands
Commands are available in Release view
### show
Show release information
### edit
Edit release
### change_status_to
Change status of release (use TAB to autocomplete)  
Status: created, open, planned, unplanned, in_progress, in_testing, done, overdue, removed
### sprints
List sprints planned in this release
### list sprints
List sprints planned in this release  
Optional: (filter) to filter out the results (ex. list sprints filter a_string)  
Optional: (sort) to sort the results based on a chosen column (ex. list sprints sort column_name)  
use TAB to autocomplete column names  
repeating list command with sort option toggles order between ASC and DESC
### back
Go back to previous view
### go_to sprint
Go to a sprint view (followed by a sprint name)

## Sprints Commands
Commands are available in Sprints view
### sprints
List all sprints
### back
Go back to previous view (Project)
### go_to sprint
Go to a sprint view (followed by a sprint name)
### add sprint
Add new sprint

## Sprint Commands
Commands are available in Sprint view
### show
Show sprint information
### edit
Edit sprint
### change_status_to
Change status of sprint (use TAB to autocomplete)  
Status: created, open, planned, unplanned, in_progress, in_testing, done, overdue, removed
### user_stories
List user stories planned in this sprint
### list user_stories
List user stories planned in this sprint  
Optional: (filter) to filter out the results (ex. list user_stories filter a_string)  
Optional: (sort) to sort the results based on a chosen column (ex. list user_stories sort column_name)  
use TAB to autocomplete column names  
repeating list command with sort option toggles order between ASC and DESC
### back
Go back to previous view
### go_to user_story
Go to a user story view (followed by a user story name)
### add user_story
Add new user story to this sprint


## User Story Commands
Commands are available in User Story view
### show
Show user story information
### edit
Edit user story
### change_status_to
Change status of user story (use TAB to autocomplete)  
Status: created, open, planned, unplanned, in_progress, in_testing, done, overdue, removed
### tasks
List tasks planned to this user story
### back
Go back to previous view
### go_to task
Go to a task view (followed by a task name)
### add task
Add new task to this user story


## Task Commands
Commands are available in Task view
### show
Show task information
### edit
Edit task
### change_status_to
Change status of task (use TAB to autocomplete)  
Status: created, open, planned, unplanned, in_progress, in_testing, done, overdue, removed 
### back
Go back to previous view


## Planner Commands
Commands are available in Planner view
### releases
List all releases
### sprints
List all sprints
### user_stories
List all user stories
### backlog
List user stories in the backlog
### plan
Show plan in short form as a tree
### plan details
Show plan in details in a table
### show releases
Similar to releases, list all releases
### show sprints
Similar to sprints, list all sprints
### show user_stories
Similar to user_stories, list all user stories
### show backlog
Similar to backlog, list user stories in the backlog
### show plan
Similar to plan, show plan in short form as a tree
### show plan details
Similar to plan details, show plan in details in a table
### move sprint# to release#
Move (or plan) sprint to release where sprint# is a sprint name and release# is a release name  
use TAB to autocomplete sprint and release names
### move user_story# to sprint#
Move (or plan) user story to sprint where user_story# is a user story name and sprint# is a sprint name  
use TAB to autocomplete user story and sprint names
### unplan sprint#
Unplan sprint where sprint# is a sprint name  
use TAB to autocomplete sprint names
### unplan user_story#
Unplan user story and move it back to the backlog where user_story# is a user story name  
use TAB to autocomplete user story names
### unplan all
Unplan all user stories and sprints
### back
Go back to previous view (Project)




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

