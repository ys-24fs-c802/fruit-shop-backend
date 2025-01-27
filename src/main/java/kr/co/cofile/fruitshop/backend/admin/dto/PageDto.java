package kr.co.cofile.fruitshop.backend.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageDto {
    private int page;
    private int size;
    private int totalPages;
    private int totalElements;
    private List<ItemDto> items;
}