package com.pc.beans;

public class Customer {
	//Define private class members
	private String custId ;
	private String revenue;// this value will be plotted in an AEM web page 
	
    private String custFirst; 
    private String custLast; 
    private String custAddress;
    private String custDescription; 
           
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

	public String getRevenue() {
		return revenue;
	}

	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}

	public String getCustFirst() {
		return custFirst;
	}

	public void setCustFirst(String custFirst) {
		this.custFirst = custFirst;
	}

	public String getCustLast() {
		return custLast;
	}

	public void setCustLast(String custLast) {
		this.custLast = custLast;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getCustDescription() {
		return custDescription;
	}

	public void setCustDescription(String custDescription) {
		this.custDescription = custDescription;
	}

	
}
