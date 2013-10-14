package com.ffg.shelter;

import org.h2.server.web.WebServlet;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        /* XmlWebApplicationContext appContext = new XmlWebApplicationContext();
       appContext.getEnvironment().setActiveProfiles("resthub-jpa", "resthub-web-server", "resthub-validation");
       String[] locations = { "classpath*:resthubContext.xml", "classpath*:databaseContext.xml", "classpath*:applicationContext.xml" };
       appContext.setConfigLocations(locations);

       ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
       dispatcher.setLoadOnStartup(1);
       dispatcher.addMapping("/*");

       servletContext.addListener(new ContextLoaderListener(appContext));*/

        //Database Console for managing the app's database (TODO : profile)
        ServletRegistration.Dynamic h2Servlet = servletContext.addServlet("h2console", WebServlet.class);
        h2Servlet.setLoadOnStartup(2);
        h2Servlet.addMapping("/console/database/*");
    }
}
