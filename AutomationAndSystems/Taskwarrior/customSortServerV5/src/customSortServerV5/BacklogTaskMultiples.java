package customSortServerV5;

import java.util.ArrayList;

/**
 * This class contains all the multiples of a single task that are stored in backlog.
 * So all the tasks in this class have the same twUuid.
 * @author a
 *
 */
public class BacklogTaskMultiples {
	private ArrayList<BacklogTask> multiples;

	BacklogTaskMultiples(ArrayList<BacklogTask> BacklogTasks){
		this.multiples = BacklogTasks;
	}
	public ArrayList<BacklogTask> getMultiples() {
		return multiples;
	}

	public void setMultiples(ArrayList<BacklogTask> multiples) {
		this.multiples = multiples;
	}
}
