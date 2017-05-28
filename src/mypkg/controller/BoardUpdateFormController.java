package mypkg.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.model.BoardDao;
import mypkg.model.boardBean;

public class BoardUpdateFormController implements SuperController{

	@Override
	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		BoardDao bdao = new BoardDao();
		boardBean bean = new boardBean();
		
		int pk = Integer.parseInt(request.getParameter("seq"));
		
		bean = bdao.SelectDataList(pk);
		
		request.setAttribute("bean", bean);
		
		String url = "view/boUpdateForm.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
