package com.pc.chart;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.List;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Reference;

import javax.jcr.Session;


//Sling Imports
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ResourceResolver;

import com.pc.beans.Customer;

//This is a component so it can provide or consume services
@Component
@Service
public class CustomerServiceImpl implements CustomerService {

	/** Default log. */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	private Session session;

	// Inject a Sling ResourceResolverFactory
	@Reference
	private ResourceResolverFactory resolverFactory;

	// Queries the AEM JCR for customer revenue data and returns
	// the data within an XML schema - it is plotted in the client web page
	public String getCustRevenue() {

		Customer cust = null;

		List<Customer> custList = new ArrayList<Customer>();
		try {

			// Invoke the adaptTo method to create a Session used to create a
			// QueryManager
			ResourceResolver resourceResolver = resolverFactory
					.getAdministrativeResourceResolver(null);
			session = resourceResolver.adaptTo(Session.class);

			// Obtain the query manager for the session ...
			javax.jcr.query.QueryManager queryManager = session.getWorkspace()
					.getQueryManager();

			// Setup the query to get all customer records
			String sqlStatement = "SELECT * FROM [nt:unstructured] WHERE CONTAINS(desc, 'Customer')";

			javax.jcr.query.Query query = queryManager.createQuery(
					sqlStatement, "JCR-SQL2");

			// Execute the query and get the results ...
			javax.jcr.query.QueryResult result = query.execute();

			// Iterate over the nodes in the results ...
			javax.jcr.NodeIterator nodeIter = result.getNodes();

			while (nodeIter.hasNext()) {

				// For each node-- create a customer instance
				cust = new Customer();

				javax.jcr.Node node = nodeIter.nextNode();

				// Set all Customer revenue fields
				cust.setRev(node.getProperty("revenue").getString());

				// Push Customer to the list
				custList.add(cust);
			}

			// Log out
			session.logout();
			return convertToString(toXml(custList));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Convert Customer rev data retrieved from the AEM JCR
	// into an XML schema to pass back to client - where it's placed in a chart
	private Document toXml(List<Customer> custList) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();

			// Start building the XML to pass back to the AEM client
			Element root = doc.createElement("Customers");
			doc.appendChild(root);

			// Get the elements from the collection
			int custCount = custList.size();

			// Iterate through the collection to build up the DOM
			for (int index = 0; index < custCount; index++) {

				// Get the Customer object from the collection
				Customer myCust = (Customer) custList.get(index);

				Element Customer = doc.createElement("Customer");
				root.appendChild(Customer);

				// Add rest of data as child elements to customer
				// Set First Name
				Element rev = doc.createElement("rev");
				rev.appendChild(doc.createTextNode(myCust.getRev()));
				Customer.appendChild(rev);

			}

			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String convertToString(Document xml) {
		try {
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(xml);
			transformer.transform(source, result);
			return result.getWriter().toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}