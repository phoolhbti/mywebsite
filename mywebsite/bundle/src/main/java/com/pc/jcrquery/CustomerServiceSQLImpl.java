package com.pc.jcrquery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.day.commons.datasource.poolservice.DataSourceNotFoundException;
import com.day.commons.datasource.poolservice.DataSourcePool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class CustomerServiceSQLImpl {

	/** Default log. */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	public int injestCustData(String firstName, String lastName, String phone,
			String desc) {
		Connection c = null;
		DataSource ds = null;
		int rowCount = 0;
		try {
			BundleContext bundleContext = FrameworkUtil.getBundle(
					this.getClass()).getBundleContext();
			ServiceReference factoryRef = bundleContext
					.getServiceReference(DataSourcePool.class.getName());
			DataSourcePool dataSourcePool = (DataSourcePool) bundleContext
					.getService(factoryRef);
			try {
				ds = (DataSource) dataSourcePool.getDataSource("mysql");
			} catch (DataSourceNotFoundException e) {
				log.error(
						"DataSourceNotFoundException while getting DataSource {}.",
						"mysql", e);
			}

			// Create a Connection object
			c = ds.getConnection();

			ResultSet rs = null;
			// Statement s = c.createStatement();
			// Statement scount = c.createStatement();

			// Use prepared statements to protected against SQL injection
			// attacks
			PreparedStatement pstmt = null;
			PreparedStatement ps = null;

			// Set the query and use a preparedStatement
			String query = "Select * FROM Customer";
			pstmt = c.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next())
				rowCount++;

			// Set the PK value
			int pkVal = rowCount + 2;

			String insert = "INSERT INTO Customer(custId,custFirst,custLast,custDesc,custAddress) VALUES(?, ?, ?, ?, ?);";
			ps = c.prepareStatement(insert);
			ps.setInt(1, pkVal);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, phone);
			ps.setString(5, desc);
			ps.execute();
			return pkVal;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.close(c);
		}
		return 0;
	}

}