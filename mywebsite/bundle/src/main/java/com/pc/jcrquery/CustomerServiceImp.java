package com.pc.jcrquery;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import javax.jcr.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.jackrabbit.commons.JcrUtils;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;

import javax.jcr.RepositoryException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.framework.Constants;

import javax.jcr.Session;

import com.pc.beans.Customer;

@Component(metatype = true)
@Service
@Properties({
		@Property(name = Constants.SERVICE_DESCRIPTION, value = "Customer Services"),
		@Property(name = Constants.SERVICE_VENDOR, value = "PC")
		})
public class CustomerServiceImp implements CustomerService {

	/** Default log. */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Reference
	private SlingRepository repository;

	// Stores customer data in the Adobe CQ JCR
	public int injestCustData(String firstName, String lastName, String phone,
			String desc) {

		try {
			Session session = this.repository.loginAdministrative(null);

			// Create a node that represents the root node
			Node root = session.getRootNode();

			// Get the content node in the JCR
			Node content = root.getNode("content");

			// Determine if the content/customer node exists
			Node customerRoot = null;
			int custRec = doesCustExist(content);

			// -1 means that content/customer does not exist
			if (custRec == -1) {
				// content/customer does not exist -- create it
				customerRoot = content.addNode("customer");
			} else {
				// content/customer does exist -- retrieve it
				customerRoot = content.getNode("customer");
			}

			int custId = custRec + 1; // assign a new id to the customer node

			// Store content from the client JSP in the JCR
			Node custNode = customerRoot.addNode("customer" + firstName
					+ lastName + custId);

			// make sure name of node is unique
			custNode.setProperty("id", custId);
			custNode.setProperty("firstName", firstName);
			custNode.setProperty("lastName", lastName);
			custNode.setProperty("address", phone);
			custNode.setProperty("desc", desc);

			// Save the session changes and log out
			session.save();
			session.logout();
			return custId;
		}

		catch (RepositoryException e) {
			log.error("RepositoryException: " + e);
		}
		return 0;
	}

	/*
	 * Determines if the content/customer node exists This method returns these
	 * values: -1 - if customer does not exist 0 - if content/customer node
	 * exists; however, contains no children number - the number of children
	 * that the content/customer node contains
	 */
	private int doesCustExist(Node content) {
		try {

			int childRecs = 0;

			java.lang.Iterable<Node> custNode = JcrUtils.getChildNodes(content,
					"customer");
			Iterator it = custNode.iterator();

			// only going to be 1 content/customer node if it exists
			if (it.hasNext()) {
				// Count the number of child nodes in content/customer
				Node customerRoot = content.getNode("customer");
				Iterable itCust = JcrUtils.getChildNodes(customerRoot);
				Iterator childNodeIt = itCust.iterator();

				// Count the number of customer child nodes
				while (childNodeIt.hasNext()) {
					childRecs++;
					childNodeIt.next();
				}
				return childRecs;
			} else
				return -1; // content/customer does not exist
		} catch (Exception e) {
			log.error("RepositoryException: " + e);
		}
		return 0;
	}

	// Queries the AEM JCR for customer data and returns
	// the data within an XML schema
	public String getCustomerData(String filter) {

		Customer cust = null;

		List<Customer> custList = new ArrayList<Customer>();
		try {

			Session session = this.repository.loginAdministrative(null);
			log.info("sqlStatement :first statement ");
			// Obtain the query manager for the session ...
			javax.jcr.query.QueryManager queryManager = session.getWorkspace()
					.getQueryManager();

			// Setup the quesry based on user input
			String sqlStatement = "";

			// Setup the query to get all customer records
			if (filter.equals("All Customers"))
				sqlStatement = "SELECT * FROM [sling:OrderedFolder] WHERE CONTAINS(desc, 'Customer')";
			else if (filter.equals("Active Customer"))
				sqlStatement = "SELECT * FROM [sling:OrderedFolder] WHERE CONTAINS(desc, 'Active')";
			else if (filter.equals("Past Customer"))
				sqlStatement = "SELECT * FROM [sling:OrderedFolder] WHERE CONTAINS(desc, 'Past')";
			log.info("sqlStatement : " + sqlStatement);
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
				log.info("node.Node value: "
						+ node.getProperty("firstName").getString());
				// Set all Customer object fields
				cust.setCustFirst(node.getProperty("firstName").getString());
				cust.setCustLast(node.getProperty("lastName").getString());
				cust.setCustAddress(node.getProperty("address").getString());
				cust.setCustDescription(node.getProperty("desc").getString());
				log.info("node.Node desc: "
						+ node.getProperty("desc").getString());
				// Push Customer to the list
				custList.add(cust);
			}

			return convertToString(toXml(custList));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Convert Customer data retrieved from the AEM JCR
	// into an XML schema to pass back to client
	private Document toXml(List<Customer> custList) {
		log.info("Document toXml");
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
				Element first = doc.createElement("First");
				first.appendChild(doc.createTextNode(myCust.getCustFirst()));
				Customer.appendChild(first);

				// Set Last Name
				Element last = doc.createElement("Last");
				last.appendChild(doc.createTextNode(myCust.getCustLast()));
				Customer.appendChild(last);

				// Set Description
				Element desc = doc.createElement("Description");
				desc.appendChild(doc.createTextNode(myCust.getCustDescription()));
				Customer.appendChild(desc);

				// Set Address
				Element address = doc.createElement("Address");
				address.appendChild(doc.createTextNode(myCust.getCustAddress()));
				Customer.appendChild(address);
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
			log.info(" result.getWriter().toString():::::"
					+ result.getWriter().toString());
			return result.getWriter().toString();
		} catch (Exception ex) {
			log.info(" (Exception ex:" + ex);
			ex.printStackTrace();
		}
		return null;
	}
}
