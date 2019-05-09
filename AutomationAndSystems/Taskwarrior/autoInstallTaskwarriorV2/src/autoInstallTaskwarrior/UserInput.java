package autoInstallTaskwarrior;

import java.util.ArrayList;
import java.util.List;

public class UserInput {
	private int nrOfQuestions;
	private List<String> questions = new ArrayList<String>();
	private List<String> answer = new ArrayList<String>();
	private List<Boolean> ansCorrectForm= new ArrayList<Boolean>();
	
	
	UserInput(){
		
	}

	public void appendQuestion(String question) {
		this.questions.add(question);
		this.nrOfQuestions++;
	}
	
	public void appendAnswer(String answer) {
		this.answer.add(answer);
	}

	/**
	 * @return the nrOfQuestions
	 */
	public int getNrOfQuestions() {
		return nrOfQuestions;
	}


	/**
	 * @return the questions
	 */
	public List<String> getQuestions() {
		return questions;
	}


	/**
	 * @return the answer
	 */
	public List<String> getAnswer() {
		return answer;
	}


	/**
	 * @return the ansCorrectForm
	 */
	public List<Boolean> getAnsCorrectForm() {
		return ansCorrectForm;
	}
	
	
	
	
}
