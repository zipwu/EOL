package entity;

public class exam_question {
	
	private int ID;
	private String type;
	private String Question;
	private String OptionA;
	private String OptionB;
	private String OptionC;
	private String OptionD;
	private String Answer;
	private String Keyword;

	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public String getOptionA() {
		return OptionA;
	}

	public void setOptionA(String optionA) {
		OptionA = optionA;
	}

	public String getOptionB() {
		return OptionB;
	}

	public void setOptionB(String optionB) {
		OptionB = optionB;
	}

	public String getOptionC() {
		return OptionC;
	}

	public void setOptionC(String optionC) {
		OptionC = optionC;
	}

	public String getOptionD() {
		return OptionD;
	}

	public void setOptionD(String optionD) {
		OptionD = optionD;
	}

	public String getAnswer() {
		return Answer;
	}

	public void setAnswer(String answer) {
		Answer = answer;
	}

	public String getKeyword() {
		return Keyword;
	}

	public void setKeyword(String keyword) {
		Keyword = keyword;
	}

}
