package customSortServerV5;

import java.util.ArrayList;

/**
 * This class contains the list of all multiples of tasks found in backlog. Once it is completed, it contains all tasks/lines
 * found in backlog.data.
 * 
 * The last entery in each multiple contains the most recent update/version of the task that is found in backlog, since the backlog file
 * is scanned from top to bottom and the task are added oldest at the top, newest at the bottom.
 * @author a
 *
 */
public class BacklogTaskCatalog {
	private ArrayList<BacklogTaskMultiples> multiples;

	BacklogTaskCatalog(ArrayList<BacklogTaskMultiples> multiples){
		this.multiples = multiples;
	}

	public ArrayList<BacklogTaskMultiples> getMultiples() {
		return multiples;
	}

	public void setMultiples(ArrayList<BacklogTaskMultiples> multiples) {
		this.multiples = multiples;
	}
}
