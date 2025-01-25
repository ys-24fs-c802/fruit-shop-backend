package kr.co.cofile.fruitshop.backend.admin.controller;

import kr.co.cofile.fruitshop.backend.admin.dto.ItemDto;
import kr.co.cofile.fruitshop.backend.admin.mapper.ItemMapper;
import kr.co.cofile.fruitshop.backend.admin.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        try {
            ItemDto itemDto = itemService.getItem(id);
            model.addAttribute("item", itemDto);
        } catch (IllegalStateException e) {
            model.addAttribute("message", e.getMessage());
            return "error/404";
        }
        return "item/detail";
    }

    @GetMapping
    public String getItems(Model model) {
        List<ItemDto> items = itemService.getItems();
        model.addAttribute("items", items);

        return "item/list";
    }

}
