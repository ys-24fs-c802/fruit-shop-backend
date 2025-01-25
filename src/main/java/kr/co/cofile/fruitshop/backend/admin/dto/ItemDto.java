package kr.co.cofile.fruitshop.backend.admin.dto;

import lombok.Getter;
import lombok.Setter;

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