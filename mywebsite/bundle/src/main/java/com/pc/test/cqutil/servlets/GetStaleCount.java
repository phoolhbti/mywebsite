package com.pc.test.cqutil.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.ServerException;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import java.util.Set;

//import MBean API
import javax.management.MBeanServerConnection;
import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;
import javax.management.ObjectName;

@SlingServlet(paths = "/bin/getCount", methods = "POST", metatype = true)
public class GetStaleCount extends
		org.apache.sling.api.servlets.SlingAllMethodsServlet {
	private static final long serialVersionUID = 2598426539166789515L;

	@Override
	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServerException,
			IOException {

		try {
			// Create a MBeanServer class
			MBeanServer server = ManagementFactory.getPlatformMBeanServer();

			ObjectName workflowMBean = getWorkflowMBean(server);

			// Get the number of stale workflowitems from AEM
			Object staleWorkflowCount = server.invoke(workflowMBean,
					"countStaleWorkflows", new Object[] { null },
					new String[] { String.class.getName() });

			int mystaleCount = (Integer) staleWorkflowCount;

			// Return the number of stale items
			response.getWriter().write(
					"There are " + mystaleCount + "  stale workflow items");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ObjectName getWorkflowMBean(MBeanServerConnection server) {
		try {
			Set<ObjectName> names = server.queryNames(new ObjectName(
					"com.adobe.granite.workflow:type=Maintenance,*"), null);

			if (names.isEmpty()) {
				return null;
			}

			return names.iterator().next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}