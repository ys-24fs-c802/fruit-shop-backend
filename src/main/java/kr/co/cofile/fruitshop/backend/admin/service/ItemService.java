package kr.co.cofile.fruitshop.backend.admin.service;

import kr.co.cofile.fruitshop.backend.admin.dto.ItemDto;
import kr.co.cofile.fruitshop.backend.admin.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    ItemMapper itemMapper;

    public ItemDto getItem(int id) {
        return itemMapper.selectItemById(id);
    }
}
