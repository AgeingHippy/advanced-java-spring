package com.codingnomads.springweb.resttemplate.DELETE.models;

import lombok.Data;

@Data
public class UserResponseObject {
    User data;
    Error error;
    String status;
}
