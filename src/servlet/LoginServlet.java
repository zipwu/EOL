package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.RandArray;
import dao.UserDAO;
import entity.User;


public class LoginServlet extends HttpServlet {

	private int length;
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				try {
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					System.out.println(username+"  "+password);
					String path = null;//初始化跳转页面的path
					UserDAO userDao = new UserDAO();//初始化用户数据访问对象实例
					User user = new User();//初始化用户实例
					user = userDao.getUserbyUsername(username);//通过用户数据访问对象实例，获取用户实例
					request.getSession().setAttribute("user", user);
					System.out.println(user.getName());//获得用户姓名，并输出。
					request.getSession().setAttribute("name", user.getName());//将用户姓名放入session中，以供其他页面调用
					length = this.getLength();
					System.out.println(length);
					RandArray randArray = new RandArray();
					int[] randnum = randArray.randarray(1,47,20);//生成从1到13之间的随机数，不重复的10个
					request.getSession().setAttribute("randarray", randnum);//将随机数组放入session中，以供其他页面调用
					
					
						if (userDao.isAllowed(username, password)) {
							path ="index.jsp";
						}else {
							path = "Login.html";
						}
						response.sendRedirect(path);
				} catch (Exception e) {
					System.out.println("登陆失败");
					String path = "Login.html";
					response.sendRedirect(path);
				}
				
	}

}
