package com.pc.test.cqutil.jcrquery;

public interface CustomerService {

	// Stores customer data in the Adobe CQ JCR
	public int injestCustData(String firstName, String lastName, String phone,
			String desc);

	/*
	 * Retrieves customer data from the AEM JCR and returns all customer data
	 * within an XML schemaThe filter argument specifies one of the following
	 * values:
	 * 
	 * Customer - retrieves all customer dataActive Customer- retrieves current
	 * customers from the JCRPast Customer - retrieves old customers no longer
	 * current customers
	 */
	public String getCustomerData(String filter);

}