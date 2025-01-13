/* CodingNomads (C)2024 */
package com.codingnomads.springweb.springrestcontrollers.simpledemo.model;

import lombok.Data;

import java.util.List;

@Data
public class ResponseObject {
    Task data;
    Error error;
    String status;
}
