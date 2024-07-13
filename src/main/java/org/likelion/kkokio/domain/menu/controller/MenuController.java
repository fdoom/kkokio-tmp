package org.likelion.kkokio.domain.menu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.menu.dto.request.MenuInfoPatchRequestDTO;
import org.likelion.kkokio.domain.menu.dto.request.MenuInfoRequestDTO;
import org.likelion.kkokio.domain.menu.dto.response.MenuInfoResponseDTO;
import org.likelion.kkokio.domain.menu.service.MenuService;
import org.likelion.kkokio.global.base.exception.ErrorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/menu")
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
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/info/{categoryId}")
    @Operation(summary = "메뉴 정보 등록", description = "가게의 메뉴 정보 등록")
    @Parameter(name = "categoryId", description = "카테고리 ID값")
    public ResponseEntity<MenuInfoResponseDTO> createMenuInfo(
            @PathVariable Long categoryId,
            @Valid @RequestPart MenuInfoRequestDTO menuInfoRequestDTO,
            @RequestPart(required = false) MultipartFile image
    ) {
        return menuService.createMenuInfo(categoryId, menuInfoRequestDTO, image);
    }

    @PatchMapping("/info/{menuId}")
    @Operation(summary = "메뉴 정보 변경", description = "등록된 메뉴 정보 변경")
    @Parameter(name = "menuId", description = "메뉴 ID값")
    public ResponseEntity<MenuInfoResponseDTO> updateMenuInfo(
            @PathVariable Long menuId,
            @Valid @RequestPart MenuInfoPatchRequestDTO menuInfoPatchRequestDTO,
            @RequestPart(required = false) MultipartFile image
    ) {
        return menuService.updateMenuInfo(menuId, menuInfoPatchRequestDTO, image);
    }

    @DeleteMapping("/info/{menuId}")
    @Operation(summary = "메뉴 정보 삭제", description = "등록된 메뉴 정보 삭제")
    @Parameter(name = "menuId", description = "메뉴 ID 값")
    public ResponseEntity<Void> deleteMenuInfo(@PathVariable Long menuId) {
        return menuService.deleteMenuInfo(menuId);
    }

    @GetMapping("/info/store/{storeId}")
    @Operation(summary = "메뉴 정보 조회", description = "가게 ID값을 기반으로 메뉴 정보 조회")
    @Parameters(value = {
            @Parameter(name = "storeId", description = "가게 ID값"),
            @Parameter(name = "pageable", description = "페이징 정보, 기본설정: menuId 값을 기준으로 오름차순")
    })
    public ResponseEntity<Page<MenuInfoResponseDTO>> getMenuInfoStoreId(@PathVariable Long storeId, @PageableDefault(sort = "menuId") Pageable pageable) {
        return menuService.getMenuInfoStoreId(storeId, pageable);
    }

    @GetMapping("/info/store/{storeId}/category/{categoryId}")
    @Operation(summary = "메뉴 정보 조회", description = "가게 ID와 카테고리 ID 값을 기반으로 메뉴 정보 조회")
    @Parameters(value = {
            @Parameter(name = "storeId", description = "가게 ID값"),
            @Parameter(name = "categoryId", description = "카테고리 ID값"),
            @Parameter(name = "pageable", description = "페이징 정보, 기본설정: menuId 값을 기준으로 오름차순")
    })
    public ResponseEntity<Page<MenuInfoResponseDTO>> getMenuInfoStoreIdAndcategoryId(
            @PathVariable Long storeId, @PathVariable Long categoryId, @PageableDefault(sort = "menuId") Pageable pageable
    ) {
        return menuService.getMenuInfoStoreIdAndcategoryId(storeId, categoryId, pageable);
    }

    @DeleteMapping("/image/{menuId}")
    @Operation(summary = "메뉴 이미지 삭제", description = "등록된 메뉴의 이미지 정보 삭제")
    @Parameter(name = "menuId", description = "메뉴 ID값")
    public ResponseEntity<MenuInfoResponseDTO> deleteImage(@PathVariable Long menuId) {
        return menuService.deleteImage(menuId);
    }
}
