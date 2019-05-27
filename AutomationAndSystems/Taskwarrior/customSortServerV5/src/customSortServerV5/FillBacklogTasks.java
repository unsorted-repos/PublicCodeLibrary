package customSortServerV5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class FillBacklogTasks {

	/**
	 * TODO: Implement interface, extends etc and merge the Task and BacklogTask object like two different types of houses in JAVA assignment.
	 * 
	 * @return
	 */
	public static BacklogTaskCatalog manageBacklogFilling() {
		ArrayList<String> lines = new ArrayList<>();
		String backlogPath = hardCoded.getUbuntuFilePath();
		String backlogFileName = hardCoded.getBacklogFileName();
		
		ArrayList<BacklogTask> backlogTaskList = new ArrayList<BacklogTask>();
		BacklogTaskMultiples multiple = new BacklogTaskMultiples(backlogTaskList);
		ArrayList<BacklogTaskMultiples> multiples = new ArrayList<BacklogTaskMultiples>();
		BacklogTaskCatalog catalog = new BacklogTaskCatalog(multiples);

		BacklogTaskCatalog filteredCatalog;
		
		if (ReadFiles.checkIfFileExist(backlogPath, backlogFileName)) {
			lines = readLines(backlogPath, backlogFileName);
			for (int i = 1; i < lines.size(); i++) { // 1 to skip the first line containing the tw uuid.
				
				catalog = generateCatalog(catalog,createBacklogTask(lines.get(i),i));
			}
		}

		filteredCatalog = filterBacklogCatalog(catalog);
		
		
		return filteredCatalog;
	}
	
	public static BacklogTaskCatalog orderCatalog(BacklogTaskCatalog filteredCatalog, int nrOfLines) {
		BacklogTask[] taskList = new BacklogTask[nrOfLines];
		int taskLineNr;
		for (int i = 0; i < filteredCatalog.getMultiples().size(); i++) {
			taskLineNr =filteredCatalog.getMultiples().get(i).getMultiples().get(0).getLineNr(); 
			taskList[taskLineNr] = filteredCatalog.getMultiples().get(i).getMultiples().get(0);
		}
		
		return filteredCatalog;
	}
	
	
	
	/**
	 * Generates a new catalog that contains all multiples with just the last entry/task of each multiple in it. 
	 * @param catalog
	 */
	public static BacklogTaskCatalog filterBacklogCatalog(BacklogTaskCatalog catalog) {
		ArrayList<BacklogTask> originalMultiple = new ArrayList<BacklogTask>();
		ArrayList<BacklogTask> filteredMultiple = new ArrayList<BacklogTask>();
		
		ArrayList<BacklogTaskMultiples> multiples =  new ArrayList<BacklogTaskMultiples>();
		BacklogTaskCatalog filteredCatalog = new BacklogTaskCatalog(multiples);
		
		BacklogTaskMultiples filteredMultipleBacklog; 
		
		for (int i = 0; i < catalog.getMultiples().size();i++) {
			filteredMultiple.clear();
			originalMultiple = catalog.getMultiples().get(i).getMultiples(); // store original multiple
			filteredMultiple.add(originalMultiple.get(originalMultiple.size()-1)); // create new multiple with last task of original multiple
			System.out.println("adding task:"+originalMultiple.get(originalMultiple.size()-1).getTwUUID());
			filteredMultipleBacklog = new BacklogTaskMultiples(filteredMultiple); // create new backlog multiple object with single task multiple
			System.out.println("Size1="+filteredMultipleBacklog.getMultiples().size());
			filteredCatalog.getMultiples().add(filteredMultipleBacklog); // add the single task multiple
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
		if (catalog == null || catalog.getMultiples().size()== 0) {
			backlogTaskList.add(backlogTask);
			multiple.setMultiples(backlogTaskList);
			catalog.getMultiples().add(multiple);
			taskAllocated = true;
			System.out.println("First new multiple");
		}else {
			for (int i = 0; i < catalog.getMultiples().size(); i++) {
				if (catalog.getMultiples().get(i).getMultiples().get(0).getTwUUID().equals(backlogTask.getTwUUID())) {
					catalog.getMultiples().get(i).getMultiples().add(backlogTask);
					taskAllocated = true;
					System.out.println("Added to existing multiple.");
				}
			}
			if (!taskAllocated) {
				System.out.println("Creating new multiple");
				catalog.getMultiples().add(multiple);
				catalog.getMultiples().get(catalog.getMultiples().size()-1).getMultiples().add(backlogTask);
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

		BacklogTask backlogTask = new BacklogTask(twUuid, parentTwUuid, line, recurring, lineNr);
//		String twUuid, String parentUuid, String textLine
		return backlogTask;
	}

	/**
	 * TODO: Find better word than multiples that describes the conglomeration of
	 * multiple task lines in backlog.data that have the same uuid (I.e. duplicates
	 * of a task representing different versions/modifications of the task through
	 * time)
	 * 
	 * @param line
	 * @return
	 */
	public static BacklogTaskCatalog assignMultiples(ArrayList<BacklogTaskCatalog> catalog,
			ArrayList<BacklogTask> task) {
		ArrayList<BacklogTaskMultiples> multiples = new ArrayList<>();

		return null;
	}

	public static boolean isRecurringTask(String line) {
		char quotation = (char) 34;
		boolean isRecurrent;
		String recurrenceIdentifier0 = quotation + "parent" + quotation + ":" + quotation;
		String recurrenceIdentifier1 = quotation + "status" + quotation + ":" + quotation + "recurring" + quotation;
		if (line.contains(recurrenceIdentifier0) || line.contains(recurrenceIdentifier0)) {
			return true;
		} else {
			return false;
		}

	}

	public static String findTaskUuid(String line, boolean parentUuid) {
		char quotation = (char) 34;
		int twUuidLength = 36;
		String uuidIdentifier;
		if (parentUuid) {
			uuidIdentifier = quotation + "parent" + quotation + ":" + quotation;
			if (findSubstring(line, uuidIdentifier, twUuidLength) != null) {
//				System.out.println("Incoming line ="+line);
//				System.out.println("Tw uuuid"+findSubstring(line, uuidIdentifier, twUuidLength));
			}
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
	 * @param taskList
	 * @return
	 */
	public static BacklogTask[] removeNullValues(BacklogTask[] taskList) {
		taskList = Arrays.stream(taskList).filter(Objects::nonNull).toArray(BacklogTask[]::new);	    
		return taskList;
	}
}
