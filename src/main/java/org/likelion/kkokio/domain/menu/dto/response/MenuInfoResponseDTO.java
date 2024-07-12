package org.likelion.kkokio.domain.menu.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.kkokio.domain.category.dto.another.CategoryDtoOnly;
import org.likelion.kkokio.domain.category.dto.response.CategoryInfoResponseDTO;
import org.likelion.kkokio.global.base.dto.BaseEntityDTO;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuInfoResponseDTO extends BaseEntityDTO {
    private Long menuId;
    private String menuName;
    private Long menuPrice;
    private String menuSummary;
    private String menuImgUrl;
    private CategoryDtoOnly categoryDtoOnly;
}
