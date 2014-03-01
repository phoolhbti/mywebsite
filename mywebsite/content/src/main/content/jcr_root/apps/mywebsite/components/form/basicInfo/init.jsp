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

  Init of address component

--%><%@include file="/apps/mywebsite/global.jsp"%><%
%><%@ page import="com.day.cq.wcm.foundation.forms.FormsHelper,
                   com.day.cq.wcm.foundation.forms.FieldDescription,
                   com.day.cq.wcm.foundation.forms.FieldHelper" %><%
    final String name = FormsHelper.getParameterName(resource);

    FieldHelper.addDescription(slingRequest, new FieldDescription(resource, name + ".ISBN10"));
    FieldHelper.addDescription(slingRequest, new FieldDescription(resource, name + ".ISBN13"));
    FieldHelper.addDescription(slingRequest, new FieldDescription(resource, name + ".FullTitle"));
    FieldHelper.addDescription(slingRequest, new FieldDescription(resource, name + ".ShortTitle"));
    FieldHelper.addDescription(slingRequest, new FieldDescription(resource, name + ".PublicationDate"));
    FieldHelper.addDescription(slingRequest, new FieldDescription(resource, name + ".Language"));
    FieldHelper.addDescription(slingRequest, new FieldDescription(resource, name + ".Price"));
    FieldHelper.addDescription(slingRequest, new FieldDescription(resource, name + ".BuyMe"));
    FieldHelper.addDescription(slingRequest, new FieldDescription(resource, name + ".NYP"));
    FieldHelper.addDescription(slingRequest, new FieldDescription(resource, name + ".PrintStatus"));
    FieldHelper.addDescription(slingRequest, new FieldDescription(resource, name + ".DivisionCode"));
    FieldHelper.addDescription(slingRequest, new FieldDescription(resource, name + ".SeriesCode"));
%>