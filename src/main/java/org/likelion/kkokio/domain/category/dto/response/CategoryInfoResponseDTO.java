package org.likelion.kkokio.domain.category.dto.response;

import lombok.*;
import org.likelion.kkokio.domain.store.dto.response.StoreInfoResponseDTO;
import org.likelion.kkokio.global.base.dto.BaseEntityDTO;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryInfoResponseDTO extends BaseEntityDTO {
    private Long categoryId;
    private String categoryName;
    private StoreInfoResponseDTO storeInfoResponseDTO;
}
