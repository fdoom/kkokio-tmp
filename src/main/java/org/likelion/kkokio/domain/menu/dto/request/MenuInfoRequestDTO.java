package org.likelion.kkokio.domain.menu.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
public class MenuInfoRequestDTO {
    @NotBlank(message = "")
    private String menuName;

    @PositiveOrZero
    private Long menuPrice;

    @NotBlank
    private String menuSummary;
}
