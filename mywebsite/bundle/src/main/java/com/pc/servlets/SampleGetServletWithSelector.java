package com.pc.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

/**
 * Sample GET Servlet with Sling Convenience Annotations.
 * This Servlet is registered on the same 'resourceType" like the SampleGetServlet,
 * but it is only called, when the selector is added to the URL.
 * It also fetches content from the JCR by adapting resources.
 */
@SlingServlet(generateService = true,
    generateComponent = true,
    resourceTypes = "adaptto/sling/basics/sample/servlet",
    label = "Sample Get Servlet with SlingServlet Annotations using a Selector",
    metatype = true,
    extensions = "html",
    selectors = "selectme")
public class SampleGetServletWithSelector extends SlingSafeMethodsServlet {

  @Override
  protected void doGet(SlingHttpServletRequest pRequest, SlingHttpServletResponse pResponse) throws ServletException, IOException {

    PrintWriter out = pResponse.getWriter();
    out.write("Hello SampleServlet with Selector");

    // get resource
    Resource resource = pRequest.getResourceResolver().getResource("/sample/content");
    if (null != resource) {
      // adaptTo!
      ValueMap valueMap = resource.adaptTo(ValueMap.class);
      String title = valueMap.get("title", "");
      Date date = valueMap.get("date", Date.class);
      String text = valueMap.get("text", "");
      out.write("<br/>Title: ");
      out.write(title);
      if (null != date) {
        out.write("<br/>Date: ");
        out.write(DateFormatUtils.ISO_DATE_FORMAT.format(date));
      }
      out.write("<br/>Text: ");
      out.write(text);
    }
  }

  private static final long serialVersionUID = 1L;
}
