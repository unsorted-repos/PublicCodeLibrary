package customSortServerV5;

public class BacklogTask {
	private String twUUID;
	private boolean recurring;
	private boolean hasParent;
	private String parentUuid;
	private String textLine;
	private int lineNr;
	
	/**
	 * If it is a recurring task it has a parent or not, else it has no parent.
	 * If parentUuid = null hasParent = false
	 * fil twUuid
	 * fill textLine
	 * @param twUuid
	 * @param parentUuid
	 * @param textLine
	 */
	BacklogTask(String twUuid, String parentUuid, String textLine, boolean recurring, int lineNr){
		if (recurring) {
			if (parentUuid != null) {
				this.parentUuid =parentUuid;
				this.hasParent = true;
			}else {
				this.hasParent = false;
			}
		}else {
			this.hasParent = false;
		}
		this.twUUID = twUuid;
		this.textLine = textLine;
		this.lineNr = lineNr;
	}
	
	
	
	
	public int getLineNr() {
		return lineNr;
	}




	public void setLineNr(int lineNr) {
		this.lineNr = lineNr;
	}




	public boolean isRecurring() {
		return recurring;
	}




	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}




	public boolean isHasParent() {
		return hasParent;
	}

	public void setHasParent(boolean hasParent) {
		this.hasParent = hasParent;
	}


	public String getTwUUID() {
		return twUUID;
	}
	public void setTwUUID(String twUUID) {
		this.twUUID = twUUID;
	}
	public String getParentUuid() {
		return parentUuid;
	}
	public void setParentUuid(String parentUuid) {
		this.parentUuid = parentUuid;
	}
	public String getTextLine() {
		return textLine;
	}
	public void setTextLine(String textLine) {
		this.textLine = textLine;
	}
}
