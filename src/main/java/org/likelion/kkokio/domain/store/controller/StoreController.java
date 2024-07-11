package org.likelion.kkokio.domain.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.store.dto.request.StoreInfoRequestDTO;
import org.likelion.kkokio.domain.store.dto.response.StoreInfoResponseDTO;
import org.likelion.kkokio.domain.store.service.StoreService;
import org.springframework.http.MediaType;
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

    @GetMapping("/infoList")
    public ResponseEntity<List<StoreInfoResponseDTO>> getStoreInfoList() {
        return storeService.getStoreInfoList();
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

}
