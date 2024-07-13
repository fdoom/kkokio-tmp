package org.likelion.kkokio.domain.store.dto.response;

import lombok.Getter;
import org.likelion.kkokio.global.base.dto.BaseEntityDTO;


@Getter
public class StoreInfoResponseDTO extends BaseEntityDTO {
    private Long storeId;
    private String storeName;
    private String storeAddress;
    private String storeSummary;
    private String profile_img_url;
}
