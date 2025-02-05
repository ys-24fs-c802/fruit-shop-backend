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
    private final UserService userService;

    @GetMapping("/auth/signup")
    public String signup() {
        return "/user/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(@ModelAttribute UserDTO userDTO) {

        userService.signup(userDTO);

        return "redirect:/auth/login";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "/user/login";
    }
}
