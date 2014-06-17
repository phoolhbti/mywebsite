package com.pc.jcrquery;
import javax.sql.DataSource;

public interface DatasourceUtil {

    public DataSource getDataSource(String dataSourceName);
}
