package org.likelion.kkokio.domain.category.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.adminAccount.entity.AdminAccount;
import org.likelion.kkokio.domain.adminAccount.repository.AdminAccountRepository;
import org.likelion.kkokio.domain.category.dto.request.CategoryInfoRequestDTO;
import org.likelion.kkokio.domain.category.dto.response.CategoryInfoResponseDTO;
import org.likelion.kkokio.domain.category.entity.Category;
import org.likelion.kkokio.domain.category.repository.CategoryRepository;
import org.likelion.kkokio.domain.store.dto.response.StoreInfoResponseDTO;
import org.likelion.kkokio.domain.store.entity.Store;
import org.likelion.kkokio.domain.store.repository.StoreRepository;
import org.likelion.kkokio.global.base.exception.CustomException;
import org.likelion.kkokio.global.base.exception.ErrorCode;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final AdminAccountRepository adminAccountRepository;
    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;

    private Long MemberId = 1L;
    @Override
    public ResponseEntity<CategoryInfoResponseDTO> createCategoryInfo(Long storeId, CategoryInfoRequestDTO categoryInfoRequestDTO) {
        AdminAccount adminAccount = adminAccountRepository.findById(MemberId).orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        Store store = storeRepository.findByAdminAccountAndStoreIdAndDeletedAtIsNull(adminAccount, storeId)
                .orElseThrow(()->new CustomException(ErrorCode.STORE_NOT_FOUND));

        if(categoryRepository.findByCategoryNameAndStoreId(storeId, categoryInfoRequestDTO.getCategoryName()).isPresent())
            throw new CustomException(ErrorCode.DUPLICATED_CATEGORYNAME);

        Category category = modelMapper.map(categoryInfoRequestDTO, Category.class);
        category.createInfo(store);
        categoryRepository.save(category);

        CategoryInfoResponseDTO categoryInfoResponseDTO = CategoryInfoResponseDTO.builder()
                .storeInfoResponseDTO(modelMapper.map(store, StoreInfoResponseDTO.class))
                .build();
        modelMapper.map(category, categoryInfoResponseDTO);
        return ResponseEntity.ok(categoryInfoResponseDTO);
    }

    @Override
    public ResponseEntity<CategoryInfoResponseDTO> updateCategoryInfo(Long categoryId, CategoryInfoRequestDTO categoryInfoRequestDTO) {
        AdminAccount adminAccount = adminAccountRepository.findById(MemberId).orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        Category category = categoryRepository.findbIdAndAdminAccountIdDeletedAtIsNULL(MemberId, categoryId)
                .orElseThrow(()->new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
        Store store = storeRepository.findByAdminAccountAndCategoryAndDeletedAtIsNull(adminAccount, category)
                        .orElseThrow(()->new CustomException(ErrorCode.STORE_NOT_FOUND));

        if(categoryRepository.findByCategoryNameAndStoreId(store.getStoreId(), categoryInfoRequestDTO.getCategoryName()).isPresent())
            throw new CustomException(ErrorCode.DUPLICATED_CATEGORYNAME);

        modelMapper.map(categoryInfoRequestDTO, category);
        categoryRepository.save(category);

        CategoryInfoResponseDTO categoryInfoResponseDTO = CategoryInfoResponseDTO.builder()
                .storeInfoResponseDTO(modelMapper.map(store, StoreInfoResponseDTO.class))
                .build();
        modelMapper.map(category, categoryInfoResponseDTO);
        return ResponseEntity.ok(categoryInfoResponseDTO);
    }

    @Override
    public ResponseEntity<Void> deleteCategoryInfo(Long categoryId) {
        Category category = categoryRepository.findbIdAndAdminAccountIdDeletedAtIsNULL(MemberId, categoryId)
                .orElseThrow(()->new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
        category.deletedInfo();
        categoryRepository.save(category);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Page<CategoryInfoResponseDTO>> getCategoryInfo(Long storeId, Pageable pageable) {
        return ResponseEntity.ok(
                categoryRepository.findByStoreIdAndDeletedAt(storeId, MemberId, pageable)
                        .map(tuple -> {
                            Category category = (Category) tuple[0];
                            Store store = (Store) tuple[1];
                            CategoryInfoResponseDTO categoryInfoResponseDTO = CategoryInfoResponseDTO.builder()
                                    .storeInfoResponseDTO(modelMapper.map(store, StoreInfoResponseDTO.class))
                                    .build();
                            modelMapper.map(category, categoryInfoResponseDTO);
                            return categoryInfoResponseDTO;
                        }));
    }
}
