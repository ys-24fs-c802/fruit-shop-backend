package kr.co.cofile.fruitshop.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
public class SupplyDto {
    @Setter
    private Integer id;
    @NotBlank(message = "구입처명은 필수입니다")
    @Pattern(regexp = "^[a-zA-Z가-힣][a-zA-Z0-9가-힣]*$",
            message = "상품명을 확인해 주세요.")
    @Size(min = 2, max = 100,
            message = "상품명은 2-100자 사이어야 합니다")
    @Setter
    private String name;
    private String contact1;
    private String contact2;
    private String businessNumber;

    public void setContact1(String contact1) {
        this.contact1 = contact1 != null && contact1.trim().isEmpty() ? null : contact1;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2 != null && contact2.trim().isEmpty() ? null : contact2;
    }

    // unique는 null값 중복은 허용
    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber != null && businessNumber.trim().isEmpty() ? null : businessNumber;
    }
}