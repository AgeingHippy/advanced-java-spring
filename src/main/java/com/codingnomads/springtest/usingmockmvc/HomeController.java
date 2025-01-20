/* CodingNomads (C)2024 */
package com.codingnomads.springtest.usingmockmvc;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/")
@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Bobbert");
        return "greeting";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String greet() {
        return "Hello Back";
    }

    @GetMapping("/redirecting")
    public String myRedirect() {
        return "redirect:/hello";
    }

    @GetMapping("/customheader")
    @ResponseBody
    public ResponseEntity<?> myCustomHeader() {
        return ResponseEntity.ok()
                .header("Header-Key","headerValue")
                .body("check my headers");
    }

    @GetMapping("/setvalue")
    public String setModelValue(@RequestParam String qry, Model model) {
        model.addAttribute("queryAttribute",qry);
        model.addAttribute("name","Bob");
        return "greeting";
    }
}
