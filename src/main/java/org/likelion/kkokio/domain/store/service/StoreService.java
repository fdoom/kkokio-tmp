package org.likelion.kkokio.domain.store.service;

import org.likelion.kkokio.domain.store.dto.request.StoreInfoRequestDTO;
import org.likelion.kkokio.domain.store.dto.response.StoreInfoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StoreService {
    ResponseEntity<StoreInfoResponseDTO> createStoreInfo(MultipartFile image, StoreInfoRequestDTO storeInfoRequestDTO);

    ResponseEntity<StoreInfoResponseDTO> updateStoreInfo(MultipartFile image, StoreInfoRequestDTO storeInfoRequestDTO, Long storeId);

    ResponseEntity<List<StoreInfoResponseDTO>> getStoreInfoList();

    ResponseEntity<Void> deleteStoreInfo(Long storeId);
}
