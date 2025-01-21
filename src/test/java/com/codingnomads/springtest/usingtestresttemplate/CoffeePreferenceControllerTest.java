/* CodingNomads (C)2024 */
package com.codingnomads.springtest.usingtestresttemplate;

import static org.assertj.core.api.Assertions.assertThat;

import com.codingnomads.springtest.usingtestresttemplate.models.CoffeePreference;
import java.util.Objects;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = UsingTestRestTemplateMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CoffeePreferenceControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    private Long coffeePreferenceId;

    @Test
    @Order(1)
    public void testPostCoffeePreference() throws Exception {

        // build new CoffeePreference to post
        CoffeePreference preferenceToPost = CoffeePreference.builder()
                .type("Black")
                .size(CoffeePreference.Size.LARGE)
                .sugar(false)
                .iced(true)
                .intensity(9)
                .build();

        // send POST request using TestRestTemplate
        ResponseEntity<CoffeePreference> postedCoffeePreference =
                testRestTemplate.postForEntity("/coffee", preferenceToPost, CoffeePreference.class);

        // confirm Location header is correct
        String locationHeader = Objects.requireNonNull(
                        postedCoffeePreference.getHeaders().getLocation())
                .toString();
        assertThat(locationHeader).isEqualTo("http://www.url.com/new/location");

        // confirm ID was assigned
        assertThat(Objects.requireNonNull(postedCoffeePreference.getBody()).getId())
                .isNotNull();

        //save id for Test#2
        coffeePreferenceId = postedCoffeePreference.getBody().getId();
    }

    @Test
    @Order(2)
    public void testGetCoffeePreferenceSuccess() {
        ResponseEntity<CoffeePreference> response =
                testRestTemplate.getForEntity("/coffee/"+coffeePreferenceId,CoffeePreference.class);

        CoffeePreference coffeePreference = response.getBody();

        assert coffeePreference != null;
        assertThat(coffeePreference.getId()).isEqualTo(coffeePreferenceId);
        assertThat(coffeePreference.getType()).isEqualTo("Black");
        assertThat(coffeePreference.getIntensity()).isEqualTo(9);
        assertThat(coffeePreference.getSize()).isEqualTo(CoffeePreference.Size.LARGE);
        assertThat(coffeePreference.isSugar()).isFalse();
        assertThat(coffeePreference.isIced()).isTrue();

    }

    @Test
    @Order(3)
    public void testGetCoffeePreferenceFailure() {
        ResponseEntity<?> response =
                testRestTemplate.getForEntity("/coffee/3000",String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo("CoffeePreference with id 3000 not found");
    }


}
