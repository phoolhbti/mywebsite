<%@include file="/libs/foundation/global.jsp"%>
<%@ page import="org.apache.sling.commons.json.io.*,org.w3c.dom.*" %><%
String filter = request.getParameter("filter");
  
com.pc.test.cqutil.jcrquery.CustomerService cs = sling.getService(com.pc.test.cqutil.jcrquery.CustomerService.class);
  
String XML = cs.getCustomerData(filter) ; 
   
//Send the data back to the client 
JSONWriter writer = new JSONWriter(response.getWriter());
writer.object();
writer.key("xml");
writer.value(XML);
  
writer.endObject();
%>