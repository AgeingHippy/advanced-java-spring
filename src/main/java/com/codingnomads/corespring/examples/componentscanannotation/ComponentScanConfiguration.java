/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.componentscanannotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.codingnomads.corespring.examples.componentscanannotation",
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
                pattern = "com\\.codingnomads\\.corespring\\.examples\\.componentscanannotation\\.furniture\\..*"))
public class ComponentScanConfiguration {

    @Bean
    public SampleBean sampleBean() {
        return new SampleBean();
    }
}
