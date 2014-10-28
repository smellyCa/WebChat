<%@ page language="java" import="java.util.*,com.WebChat.dao.*,com.WebChat.pojo.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
	RecordDao recordDao=new RecordDao();
	List<Record> records=recordDao.findAllRecords();
	request.setAttribute("records", records);
%>
<!DOCTYPE HTML>
<html>
  <head>
	<meta http-equiv="refresh" content="2">
  </head>
  
  <body>
    <!--用application存储聊天记录
     Object o=application.getAttribute("ms");
    List list=o==null?null:(List)o;
    if(list!=null&&list.size()>0){
	    for(int i=list.size()-1;i>=0;i--){
	    	out.write((String)list.get(i)+"<br/>");
	    }
	}-->
	
	<c:if test="${empty records}">
		暂无消息
	</c:if>
	<c:if test="${not empty records}">
		<c:forEach items="${records}" var="record">
			${record.words} <br/>
		</c:forEach>
	</c:if>
  </body>
</html>
