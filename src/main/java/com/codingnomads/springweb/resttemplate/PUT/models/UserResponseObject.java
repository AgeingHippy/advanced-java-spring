package com.codingnomads.springweb.resttemplate.PUT.models;

import lombok.Data;

@Data
public class UserResponseObject {
    User data;
    Error error;
    String status;
}
