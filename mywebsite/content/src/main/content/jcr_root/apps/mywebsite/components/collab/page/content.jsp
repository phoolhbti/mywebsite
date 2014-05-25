<%--
  Copyright 1997-2009 Day Management AG
  Barfuesserplatz 6, 4001 Basel, Switzerland
  All Rights Reserved.

  This software is the confidential and proprietary information of
  Day Management AG, ("Confidential Information"). You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with Day.

  ==============================================================================

  Blog: Content script (included by body.jsp)

  ==============================================================================

--%><%@ page import="com.day.cq.collab.blog.Blog,
                     com.day.cq.collab.blog.BlogManager" %><%
%><%@include file="/apps/mywebsite/global.jsp" %><%

    BlogManager blogMgr = resource.getResourceResolver().adaptTo(BlogManager.class);
    Blog blog = blogMgr.getBlog(slingRequest);

%><div id="content" class="<%= blog.isEntry() ? "widecolumn" : "narrowcolumn" %>">

<cq:include path="blog" resourceType="collab/blog/components/main" />

</div>
<%

    if (!blog.isEntry()) {
    
    %>
    <div id="sidebar">
        <ul>
            <cq:include path="sidebar" resourceType="foundation/components/parsys"/>
        </ul>
    </div>
    <%
    }
%>