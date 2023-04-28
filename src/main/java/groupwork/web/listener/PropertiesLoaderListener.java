package groupwork.web.listener;

import groupwork.dao.db.ds.DataSourceC3PO;
import groupwork.dao.db.ds.factory.DataSourceSingleton;
import groupwork.service.fabrics.SendMailServiceSingleton;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PropertiesLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        File confDir = new File(System.getenv("catalina_base") + "/conf");
        File prop = new File(confDir + "/applicationHome.properties");
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(prop));
            DataSourceSingleton.setProperties(properties);
            servletContext.setAttribute("DataSource", DataSourceSingleton.getInstance());
            SendMailServiceSingleton.setProperties(properties);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Файл с настройками не найден", e);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла", e);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        DataSourceC3PO dataSource = (DataSourceC3PO)servletContext.getAttribute("DataSource");
        try {
            dataSource.close();
            DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
                try {
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
