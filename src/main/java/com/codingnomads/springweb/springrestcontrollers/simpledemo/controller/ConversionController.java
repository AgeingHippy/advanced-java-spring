/* CodingNomads (C)2024 */
package com.codingnomads.springweb.springrestcontrollers.simpledemo.controller;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConversionController {

    private final String text = "this is the text that this is all based on.";

    @RequestMapping(path = "/binary", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String returnSomeBinary() {
        StringBuilder binary = new StringBuilder();
        char[] chars = text.toCharArray();

        for (char c : chars) {
            binary.append("   ").append(Integer.toBinaryString(c));
        }
        return binary.toString();
    }

    @RequestMapping(path = "/normal", method = RequestMethod.GET)
    public String returnTheString() {
        return text;
    }

    @RequestMapping(path="/backwards", method = RequestMethod.GET)
    public String reverse() {
        StringBuilder sb = new StringBuilder(text);
        return sb.reverse().toString();
    }
}
