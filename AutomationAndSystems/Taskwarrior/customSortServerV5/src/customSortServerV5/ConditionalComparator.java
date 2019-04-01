package customSortServerV5;

import java.util.Comparator;

/**
 * @author a
 *
 */
public class ConditionalComparator<T> implements Comparator<T> {

	//This is wrong, question about it in interface ICheckThresHoldCondition<T>
//	public ConditionalComparator(boolean belowUrgencyThreshold,Comparator<T> projectComparator,Comparator<T> urgencyComparator) {
//		// TODO Auto-generated constructor stub
//	}
	
	
	private ICheckThresholdCondition<T> thresholdCondition;
	private Comparator<T> sortProject;
	private Comparator<T> sortUrgency;


	public ConditionalComparator(ICheckThresholdCondition<T> condition, Comparator<T> projectComparator,
			Comparator<T> urgencyComparator) {
		// TODO Auto-generated constructor stub
		this.thresholdCondition = condition;
		this.sortProject = projectComparator;
		this.sortUrgency = urgencyComparator;
	}

	/**
	 * If the task is below urgency threshold, sort on project
	 * If task is equal to/above threshold, sort on urgency low to high.
	 */
	@Override
	public int compare(T object1, T object2) {
		// TODO Auto-generated method stub
		if (thresholdCondition.check(object1) && thresholdCondition.check(object2) ) {
			return sortProject.compare(object1, object2);
		}else if(thresholdCondition.check(object1) && !thresholdCondition.check(object2)){
			return -1;
		}else if(!thresholdCondition.check(object1) && thresholdCondition.check(object2)){
			return 1;
		}else if(!thresholdCondition.check(object1) && !thresholdCondition.check(object2)){
			return sortUrgency.compare(object1, object2);
		}
		return 0;
	}

}
