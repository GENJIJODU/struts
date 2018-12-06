package org.superbiz.struts;

import com.opensymphony.module.sitemesh.filter.PageFilter;
import org.apache.struts2.dispatcher.ActionContextCleanUp;
import org.apache.struts2.dispatcher.FilterDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FilterRegistrationBean struts2Filter() {
        FilterRegistrationBean struts2Filter = new FilterRegistrationBean();
        struts2Filter.setName("struts2");
        struts2Filter.setFilter(new FilterDispatcher());

        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("actionPackages", "com.lq");
        struts2Filter.setInitParameters(initParameters);

        struts2Filter.addUrlPatterns("/*");

        return struts2Filter;
    }

    @Bean
    public FilterRegistrationBean strutsCleanUpFilter() {
        FilterRegistrationBean strutsCleanUpFilter = new FilterRegistrationBean();
        strutsCleanUpFilter.setName("struts-cleanup");
        strutsCleanUpFilter.setFilter(new ActionContextCleanUp());
        strutsCleanUpFilter.addUrlPatterns("/*");
        return strutsCleanUpFilter;
    }

    @Bean
    public FilterRegistrationBean sitemeshFilter() {
        FilterRegistrationBean sitemeshFilter = new FilterRegistrationBean();
        sitemeshFilter.setName("sitemesh");
        sitemeshFilter.setFilter(new PageFilter());
        sitemeshFilter.addUrlPatterns("/*");
        return sitemeshFilter;
    }

    /**
     *
     *     <filter-mapping>
     *         <filter-name>struts-cleanup</filter-name>
     *         <url-pattern>/*</url-pattern>
     *     </filter-mapping>
     *     <filter-mapping>
     *         <filter-name>sitemesh</filter-name>
     *         <url-pattern>/*</url-pattern>
     *     </filter-mapping>
     *     <filter-mapping>
     *         <filter-name>struts2</filter-name>
     *         <url-pattern>/*</url-pattern>
     *     </filter-mapping>
     */
}
