package org.likelion.kkokio.domain.category.dto.another;

import lombok.Getter;
import org.likelion.kkokio.global.base.dto.BaseEntityDTO;

@Getter
public class CategoryDtoUsingOrdersMenu extends BaseEntityDTO {
    private Long categoryId;
    private String categoryName;
}
