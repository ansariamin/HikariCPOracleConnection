

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import oracle.jdbc.pool.OracleDataSource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * @AUTHOR a.ansari
 * @DATE 6/14/2022  10:20 AM
 */
public class OraDbConnectionImp  {

    private static OraDbConnectionImp instance;

    public OraDbConnectionImp() {
    }

    public static OraDbConnectionImp getInstance() {
        if (instance == null) {
            synchronized (OraDbConnectionImp.class) {
                if (instance == null) {
                    instance = new OraDbConnectionImp();
                }
            }
        }
        return instance;
    }

    public static DataSource datasource;

  
    public DataSource getDataSource() {
        return datasource == null ? CreateDataSource() : datasource;
    }
   
    public DataSource CreateDataSource() {


        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(configData.getDbMaxPoolSize());
        config.setJdbcUrl(configData.getDbUrl());
        config.setUsername(configData.getDbUser());
        config.setPassword(configData.getDbPassword());
        config.setAutoCommit(true);
        config.addDataSourceProperty("minimumPoolSize", configData.getDbMinPoolSize());
        config.addDataSourceProperty("maximumPoolSize", configData.getDbMaxPoolSize());
        config.addDataSourceProperty("idleTimeout", configData.getDbIdleTimeOut());
        config.addDataSourceProperty("keepaliveTime", configData.getDbKeepAliveTime()); // 5 min

        config.addDataSourceProperty("cachePrepStmts", configData.isDbCachePrepStmts());
        config.addDataSourceProperty("prepStmtCacheSize", configData.getDbPrepStmtCacheSize());
        config.addDataSourceProperty("prepStmtCacheSqlLimit", configData.getDbPrepStmtCacheSqlLimit());
        config.setPoolName(configData.getDbPoolName());
        config.addHealthCheckProperty("connectivityCheckTimeoutMs", configData.getDbconnectivityCheckTimeoutMs());
        datasource = new HikariDataSource(config);

        return datasource;


    }


//    public DataSource CreateDataSource() {
//
//
//        final HikariDataSource ds = new HikariDataSource();
//        ds.setMaximumPoolSize(100);
//         ds.setAutoCommit(true);
//        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//        ds.setJdbcUrl(configData.getDbUrl());
////        ds.addDataSourceProperty("serverName", "localhost");
////        ds.addDataSourceProperty("port", "5432");
////        ds.addDataSourceProperty("databaseName", "test");
//        ds.addDataSourceProperty("user", configData.getDbUser());
//        ds.addDataSourceProperty("password", AesCrypt.decrypt(configData.getDbPassword()));
//
//        return ds;
//
//
//
//
//    }

//    public Connection getConnection() throws Exception {
//        Connection con = null;
//        try {
//            con = getDataSource().getConnection();
//
//        } catch (SQLException e) {
//            StringWriter stack = new StringWriter();
//            e.printStackTrace(new PrintWriter(stack));
//            LOGTOPUP.error(StartTopUp.class.getSimpleName() + stack);
//            throw new Exception("DB Connection Exception");
//        }
//        return con;
//    }
}
