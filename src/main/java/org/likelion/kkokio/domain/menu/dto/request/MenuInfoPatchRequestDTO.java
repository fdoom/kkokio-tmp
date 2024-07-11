package org.likelion.kkokio.domain.menu.dto.request;

import lombok.Getter;

@Getter
public class MenuInfoPatchRequestDTO extends MenuInfoRequestDTO {
    private Long categoryId;
}
