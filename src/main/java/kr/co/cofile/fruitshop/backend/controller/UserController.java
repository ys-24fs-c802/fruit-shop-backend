package kr.co.cofile.fruitshop.backend.controller;

import kr.co.cofile.fruitshop.backend.dto.UserDTO;
import kr.co.cofile.fruitshop.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/admin/dashboard")
    public String home() {
        return "/common/dashboard";
    }

    @GetMapping("/admin/login")
    public String login() {
        return "/user/login";
    }
}
