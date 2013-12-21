package com.pc.test.cqutil.servlets;

import java.io.IOException;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import javax.jcr.Session;
import javax.servlet.ServletException;
import java.util.Map;
import java.util.HashMap;

//import com.pearson.manager.ProductManagerService;
//import com.pearson.model.ProductBean;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.commons.json.JSONArray;

@SlingServlet(paths = { "/apps/services/productPC.html" })
@Properties( {
        @Property(name = "service.pid", value = "com.pearson.servlets.ProductServletPC", propertyPrivate = false),
        @Property(name = "service.description", value = "ProductServletPC", propertyPrivate = false),
        @Property(name = "service.vendor", value = "PC", propertyPrivate = false) })
public class ProductServletPC extends SlingAllMethodsServlet {
   

    @Override
    protected void doGet(SlingHttpServletRequest request,
            SlingHttpServletResponse response) throws ServletException,
            IOException {
             System.out.println("Entering into get PC servelt");
           try{  
                    response.getWriter().write("Hello worldllllllllllllllllll");
                    
                     //response.getWriter().write("Page Created");
                     // response.getWriter().write(productManagerService.search(mockValues).get(0).getTitle());
           
            }catch(Exception e){
            
            System.out.println(e);
            e.printStackTrace();
        }
            
            
      
    }

    @Override
    protected void doPost(SlingHttpServletRequest request,
            SlingHttpServletResponse response) throws ServletException,
            IOException {
        doGet(request, response);
    }
}
