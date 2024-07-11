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

@RequiredArgsConstructor
@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/createStoreInfo")
    public ResponseEntity<StoreInfoResponseDTO> createStoreInfo(
            @RequestPart StoreInfoRequestDTO storeInfoRequestDTO,
            @RequestPart(required = false) MultipartFile image
            ) {
        return storeService.createStoreInfo(image, storeInfoRequestDTO);
    }
}
