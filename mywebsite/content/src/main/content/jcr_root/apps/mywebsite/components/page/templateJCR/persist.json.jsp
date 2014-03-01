<%@include file="/apps/mywebsite/global.jsp"%>
<%@ page import="org.apache.sling.commons.json.io.*,com.adobe.cq.*" %><%
String first = request.getParameter("first");
String last = request.getParameter("last");
String phone = request.getParameter("phone");
String desc = request.getParameter("desc");
 
com.pc.test.cqutil.jcrquery.CustomerService cs = sling.getService(com.pc.test.cqutil.jcrquery.CustomerService.class);
 
int myPK = cs.injestCustData(first, last, phone, desc) ; 
  
//Send the data back to the client 
JSONWriter writer = new JSONWriter(response.getWriter());
writer.object();
writer.key("pk");
writer.value(myPK);
 
writer.endObject();
%>