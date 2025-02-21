# Brownie

<img width="422" alt="Ui" src="https://github.com/user-attachments/assets/e835c90d-df5b-408f-a450-624d04261065" />

This is a simple chatbot-like application that helps you manage your life by keeping track of your tasks.
It is named Brownie after a guide dog that accompanied me hiking in Kedarkantha.
Given below are instructions on how to use it.

You can keep track of 3 types of tasks.
1. Todo
    * A task with no time constraint
3. Deadline
    * A task with a deadline
5. Event
    * A task with a start time and end time.

## Adding todo
Adds a todo task to the list of tasks.

Format: `todo {description}`

Example: `todo Hit the gym!`

## Adding deadline
Adds a deadline task to the list of tasks.

Format: `deadline {description} by: {dd-mm-yyyy} {HH:mm}`

Example: `deadline Complete IP by: 20-02-2025 23:59`

## Adding event
Adds a event task to the list of tasks.

Format: `event {description} start: {dd-mm-yyyy} {HH:mm} end: {dd-mm-yyyy} {HH:mm}`

Example: `event Hari Raya Dinner start: 22-04-2025 19:00 end: 22-04-2025 21:00`

## Listing all tasks
Lists all the tasks in the list of tasks.

Command: `list`
```
1. *T*[ ] Hit the gym!
2. *D*[ ] Complete IP (by: 20 Feb 2025 11:59 PM)
2. *E*[ ] Hari Raya Dinner (start: 22 April 2025 07:00 PM) (end: 22 April 2025 09:00 PM)
```

## Deleting a task
Deletes a task based on the index in the list

Format: `delete {index}`


## Marking a task
Marks a task based on the index in the list. Marked task remains marked.

Format: `mark {index}`

## Unmarking a task
Unmarks a task based on the index in the list. Unmarked task remains unmarked.

Format: `unmmark {index}`

## Finding a task
Returns the list of tasks with description containing `target_string`. Search is not case-sensitive.

Format: `find {target_string}`

Example: `find dinner`

Example Output:
```
1. *E*[ ] Hari Raya Dinner (start: 22 April 2025 07:00 PM) (end: 22 April 2025 09:00 PM)
```

## Duplicate Detection
When adding a task, the app checks for duplicates based on equality of the type of task, the description of the task and the timings related to the task (if applicable).
If a duplicate is detected, the new task will be added with a warning.

## Exiting
Exits the app

Command: `bye`

# Declaration of AI use
The project was assisted by JetBrains AI Assistant through the use of auto-code completion.

Automated code generation was also used extensively for creating JavaDocs. More details on AI use can be found 
in the AI.md file



