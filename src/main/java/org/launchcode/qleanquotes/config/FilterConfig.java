package org.launchcode.qleanquotes.config;

import org.launchcode.qleanquotes.CachedBodyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CachedBodyFilter> cachedBodyFilterRegistrationBean() {
        FilterRegistrationBean<CachedBodyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CachedBodyFilter());
        registrationBean.addUrlPatterns("/*"); // Set the appropriate URL patterns
        return registrationBean;
    }
}

