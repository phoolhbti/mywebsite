<%@ page import="org.apache.sling.commons.json.io.*,com.pc.test.cqutil.jcrquery.*" %><%
String first = request.getParameter("first");
String last = request.getParameter("last");
String phone = request.getParameter("phone");
String desc = request.getParameter("desc");
 
//invoke the CustomerServer object's injestCustData method
out.println("persistes json jsp");
com.pc.test.cqutil.jcrquery.CustomerServiceSQLImpl custService = new com.pc.test.cqutil.jcrquery.CustomerServiceSQLImpl();
out.println("object is"+custService);
int myPK = custService.injestCustData(first, last, phone, desc) ; 
  
//Send the data back to the client 
JSONWriter writer = new JSONWriter(response.getWriter());
writer.object();
writer.key("pk");
writer.value(myPK);
 
writer.endObject();
%>