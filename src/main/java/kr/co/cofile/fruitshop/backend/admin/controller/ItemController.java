package kr.co.cofile.fruitshop.backend.admin.controller;

import kr.co.cofile.fruitshop.backend.admin.dto.ItemDto;
import kr.co.cofile.fruitshop.backend.admin.mapper.ItemMapper;
import kr.co.cofile.fruitshop.backend.admin.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemMapper itemMapper;

    private final ItemService itemService;

    //public ItemController(ItemMapper itemMapper) {
    //    this.itemMapper = itemMapper;
    //}

    @GetMapping("/create")
    public String create() {
        return "item/create";
    }

    @PostMapping
    @ResponseBody
    public void createItem(@RequestBody ItemDto itemDto) {
        System.out.println(itemDto.getName());
        itemMapper.insertItem(itemDto);
    }

    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") int id, Model model) {
        ItemDto itemDto = itemService.getItem(id);
        model.addAttribute("item", itemDto);
        return "item/detail";
    }

}
