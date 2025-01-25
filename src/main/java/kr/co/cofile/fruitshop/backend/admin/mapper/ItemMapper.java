package kr.co.cofile.fruitshop.backend.admin.mapper;

import kr.co.cofile.fruitshop.backend.admin.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper {

    void insertItem(ItemDto itemDto);

}
