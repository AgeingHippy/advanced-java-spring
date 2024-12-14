/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.propertysourceannotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class App {
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${another.name}")
    private String anotherName;

    @Value("${another.place}")
    private String anotherPlace;

    @Value("${profileDependant.value}")
    private String profileDependantValue;

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getAnotherName() {
        return anotherName;
    }

    public String getAnotherPlace() {
        return anotherPlace;
    }

    public String getProfileDependantValue() {
        return profileDependantValue;
    }
}
