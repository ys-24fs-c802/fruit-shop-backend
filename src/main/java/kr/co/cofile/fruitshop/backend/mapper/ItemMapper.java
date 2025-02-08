package kr.co.cofile.fruitshop.backend.mapper;

import kr.co.cofile.fruitshop.backend.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    void insertItem(ItemDto itemDto); // 반환타입이 int인 경우 성공(1) 또는 실패(0) 반환
    Optional<ItemDto> selectItemById(int id);
    List<ItemDto> selectItems(@Param("size") int size, @Param("offset") int offset);
    int countTotal();
    void updateItem(ItemDto itemDto); // 내용을 변경
    void deleteItem(int id);

}
