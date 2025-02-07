package kr.co.cofile.fruitshop.backend.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
public class ItemDto {

    private Integer id;
    private String name;

    // 롬복 Getter / Setter로 대체
    //public Integer getId() {
    //    return id;
    //}
    //public void setId(Integer id) {
    //    this.id = id;
    //}
    //public String getName() {
    //    return name;
    //}
    //public void setName(String name) {
    //    this.name = name;
    //}

}