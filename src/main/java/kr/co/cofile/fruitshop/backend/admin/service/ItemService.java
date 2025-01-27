package kr.co.cofile.fruitshop.backend.admin.service;

import kr.co.cofile.fruitshop.backend.admin.dto.ItemDto;
import kr.co.cofile.fruitshop.backend.admin.dto.PageDto;
import kr.co.cofile.fruitshop.backend.admin.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemMapper itemMapper;

    //public ItemService(ItemMapper itemMapper) {
    //    this.itemMapper = itemMapper;
    //}

    public void createItem(ItemDto itemDto) {
        itemMapper.insertItem(itemDto);
    }

    public ItemDto getItem(int id) {
        // NullException 처리
        ItemDto itemDto = itemMapper.selectItemById(id).orElseThrow(
                () -> new IllegalStateException("데이터를 찾을 수 없습니다.")
        );
        return itemDto;
    }

    // size: 요천건수
    public PageDto getItems(int page, int size) {
        int offset = (page - 1) * size;
        // 갯수가 size인 item목록
        List<ItemDto> items = itemMapper.selectItems(size, offset);
        // 총갯수
        int totalElements = itemMapper.countTotal();
        // 총페이지
        // 13/5 2.xxx 2, 3 Math.ceil(2.xxx) => 3.0  => 3
        int totalPages = (int) Math.ceil((double) totalElements / size);

        return new PageDto(page, size, totalPages, totalElements, items);
    }

    public void modifyItem(ItemDto itemDto) {
        itemMapper.updateItem(itemDto);
    }

    public void removeItem(int id) {
        itemMapper.deleteItem(id);
    }

    // 메서드 구문
    // 접근제어자 리턴타입 메서드이름() {}

}
