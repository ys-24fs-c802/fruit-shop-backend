package kr.co.cofile.fruitshop.backend.admin.supply;

import kr.co.cofile.fruitshop.backend.admin.dto.SupplyDto;
import kr.co.cofile.fruitshop.backend.admin.mapper.SupplyMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SupplyTests {

    @Autowired
    private SupplyMapper supplyMapper;

    @Test
    @DisplayName("더미 상품 데이터 100개 등록")
    void insertItemTest() {
        int start = supplyMapper.countTotal() + 1;
        int end = start + 100;

        String name = "청과";

        for (int i = start; i <= end; i++) {
            SupplyDto supplyDto = new SupplyDto();
            supplyDto.setName(name + i); // 문자열에 숫자를 더하면 문자열로 자동형변환

            supplyMapper.insertSupply(supplyDto);
        }
    }

}
