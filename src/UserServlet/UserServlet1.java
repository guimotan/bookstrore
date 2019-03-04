package UserServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserInfoDao;
import dao.impl.UserInfoDaoImpl;
import entity.UserInfo;

/**
 * Servlet implementation class UserServlet1
 */
@WebServlet("/User")
public class UserServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
  
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserInfoDao dao = new UserInfoDaoImpl();
		UserInfo userinfo = dao.query(username, password);
		if(userinfo == null) {//µÇÂ¼Ê§°Ü
			//Ìø×ªµ½µÇÂ¼Ò³Ãæ
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}else {
			
		}
	}



}
