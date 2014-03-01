<%@page import="java.util.List" %><%
%><%@page import="com.pc.test.cqutil.multipageform.*" %><%
%><%@include file="/apps/mywebsite/global.jsp"%>
<%
    //init; set first page
    if (request.getAttribute("mywebsite/components/multipageform/form/displayPage") == null) {
        request.setAttribute("mywebsite/components/multipageform/form/displayPage", "inputPage1");
    }

    //init; set field defaults
    List<FormField> fields = com.pc.test.cqutil.multipageform.SampleUtil.getFieldNames();
    for (FormField field: fields) {
        if (request.getAttribute("mywebsite/components/multipageform/form/" + field.getName()) == null) {
            request.setAttribute("mywebsite/components/multipageform/form/" + field.getName(), "");
        }
    }

    //-------
    //very simple display logic. If it's an 'input' page use the input script, otherwise display a summary.
    //extend this as needed.
    
    String displayPage = (String) request.getAttribute("mywebsite/components/multipageform/form/displayPage");
    if (displayPage == null || displayPage.startsWith("input")) {
%>
<cq:include script="input.jsp" />
<%
    } else /*if (displayPage.equals("summary"))*/ {
%>
<cq:include script="summary.jsp" />
<%
    }
%>