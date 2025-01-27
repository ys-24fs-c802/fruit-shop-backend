package kr.co.cofile.fruitshop.backend.admin.service;

import kr.co.cofile.fruitshop.backend.admin.dto.Page2Dto;
import kr.co.cofile.fruitshop.backend.admin.dto.SupplyDto;
import kr.co.cofile.fruitshop.backend.admin.mapper.SupplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyService {

    @Autowired
    private SupplyMapper supplyMapper;

    public void createSupply(SupplyDto supplyDto) {
        supplyMapper.insertSupply(supplyDto);
    }

    public SupplyDto getSupply(int id) {
        return supplyMapper.getSupplyById(id).orElseThrow(
                () -> new IllegalStateException("데이터를 찾을 수 없습니다.")
        );
    }

    public Page2Dto getSupplies(int page, int size) {
        int offset = (page - 1) * size;
        List<SupplyDto> supplies = supplyMapper.getSupplies(size, offset);
        int totalElements = supplyMapper.countTotal();

        return new Page2Dto(page, size, totalElements, supplies);
    }

    public void modifySupply(SupplyDto supplyDto) {
        supplyMapper.updateSupply(supplyDto);
    }

    public void removeSupply(int id) {
        supplyMapper.deleteSupply(id);
    }
}
