package org.likelion.kkokio.domain.store.service;

import jakarta.persistence.EntityManager;
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

import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

    private final AdminAccountRepository adminAccountRepository;
    private final StoreRepository storeRepository;
    private final ImageService imageService;
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;

    Long MemberId = 1L;

    @Override
    public ResponseEntity<StoreInfoResponseDTO> createStoreInfo(MultipartFile image, StoreInfoRequestDTO storeInfoRequestDTO) {
        AdminAccount adminAccount = adminAccountRepository.findById(MemberId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Store store = modelMapper.map(storeInfoRequestDTO, Store.class);

        if(image != null && !image.isEmpty())
            store.uploadImageUrl(imageService.uploadImage(image));

        store.connetionAdminAccount(adminAccount);
        storeRepository.saveAndFlush(store);
        entityManager.clear();

        return ResponseEntity.ok(
                modelMapper.map(storeRepository.findById(store.getStoreId())
                                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND)),
                        StoreInfoResponseDTO.class));
    }

    @Override
    public ResponseEntity<List<StoreInfoResponseDTO>> getStoreInfoList() {
        AdminAccount adminAccount = adminAccountRepository.findById(MemberId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return ResponseEntity.ok(storeRepository.findByAdminAccountAndDeletedAtIsNull(adminAccount).stream().map( store ->
                modelMapper.map(store, StoreInfoResponseDTO.class)).toList());
    }

    @Override
    public ResponseEntity<Void> deleteStoreInfo(Long storeId) {
        AdminAccount adminAccount = adminAccountRepository.findById(MemberId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        if(store.getAdminAccount().getAccountId() != adminAccount.getAccountId())
            throw new CustomException(ErrorCode.STORE_USER_NOTMATH);

        if(store.getDeletedAt() != null)
            throw new CustomException(ErrorCode.STORE_ALREADY_DELETED);

        imageService.deleteImage(store.getProfile_img_url());
        store.deletedInfo();
        storeRepository.save(store);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<StoreInfoResponseDTO> deleteImage(Long storedId) {
        Store store = storeRepository.findByStoreIdAndAccountIdAndDeletedAtIsNull(storedId, MemberId)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
        imageService.deleteImage(store.getProfile_img_url());
        store.deleteImage();
        storeRepository.save(store);
        return ResponseEntity.ok(modelMapper.map(store, StoreInfoResponseDTO.class));
    }

    @Override
    public ResponseEntity<StoreInfoResponseDTO> updateStoreInfo(MultipartFile image, StoreInfoRequestDTO storeInfoRequestDTO, Long storeId) {
        AdminAccount adminAccount = adminAccountRepository.findById(MemberId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        if(store.getAdminAccount().getAccountId() != adminAccount.getAccountId())
            throw new CustomException(ErrorCode.STORE_USER_NOTMATH);

        modelMapper.map(storeInfoRequestDTO, store);

        if(image != null && !image.isEmpty()) {
            imageService.deleteImage(store.getProfile_img_url());
            store.uploadImageUrl(imageService.uploadImage(image));
        }

        storeRepository.save(store);
        return ResponseEntity.ok(
                modelMapper.map(storeRepository.findById(store.getStoreId())
                                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND)),
                        StoreInfoResponseDTO.class));
    }
}
