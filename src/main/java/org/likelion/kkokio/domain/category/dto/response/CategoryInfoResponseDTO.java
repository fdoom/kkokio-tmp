package org.likelion.kkokio.domain.category.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.likelion.kkokio.global.base.dto.BaseEntityDTO;

@Getter
public class CategoryInfoResponseDTO extends BaseEntityDTO {
    private Long categoryId;
    private String categoryName;
}
