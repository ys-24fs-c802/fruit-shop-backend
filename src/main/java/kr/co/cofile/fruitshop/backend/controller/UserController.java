package kr.co.cofile.fruitshop.backend.controller;

import kr.co.cofile.fruitshop.backend.dto.UserDTO;
import kr.co.cofile.fruitshop.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserController {

    @GetMapping("/auth/login")
    public String login() {
        return "/user/login";
    }

    @GetMapping("/auth/signup")
    public String signup() {
        return "/user/signup";
    }

    @GetMapping("/auth/logout")
    public String logout() {
        return "/user/logout";
    }

}
