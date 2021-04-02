package org.fiipractic.config;

import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.apache.jasper.servlet.JasperInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class Application {

    public static void main(String[] args) throws Exception {
        File base = new File("src/main/webapp");

        final Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        Context rootCtx = tomcat.addContext("", base.getAbsolutePath());
        AnnotationConfigWebApplicationContext annotationContext = new AnnotationConfigWebApplicationContext();
        annotationContext.register(WebConfig.class);

        DispatcherServlet dispatcher = new DispatcherServlet(annotationContext);
        Tomcat.addServlet(rootCtx, "SpringMVCDispatcher", dispatcher);
        rootCtx.addServletMapping("/", "SpringMVCDispatcher");

        Wrapper jspServlet = rootCtx.createWrapper();
        jspServlet.setName("jsp");
        jspServlet.setServletClass("org.apache.jasper.servlet.JspServlet");
        jspServlet.setLoadOnStartup(2);

        rootCtx.addChild(jspServlet);
        rootCtx.addServletMapping("*.jsp", "jsp");
        rootCtx.addServletContainerInitializer(new JasperInitializer(), null);

        tomcat.start();
        tomcat.getServer().await();
    }

}
