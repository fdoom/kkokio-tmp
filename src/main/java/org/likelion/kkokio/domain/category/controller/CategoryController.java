package org.likelion.kkokio.domain.category.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.category.dto.request.CategoryInfoRequestDTO;
import org.likelion.kkokio.domain.category.dto.response.CategoryInfoResponseDTO;
import org.likelion.kkokio.domain.category.service.CategoryService;
import org.likelion.kkokio.global.base.exception.ErrorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
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
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/info/{storeId}")
    @Operation(summary = "카테고리 정보 등록",
            description = "가게의 상품 혹은 음식에 대한 카테고리 정보 등록\n기존에 저장된 동일한 카테고리 정보를 다시 저장하는 것은 불가능")
    @Parameter(name = "storeId", description = "가게에 대한 ID값")
    @ApiResponse(responseCode = "409", description = "해당 가게에는 해당 카테고리 정보가 이미 존재하여 중복되었습니다.",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<CategoryInfoResponseDTO> createCategoryInfo(
            @PathVariable Long storeId,
            @Valid @RequestBody CategoryInfoRequestDTO categoryInfoRequestDTO
    ) {
        return categoryService.createCategoryInfo(storeId, categoryInfoRequestDTO);
    }

    @PutMapping("/info/{categoryId}")
    @Operation(summary = "카테고리 정보 변경", description = "등록된 카테고리 정보를 변경")
    @Parameter(name = "categoryId", description = "카테고리 ID값")
    public ResponseEntity<CategoryInfoResponseDTO> updateCategoryInfo(
            @PathVariable Long categoryId,
            @Valid @RequestBody CategoryInfoRequestDTO categoryInfoRequestDTO
    ) {
        return categoryService.updateCategoryInfo(categoryId, categoryInfoRequestDTO);
    }

    @DeleteMapping("/info/{categoryId}")
    @Operation(summary = "카테고리 정보 제거", description = "등록된 카테고리 정보를 제거")
    @Parameter(name = "categoryId", description = "카테고리 ID 값")
    public ResponseEntity<Void> deleteCategoryInfo(@PathVariable Long categoryId) {
        return categoryService.deleteCategoryInfo(categoryId);
    }

    @GetMapping("/info/store/{storeId}")
    @Operation(summary = "카테고리 정보 조회", description = "가게 ID값 기반으로 등록된 카테고리 정보 조회")
    @Parameters (value = {
            @Parameter(name = "storeId", description = "가게에 대한 ID값"),
            @Parameter(name = "pageable", description = "페이징 정보, 기본설정: categoryId 값을 기준으로 오름차순 정렬")
    })
    public ResponseEntity<Page<CategoryInfoResponseDTO>> getCategoryInfo(@PathVariable Long storeId, @PageableDefault(sort = "categoryId") Pageable pageable) {
        return categoryService.getCategoryInfoPage(storeId, pageable);
    }

    @GetMapping("/info/{categoryId}")
    @Operation(summary = "특정 카테고리 정보 조회", description = "특정 카테고리 정보 조회")
    @Parameter(name = "categoryId", description = "카테고리 ID값")
    public ResponseEntity<CategoryInfoResponseDTO> getCategoryInfo(@PathVariable Long categoryId) {
        return categoryService.getCategoryInfo(categoryId);
    }
}
