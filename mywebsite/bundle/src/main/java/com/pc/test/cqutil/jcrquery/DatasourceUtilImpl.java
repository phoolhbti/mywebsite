package com.pc.test.cqutil.jcrquery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import javax.sql.DataSource;
import com.day.commons.datasource.poolservice.DataSourceNotFoundException;
import com.day.commons.datasource.poolservice.DataSourcePool;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

import org.osgi.framework.Constants;
  

@Component(metatype=true)
@Service
@Properties({
        @org.apache.felix.scr.annotations.Property(name = Constants.SERVICE_DESCRIPTION, value = "DatasourceUtil Services"),
        @org.apache.felix.scr.annotations.Property(name = Constants.SERVICE_VENDOR, value = "PC")
       })

public class DatasourceUtilImpl implements DatasourceUtil {
    
     private static final Logger log = LoggerFactory
        .getLogger(DatasourceUtilImpl.class);

    /** @scr.reference policy="static" */
    private DataSourcePool dataSourceService;
    
    public DataSource getDataSource(String dataSourceName) {
        log.info("Using DataSourcePool service lookup to get connection pool " + dataSourceName);
        DataSource dataSource = null;
        try {
            dataSource = (DataSource) dataSourceService.getDataSource(dataSourceName);
        } catch (DataSourceNotFoundException e) {
            log.error("Unable to find datasource {}.", dataSourceName, e);
        }
        return dataSource;
    }
   
}
