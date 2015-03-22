package com.coupondunia.startup;

import com.coupondunia.bo.lookups.SystemResource;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Set;

/**
 * Created by roushan on 17/3/15.
 */
public class StartupListener extends ContextLoaderListener {

    private static final Logger LOG = Logger.getLogger(StartupListener.class);

    public void contextInitialized(ServletContextEvent event){
        LOG.info( "Starting application..." );
        LOG.info( "Initializing context..." );

        ServletContext context = event.getServletContext();
        setupContext(context);
    }

    private static void setupContext(ServletContext context) {
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        if(ctx.containsBean("masterResource")) {
            ((SystemResource) ctx.getBean("masterResource")).reload();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver d = null;
        while(drivers.hasMoreElements()) {
            try {
                d = drivers.nextElement();
                DriverManager.deregisterDriver(d);
                LOG.warn(String.format("Driver %s deregistered", d));
            } catch (SQLException ex) {
                LOG.warn(String.format("Error deregistering driver %s", d), ex);
            }
        }
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
        for(Thread t:threadArray) {
            if(t.getName().contains("Abandoned connection cleanup thread")) {
                synchronized(t) {
                    t.interrupt();
                }
            }
        }
    }
}
