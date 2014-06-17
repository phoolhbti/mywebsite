package com.pc.servlets;

import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.io.JSONWriter;
import java.io.IOException;

import javax.servlet.ServletException;

@SlingServlet(paths = { "/apps/services/myoptions.json" })
@Properties({
		@Property(name = "service.pid", value = "com.pc.test.cqutil.servlets.ProductLoggingServlet", propertyPrivate = false),
		@Property(name = "service.description", value = "ProductLoggingServlet", propertyPrivate = false),
		@Property(name = "service.vendor", value = "pc", propertyPrivate = false) })
public class ProductLoggingServlet extends SlingAllMethodsServlet {
	private static final long serialVersionUID = -6549518176129073296L;
	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		response.setContentType("application/json");
		System.out.println("with in get Method");
		JSONWriter writer = new JSONWriter(response.getWriter());

		try {
			/**
			 * [ { "text":"Test1" }, { "text":"Test2" } ]
			 */
			writer.array();
			writer.object();
			writer.key("text").value("Value1");
			writer.endObject();
			writer.object();
			writer.key("text").value("Value2");
			writer.endObject();
			writer.object();
			writer.key("text").value("Value3");
			writer.endObject();
			writer.endArray();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}
}