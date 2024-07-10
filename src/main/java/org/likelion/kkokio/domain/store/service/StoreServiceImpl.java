package org.likelion.kkokio.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.adminAccount.entity.AdminAccount;
import org.likelion.kkokio.domain.adminAccount.repository.AdminAccountRepository;
import org.likelion.kkokio.domain.image.service.ImageService;
import org.likelion.kkokio.domain.store.dto.request.StoreInfoRequestDTO;
import org.likelion.kkokio.domain.store.dto.response.StoreInfoResponseDTO;
import org.likelion.kkokio.domain.store.entity.Store;
import org.likelion.kkokio.domain.store.repository.StoreRepository;
import org.likelion.kkokio.global.base.exception.CustomException;
import org.likelion.kkokio.global.base.exception.ErrorCode;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

    private final AdminAccountRepository adminAccountRepository;
    private final StoreRepository storeRepository;
    private final ImageService imageService;
    private final ModelMapper modelMapper;

    Long MemberId = 1L;

    @Override
    public ResponseEntity<StoreInfoResponseDTO> createStoreInfo(MultipartFile image, StoreInfoRequestDTO storeInfoRequestDTO) {
        AdminAccount adminAccount = adminAccountRepository.findById(MemberId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Store store = modelMapper.map(storeInfoRequestDTO, Store.class);
        store.uploadImageUrl(imageService.uploadImage(image));
        store.connetionAdminAccount(adminAccount);
        storeRepository.save(store);
        return ResponseEntity.ok(modelMapper.map(store, StoreInfoResponseDTO.class));
    }
}
