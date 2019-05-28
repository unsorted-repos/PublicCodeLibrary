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
		BacklogTaskCatalog orderedFilteredCatalog;
		BacklogTask[] orderedFilteredTaskList;

		if (ReadFiles.checkIfFileExist(backlogPath, backlogFileName)) {
			lines = readLines(backlogPath, backlogFileName);
			for (int i = 1; i < lines.size(); i++) { // 1 to skip the first line containing the tw uuid.
				catalog = generateCatalog(catalog, createBacklogTask(lines.get(i), i));
			}
		}

		filteredCatalog = filterBacklogCatalog(catalog);
		orderedFilteredCatalog = orderCatalog(filteredCatalog, lines.size() - 1); // -1 to remove the entry of the first
																					// line
		orderedFilteredTaskList = catalogToTaskArray(orderedFilteredCatalog);

		CreateFiles.writeFileContent("/home/" + hardCoded.getLinuxUserName() + "/.task/", "backlogCopy.data",
				orderedFilteredTaskList);
		return filteredCatalog;
	}

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

	/**
	 *  Converts the catalog to a convenient array of tasks to facilitate writing the catalog
	 *  back to backlog.data.
	 * @param filteredOrderedCatalog
	 * @return
	 */
	private static BacklogTask[] catalogToTaskArray(BacklogTaskCatalog filteredOrderedCatalog) {
		int catalogSize = filteredOrderedCatalog.getMultiples().size();
		BacklogTask[] taskList = new BacklogTask[catalogSize];
		for (int i = 0; i < catalogSize; i++) {
			taskList[i] = filteredOrderedCatalog.getMultiples().get(i).getMultiples().get(0);
		}
		return taskList;
	}

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
	private static BacklogTaskCatalog orderCatalog(BacklogTaskCatalog filteredCatalog, int nrOfLines) {
		BacklogTask[] taskList = new BacklogTask[nrOfLines + 1];
		int taskLineNr;
		for (int i = 0; i < filteredCatalog.getMultiples().size(); i++) {
			taskLineNr = filteredCatalog.getMultiples().get(i).getMultiples().get(0).getLineNr();
			taskList[taskLineNr] = filteredCatalog.getMultiples().get(i).getMultiples().get(0);
		}

		return filteredCatalog;
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

			// add the single task multiple
			filteredCatalog.getMultiples().add(filteredMultipleBacklog);
		}
		return filteredCatalog;
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
