package kr.co.cofile.fruitshop.backend.controller;

import kr.co.cofile.fruitshop.backend.dto.PageDto;
import kr.co.cofile.fruitshop.backend.dto.SupplyDto;
import kr.co.cofile.fruitshop.backend.service.SupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/supplies")
public class SupplyController {

    private final SupplyService supplyService;

    @GetMapping("/create")
    public String createSupply() {
        return "supply/create";
    }

    @PostMapping
    @ResponseBody
    public void createSupply(@RequestBody SupplyDto supplyDto) {
        System.out.println(supplyDto.getName());
        supplyService.createSupply(supplyDto);
    }

    @GetMapping("/{id}")
    public String getSupply(@PathVariable("id") int id, Model model) {
        try {
            SupplyDto supplyDto = supplyService.getSupply(id);
            model.addAttribute("supply", supplyDto);
        } catch (IllegalStateException e) {
            model.addAttribute("message", e.getMessage());
            return "error/404";
        }
        return "supply/detail";
    }

    // 요청URL 형식: /supplies?page=1&size=10
    @GetMapping
    public String getSupplies(@RequestParam(name="page", defaultValue = "1") int page,
                           @RequestParam(name="size", defaultValue = "10") int size,
                           Model model) {
        PageDto<SupplyDto> pageDto = supplyService.getSupplies(page, size);
        model.addAttribute("pageDto", pageDto);

        return "supply/list";
    }

    // modify나 edit을 사용
    @GetMapping("/{id}/modify")
    public String modifySupply(@PathVariable("id") int id, Model model) {
        try {
            SupplyDto supplyDto = supplyService.getSupply(id);
            model.addAttribute("supply", supplyDto);
        } catch (IllegalStateException e) {
            model.addAttribute("message", e.getMessage());
            return "error/404";
        }
        return "supply/modify";
    }

    @PutMapping("/{id}/modify")
    @ResponseBody
    public void modifySupply(@RequestBody SupplyDto supplyDto) {
        System.out.println(supplyDto.getName());
        supplyService.modifySupply(supplyDto);
        // 수정 후 목록으로 리다이렉트는 js가 OK를 응답받고 처리
    }

    @DeleteMapping("/{id}/remove")
    @ResponseBody
    public void removeItem(@PathVariable("id") int id) {
        supplyService.removeSupply(id);
        // return "redirect:/supplies";
    }

}
