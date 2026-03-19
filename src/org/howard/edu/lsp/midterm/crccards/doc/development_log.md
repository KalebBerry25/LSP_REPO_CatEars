
Can you provide an outline for the best way to go about the following question description

Question 1 - Implement Classes from CRC Cards (40 points)
The following CRC cards describe a simple Task Management System. Using these CRC cards, implement the required Java classes.
Requirements
•	Each class must be in its own .java file.
•	Use the package org.howard.edu.lsp.midterm.crccards in every Java class.
GitHub Push Requirement:
Push your solution to your GitHub repository early and often during the exam. Students are strongly encouraged to push their work multiple times throughout the exam to avoid losing work due to system failures. Only code that exists in the GitHub repository at the end of the exam will be graded.
•	Your implementation must work with the Driver class provided below.
•	Do not modify the Driver class.
•	TaskManager must select and use an appropriate Java data structure to manage Task objects according to the requirements.
•	All classes and public methods must include Javadoc comments.
•	You must include the required documentation files described below.
•	You may use any standard classes from the Java Collections Framework (for example, List, ArrayList, Map, etc.).
CRC Cards
Class	Responsibilities	Collaborators
Task	• store task information
• update task status
• provide task details	none
TaskManager	• store tasks
• add new tasks
• find a task by ID
• return tasks by status	Task
Part 1 - Implement Task
Fields: taskId, description, status
Constructor:
public Task(String taskId, String description)
Rule: The default status must be "OPEN".
Required methods:
public String getTaskId()
public String getDescription()
public String getStatus()
public void setStatus(String status)
public String toString()
The toString() method must produce output in the following format:
T1 Write report [OPEN]

Where the above is defined by: taskId description [status]
Valid status values include: OPEN, IN_PROGRESS, COMPLETE.
Status comparisons are case-sensitive.
If setStatus() is called with any value other than the valid status values listed above, the task's status must be set to UNKNOWN.
Part 2 - Implement TaskManager
TaskManager manages a collection of Task objects.
You must select and use an appropriate Java data structure to store tasks internally. Your design should support:
•	storing tasks
•	locating a task by its taskId
•	preventing duplicate task IDs
•	retrieving tasks by status
The internal data structure you choose is up to you, provided it supports the required behavior.
Required methods:
public void addTask(Task task)
public Task findTask(String taskId)
public List<Task> getTasksByStatus(String status)
Important note: getTasksByStatus() must return a List<Task> containing all tasks whose status matches the specified value. This return type does not restrict the internal data structure used by TaskManager.
Duplicate Task Rule: TaskManager must not allow duplicate task IDs. If a task with the same taskId already exists, addTask(Task task) must throw IllegalArgumentException.
Additional behavior: If findTask() cannot locate the requested task ID, it must return null.
Javadoc Requirement
All classes and public methods must include Javadoc comments.
•	Each class must include a brief description of the class and author name.
•	Each public method must include a description of what the method does, @param descriptions where applicable, and @return descriptions where applicable.


Documentation Requirements
Documentation files must be placed in package:
org.howard.edu.lsp.midterm.crccards.doc

development_log.md (5 points):
Create a file named development_log.md that documents any external resources used while completing this problem. External resources include but are not limited to: AI tools (ChatGPT, Claude, Gemini, Copilot, etc.), Google searches, websites, online documentation, lecture notes, textbooks, or any other reference material.

For each resource used, provide the following:

• If you used an AI tool: You must include the complete conversation transcript (all prompts and responses) used to help produce your solution. Partial transcripts or summaries are not acceptable.

• If you used websites, documentation, Stack Overflow, articles, or other online materials: You must include the full URL and a brief explanation of how that resource helped you complete the problem.

• If you used books, lecture notes, or other offline references: Cite the source (title, author, and section if applicable) and explain how it helped.

If no external assistance was used, write exactly:
"No external assistance was used."
2. crccards.md (5 points): In 2-3 sentences, explain why TaskManager collaborates with Task, but Task does not collaborate with TaskManager. Your explanation should reference the responsibilities listed in the CRC cards.
Provided Driver Class
Do not modify this class.
package org.howard.edu.lsp.midterm.crccards;

import java.util.List;

public class Driver {

    public static void main(String[] args) {

        TaskManager manager = new TaskManager();

        Task t1 = new Task("T1", "Write report");
        Task t2 = new Task("T2", "Study for exam");
        Task t3 = new Task("T3", "Submit homework");

        manager.addTask(t1);
        manager.addTask(t2);
        manager.addTask(t3);

        t2.setStatus("IN_PROGRESS");

        System.out.println("OPEN TASKS");

        List<Task> openTasks = manager.getTasksByStatus("OPEN");

        for (Task t : openTasks) {
            System.out.println(t.toString());
        }

        System.out.println("\nTESTING DUPLICATE TASK ID");

        try {
            Task duplicate = new Task("T1", "Duplicate task");
            manager.addTask(duplicate);
        } catch (IllegalArgumentException e) {
            System.out.println("Duplicate task ID detected.");
        }

        System.out.println("\nTESTING INVALID STATUS");

        Task t4 = new Task("T4", "Invalid status test");
        t4.setStatus("DONE");
        System.out.println(t4.toString());

        System.out.println("\nTESTING FIND TASK");

        Task found = manager.findTask("T2");
        System.out.println(found);

        Task notFound = manager.findTask("T99");

        if (notFound == null) {
            System.out.println("Task not found.");
        }
    }
}
Expected Output
OPEN TASKS
T1 Write report [OPEN]
T3 Submit homework [OPEN]

