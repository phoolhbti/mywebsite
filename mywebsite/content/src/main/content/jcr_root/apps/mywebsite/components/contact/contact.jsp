<%--

  My Logo Component component.

   This is My Logo Component

--%><%@include file="/apps/mywebsite/global.jsp"%><%
%><%@ page import="com.day.cq.commons.Doctype,
                    com.day.cq.wcm.foundation.Image,
                    com.day.cq.wcm.api.components.DropTarget,
                    com.day.cq.wcm.api.components.EditConfig,
                    com.day.cq.wcm.api.WCMMode,
                    com.day.cq.wcm.commons.WCMUtils" %>
<cq:includeClientLib categories="email.sample" />
<cq:setContentBundle />
<form name="contact" id="contact" action="${resource.path}.email.html" method="get">
    <fieldset>
        <legend><fmt:message key="Contact Us" /></legend>
        <label for="email"><fmt:message key="Email" /></label>
        <input type="email" id="email" name="email" />
        <label for="subject"><fmt:message key="Subject" /></label>
        <input type="text" id="subject" name="subject" />
        <label for="message"><fmt:message key="Message" /></label>
        <textarea id="message" name="message"></textarea>
        <input type="submit" value="<fmt:message key="Submit" />" />
    </fieldset>
</form>
<c:if test="<%= WCMMode.fromRequest(request) == WCMMode.EDIT %>">
    <h3>Success Message</h3>
    <cq:include path="success" resourceType="foundation/components/textimage" />
    <br style="clear:both"/>
    <h3>Failure Message</h3>
    <cq:include path="fail" resourceType="foundation/components/textimage" />
</c:if>