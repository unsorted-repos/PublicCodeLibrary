package customSortServerV5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class FillBacklogTasks {

	/**
	 * TODO: Implement interface, extends etc and merge the Task and BacklogTask
	 * object like two different types of houses in JAVA assignment.
	 * 
	 * @return
	 */
	public static BacklogTaskCatalog manageBacklogFilling() {
		ArrayList<String> lines = new ArrayList<>();
		String backlogPath = hardCoded.getUbuntuFilePath();
		String backlogFileName = hardCoded.getBacklogFileName();


		ArrayList<BacklogTaskMultiples> multiples = new ArrayList<BacklogTaskMultiples>();
		BacklogTaskCatalog catalog = new BacklogTaskCatalog(multiples);

		BacklogTaskCatalog filteredCatalog;
//		BacklogTaskCatalog orderedFilteredCatalog;
		BacklogTask[] orderedFilteredTaskList;

		if (ReadFiles.checkIfFileExist(backlogPath, backlogFileName)) {
			lines = readLines(backlogPath, backlogFileName);
			for (int i = 1; i < lines.size(); i++) { // 1 to skip the first line containing the tw uuid.
				catalog = generateCatalog(catalog, createBacklogTask(lines.get(i), i));
			}
		}

		filteredCatalog = filterBacklogCatalog(catalog);
//		orderedFilteredCatalog = orderCatalog(filteredCatalog, lines.size() - 1); // -1 to remove the entry of the first
																					// line
		// converting the catalog into an array of BacklogTask objects that has the size nr of lines.
		orderedFilteredTaskList = orderCatalog(filteredCatalog,lines.size());
		
		// remove all null entries for the tasks of the lines that were removed.
		orderedFilteredTaskList = removeNullValues(orderedFilteredTaskList);
		
//		orderedFilteredTaskList = catalogToTaskArray(orderedFilteredCatalog);

		CreateFiles.writeFileContent("/home/" + hardCoded.getLinuxUserName() + "/.task/", "backlogCopy.data",
				orderedFilteredTaskList);
		return filteredCatalog;
	}

	
	/**
	 * TODO: Verify that if a customSort is changed, that the only thing that is changed in the backlog line is the value of cSort, 
	 * and not also some time of modification or anything else.
	 * TODO: Instead of only taking last, evaluate all tasks in multiple, if the only difference between the lines of the same task
	 * is the value of the cSort parameter, then remove the 2nd task of the comparison. 
	 * Then put that list of multiples back instead of just putting the last task of a multiple back
	 * Then check where you convert those multiples into a array of BacklogTask objects, that you not only take the last task/single task
	 * of a multiple, but all the remaining tasks in the multiple
	 */
	
//	/**
//	 * prints the line numbers if needed
//	 * @param filteredCatalog
//	 */
//	private static void printLineNrs(BacklogTaskCatalog filteredCatalog) {
//		int size = filteredCatalog.getMultiples().size();
//		System.out.println("Size=" + size);
//		for (int i = 0; i < size; i++) {
////			System.out.println("linenrsChanging="+filteredCatalog.getMultiples().get(i).getMultiples().get(0).getLineNr());
//		}
//
//	}

//	/**
//	 *  Converts the catalog to a convenient array of tasks to facilitate writing the catalog
//	 *  back to backlog.data.
//	 * @param filteredOrderedCatalog
//	 * @return
//	 */
//	private static BacklogTask[] catalogToTaskArray(BacklogTaskCatalog filteredOrderedCatalog) {
//		int catalogSize = filteredOrderedCatalog.getMultiples().size();
//		BacklogTask[] taskList = new BacklogTask[catalogSize];
//		for (int i = 0; i < catalogSize; i++) {
//			taskList[i] = filteredOrderedCatalog.getMultiples().get(i).getMultiples().get(0);
//		}
//		return taskList;
//	}

	/**
	 * This should: 
	 * Create a new array of BacklogTask[] with the size nr of lines in the backlog.data
	 * Take the un-ordered filteredCatalog (that only contains 1 task per multiple/line)
	 * and put the task of every multiple back into the array index of the line number of that task.
	 * Then it should return the array of tasks with their order (e.g. in index 1 a task, then in 4 then in 9 or 
	 * whichever lines remain after the filtering procedure.
	 * 
	 * TODO: Find out how the code works when it returns the unmodified filteredCatalog, instead of this taskList.
	 * 
	 * @param filteredCatalog
	 * @param nrOfLines
	 * @return
	 */
	private static BacklogTask[] orderCatalog(BacklogTaskCatalog filteredCatalog, int nrOfLines) {
		BacklogTask[] taskList = new BacklogTask[nrOfLines + 1];
		int taskLineNr;
		for (int i = 0; i < filteredCatalog.getMultiples().size(); i++) {
			taskLineNr = filteredCatalog.getMultiples().get(i).getMultiples().get(0).getLineNr();
			taskList[taskLineNr] = filteredCatalog.getMultiples().get(i).getMultiples().get(0);
		}

//		return filteredCatalog;
		return taskList;
	}

	/**
	 * Generates a new catalog that contains all multiples with just the last
	 * entry/task of each multiple in it.
	 * 
	 * @param catalog
	 */
	public static BacklogTaskCatalog filterBacklogCatalog(BacklogTaskCatalog catalog) {
		ArrayList<BacklogTask> originalMultiple = new ArrayList<BacklogTask>();
		ArrayList<BacklogTaskMultiples> multiples = new ArrayList<BacklogTaskMultiples>();
		BacklogTaskCatalog filteredCatalog = new BacklogTaskCatalog(multiples);

		for (int i = 0; i < catalog.getMultiples().size(); i++) {
			
			// re-initialize objects that needs to be added to the catalog.
			ArrayList<BacklogTask> filteredMultiple = new ArrayList<BacklogTask>();
			BacklogTaskMultiples filteredMultipleBacklog = new BacklogTaskMultiples(filteredMultiple);

			// store original multiple.
			originalMultiple = catalog.getMultiples().get(i).getMultiples();

			// create new multiple with last task of original multiple
			filteredMultiple.add(originalMultiple.get(originalMultiple.size() - 1));

			// create new backlog multiple object with single task multiple
			filteredMultipleBacklog = new BacklogTaskMultiples(filteredMultiple);
			// TODO: Check here whether filteredMultipleBacklog's ArrayList contains multiple tasks

			// add the single task multiple
			filteredCatalog.getMultiples().add(filteredMultipleBacklog);
		}
		return filteredCatalog;
	}

	/**
	 * This method compares the line of task i with that of task i+a (with a = 1<size of taskList) for all tasks in a multiple
	 * If the only difference is the change of value of the cSort parameter, 
	 * 		then task i is kept, 
	 * 		task i+a is removed/set to null
	 * 		a is incremented
	 * If there are more differences betweeen the lines of task i and i+a then
	 * 		both task i is kept, 
	 * 		and task i+a is kept, 
	 * 		i is set to a (so that task a is compared with task a+1 at first, which then again is the i+a with a=1)
	 * @param taskList
	 * @return
	 */
	public static ArrayList<BacklogTask> onlyRemoveCSortModifications(ArrayList<BacklogTask> taskList){
		BacklogTask[] returnArray = new BacklogTask[taskList.size()];
		ArrayList<BacklogTask> returnArrayList = new ArrayList<BacklogTask>();
		for (int i = 0;i <taskList.size()-2;i++) {
			for (int diff = i+1;diff<taskList.size()-1;diff++) {
				if (!keepLines(taskList.get(i).getTextLine(),taskList.get(diff).getTextLine())[1]) { 
					//discard task[diff] and scan for next task(diff+1) to compare with i.
					returnArray[i] = taskList.get(i);
				}else {
					returnArray[i] = taskList.get(i);
					returnArray[diff] = taskList.get(diff);
					i = diff-1; // start comparing in the next iteration at i.
					// TODO: Verify counter indeed goes from i=2 and diff = 5 to i=5 and diff = 6 in this if condition.
					// TODO: Verify counter indeed goes from i=2 and diff = 3 to i=3 and diff = 4 in this if condition.
					diff = taskList.size()+1; // get out of dif loop to go to next i iteration. 
				}
			}
		}
		
		System.out.println("before removal");
		printReturnArray(returnArray);
		returnArray =removeNullValues(returnArray);
		System.out.println("after removal");
		printReturnArray(returnArray);
		returnArrayList =backlogTaskArrayToArrayList(returnArray);
//		printReturnArrayList(returnArrayList);
		return returnArrayList;
//		return backlogTaskArrayToArrayList(returnList);
	}
	
	public static void printReturnArray(BacklogTask[] returnArray) {
		for (int i = 0; i < returnArray.length; i++) {
			if (returnArray[i] !=null) {
				System.out.println("arrayi="+i+" and="+returnArray[i].getLineNr());
			}else {
				System.out.println("null");
			}
		}
	}
	
	public static void printReturnArrayList(ArrayList<BacklogTask> returnArrayList) {
		for (int i = 0; i < returnArrayList.size(); i++) {
			if (returnArrayList.get(i) !=null) {
				System.out.println("arraylisti="+i+" and="+returnArrayList.get(i).getLineNr());
			}else {
				System.out.println("null");
			}
		}
	}
	public static ArrayList<BacklogTask> backlogTaskArrayToArrayList(BacklogTask[] returnList){
		ArrayList<BacklogTask> returnArrayList = new ArrayList<BacklogTask>();
		for (int i = 0; i < returnList.length; i++) {
			returnArrayList.add(returnList[i]);
		}
		return returnArrayList;
	}

	/**
	 * Determines which task modifications lead to task removal and not.
	 * All task line combinations tested except null lines, which should not occur.
	 * 
	 * @param line1
	 * @param line2
	 * @return index0: represents line0, index[1] represents line1 false = remove, true = keep
	 */
	public static boolean[] keepLines(String line0, String line1) {
//		System.out.println("line0="+line0);
//		System.out.println("line1="+line1);
		boolean[] returnArray = new boolean[2];
		if (containsCSort(line0) && containsCSort(line1)) {
			if (onlyDifferenceIsCSort(line0,line1)) {
				returnArray[0] = true;
				returnArray[1] = false;
			}else {
				returnArray[0] = true;
				returnArray[1] = true;
			}
		}
		if (containsCSort(line0) && !containsCSort(line1)) { //user actively removed the cSort from the task in this modification, keep it.
			returnArray[0] = true;
			returnArray[1] = true;
		}
		if (!containsCSort(line0) && containsCSort(line1)) {
			if (onlyDifferenceIsCSort(line0,line1)) {
				returnArray[0] = true;
				returnArray[1] = false; // remove the modification induced by the Csort
			}else {
				returnArray[0] = true;
				returnArray[1] = true;
			}
		}
		if (!containsCSort(line0) && !containsCSort(line1)) {
			returnArray[0] = true;
			returnArray[1] = true;
		}
		return returnArray;
	}
	
	public static boolean containsCSort(String line) {
		char quotation = (char) 34;
		String searchSubString = quotation+"customSort"+quotation+":";
		if (line!=null && line.contains(searchSubString)) {
			return true;
		}
		return false;
	}
	
	public static boolean onlyDifferenceIsCSort(String line0, String line1) {
		if (removeCSortInfoFromLine(line0).equals(removeCSortInfoFromLine(line1))) {
			return true;
		}
		return false;
	}
	
	public static String removeCSortInfoFromLine(String line) {
		if (containsCSort(line)) {
			char quotation = (char) 34;
			String searchSubString = quotation+"customSort"+quotation+":";
			
			int startIndex = line.indexOf(searchSubString);
			int endIndex =startIndex+ line.substring(startIndex).indexOf(",")+1;
			
			String leftOfCSort = line.substring(0,startIndex);
			String rightOfCSort = line.substring(endIndex,line.length());
//			System.out.println("returning with CSort="+leftOfCSort+rightOfCSort);
			return leftOfCSort+rightOfCSort;
		}else {
//			System.out.println("returning"+line);
			return line;
		}
	}
	
	/**
	 * if no multiples exist, create the first one, and add the task to it. If
	 * multiples exist, check the uuid of the first task in the multiple, it is the
	 * same as the uuid of the incoming task, add the incoming task to the multiple,
	 * if it does not match, check the next multiple, untill all multiples are
	 * checked, if no matching multiple has been found, add a new multiple to the
	 * collection and add the task in it.
	 * 
	 * @param catalog
	 * @param backlogTask
	 * @return
	 */
	public static BacklogTaskCatalog generateCatalog(BacklogTaskCatalog catalog, BacklogTask backlogTask) {
		ArrayList<BacklogTask> backlogTaskList = new ArrayList<BacklogTask>();
		BacklogTaskMultiples multiple = new BacklogTaskMultiples(backlogTaskList);
		boolean taskAllocated = false;
		if (catalog == null || catalog.getMultiples().size() == 0) {
			backlogTaskList.add(backlogTask);
			multiple.setMultiples(backlogTaskList);
			catalog.getMultiples().add(multiple);
			taskAllocated = true;
		} else {
			for (int i = 0; i < catalog.getMultiples().size(); i++) {
				if (catalog.getMultiples().get(i).getMultiples().get(0).getTwUUID().equals(backlogTask.getTwUUID())) {
					catalog.getMultiples().get(i).getMultiples().add(backlogTask);
					taskAllocated = true;
				}
			}
			if (!taskAllocated) {
				catalog.getMultiples().add(multiple);
				catalog.getMultiples().get(catalog.getMultiples().size() - 1).getMultiples().add(backlogTask);
			}
		}
		return catalog;
	}

	public static ArrayList<String> readLines(String backlogPath, String backlogFileName) {
		ArrayList<String> lines = new ArrayList<String>();
		lines = ReadFiles.readFiles(backlogPath + backlogFileName);
		return lines;
	}

	public static BacklogTask createBacklogTask(String line, int lineNr) {
		String twUuid = findTaskUuid(line, false);
		String parentTwUuid = null;

		// get recurrent data
		boolean recurring = isRecurringTask(line);
		if (recurring) {
			parentTwUuid = findTaskUuid(line, true);
		}

		// create task based on absorbed info from line.
		BacklogTask backlogTask = new BacklogTask(twUuid, parentTwUuid, line, recurring, lineNr);

		return backlogTask;
	}

	private static boolean isRecurringTask(String line) {
		char quotation = (char) 34;
		String recurrenceIdentifier0 = quotation + "parent" + quotation + ":" + quotation;
		String recurrenceIdentifier1 = quotation + "status" + quotation + ":" + quotation + "recurring" + quotation;
		if (line.contains(recurrenceIdentifier0) || line.contains(recurrenceIdentifier0)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Creates the desired substring that used to find the uuid of the task or of
	 * the parent task depending on the parentUuid boolean.
	 * 
	 * @param line
	 * @param parentUuid
	 * @return
	 */
	public static String findTaskUuid(String line, boolean parentUuid) {
		char quotation = (char) 34;
		int twUuidLength = 36;
		String uuidIdentifier;
		if (parentUuid) {
			uuidIdentifier = quotation + "parent" + quotation + ":" + quotation;
		} else {
			uuidIdentifier = quotation + "uuid" + quotation + ":" + quotation;
		}
		return findSubstring(line, uuidIdentifier, twUuidLength);
	}

	/**
	 * Finds the tw uuid in the task description of the line that originates from
	 * backlog.data
	 * 
	 * @param line
	 * @return
	 */
	public static String findSubstring(String line, String subString, int targetStringLength) {
//		System.out.println("IncomingLine = "+line);
		String twUuid = null;
		int substringEndIndex;

		if (line.contains(subString)) {

			// get index of occurence of the substring(including quotation marks):"uuid":"
			substringEndIndex = line.indexOf(subString) + subString.length();

			// get the next 36 characters representing the tw uuid
			twUuid = line.substring(substringEndIndex, substringEndIndex + targetStringLength);

			return twUuid;
		} else {
			return null;
		}
	}

	/**
	 * Removes null values from an array of BacklogTask objects.
	 * 
	 * @param taskList
	 * @return
	 */
	public static BacklogTask[] removeNullValues(BacklogTask[] taskList) {
		taskList = Arrays.stream(taskList).filter(Objects::nonNull).toArray(BacklogTask[]::new);
		return taskList;
	}
}