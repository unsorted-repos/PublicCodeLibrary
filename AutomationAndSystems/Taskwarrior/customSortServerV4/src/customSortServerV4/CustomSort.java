package customSortServerV4;

public class CustomSort {

	/**
	 * Put's tasks into separete TaskList objects notUrgentTasks and urgentTasks
	 * then puts the TaskLists into a TaskDataSet. 
	 * @param threshold
	 * @return the tasks divided on TaskLists notUrgentTasks and urgentTasks merged into a TaskDataSet.
	 */
	public TaskDataSet divdeOnUrgencyThreshold(double threshold) {
		return null;
	}
	
	/**
	 * Receives a tasklist of non-urgent tasks, and divides it into two tasklists:
	 * noProjectNotUrgentTasks and projectNotUrgentTasks. Then merges these two tasks
	 * into a taskdataset and returns it.
	 * @param incoming contains the tasks that are not urgent.
	 * @return returns a separated dataset of two lists for non-urgent tasks with and without a project.
	 */
	public TaskDataSet divideOnHasProject(TaskList incoming) {
		return null;
	}
	
	/**
	 * Sorts the tasks on urgency.
	 * @param incoming Contains the task list that needs to be sorted
	 * @return tasklist sorted on urgency
	 */
	public TaskList sortOnUrgency(TaskList incoming) {
		return null;
	}
	
	/**
	 * Sorts the non-urgent tasks that do have a project on project.
	 * @param nonUrgentHasProject
	 * @return 
	 */
	public TaskList sortOnProject(TaskList nonUrgentHasProject) {
		return null;
	}
	
	/**
	 * This combines the 3 customSorted tasklists to a single output tasklist 
	 * @param nonUrgentHasProject
	 * @param nonUrgentNoProject
	 * @return
	 */
	public TaskList mergeOutput(TaskList nonUrgentHasProject, TaskList nonUrgentNoProject, TaskList urgentTasks) {
		return null;
	}
	
	/**
	 * This will set the customSort property of the tasks in the Java object Task.(Not in linux). 
	 * @param sortedTaskList this is the final list of all tasks, merged and sorted.
	 * @return It will return the list, but now the Task objects will have their customSort field/property set.
	 */
	public TaskList setCustomSort(TaskList sortedTaskList) {
		return null;
	}
	
}
