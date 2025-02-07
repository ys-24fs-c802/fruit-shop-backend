package kr.co.cofile.fruitshop.backend.controller;

import jakarta.validation.Valid;
import kr.co.cofile.fruitshop.backend.dto.PageDto;
import kr.co.cofile.fruitshop.backend.dto.SupplyDto;
import kr.co.cofile.fruitshop.backend.service.SupplyService;
import kr.co.cofile.fruitshop.backend.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> createSupply(@Valid @RequestBody SupplyDto supplyDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtil.handleValidationErrors(bindingResult);
        }

        System.out.println(supplyDto.getName());

        // TODO 중복아이템 예외 처리
        supplyService.createSupply(supplyDto);
        return new ResponseEntity<>(supplyDto, HttpStatus.CREATED);
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
