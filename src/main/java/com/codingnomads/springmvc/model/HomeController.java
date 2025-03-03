/* CodingNomads (C)2024 */
package com.codingnomads.springmvc.model;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    // using addAttribute for each attribute
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Developer");
        model.addAttribute("language", "Java");
        model.addAttribute("framework", "Spring");

        model.addAttribute("field1", "Value 1");
        model.addAttribute("field2", "Value Two");
        model.addAttribute("field3", "Third value");
        model.addAttribute("field4", "4");

        return "model-page";
    }

    // using mergeAttributes to merge a Map of attributes
    @GetMapping("/map")
    public String indexMap(Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Developer");
        map.put("language", "Java");
        model.mergeAttributes(map);

        model.addAttribute("framework", "Spring");

        return "model-page";
    }

    @GetMapping("/model-and-view-page")
    public ModelAndView passDataWithModelAndView() {
        ModelAndView modelAndView = new ModelAndView("model-and-view");
        modelAndView.addObject("message", "CodingNomads!");
        return modelAndView;
    }
}
