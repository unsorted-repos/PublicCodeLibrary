package customSortServerV4;

import java.util.ArrayList;

public class Task {

	String	depends	=null;
	String	description=null;	
	String	due	=null; //date
	String	end	=null; //date
	String	entry	=null; //date
	String	estimate=null;	
	double	id=-1;
	double	imask=-1;	
	String	mask=null;	
	String	modified	=null; //date
	String	parent=null;	
	String	priority=null;	
	String	project=null;	
	String	recur=null;	
	String	scheduled	=null; //date
	String	start	=null; //date
	String	status=null;	
	String	tags=null;	
	double	tracnumber=-1;	
	String	tracsummary=null;	
	String	tracurl=null;	
	String	until	=null; //date
	double	urgency	=-1;
	String	uuid=null;	
	String	wait	=null; //date
	
	//User defined attributes (UDA's)
	int customSort =-1;
	
	//Suggested to convert the combination of these ArrayLists into a hashmap.
	//TODO: determine how that would be implemented maintaining their paired relation
	ArrayList<String> udaNames =new ArrayList();
	ArrayList<String> udaProperties =new ArrayList();
	
	/**
	 * constructor for the task objects of type task.
	 */
	public void Task() {

	}
}
