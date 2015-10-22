package util;

public class isRight {
	
	public boolean isTrue(String[] option,String answer){
		boolean flag = true;
		String[] answerArray = answer.split(",");
		if (option.length==answerArray.length) {
			for (int i = 0; i < option.length; i++) {
				if (option[i].equals(answerArray[i])) {
					flag = flag && true;
				}else {
					flag = flag && false;
				}
			}
		}else {
			flag = false;
		}
		return flag;
	} 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String answer = "A,C,D";
		String[] option ={"A","C","D"};
		boolean flag = new isRight().isTrue(option, answer);
		System.out.println(flag);

	}

}
