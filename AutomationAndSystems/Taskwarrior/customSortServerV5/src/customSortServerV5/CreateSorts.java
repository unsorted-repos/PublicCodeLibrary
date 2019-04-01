package customSortServerV5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

/**
 * The sorting procedure should output in the following order:
 * tasks that are: (with a project AND below the urgency threshold):  sorted on project a-z
 * tasks that are: (without a project AND below the urgency threshold): sorted on urgency (low to high)
 * tasks that are: equal to, or above the urgency threshold: sorted on urgency (low to high)
 * 
 * 
 * One should not directly make this an implementation of the interface 
 * ICreateSorts because then you have to explicitly add method
 * "createSorts" in this code, which you don't necessarily want for
 * the generic anonymous methods written with the lambda function.
 * (The lambda function code of "sortProject" and "sortUrgency" 
 * implement the method "createSorts" of the interface.
 * 
 * Question 0.: Are "sortProject" and "sortUrgency" methods?
 * Question 1.: Are sortProject and sortUrgency an implementation of
 * method "createSorts" of interface ICreateSorts?
 * Question 2.: Is this overloading? Since normally overloading is when
 * you have 2 methods with the same name but different type/nr of input
 * arguments. But here I am not quite sure whether "sortProject" and 
 * "sortUrgency" are the methods, or whether the methods are the anonymous
 * lambda functions. And if they are methods, they have a different name,
 * is it still called overloading for this kind of scenario/relation?
 */
//public class CreateSorts implements ICreateSorts {
public class CreateSorts{

	/**
	 * If the method createSorts(Object object) of interface ICreateSorts is called
	 * wilst createSorts( is passed a string, it will end up here and execute the generic
	 * method sortProject. 
	 */
	//	static ICreateSorts<String> sortProject = (objectA,objectB) -> {
	//		
	//		//A normal sort on Project:
	//		Comparator<Task> projectComparator = (object1, object2) -> String.join("", object1.getProject()).compareTo(String.join("", object2.getProject()));
	//		System.out.println("sorting project");
	//		//return object1,object2;
	//		return null;
	//	};
	//	
	//	static ICreateSorts<Double> sortUrgency = (objectA,objectB) -> {
	//		//sorts urgency
	//		Comparator<Task> urgencyComparator = (object1,object2) -> Double.compare(object1.getUrgency(), object2.getUrgency());
	//		System.out.println("sorting urgency");
	//		//return task;
	//		return null;
	//	};

	public static ArrayList<Task> mainSort(ArrayList<Task> taskList) {	
		//A normal sort on Project:
		Comparator<Task> projectComparator = (object1, object2) -> {
			if (object1.getProject()==null && object2.getProject()!=null){
				return 1; // non-urgent projects should be located below non-urgent tasks without a project
			} else if (object1.getProject()!=null && object2.getProject()==null){
				return -1; // non-urgent projects should be located below non-urgent tasks without a project
			}else if (object1.getProject()!=null && object2.getProject()!=null){
				return String.join("", object1.getProject()).compareTo(String.join("", object2.getProject()));
			}
			return Double.compare(object1.getUrgency(), object2.getUrgency());
		};

		//sorts urgency
		Comparator<Task> urgencyComparator = (object1,object2) -> Double.compare(object1.getUrgency(), object2.getUrgency());

		//sort procedure type determination per task:

		/**
		 * This creates a new object named mainComparator which is a subtype of abstract class Comparator.
		 * Since Comparator is a pre-built-in Java class, you don't have to specify it to be abstract or normal
		 * you can just type whatever class implements it.
		 * how it works, is, you put an object task into the ConditionalComparator constructor brackets
		 * but since that is followed by a lambda function, the task is converted into 3 arguments before 
		 * it is passed to the actual constructor.
		 * 
		 * The first argument is a condition checks whether the task urgency is above or below some threshold
		 * The second argument passes the "method" sortProject in which the project comparator is called.
		 * The third argument passes the "method sortUrgency in which the urgency comparator is called. 
		 */
		Comparator<Task> mainComparator = new ConditionalComparator<Task>(task -> task.getUrgency() < hardCoded.getUrgencyThreshold(), projectComparator, urgencyComparator);

		//Call the actual mainComparator with a tasklist:
		Collections.sort(taskList, mainComparator);
		return taskList;
	}
}
