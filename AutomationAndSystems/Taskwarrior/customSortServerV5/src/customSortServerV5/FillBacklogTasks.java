package customSortServerV5;

import java.io.File;
import java.util.ArrayList;

public class FillBacklogTasks {

	public static BacklogTaskCatalog manageBacklogFilling() {
		ArrayList<String> lines = new ArrayList<>();
		String backlogPath = hardCoded.getUbuntuFilePath();
		String backlogFileName = hardCoded.getBacklogFileName();
		BacklogTaskCatalog catalog = null;

		if (ReadFiles.checkIfFileExist(backlogPath, backlogFileName)) {
			lines = readLines(backlogPath, backlogFileName);
			for (int i = 1; i < lines.size(); i++) { // 1 to skip the first line containing the tw uuid.
				createBacklogTask(lines.get(i));
			}
		}

		return catalog;
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

		return catalog;
	}

	public static ArrayList<String> readLines(String backlogPath, String backlogFileName) {
		ArrayList<String> lines = new ArrayList<String>();
		lines = ReadFiles.readFiles(backlogPath + backlogFileName);
		return lines;
	}

	public static BacklogTask createBacklogTask(String line) {
		String twUuid = findTaskUuid(line, false);
		String parentTwUuid = null;

		// get recurrent data
		boolean recurring = isRecurringTask(line);
		if (recurring) {
			parentTwUuid = findTaskUuid(line, true);
		}

		BacklogTask backlogTask = new BacklogTask(twUuid, parentTwUuid, line, recurring);
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

}
