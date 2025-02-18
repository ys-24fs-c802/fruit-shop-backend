package kr.co.cofile.fruitshop.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
public class ItemDto {

    private Integer id;

    @NotBlank(message = "상품명은 필수입니다")
    @Pattern(regexp = "^[a-zA-Z가-힣][a-zA-Z0-9가-힣]*$",
            message = "상품명을 확인해 주세요.")
    @Size(min = 2, max = 100,
            message = "상품명은 2-100자 사이어야 합니다")
    private String name;

    private Integer userId;
}