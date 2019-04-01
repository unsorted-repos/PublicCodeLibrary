package customSortServerV5;

import java.util.ArrayList;

public class TaskList {
	private ArrayList<Task> tasks=new ArrayList<Task>(); 
	
	/**
	 * Constructor
	 */
	public TaskList() {		
	}
	
	/**
	 * gets the TaskList Object
	 * @return an ArrayList of tasks
	 */
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	/**
	 * sets the TaskList object.
	 * @param tasks
	 */
	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	
	/**
	 * Adds a task
	 * @param task
	 */
	public void addTask(Task task) {
		tasks.add(task);
	}

	public void removeTask(Task task) {
		tasks.remove(task);
	}
}
