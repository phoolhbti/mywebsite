<%@include file="/libs/foundation/global.jsp"%><%
%><div class="center">
<div><cq:include path="trail" resourceType="foundation/components/breadcrumb" /></div>
<div><cq:include path="title" resourceType="foundation/components/title" /></div>
<div></div>
<div><sling:include path="/content/mywebsite/en/producttest.html" resourceType="mywebsite/components/productreader" addSelectors="<%=request.getParameter("stateSelector")%>" /></div>
</div>