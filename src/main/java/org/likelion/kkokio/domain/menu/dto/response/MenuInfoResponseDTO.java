package org.likelion.kkokio.domain.menu.dto.response;


import lombok.Getter;
import org.likelion.kkokio.domain.category.dto.response.CategoryInfoResponseDTO;

@Getter
public class MenuInfoResponseDTO extends CategoryInfoResponseDTO {
    private Long menuId;
    private String menuName;
    private Long menuPrice;
    private String menuSummary;
    private String menuImgUrl;
}
