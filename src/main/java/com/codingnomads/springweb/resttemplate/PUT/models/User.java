package com.codingnomads.springweb.resttemplate.PUT.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private  long id;
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("updated_at")
    private long updatedAt;
    @JsonProperty("created_at")
    private long createdAt;
}
