package mypkg.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.model.BoardDao;
import mypkg.model.boardBean;
import mypkg.util.Paging;

public class BoardListController implements SuperController {
	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
				
		BoardDao bdao = new BoardDao();
		
		String mode = request.getParameter("mode");
		if(mode == null || mode.equals("null") || mode.equals("")){
			mode = "all";
		}
		
		String keyword = request.getParameter("keyword");
		if(keyword == null || keyword.equals("null") || keyword.equals("")){
			keyword = "";
		}
		
		String _pageNumber = "1";
		String _pageSize = "10";
		int totalCount = 59;
		
		String myurl = request.getContextPath()+"ex0801?command=boList";
		
		System.out.println("mode : "+mode);
		System.out.println("keyword : "+keyword);
		System.out.println("_pageNumber : "+_pageNumber);
		System.out.println("_pageSize : "+_pageSize);
		System.out.println("totalCount : "+totalCount);
		System.out.println("myurl : "+myurl);
		
		Paging page = new Paging(_pageNumber, _pageSize, totalCount, myurl, mode, keyword);
		
		int beginRow = page.getBeginRow();
		int endRow = page.getEndRow();
		
		//List<boardBean> lists = bdao.SelectDataList(beginRow, endRow);
		
		//request.setAttribute("lists", lists);
		
		String url = "/ex0801?command=boList&";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
