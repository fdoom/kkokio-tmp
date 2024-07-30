package org.likelion.kkokio.domain.store.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.security.service.SecurityService;
import org.likelion.kkokio.domain.store.dto.request.StoreInfoRequestDTO;
import org.likelion.kkokio.domain.store.dto.response.StoreInfoResponseDTO;
import org.likelion.kkokio.domain.store.service.StoreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/store")
@Validated
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/info")
    @Operation(summary = "가게 정보 등록", description = "가게 정보 등록")
    public ResponseEntity<StoreInfoResponseDTO> createStoreInfo(
            @RequestPart @Valid StoreInfoRequestDTO storeInfoRequestDTO,
            @RequestPart(required = false) MultipartFile image
    ) {
        return storeService.createStoreInfo(image, storeInfoRequestDTO);
    }

    @GetMapping("/info")
    @Operation(summary = "가게 정보 조회", description = "가게 주인 정보를 기반으로 소유한 가게 정보 목록 조회")
    @Parameter(name = "pageable", description = "페이징 정보, 기본설정: storeId 기준으로 오름차순")
    public ResponseEntity<Page<StoreInfoResponseDTO>> getStoreInfoList(@PageableDefault(sort = "storeId") Pageable pageable) {
        return storeService.getStoreInfoList(pageable);
    }

    @DeleteMapping("/info/{storeId}")
    @Operation(summary = "가게 정보 삭제", description = "등록된 가게 정보 삭제")
    @Parameter(name = "storeId", description = "가게 ID값")
    public ResponseEntity<Void> deleteStoreInfo(@PathVariable Long storeId) {
        return storeService.deleteStoreInfo(storeId);
    }

    @PatchMapping("/info/{storeId}")
    @Operation(summary = "가게 정보 변경", description = "등록된 가게 정보 변경")
    @Parameter(name = "storeId", description = "가게 ID값")
    public ResponseEntity<StoreInfoResponseDTO> updateStoreInfo(
            @PathVariable Long storeId,
            @RequestPart @Valid StoreInfoRequestDTO storeInfoRequestDTO,
            @RequestPart(required = false) MultipartFile image
    ) {
        return storeService.updateStoreInfo(image, storeInfoRequestDTO, storeId);
    }

    @DeleteMapping("/image/{storedId}")
    @Operation(summary = "가게 대표 이미지 제거", description = "가게 대표 이미지 정보만 제거")
    @Parameter(name = "storedId", description = "가게 ID값")
    public ResponseEntity<StoreInfoResponseDTO> deleteImage(@PathVariable Long storedId) {
        return storeService.deleteImage(storedId);
    }

    @GetMapping("/info/{storeId}")
    @Operation(summary = "특정 가게 정보 조회", description = "특정 가게 정보 조회")
    @Parameter(name = "storeId", description = "가게 ID값")
    public ResponseEntity<StoreInfoResponseDTO> getStoreInfo(@PathVariable Long storeId) {
        return storeService.getStoreInfo(storeId);
    }
}
