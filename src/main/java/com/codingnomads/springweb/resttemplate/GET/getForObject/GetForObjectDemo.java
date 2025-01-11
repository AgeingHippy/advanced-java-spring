/* CodingNomads (C)2024 */
package com.codingnomads.springweb.resttemplate.GET.getForObject;

import com.codingnomads.springweb.resttemplate.GET.models.Excuse;
import com.codingnomads.springweb.resttemplate.GET.models.QuoteTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GetForObjectDemo {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(GetForObjectDemo.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            QuoteTemplate[] randomQuote;
            randomQuote = restTemplate.getForObject("https://zenquotes.io/api/random/", QuoteTemplate[].class);
            System.out.println(Arrays.toString(randomQuote));

            // submit more requests here

            //        CodingNomadsTasksApiResponse response =
            //                restTemplate.getForObject("http://demo.codingnomads.co:8080/tasks_api/users/5",
            //                        CodingNomadsTasksApiResponse.class);
            //
            //        System.out.println(response.toString());

            final String excuseBaseUrl = "https://excuser-three.vercel.app/v1/excuse";
            Excuse[] excuses;
            excuses = restTemplate.getForObject(excuseBaseUrl,Excuse[].class);
            Arrays.asList(excuses).forEach(System.out::println);

            System.out.println("== 3 Funny (?) excuses ==");
            excuses = restTemplate.getForObject(excuseBaseUrl+"/funny/3", Excuse[].class);
            Arrays.asList(excuses).forEach(System.out::println);

            System.out.println("== 3 Office excuses ==");
            excuses = restTemplate.getForObject(excuseBaseUrl+"/office/3", Excuse[].class);
            Arrays.asList(excuses).forEach(System.out::println);

            System.out.println("One particular excuse with id 102");
            Excuse excuse = restTemplate.getForObject(excuseBaseUrl+"/id/102", Excuse.class);
            System.out.println(excuse);

            System.out.println("== fetching using params ==");
            final String excuseBaseUrlWithParameters = excuseBaseUrl + "/{category}/{count}";
            Map<String ,String> excusesParams = new HashMap<>();
            excusesParams.put("category","gaming");
            excusesParams.put("count","3");

            excuses = restTemplate.getForObject(excuseBaseUrlWithParameters, Excuse[].class, excusesParams);
            Arrays.asList(excuses).forEach(System.out::println);

        };
    }
}
