package kr.co.cofile.fruitshop.backend.mapper;

import kr.co.cofile.fruitshop.backend.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    void insertPayment(PaymentDTO paymentDTO);
    PaymentDTO findPaymentById(int id);
}
