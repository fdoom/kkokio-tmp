package org.likelion.kkokio.domain.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class StoreInfoRequestDTO {
    @NotBlank(message = "빈칸이 아니여야 합니다.")
    @Size(max = 254, message = "254글자 이하여야 합니다.")
    private String storeName;

    @NotBlank(message = "빈칸이 아니여야 합니다.")
    private String storeAddress;

    @NotBlank(message = "빈칸이 아니여야 합니다.")
    @Size(max = 254, message = "254글자 이하여야 합니다.")
    private String storeSummary;
}
