/* CodingNomads (C)2024 */
package com.codingnomads.springfundamentals.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatusController {

    private final StatusService statusService;
    private final MyRandomService myRandomService;

    public StatusController(StatusService statusService, MyRandomService myRandomService) {
        this.statusService = statusService;
        this.myRandomService = myRandomService;
    }

    @GetMapping("/arrival")
    public String sayHello() {
        return statusService.processStatus(true);
    }

    @GetMapping("/departure")
    public String sayGoodbye() {
        return statusService.processStatus(false);
    }

    @GetMapping("/success")
    public String showSuccess(Model model) {
        model.addAttribute("randomInteger", myRandomService.someRandomNumber(100));
        return "springfundamentals/success";
    }
}
