package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;

import dao.JFreeChartDAO;
import dao.ScoreRecordDAO;
import dao.questionDAO;
import entity.User;
import util.PieChartData;
import util.isRight;

public class SubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			int[] num = (int[]) request.getSession().getAttribute("randarray");
			int count = 0;
			isRight iRight = new isRight();
			for (int i = 0;i<num.length;i++) {System.out.print(num[i]+" ");}
			HashMap<String, Integer> KeywordMap = new HashMap<String, Integer>();
			questionDAO qDao = new questionDAO();
			
			for (int i = 0; i < num.length; i++) {	
				if (!KeywordMap.containsKey(qDao.getKeywordById(num[i]))) {
					KeywordMap.put(qDao.getKeywordById(num[i]), 1);
				}else {
					KeywordMap.put(qDao.getKeywordById(num[i]), KeywordMap.get(qDao.getKeywordById(num[i]))+1);
				}
				if(request.getParameterValues(String.valueOf(num[i]))!=null) {
					System.out.print(num[i]+" ");
					String answer = (String) request.getSession().getAttribute(String.valueOf(num[i]));	
					String[] option = request.getParameterValues(String.valueOf(num[i]));
					System.out.println(answer);
					for (int j = 0; j < option.length; j++) {
						System.out.print(option[j]+",");
					}
					System.out.println("");
					if (iRight.isTrue(option, answer)) {
						count = count + 5;
						System.out.println("题目"+i+"正确");
					}
					else {
						count = count + 0;
						System.out.println("题目"+i+"错误");
					}
				}
				else {
					count = count + 0;
					System.out.println("题目"+i+"错误");
				}
			}
			
			request.getSession().setAttribute("set", KeywordMap);
			ArrayList<PieChartData> listPieChart = new ArrayList<PieChartData>();
			for (Entry<String, Integer> entry : KeywordMap.entrySet()) {
				PieChartData pData = new PieChartData();
				pData.setKeyString(entry.getKey());
				pData.setValueDouble(Double.valueOf(String.valueOf(entry.getValue())));
				listPieChart.add(pData);
			}
			request.getSession().setAttribute("listPie", listPieChart);
			JFreeChartDAO jChart = new JFreeChartDAO();
			JFreeChart  pieChart = jChart.PieChart(listPieChart, "题型分布");
			request.getSession().setAttribute("Pie", pieChart);
			
			String filenamePie = ServletUtilities.saveChartAsPNG(pieChart, 700, 500, null, request.getSession());
			String graphURLPie = request.getContextPath() + "/DisplayChart?filename=" + filenamePie;
			System.out.println(graphURLPie);
			request.getSession().setAttribute("graphURLPie", graphURLPie);
			
			
			System.out.println(count);
			request.getSession().setAttribute("score", String.valueOf(count));
			String name = (String)request.getSession().getAttribute("name");
			System.out.println(name);
			
			User user = (User) request.getSession().getAttribute("user");
			System.out.println(user.getName());
			ScoreRecordDAO scoreRecordDAO  = new ScoreRecordDAO();
			scoreRecordDAO.insertScoreRecord(user, count);
			count=0;
			String path ="ExamResult.jsp";
			response.sendRedirect(path);
			
	}

}
