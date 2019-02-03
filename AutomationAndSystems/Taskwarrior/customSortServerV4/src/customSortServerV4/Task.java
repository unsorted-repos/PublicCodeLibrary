package customSortServerV4;

import java.util.ArrayList;

public class Task {

	 String depends =null;
	 String description=null; 
	 String due =null; //date
	 String end =null; //date
	 String entry =null; //date
	 String estimate=null; 
	 double id=-1;
	 double imask=-1; 
	 String mask=null; 
	 String modified =null; //date
	 String parent=null; 
	 String priority=null; 
	 String project=null; 
	 String recur=null; 
	 String scheduled =null; //date
	 String start =null; //date
	 String status=null; 
	 String tags=null; 
	 double tracnumber=-1; 
	 String tracsummary=null; 
	 String tracurl=null; 
	 String until =null; //date
	 double urgency =-1;
	 String uuid=null; 
	 String wait =null; //date
	
	//User defined attributes (UDA's)
	int customSort =-1;
	
	//Suggested to convert the combination of these ArrayLists into a hashmap.
	//TODO: determine how that would be implemented maintaining their paired relation
	ArrayList<String> udaNames =new ArrayList();
	ArrayList<String> udaProperties =new ArrayList();
	ArrayList<String[]> userDefinedAttributes =new ArrayList(); //first String dimension=UDA name, 2nd string dimension =UDA value.
	
	/**
	 * constructor for the task objects of type task.
	 */
	public void Task() {
		//fill user defined attributes
	}
	
	public String getDepends() {
		return depends;
	}

	public void setDepends(String depends) {
		this.depends = depends;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDue() {
		return due;
	}

	public void setDue(String due) {
		this.due = due;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getEstimate() {
		return estimate;
	}

	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public double getImask() {
		return imask;
	}

	public void setImask(double imask) {
		this.imask = imask;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getRecur() {
		return recur;
	}

	public void setRecur(String recur) {
		this.recur = recur;
	}

	public String getScheduled() {
		return scheduled;
	}

	public void setScheduled(String scheduled) {
		this.scheduled = scheduled;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public double getTracnumber() {
		return tracnumber;
	}

	public void setTracnumber(double tracnumber) {
		this.tracnumber = tracnumber;
	}

	public String getTracsummary() {
		return tracsummary;
	}

	public void setTracsummary(String tracsummary) {
		this.tracsummary = tracsummary;
	}

	public String getTracurl() {
		return tracurl;
	}

	public void setTracurl(String tracurl) {
		this.tracurl = tracurl;
	}

	public String getUntil() {
		return until;
	}

	public void setUntil(String until) {
		this.until = until;
	}

	public double getUrgency() {
		return urgency;
	}

	public void setUrgency(double urgency) {
		this.urgency = urgency;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getWait() {
		return wait;
	}

	public void setWait(String wait) {
		this.wait = wait;
	}

	public int getCustomSort() {
		return customSort;
	}

	public void setCustomSort(int customSort) {
		this.customSort = customSort;
	}

	public ArrayList<String> getUdaNames() {
		return udaNames;
	}

	public void setUdaNames(ArrayList<String> udaNames) {
		this.udaNames = udaNames;
	}

	public ArrayList<String> getUdaProperties() {
		return udaProperties;
	}

	public void setUdaProperties(ArrayList<String> udaProperties) {
		this.udaProperties = udaProperties;
	}
	
	
}
