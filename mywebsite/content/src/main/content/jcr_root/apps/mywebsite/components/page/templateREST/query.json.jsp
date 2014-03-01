<%@include file="/apps/mywebsite/global.jsp"%>
<%@ page import="org.apache.sling.commons.json.io.*,org.w3c.dom.*" %><%
String filter = request.getParameter("filter");
   com.pc.test.cqutil.rest.Distance cs = sling.getService(com.pc.test.cqutil.rest.Distance.class);
   
String myJSON= cs.getDistance() ;
    
//Send the data back to the client
JSONWriter writer = new JSONWriter(response.getWriter());
writer.object();
writer.key("json");
writer.value(myJSON);
   
writer.endObject();
%>