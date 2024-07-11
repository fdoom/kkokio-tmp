package org.likelion.kkokio.domain.category.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.likelion.kkokio.domain.store.dto.response.StoreInfoResponseDTO;

@Getter
public class CategoryInfoResponseDTO extends StoreInfoResponseDTO {
    private Long categoryId;
    private String categoryName;
}
