package mypkg.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypkg.model.Member;
import mypkg.model.MemberDao;

public class loginController implements SuperController{

	@Override
	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		MemberDao mdao = new MemberDao();
		
		
		
		String id = request.getParameter("id");
		Member bean = mdao.SelectDataByPk(id);
		
		String formId = request.getParameter("id");
		String formPass = request.getParameter("password");
		
		
		if (bean != null) {
			
				if (formPass.equals(bean.getPassword())) {
					System.out.println("3");
					session.setAttribute("loginfo", bean);
				}
		}else{
			System.out.println("id ¤¤¤¤");
		}
		String url = "main.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
