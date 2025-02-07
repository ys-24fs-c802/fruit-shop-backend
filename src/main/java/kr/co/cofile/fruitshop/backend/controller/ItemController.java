package kr.co.cofile.fruitshop.backend.controller;

import kr.co.cofile.fruitshop.backend.dto.ItemDto;
import kr.co.cofile.fruitshop.backend.dto.PageDto;
import kr.co.cofile.fruitshop.backend.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/items")
public class ItemController {

    private final ItemService itemService;

    //public ItemController(ItemMapper itemMapper) {
    //    this.itemMapper = itemMapper;
    //}

    @GetMapping("/create")
    public String createItem() {
        return "item/create";
    }

    @PostMapping
    @ResponseBody
    public void createItem(@RequestBody ItemDto itemDto) {
        System.out.println(itemDto.getName());

        // TODO 중복아이템 예외 처리
        itemService.createItem(itemDto);
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

    // 요청URL 형식: /items?page=1&size=10
    @GetMapping
    public String getItems(@RequestParam(name="page", defaultValue = "1") int page,
                           @RequestParam(name="size", defaultValue = "10") int size,
                           Model model) {
        PageDto pageDto = itemService.getItems(page, size);
        model.addAttribute("pageDto", pageDto);

        return "item/list";
    }

    // modify나 edit을 사용
    @GetMapping("/{id}/modify")
    public String modifyItem(@PathVariable("id") int id, Model model) {
        try {
            ItemDto itemDto = itemService.getItem(id);
            model.addAttribute("item", itemDto);
        } catch (IllegalStateException e) {
            model.addAttribute("message", e.getMessage());
            return "error/404";
        }
        return "item/modify";
    }

    @PutMapping("/{id}/modify")
    @ResponseBody
    public void modifyItem(@RequestBody ItemDto itemDto) {
        System.out.println(itemDto.getName());

        // TODO 중복아이템 예외 처리
        itemService.modifyItem(itemDto);
        // 수정 후 목록으로 리다이렉트는 js가 OK를 응답받고 처리
    }

    @DeleteMapping("/{id}/remove")
    @ResponseBody
    public void removeItem(@PathVariable("id") int id) {
        itemService.removeItem(id);
        // return "redirect:/items";
    }

}
