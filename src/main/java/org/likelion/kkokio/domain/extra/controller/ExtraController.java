package org.likelion.kkokio.domain.extra.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.extra.dto.request.ExtraInfoRequestDTO;
import org.likelion.kkokio.domain.extra.dto.response.ExtraInfoResponseDTO;
import org.likelion.kkokio.domain.extra.service.ExtraService;
import org.likelion.kkokio.global.base.exception.ErrorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/extra")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "400", description = "빈칸이 존재하거나 잘못된 요청을 했습니다.",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "인증 정보가 존재하지 않습니다.",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "403", description = "접근 권한이 없습니다.",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "해당 내용이 존재하지 않습니다.",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
})
public class ExtraController {

    private final ExtraService extraService;

    @PostMapping("/info/{menuId}")
    @Operation(summary = "메뉴 추가 옵션 등록", description = "메뉴 추가 옵션 정보 등록")
    @Parameter(name = "menuId", description = "메뉴 ID값")
    public ResponseEntity<ExtraInfoResponseDTO> createExtraInfo(
            @PathVariable Long menuId,
            @Valid @RequestBody ExtraInfoRequestDTO extraInfoRequestDTO) {
        return extraService.createExtraInfo(menuId, extraInfoRequestDTO);
    }

    @PutMapping("/info/{extraId}")
    @Operation(summary = "메뉴 추가 옵션 변경", description = "메뉴 추가 옵션 정보 변경")
    @Parameter(name = "extraId", description = "메뉴 추가 옵션 ID값")
    public ResponseEntity<ExtraInfoResponseDTO> updateExtraInfo(
            @PathVariable Long extraId,
            @Valid @RequestBody ExtraInfoRequestDTO extraInfoRequestDTO
    ) {
        return extraService.updateExtraInfo(extraId, extraInfoRequestDTO);
    }

    @DeleteMapping("/info/{extraId}")
    @Operation(summary = "메뉴 추가 옵션 제거", description = "메뉴 추가 옵션 정보 제거")
    @Parameter(name = "extraId", description = "메뉴 추가 옵션 ID값")
    public ResponseEntity<Void> deleteExtraInfo(
            @PathVariable Long extraId
    ) {
        return extraService.deleteExtraInfo(extraId);
    }

    @GetMapping("/info/{extraId}")
    @Operation(summary = "특정 메뉴 추가 옵션 정보 조회", description = "특정 메뉴 추가 옵션 정보 조회")
    @Parameter(name = "extraId", description = "메뉴 추가 옵션 ID값")
    public ResponseEntity<ExtraInfoResponseDTO> getExtraInfo(
            @PathVariable Long extraId
    ) {
        return extraService.getExtraInfo(extraId);
    }

    @GetMapping("/info/menu/{menuId}")
    @Operation(summary = "메뉴 ID값기반 메뉴 추가 옵션 정보 조회", description = "메뉴 ID값기반 메뉴 추가 옵션 정보 조회")
    @Parameters(value = {
            @Parameter(name = "menuId", description = "메뉴 ID값"),
            @Parameter(name = "pageable", description = "페이징 정보, 기본설정: extraId 값을 기준으로 오름차순")
    })
    public ResponseEntity<Page<ExtraInfoResponseDTO>> getExtraInfoByMenuId(
            @PathVariable Long menuId,
            @PageableDefault(sort = "extraId") Pageable pageable
    ) {
        return extraService.getExtraInfoByMenuId(menuId, pageable);
    }
}
