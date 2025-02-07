package kr.co.cofile.fruitshop.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:admin/dashboard";
    }

    @GetMapping("/admin")
    public String dashboard() {
        return "common/dashboard";
    }
}
