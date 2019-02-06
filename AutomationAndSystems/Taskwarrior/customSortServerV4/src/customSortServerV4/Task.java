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
	
	
	/**
	 * constructor for the task objects of type task.
	 */
	public void Task() {
		//fill user defined attributes
	}
	
	public String getDepends() {
		return depends;
	}

	public void setDepends(Object depends) {
		this.depends = (String) depends;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(Object description) {
		this.description = (String) description;
	}

	public String getDue() {
		return due;
	}

	public void setDue(Object due) {
		this.due = (String) due;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(Object end) {
		this.end = (String) end;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(Object entry) {
		this.entry = (String) entry;
	}

	public String getEstimate() {
		return estimate;
	}

	public void setEstimate(Object estimate) {
		this.estimate = (String) estimate;
	}

	public double getId() {
		return id;
	}

	public void setId(Object id) {
		//this.id = (double) id;
		this.id = Double.parseDouble((String) id);
	}

	public double getImask() {
		return imask;
	}

	public void setImask(Object imask) {
		//this.imask = (double) imask;
		this.imask = Double.parseDouble((String) imask);
	}

	public String getMask() {
		return mask;
	}

	public void setMask(Object mask) {
		this.mask = (String) mask;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(Object modified) {
		this.modified = (String) modified;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(Object parent) {
		this.parent = (String) parent;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(Object priority) {
		this.priority = (String) priority;
	}

	public String getProject() {
		return project;
	}

	public void setProject(Object project) {
		this.project = (String) project;
	}

	public String getRecur() {
		return recur;
	}

	public void setRecur(Object recur) {
		this.recur = (String) recur;
	}

	public String getScheduled() {
		return scheduled;
	}

	public void setScheduled(Object scheduled) {
		this.scheduled = (String) scheduled;
	}

	public String getStart() {
		return start;
	}

	public void setStart(Object start) {
		this.start = (String) start;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(Object status) {
		this.status = (String) status;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(Object tags) {
		this.tags = (String) tags;
	}

	public double getTracnumber() {
		return tracnumber;
	}

	public void setTracnumber(Object tracnumber) {
		//this.tracnumber = (double) tracnumber;
		this.tracnumber = Double.parseDouble((String) tracnumber);
	}

	public String getTracsummary() {
		return tracsummary;
	}

	public void setTracsummary(Object tracsummary) {
		this.tracsummary = (String) tracsummary;
	}

	public String getTracurl() {
		return tracurl;
	}

	public void setTracurl(Object tracurl) {
		this.tracurl = (String) tracurl;
	}

	public String getUntil() {
		return until;
	}

	public void setUntil(Object until) {
		this.until = (String) until;
	}

	public double getUrgency() {
		return urgency;
	}

	public void setUrgency(Object urgency) {
		//this.urgency = (double) urgency;
		this.urgency = Double.parseDouble((String) urgency);
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(Object uuid) {
		this.uuid = (String) uuid;
	}

	public String getWait() {
		return wait;
	}

	public void setWait(Object wait) {
		this.wait = (String) wait;
	}

	public int getCustomSort() {
		return customSort;
	}

	public void setCustomSort(Object customSort) {
		this.customSort = (int) customSort;
	}	
	
}
