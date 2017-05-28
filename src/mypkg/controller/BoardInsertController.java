package mypkg.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.model.BoardDao;
import mypkg.model.boardBean;

public class BoardInsertController implements SuperController{
	@Override
	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		boardBean bean = new boardBean();
		
		bean.setSubject(request.getParameter("subject"));
		bean.setWriter(request.getParameter("writer"));
		bean.setwDate(request.getParameter("wDate"));
		bean.setContents(request.getParameter("contents"));
		
		System.out.println(bean);
		BoardDao bdao = new BoardDao();
		int cnt = bdao.InsertData(bean);
		
		String url = "/ex0801?command=boList";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
