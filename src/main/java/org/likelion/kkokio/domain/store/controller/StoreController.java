package org.likelion.kkokio.domain.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.store.dto.request.StoreInfoRequestDTO;
import org.likelion.kkokio.domain.store.dto.response.StoreInfoResponseDTO;
import org.likelion.kkokio.domain.store.service.StoreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/info")
    public ResponseEntity<StoreInfoResponseDTO> createStoreInfo(
            @RequestPart StoreInfoRequestDTO storeInfoRequestDTO,
            @RequestPart(required = false) MultipartFile image
    ) {
        return storeService.createStoreInfo(image, storeInfoRequestDTO);
    }

    @GetMapping("/info")
    public ResponseEntity<Page<StoreInfoResponseDTO>> getStoreInfoList(@PageableDefault(sort = "storeId") Pageable pageable) {
        return storeService.getStoreInfoList(pageable);
    }

    @DeleteMapping("/info/{storeId}")
    public ResponseEntity<Void> deleteStoreInfo(@PathVariable Long storeId) {
        return storeService.deleteStoreInfo(storeId);
    }

    @PatchMapping("/info/{storeId}")
    public ResponseEntity<StoreInfoResponseDTO> updateStoreInfo(
            @PathVariable Long storeId,
            @RequestPart StoreInfoRequestDTO storeInfoRequestDTO,
            @RequestPart(required = false) MultipartFile image
    ) {
        return storeService.updateStoreInfo(image, storeInfoRequestDTO, storeId);
    }

    @DeleteMapping("/image/{storedId}")
    public ResponseEntity<StoreInfoResponseDTO> deleteImage(@PathVariable Long storedId) {
        return storeService.deleteImage(storedId);
    }
}
