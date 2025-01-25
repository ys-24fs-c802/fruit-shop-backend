package kr.co.cofile.fruitshop.backend.admin.service;

import kr.co.cofile.fruitshop.backend.admin.dto.ItemDto;
import kr.co.cofile.fruitshop.backend.admin.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemMapper itemMapper;

    //public ItemService(ItemMapper itemMapper) {
    //    this.itemMapper = itemMapper;
    //}

    public ItemDto getItem(int id) {
        // NullException 처리
        ItemDto itemDto = itemMapper.selectItemById(id).orElseThrow(
                () -> new IllegalStateException("데이터를 찾을 수 없습니다.")
        );
        return itemDto;
    }

    public void modify(ItemDto itemDto) {
        itemMapper.updateItem(itemDto);
    }

    public void remove(int id) {
        itemMapper.deleteItem(id);
    }

    // 메서드 구문
    // 접근제어자 리턴타입 메서드이름() {}

}
