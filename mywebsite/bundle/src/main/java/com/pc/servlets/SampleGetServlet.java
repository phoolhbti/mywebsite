package com.pc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;


/**
 * Sample GET Servlet with Felix SRC Annotations.
 */
@Component(immediate = true, metatype = true, label = "Sample Get Servlet")
@Service(javax.servlet.Servlet.class)
@Properties({
    @Property(name = "sling.servlet.resourceTypes", value = "adaptto/sling/basics/sample/servlet"),
    @Property(name = "sling.servlet.extensions", value = "html"),
    @Property(name = "sling.servlet.methods", value = "GET")
})
public class SampleGetServlet extends SlingSafeMethodsServlet {

  @Override
  protected void doGet(SlingHttpServletRequest pRequest, SlingHttpServletResponse pResponse) throws ServletException, IOException {

    PrintWriter out = pResponse.getWriter();
    out.write("Hello SampleServlet");


  }

  private static final long serialVersionUID = 1L;

}
