package kr.co.cofile.fruitshop.backend.admin.controller;

import kr.co.cofile.fruitshop.backend.admin.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemController {

    @GetMapping("/create")
    public String create() {
        return "item/create";
    }

    @PostMapping
    public void createItem(@RequestBody ItemDto itemDto) {
        System.out.println(itemDto.getName());
    }

}
