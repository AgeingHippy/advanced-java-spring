package com.codingnomads.springweb.resttemplate.PATCH.models;

import lombok.Data;

@Data
public class UserResponseObject {
    User data;
    Error error;
    String status;
}
