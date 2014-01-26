package com.pc.test.cqutil.chart;

//This bean holds customer information
public class Customer {
      
//Define private class members
private String custId ;
private String revenue; // this value will be plotted in an AEM web page 
       
public void setCustId(String id)
{
    this.custId = id; 
}
      
public String getCustId()
{
    return this.custId;
}
      
public void setRev(String rev)
{
    this.revenue = rev; 
}
      
public String getRev()
{
    return this.revenue;
}
   
   
  
}