TESTING DUPLICATE TASK ID
Duplicate task ID detected.

TESTING INVALID STATUS
T4 Invalid status test [UNKNOWN]

TESTING FIND TASK
T2 Study for exam [IN_PROGRESS]
Task not found.

Yes. The best approach is to treat this as a small, checklist-driven implementation task and build it in the same order the prompt is written.

Best overall plan
Start by identifying exactly what must exist at the end:

Task.java

TaskManager.java

Driver.java unchanged

development_log.md

crccards.md

All Java files must use:

package org.howard.edu.lsp.midterm.crccards;
The two markdown files must go in:

org.howard.edu.lsp.midterm.crccards.doc
Recommended order to complete the problem
1. Set up the package structure first
Create the folders/packages before writing code so you do not waste time moving files later.

You want something like:

src/
└── org/
    └── howard/
        └── edu/
            └── lsp/
                └── midterm/
                    ├── crccards/
                    │   ├── Task.java
                    │   ├── TaskManager.java
                    │   └── Driver.java
                    └── crccards/
                        └── doc/
                            ├── development_log.md
                            └── crccards.md
2. Implement Task first
This is the easier class and TaskManager depends on it.

Focus on these requirements:

fields:

taskId

description

status

constructor:

takes taskId and description

sets status to "OPEN"

getters:

getTaskId()

getDescription()

getStatus()

setter:

setStatus(String status)

toString()

Important logic:

valid statuses are only:

"OPEN"

"IN_PROGRESS"

"COMPLETE"

comparisons are case-sensitive

anything else becomes "UNKNOWN"

So your setStatus method should check for those three exact strings and otherwise assign "UNKNOWN".

For toString(), make sure it matches exactly:

T1 Write report [OPEN]
That means:

taskId

space

description

space

[status]

3. Implement TaskManager second
Now decide on the internal data structure.

The best choice here is a Map<String, Task>, usually a HashMap.

Why this is a strong choice:

fast lookup by taskId

easy duplicate checking

easy storage of tasks

Even though getTasksByStatus() returns a List<Task>, that does not mean the internal structure has to be a List.

Internal structure recommendation
private Map<String, Task> tasks;
Initialize it in the constructor.

4. Implement TaskManager methods in this order
addTask(Task task)
Logic:

check whether task.getTaskId() already exists in the map

if yes, throw IllegalArgumentException

if no, add it

findTask(String taskId)
Logic:

return the task if found

return null if not found

Using a map, this becomes very simple.

getTasksByStatus(String status)
Logic:

create a new List<Task>

loop through all tasks in the map

add each task whose getStatus().equals(status)

return the list

This must return all matching tasks.

5. Add Javadoc as you go
Do not wait until the end, because it is easy to forget.

You need Javadoc for:

each class

each public method

Each class should include:

what the class does

author name

Each public method should include:

description

@param where needed

@return where needed

For example, for getTaskId() you need a @return.
For setStatus(String status) you need a @param.
For addTask(Task task) you should document that it throws IllegalArgumentException for duplicates.

6. Test against the provided Driver
After both classes are done, run the given Driver exactly as provided.

Check these behaviors carefully:

open tasks should show only T1 and T3

duplicate T1 should trigger exception

invalid status "DONE" should become "UNKNOWN"

finding "T2" should return the task

finding "T99" should return null

If your output matches the expected output, your implementation is likely correct.

7. Write the documentation files last
development_log.md
This file is about resources used.

If you used anything at all, you must document it exactly as required.

Since you are using me right now, that counts as external assistance. If your instructor requires full transcript inclusion, you should paste the full conversation into that file.

If no outside help was used, the file must contain exactly:

No external assistance was used.
But based on your current situation, that exact statement would not be accurate.

crccards.md
Keep this short and tied directly to the CRC cards.

Main idea:

TaskManager collaborates with Task because its responsibilities involve storing, finding, and filtering Task objects.

Task does not collaborate with TaskManager because its responsibilities are only about its own state: storing task information, updating status, and providing details.

Good time-saving implementation strategy during the exam
Follow this sequence:

create package structure

write Task.java

write TaskManager.java

paste in Driver.java

run and test

add/fix Javadocs

write markdown docs

push to GitHub

run one last test

push again

Common mistakes to avoid
Do not:

use lowercase status checks like "open" unless specified

forget that comparisons are case-sensitive

return something other than null from findTask() when not found

allow duplicate IDs silently

modify Driver

forget Javadocs

use the wrong package name

