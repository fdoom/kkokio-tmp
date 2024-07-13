package org.likelion.kkokio.domain.store.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.likelion.kkokio.global.base.dto.BaseEntityDTO;


@Getter
@Schema(description = "가게 요청에 대한 응답 정보")
public class StoreInfoResponseDTO extends BaseEntityDTO {
    @Schema(description = "가게 ID값", example = "1")
    private Long storeId;

    @Schema(description = "가게 이름", example = "OO 가게")
    private String storeName;

    @Schema(description = "가게 주소", example = "OO도 OO로 OO, OO상가 O층")
    private String storeAddress;

    @Schema(description = "가게 소개글", example = "OO이 맛있기로 유명한 가게")
    private String storeSummary;

    @Schema(description = "가게 대표 이미지 url", example = "https://storage.googleapis.com/kkokio_storage_image_bucket/*")
    private String profile_img_url;
}
