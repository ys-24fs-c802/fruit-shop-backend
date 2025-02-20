package kr.co.cofile.fruitshop.backend.dto;

import kr.co.cofile.fruitshop.backend.enums.PaymentState;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private Integer id;
    private String paymentId;
    private Integer payerId;
    private BigDecimal amount;
    private PaymentState state; // Enum 사용
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
