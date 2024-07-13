package org.likelion.kkokio.domain.store.service;

import org.likelion.kkokio.domain.store.dto.request.StoreInfoRequestDTO;
import org.likelion.kkokio.domain.store.dto.response.StoreInfoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


public interface StoreService {
    ResponseEntity<StoreInfoResponseDTO> createStoreInfo(MultipartFile image, StoreInfoRequestDTO storeInfoRequestDTO);

    ResponseEntity<StoreInfoResponseDTO> updateStoreInfo(MultipartFile image, StoreInfoRequestDTO storeInfoRequestDTO, Long storeId);

    ResponseEntity<Page<StoreInfoResponseDTO>> getStoreInfoList(Pageable pageable);

    ResponseEntity<Void> deleteStoreInfo(Long storeId);

    ResponseEntity<StoreInfoResponseDTO> deleteImage(Long storedId);

    ResponseEntity<StoreInfoResponseDTO> getStoreInfo(Long storeId);
}
