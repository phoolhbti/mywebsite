<%@include file="/libs/foundation/global.jsp" %>
 
 
<body>
<cq:include path="clientcontext" resourceType="cq/personalization/components/clientcontext"/>
<div id="page">
    <cq:include path="header" resourceType="collab/blog/components/header" />
    <cq:include script="content.jsp"/>
    <cq:include path="footer" resourceType="collab/blog/components/footer" />
</div> 
<cq:include path="cloudservices" resourceType="cq/cloudserviceconfigs/components/servicecomponents"/>
</body>