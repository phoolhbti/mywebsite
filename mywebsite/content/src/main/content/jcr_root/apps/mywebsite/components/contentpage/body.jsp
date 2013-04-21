<%@include file="/libs/foundation/global.jsp"%><%
%><body>
<div id="CQ">
<div class="topnav"><cq:include path="topnav" resourceType="mywebsite/components/topnav" /></div>
<div class="content">
<cq:include script="left.jsp" />
<cq:include script="center.jsp" />
<cq:include script="right.jsp" />
</div>
<div class="footer">
<div class="toolbar"><cq:include path="toolbar" resourceType="foundation/components/toolbar"/></div>
<div class="pearsonfooter"><cq:include path="pearsonfooter" resourceType="pearson/components/content/pearsonfooter"/></div>
</div>
</div>
</body>