package autoInstallTaskwarrior;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CronJobTest {
	
	@Test
	void testGoodBehaviorCompleteCommand() {
		String cronTiming = "*/1 * * * *";
		String cronCommand = "sudo touch";
		String cronPath = "/test folder/";
		String cronFileName ="some File";
		CronJob cronJob = new CronJob(cronTiming, cronCommand, cronPath, cronFileName, true);
		String expectedResult = "*/1 * * * * sudo touch " + (char)34 + "/test folder/some File" + (char)34;
		
		assertTrue(cronJob.getCompleteCommand().contentEquals(expectedResult));
	}
	
	@Test
	void testGoodBehaviorInCompleteCommand() {
		String cronTiming = "*/1 * * * *";
		String cronCommand = "sudo touch";
		String cronPath = "/test folder";
		String cronFileName ="some File";
		CronJob cronJob = new CronJob(cronTiming, cronCommand, cronPath, cronFileName, true);		
		String expectedResult = "*/1 * * * * sudo touch " + (char)34 + "/test folder/some File" + (char)34;
		
		assertTrue(cronJob.getCompleteCommand().contentEquals(expectedResult));
	}
	
	@Test
	void testBadBehaviorInCompleteCommand() {
		String cronTiming = "*/1 * * * *";
		String cronCommand = "sudo touch";
		String cronPath = "/test folder";
		String cronFileName ="some File";
		CronJob cronJob = new CronJob(cronTiming, cronCommand, cronPath, cronFileName, true);
		String expectedResult = "*/1 * * * * sudo touch " + (char)34 + "/test foldersome File" + (char)34;
		
		assertFalse(cronJob.getCompleteCommand().contentEquals(expectedResult));
	}
	
	
}
