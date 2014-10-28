package com.WebChat.Servlet;

import java.io.IOException;

//import java.util.ArrayList;

//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.ws.rs.core.Application;


import com.WebChat.dao.RecordDao;
import com.WebChat.pojo.Record;

public class PostRecordServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String message=request.getParameter("words");
//用application存储聊天记录		
//		ServletContext application=getServletContext();
//		Object ms=application.getAttribute("ms");
//	    List list=ms==null?new ArrayList():(List)ms;
//	    if(message==null||message.trim().length()==0){
//	    	
//	    }
//	    else{
//	    	list.add(message);
//	    }
//	    application.setAttribute("ms",list);
		
//用数据库存储聊天记录
		RecordDao recordDao=new RecordDao();
		Record record=new Record();
		record.setWords(message);
		int i=recordDao.addRecord(record);
		if (i==-1) {
			System.out.println("Fail to post");
			response.sendRedirect("sendMessage.jsp");
		}
		else{
			response.sendRedirect("sendMessage.jsp");
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
