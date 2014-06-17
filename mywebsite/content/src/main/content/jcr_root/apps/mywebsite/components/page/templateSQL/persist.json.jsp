<%@ page import="org.apache.sling.commons.json.io.JSONWriter,
				 com.pc.jcrquery.CustomerServiceSQLImpl" %><%
String first = request.getParameter("first");
String last = request.getParameter("last");
String phone = request.getParameter("phone");
String desc = request.getParameter("desc");
 
//invoke the CustomerServer object's injestCustData method
out.println("persistes json jsp");
CustomerServiceSQLImpl custService = new CustomerServiceSQLImpl();
out.println("object is"+custService);
int myPK = custService.injestCustData(first, last, phone, desc) ; 
  
//Send the data back to the client 
JSONWriter writer = new JSONWriter(response.getWriter());
writer.object();
writer.key("pk");
writer.value(myPK);
 
writer.endObject();
%>