store tasks in a structure that makes duplicate checking awkward unless you handle it carefully

Best design choice summary
Use:

Task as a simple model class

TaskManager with a HashMap<String, Task>

That gives you:

clean separation of responsibilities

fast findTask

easy duplicate prevention

easy status filtering by iterating over stored tasks

Simple mental blueprint
Think of it like this:

Task = one task object with id, description, and status

TaskManager = container/controller for many tasks

Suggested final checklist
Before submitting, make sure:

every class is in its own .java file

package names are correct

Task defaults status to "OPEN"

invalid status becomes "UNKNOWN"

duplicate IDs throw IllegalArgumentException

findTask() returns null when missing

getTasksByStatus() returns a List<Task>

Javadocs are present

markdown docs are present

code works with the given Driver

changes are pushed to GitHub

If you want, I can also turn this outline into a step-by-step coding template for Task.java and TaskManager.java without fully giving away the final solution.


Yes the coding template would be helpful

Absolutely — here is a clean coding template you can follow during the exam.

1. Task.java template
package org.howard.edu.lsp.midterm.crccards;

/**
 * Represents a task in the task management system.
 * A task stores an ID, description, and status.
 *
 * @author Your Name
 */
public class Task {
    
    // Fields
    private String taskId;
    private String description;
    private String status;

    /**
     * Constructs a Task with the given ID and description.
     * The default status is OPEN.
     *
     * @param taskId the unique task ID
     * @param description the task description
     */
    public Task(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.status = "OPEN";
    }

    /**
     * Returns the task ID.
     *
     * @return the task ID
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * Returns the task description.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the current task status.
     *
     * @return the task status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the task status.
     * Valid values are OPEN, IN_PROGRESS, and COMPLETE.
     * Any other value sets the status to UNKNOWN.
     *
     * @param status the new status value
     */
    public void setStatus(String status) {
        if (status.equals("OPEN") || status.equals("IN_PROGRESS") || status.equals("COMPLETE")) {
            this.status = status;
        } else {
            this.status = "UNKNOWN";
        }
    }

    /**
     * Returns a string representation of the task.
     *
     * @return the task in the format: taskId description [status]
     */
    @Override
    public String toString() {
        return taskId + " " + description + " [" + status + "]";
    }
}
2. TaskManager.java template
package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of Task objects.
 * Supports adding tasks, finding tasks by ID, and retrieving tasks by status.
 *
 * @author Your Name
 */
public class TaskManager {
    
    // Internal data structure
    private Map<String, Task> tasks;

    /**
     * Constructs an empty TaskManager.
     */
    public TaskManager() {
        tasks = new HashMap<>();
    }

    /**
     * Adds a task to the manager.
     * Throws an exception if a task with the same ID already exists.
     *
     * @param task the task to add
     * @throws IllegalArgumentException if the task ID already exists
     */
    public void addTask(Task task) {
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Duplicate task ID");
        }
        tasks.put(task.getTaskId(), task);
    }

    /**
     * Finds a task by its task ID.
     *
     * @param taskId the ID of the task to find
     * @return the matching Task, or null if not found
     */
    public Task findTask(String taskId) {
        return tasks.get(taskId);
    }

    /**
     * Returns all tasks whose status matches the given status.
     *
     * @param status the status to search for
     * @return a list of all tasks with the matching status
     */
    public List<Task> getTasksByStatus(String status) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks.values()) {
            if (task.getStatus().equals(status)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}
3. Why this template is a good match
This version directly satisfies the prompt because:

Task stores the required fields

status defaults to "OPEN"

invalid status becomes "UNKNOWN"

toString() matches the required format

TaskManager prevents duplicate task IDs

findTask() returns null automatically when the map has no match

getTasksByStatus() returns a List<Task>

4. crccards.md template
You can use something like this:

TaskManager collaborates with Task because its responsibilities include storing tasks, finding tasks by ID, and returning tasks by status. To perform those responsibilities, TaskManager must work directly with Task objects.

Task does not collaborate with TaskManager because its responsibilities are limited to storing its own information, updating its own status, and providing its own details. A Task does not need to manage collections of tasks or search for other tasks.
5. development_log.md template
Since you used ChatGPT, your instructor’s rules say you should document that. A simple starter structure could be:

External Resource 1: ChatGPT
How it helped:
Used to help outline the implementation plan for Task and TaskManager and to generate a coding template based on the CRC card requirements.

Complete conversation transcript:
[Paste full conversation here]
If you also used Java docs or class notes, add entries for those too.

6. Quick testing checklist
When you run the provided Driver, confirm:

T1 and T3 appear under open tasks

duplicate T1 throws exception

"DONE" becomes "UNKNOWN"

findTask("T2") returns the correct task

findTask("T99") returns null

7. Small exam tip
After finishing Task.java, compile and test immediately before moving on to TaskManager.java. Then test again after TaskManager.java. That catches mistakes faster.

If you want, I can also give you a slightly more polished final version with professor-style Javadocs and cleaner formatting that you could submit almost as-is.