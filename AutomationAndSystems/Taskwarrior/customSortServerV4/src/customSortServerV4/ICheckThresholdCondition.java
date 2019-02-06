/**
 * 
 */
package customSortServerV4;

/**
 * Question 0.: Why does the conditional comparator constructor require an interface for this?
 * Why can it not simply accept a boolean named "condition" which can be evaluated
 * within the constructor?
 * Question 1. what is check? is it a method that returns a boolean value which without
 * explicit return type returns it's argument?
 * @author a
 *
 */
public interface ICheckThresholdCondition<Object> {
	boolean check(Object conditionObject);
}