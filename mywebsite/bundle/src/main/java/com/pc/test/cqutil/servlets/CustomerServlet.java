package com.pc.test.cqutil.servlets;

import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.io.JSONWriter;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.pc.test.cqutil.chart.CustomerService;

import java.io.*;
 
import javax.servlet.ServletException;
import java.io.IOException;
 

@SlingServlet(paths = { "/apps/services/customers.json" })
@Properties( {
        @Property(name = "service.pid", value = "com.pc.test.cqutil.servlets.CustomerServlet", propertyPrivate = false),
        @Property(name = "service.description", value = "ProductLoggingServlet", propertyPrivate = false),
        @Property(name = "service.vendor", value = "pc", propertyPrivate = false) })
public class CustomerServlet extends SlingSafeMethodsServlet
{
	private static final long serialVersionUID = -6549518176129073294L;
	@Reference
	 private CustomerService customerService;
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException
    {
    		response.setContentType("text/plain");
    		String XML = customerService.getCustRevenue() ;     	    
    		//Send the data back to the client 
    		JSONWriter writer = new JSONWriter(response.getWriter());
    		try {
    		writer.object();
    		writer.key("xml");
    		writer.value(XML);    		   
    		writer.endObject();
    		 } catch (JSONException e) {
    	            e.printStackTrace();
    	        }
    		}
 
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException
    { 
    doGet(request,response);
    }
}