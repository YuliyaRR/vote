package groupwork.dao.db.ds;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import groupwork.dao.db.ds.api.IDataSourceWrapper;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceC3PO implements IDataSourceWrapper {

    //константы - закладываем название, которое убирает недопонимание
    // = поясняем суть присвоенного значения через имя константы
    private final String DRIVER_CLASS_PROPERTY_NAME = "db.class";
    private final String URL_PROPERTY_NAME = "db.url";
    private final String USER_PROPERTY_NAME = "db.user";
    private final String PASSWORD_PROPERTY_NAME = "db.password";

    private ComboPooledDataSource dataSource;

    public DataSourceC3PO(Properties properties) throws PropertyVetoException {
        this.dataSource = new ComboPooledDataSource();//композиция, жесткая связь
        this.dataSource.setDriverClass(properties.getProperty(DRIVER_CLASS_PROPERTY_NAME));
        this.dataSource.setJdbcUrl(properties.getProperty(URL_PROPERTY_NAME));
        this.dataSource.setUser(properties.getProperty(USER_PROPERTY_NAME));
        this.dataSource.setPassword(properties.getProperty(PASSWORD_PROPERTY_NAME));

 }

    @Override
    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    @Override
    public void close() throws Exception {
        this.dataSource.close();
    }


}


