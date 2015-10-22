package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBHelper;
import entity.exam_question;

public class questionDAO {
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	DBHelper dbHelper = new DBHelper();
	
	public ArrayList<exam_question> getAllExamQuestions()
	{
		ArrayList<exam_question> list = new ArrayList<exam_question>();
		
		try {
			connection = dbHelper.getConnection();
			String sql = "SELECT * FROM exam;";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				exam_question eQuestion = new exam_question();
				eQuestion.setID(Integer.parseInt(resultSet.getString(1)));
				eQuestion.setType(resultSet.getString(2));
				eQuestion.setQuestion(resultSet.getString(3));
				eQuestion.setOptionA(resultSet.getString(4));
				eQuestion.setOptionB(resultSet.getString(5));
				eQuestion.setOptionC(resultSet.getString(6));
				eQuestion.setOptionD(resultSet.getString(7));
				eQuestion.setAnswer(resultSet.getString(8));
				eQuestion.setKeyword(resultSet.getString(9));
				list.add(eQuestion);		
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if (resultSet!=null) {
				try {
					resultSet.close();
					resultSet=null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement!=null) {
				try {
					statement.close();
					statement = null;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (connection!=null) {
				dbHelper.close();
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			}
		}	
	}
	
	public exam_question getQuestionById(int id){
		try {
			connection = dbHelper.getConnection();
			String sql = "SELECT * FROM exam WHERE ID=?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				exam_question eQuestion = new exam_question();
				eQuestion.setID(Integer.parseInt(resultSet.getString("ID")));
				eQuestion.setType(resultSet.getString(2));
				eQuestion.setQuestion(resultSet.getString("QUESTION"));
				eQuestion.setOptionA(resultSet.getString(4));
				eQuestion.setOptionB(resultSet.getString(5));
				eQuestion.setOptionC(resultSet.getString(6));
				eQuestion.setOptionD(resultSet.getString(7));
				eQuestion.setAnswer(resultSet.getString(8));
				eQuestion.setKeyword(resultSet.getString(9));
				return eQuestion;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if (resultSet!=null) {
				try {
					resultSet.close();
					resultSet = null;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (statement!=null) {
				try {
					statement.close();
					statement = null;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (connection!=null) {
				dbHelper.close();
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public String getAnswerById(int id){

		try {
			connection = dbHelper.getConnection();
			String sql = "SELECT ANSWER FROM exam WHERE ID=?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getString("ANSWER");
			}
			else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if (resultSet!=null) {
				try {
					resultSet.close();
					resultSet=null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (statement!=null) {
				try {
					statement.close();
					statement=null;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (connection!=null) {
				dbHelper.close();
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			}
		}
	}

	public String getKeywordById(int id){
		try {
			connection = dbHelper.getConnection();
			String sql = "SELECT KEYWORD FROM exam WHERE ID=?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getString(1);
			}
			else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if (resultSet!=null) {
				try {
					resultSet.close();
					resultSet=null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (statement!=null) {
				try {
					statement.close();
					statement=null;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (connection!=null) {
				dbHelper.close();
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			}
		}
		
	}
	public void closeConnection() throws SQLException
	{
		dbHelper.close();
	}
	
	public static void main(String[] args) {
			questionDAO qDao = new questionDAO();
			ArrayList<exam_question> list = qDao.getAllExamQuestions();
			if (list!=null&&list.size()>0) {
				for (int i = 1; i <= list.size(); i++) {
					exam_question eQuestion = list.get(i);
					System.out.println("*****************START*****************");
					System.out.println(eQuestion.getID()+"."+eQuestion.getQuestion());
					System.out.println(eQuestion.getOptionA());
					System.out.println(eQuestion.getOptionB());
					System.out.println(eQuestion.getOptionC());
					System.out.println(eQuestion.getOptionD());
					System.out.println(qDao.getKeywordById(i));
					System.out.println("*****************END*****************");
				}
			}
			
		   exam_question question = qDao.getQuestionById(2);
			System.out.println("***************START*****************");
			System.out.println(question.getID()+" "+question.getQuestion());
			System.out.println(question.getOptionA());
			System.out.println(question.getOptionB());
			System.out.println(question.getOptionC());
			System.out.println(question.getOptionD());
			System.out.println(question.getKeyword());
			System.out.println("*******************END*****************");
			String answer = qDao.getAnswerById(10);
			System.out.println(answer);
			
			try {
				qDao.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
