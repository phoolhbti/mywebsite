<%--
  Copyright 1997-2008 Day Management AG
  Barfuesserplatz 6, 4001 Basel, Switzerland
  All Rights Reserved.

  This software is the confidential and proprietary information of
  Day Management AG, ("Confidential Information"). You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with Day.

  ==============================================================================

  Form 'element' component

  Draws an element of a form

--%><%@include file="/libs/foundation/global.jsp"%><%
%><%@ page import="com.day.cq.wcm.foundation.forms.FormsHelper,
        com.day.cq.wcm.foundation.forms.LayoutHelper,
        org.apache.commons.lang3.StringEscapeUtils,
        java.util.Map,
        java.util.LinkedHashMap,
        java.util.Locale,
		java.util.ResourceBundle,
		com.day.cq.i18n.I18n" %>
<%
	
	final Locale pageLocale = currentPage.getLanguage(true);
	final ResourceBundle bundle = slingRequest.getResourceBundle(pageLocale);
	I18n i18n = new I18n(bundle);
		
    final String name = FormsHelper.getParameterName(resource);
    final String id = FormsHelper.getFieldId(slingRequest, resource);
    final boolean required = FormsHelper.isRequired(resource);
    final boolean hideTitle = properties.get("hideTitle", false);
    final String title = FormsHelper.getTitle(resource, i18n.get("Address"));
    
    final String firstName = FormsHelper.getValue(slingRequest, resource, name + ".firstname");
    final String lastName = FormsHelper.getValue(slingRequest, resource, name + ".lastname");
    final String street1 = FormsHelper.getValue(slingRequest, resource, name + ".street1");
    final String street2 = FormsHelper.getValue(slingRequest, resource, name + ".street2");
    final String zip = FormsHelper.getValue(slingRequest, resource, name + ".zip");
    final String city = FormsHelper.getValue(slingRequest, resource, name + ".city");
    final String state = FormsHelper.getValue(slingRequest, resource, name + ".state");
    final String country = FormsHelper.getValue(slingRequest, resource, name + ".country");

    final Map<String, String> countries = new LinkedHashMap<String, String>();
    countries.put("Argentina", i18n.get("Argentina"));
    countries.put("Australia", i18n.get("Australia"));
    countries.put("Austria", i18n.get("Austria"));
    countries.put("Bahamas", i18n.get("Bahamas"));
    countries.put("Bahrain", i18n.get("Bahrain"));
    countries.put("Brazil", i18n.get("Brazil"));
    countries.put("Canada", i18n.get("Canada"));
    countries.put("Chile", i18n.get("Chile"));
    countries.put("China", i18n.get("China"));
    countries.put("Colombia", i18n.get("Colombia"));
    countries.put("Egypt", i18n.get("Egypt"));
    countries.put("France", i18n.get("France"));
    countries.put("Germany", i18n.get("Germany"));
    countries.put("Gibraltar", i18n.get("Gibraltar"));
    countries.put("Hongkong", i18n.get("Hong Kong"));
    countries.put("Italy", i18n.get("Italy"));
    countries.put("Japan", i18n.get("Japan"));
    countries.put("Luxembourg", i18n.get("Luxembourg"));
    countries.put("Malaysia", i18n.get("Malaysia"));
    countries.put("Mexico", i18n.get("Mexico"));
    countries.put("Monaco", i18n.get("Monaco"));
    countries.put("Russia", i18n.get("Russia"));
    countries.put("Singapore", i18n.get("Singapore"));
    countries.put("Spain", i18n.get("Spain"));
    countries.put("Switzerland", i18n.get("Switzerland"));
    countries.put("United States of America", i18n.get("United States of America"));
    countries.put("United Arab Emirates", i18n.get("United Arab Emirates"));
    countries.put("United Kingdom", i18n.get("United Kingdom"));
    countries.put("Uruguay", i18n.get("Uruguay"));
    countries.put("Venezuela", i18n.get("Venezuela"));
    countries.put("other...", i18n.get("other..."));
    %>
    <div class="form_row">
        <% LayoutHelper.printTitle(id, title, required, hideTitle, out); %>
    </div>
    <div class="form_row">
        <div class="form_leftcolnobr">
            <input class="<%= FormsHelper.getCss(properties, "form_field form_field_text form_address_firstname") %>"
                id="<%= StringEscapeUtils.escapeHtml4(id + "-firstname") %>"
                name="<%= StringEscapeUtils.escapeHtml4(name + ".firstname") %>"
                value="<c:out value="<%= firstName != null ? firstName : "" %>"/>"
                size="16">
            <% LayoutHelper.printDescription(id + "-firstname", bundle.getString("First Name"), out); %>
        </div>
        <div class="form_rightcolnobr">
            <input class="<%= FormsHelper.getCss(properties, "form_field form_field_text form_address_lastname") %>"
                id="<%= StringEscapeUtils.escapeHtml4(id + "-lastname") %>"
                name="<%= StringEscapeUtils.escapeHtml4(name + ".lastname") %>"
                value="<c:out value="<%= lastName != null ? lastName : "" %>"/>"
                size="16">
            <% LayoutHelper.printDescription(id + "-lastname", bundle.getString("Last Name"), out); %>
        </div>
    </div>
    <div class="form_row">
        <div class="form_leftcolnobr">
            <input class="<%= FormsHelper.getCss(properties, "form_field form_field_text form_address_street1") %>"
                id="<%= StringEscapeUtils.escapeHtml4(id + "-street1") %>"
                name="<%= StringEscapeUtils.escapeHtml4(name + ".street1") %>"
                value="<c:out value="<%= street1 != null ? street1 : "" %>"/>" >
            <% LayoutHelper.printDescription(id + "-street1", bundle.getString("Street address 1"), out); %>
        </div>
        <div class="form_rightcolnobr">
            <input class="<%= FormsHelper.getCss(properties, "form_field form_field_text form_address_street2") %>"
                id="<%= StringEscapeUtils.escapeHtml4(id + "-street2") %>"
                name="<%= StringEscapeUtils.escapeHtml4(name + ".street2") %>"
                value="<c:out value="<%= street2 != null ? street2 : "" %>"/>" >
            <% LayoutHelper.printDescription(id + "-street2", bundle.getString("Street address 2"), out); %>
        </div>
    </div>
    <div class="form_row">
        <div class="form_leftcolnobr">
            <input class="<%= FormsHelper.getCss(properties, "form_field form_field_text form_address_zip") %>"
                id="<%= StringEscapeUtils.escapeHtml4(id + "-zip") %>"
                name="<%= StringEscapeUtils.escapeHtml4(name + ".zip") %>"
                value="<c:out value="<%= zip != null ? zip : "" %>"/>" >
            <% LayoutHelper.printDescription(id + "-zip", bundle.getString("Postal / Zip Code"), out); %>
        </div>
        <div class="form_rightcolnobr">
            <input class="<%= FormsHelper.getCss(properties, "form_field form_field_text form_address_city") %>"
                id="<%= StringEscapeUtils.escapeHtml4(id + "-city") %>"
                name="<%= StringEscapeUtils.escapeHtml4(name + ".city") %>"
                value="<c:out value="<%= city != null ? city : "" %>"/>" >
            <% LayoutHelper.printDescription(id + "-city", bundle.getString("City"), out); %>
        </div>
    </div>
    <div class="form_row">
        <div class="form_leftcolnobr">
            <input class="<%= FormsHelper.getCss(properties, "form_field form_field_text form_address_state") %>"
                id="<%= StringEscapeUtils.escapeHtml4(id + "-state") %>"
                name="<%= StringEscapeUtils.escapeHtml4(name + ".state") %>"
                value="<c:out value="<%= state != null ? state : "" %>"/>" >
            <% LayoutHelper.printDescription(id + "-state", bundle.getString("State / Province"), out); %>
        </div>
        <div class="form_rightcolnobr">
            <select class="<%= FormsHelper.getCss(properties, "form_field form_field_select form_address_country") %>"
                id="<%= StringEscapeUtils.escapeHtml4(id + "-country") %>"
                name="<%= StringEscapeUtils.escapeHtml4(name + ".country") %>"><%
            for (final Map.Entry<String, String> c : countries.entrySet()) {
                if (c.getKey().equals(country)) {
                    %><option value="<c:out value="<%= c.getKey() %>"/>" selected><%= c.getValue() %></option><%
                } else {
                    %><option value="<c:out value="<%= c.getKey() %>"/>"><%= c.getValue() %></option><%
                }
            }
            %></select>
            <% LayoutHelper.printDescription(id + "-country", bundle.getString("Country"), out); %>
        </div>
    </div>
    <%
    LayoutHelper.printDescription(FormsHelper.getDescription(resource, ""), out);
    LayoutHelper.printErrors(slingRequest, name, hideTitle, out);
%>
