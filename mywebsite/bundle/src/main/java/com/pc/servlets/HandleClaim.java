package com.pc.servlets;

import java.io.IOException;
import java.rmi.ServerException;
  
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
 
//import MySQL APIs
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.sql.SQLException;
import javax.sql.DataSource;
 
//Import CQ DataSOurcePool
import com.day.commons.datasource.poolservice.DataSourcePool;
  
@SlingServlet(paths="/bin/mySearchServlet", methods = "POST", metatype=true)
public class HandleClaim extends org.apache.sling.api.servlets.SlingAllMethodsServlet {
     private static final long serialVersionUID = 2598426539166789515L;
     protected final Logger log = LoggerFactory.getLogger(this.getClass());       
     @Reference
     private DataSourcePool source;                 
     @Override
     protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {
        
      try
      {
         //Get the submitted form data that is sent from the
         //CQ web page  
          String id = UUID.randomUUID().toString();
          String firstName = request.getParameter("firstName");
          String lastName = request.getParameter("lastName");
          String address = request.getParameter("address");
          String cat = request.getParameter("cat");
          String state = request.getParameter("state");
          String details = request.getParameter("details");
          String date = request.getParameter("date"); 
          String city = request.getParameter("city"); 
           
           
          //Persist the Data into MySQL by using connection build with the DataSourcePool
          injestCustData(firstName, lastName, address, details);
        
          //Encode the submitted form data to JSON
          JSONObject obj=new JSONObject();
          obj.put("id",id);
          obj.put("firstname",firstName);
          obj.put("lastname",lastName);
          obj.put("address",address);
          obj.put("cat",cat);
          obj.put("state",state);
          obj.put("details",details);
          obj.put("date",date);
          obj.put("city",city);
            
          //Get the JSON formatted data    
          String jsonData = obj.toJSONString();
          log.info("first value:"+jsonData);
            
             //Return the JSON formatted data
         response.getWriter().write(jsonData);
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
      
      
   //Returns a connection using the configured DataSourcePool 
     private Connection getConnection()
     {
     DataSource dataSource = null;
    Connection con = null;
    try
    {
        //Inject the DataSourcePool right here! 
       dataSource = (DataSource) source.getDataSource("mysql");
       con = dataSource.getConnection();
        return con;
                     
          }
    catch (Exception e)
    {
        e.printStackTrace(); 
        }
    return null; 
      }
      
     //Adds a new customer record in the Customer table
     public int injestCustData(String firstName, String lastName, String phone, String desc) {
        Connection c = null;          
        int rowCount= 0; 
        try {
        		c =  getConnection();            
               ResultSet rs = null;
               log.info("with in injest cust data");
               PreparedStatement pstmt = null;
               PreparedStatement ps = null;                             
               //Set the query and use a preparedStatement
               String query = "Select * FROM customer";
               pstmt = c.prepareStatement(query); 
               rs = pstmt.executeQuery();                  
               while (rs.next()) 
                       rowCount++;                               
               //Set the PK value
               int pkVal = rowCount + 2;                  
               String insert = "INSERT INTO customer(custId,custFirst,custLast,custDesc,custAddress) VALUES(?, ?, ?, ?, ?);";
               log.debug(insert);
               ps = c.prepareStatement(insert);
               log.info("insert::::"+insert);
               ps.setInt(1, pkVal);
               ps.setString(2, firstName);
               ps.setString(3, lastName);
               ps.setString(4, phone);
               ps.setString(5, desc);               
               ps.execute();
               log.info("Handle claim in method");
               return pkVal;
        }
        catch (Exception e) {
          e.printStackTrace();
         }
        finally {
          try
          {
            c.close();
          }
           
            catch (SQLException e) {
              e.printStackTrace();
            }
    }
        return 0; 
 }
      
}