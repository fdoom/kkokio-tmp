package org.likelion.kkokio.domain.menu.service;


import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.category.dto.another.CategoryDtoOnly;
import org.likelion.kkokio.domain.category.dto.response.CategoryInfoResponseDTO;
import org.likelion.kkokio.domain.category.entity.Category;
import org.likelion.kkokio.domain.category.repository.CategoryRepository;
import org.likelion.kkokio.domain.image.service.ImageService;
import org.likelion.kkokio.domain.menu.dto.request.MenuInfoPatchRequestDTO;
import org.likelion.kkokio.domain.menu.dto.request.MenuInfoRequestDTO;
import org.likelion.kkokio.domain.menu.dto.response.MenuInfoResponseDTO;
import org.likelion.kkokio.domain.menu.entity.Menu;
import org.likelion.kkokio.domain.menu.repository.MenuRepository;
import org.likelion.kkokio.domain.security.service.SecurityService;
import org.likelion.kkokio.global.base.exception.CustomException;
import org.likelion.kkokio.global.base.exception.ErrorCode;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ImageService imageService;
    private final SecurityService securityService;

    @Override
    public ResponseEntity<MenuInfoResponseDTO> createMenuInfo(Long categoryId, MenuInfoRequestDTO menuInfoRequestDTO, MultipartFile image) {
        Category category = categoryRepository.findbIdAndAdminAccountIdDeletedAtIsNullJoinStore(securityService.getCurrentUserId().orElseThrow(), categoryId)
                .orElseThrow(()-> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));

        Menu menu = modelMapper.map(menuInfoRequestDTO, Menu.class);
        if(image != null && !image.isEmpty())
            menu.uploadImageUrl(imageService.uploadImage(image));
        menu.updateCategoryInfo(category);
        menuRepository.save(menu);

        MenuInfoResponseDTO menuInfoResponseDTO = MenuInfoResponseDTO.builder()
                .categoryDtoOnly(modelMapper.map(category, CategoryDtoOnly.class))
                .build();
        modelMapper.map(menu, menuInfoResponseDTO);
        return ResponseEntity.ok(menuInfoResponseDTO);
    }

    @Override
    public ResponseEntity<MenuInfoResponseDTO> updateMenuInfo(Long menuId, MenuInfoPatchRequestDTO menuInfoPatchRequestDTO, MultipartFile image) {
        Menu menu = menuRepository.findByIdALLDeletedAtIsNull(menuId, securityService.getCurrentUserId().orElseThrow())
                .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND));

        modelMapper.map(menuInfoPatchRequestDTO, menu);

        if(menu.getCategory().getCategoryId() != menuInfoPatchRequestDTO.getCategoryId()) {
            Category category = categoryRepository.findbIdAndAdminAccountIdDeletedAtIsNULL(securityService.getCurrentUserId().orElseThrow(), menuInfoPatchRequestDTO.getCategoryId())
                    .orElseThrow(()-> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
            menu.updateCategoryInfo(category);
        }

        if(image != null && !image.isEmpty()) {
            imageService.deleteImage(menu.getMenuImgUrl());
            menu.uploadImageUrl(imageService.uploadImage(image));
        }

        menuRepository.save(menu);

        MenuInfoResponseDTO menuInfoResponseDTO = MenuInfoResponseDTO.builder()
                .categoryDtoOnly(modelMapper.map(menu.getCategory(), CategoryDtoOnly.class))
                .build();
        modelMapper.map(menu, menuInfoResponseDTO);
        return ResponseEntity.ok(menuInfoResponseDTO);
    }

    @Override
    public ResponseEntity<Void> deleteMenuInfo(Long menuId) {
        Menu menu = menuRepository.findByIdALLDeletedAtIsNull(menuId, securityService.getCurrentUserId().orElseThrow())
                .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND));
        imageService.deleteImage(menu.getMenuImgUrl());
        menu.deletedInfo();
        menuRepository.save(menu);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Page<MenuInfoResponseDTO>> getMenuInfoStoreId(Long storeId, Pageable pageable) {
        return ResponseEntity.ok(
                menuRepository.getMenuInfoStoreId(storeId, pageable)
                        .map(this::MerageMenuInfoResponseDTO));
    }

    @Override
    public ResponseEntity<Page<MenuInfoResponseDTO>> getMenuInfoStoreIdAndcategoryId(Long storeId, Long categoryId, Pageable pageable) {
        return ResponseEntity.ok(
                menuRepository.getMenuInfoStoreIdAndCategoryId(storeId, categoryId, pageable)
                        .map(this::MerageMenuInfoResponseDTO)
        );
    }

    @Override
    public ResponseEntity<MenuInfoResponseDTO> deleteImage(Long menuId) {
        Menu menu = menuRepository.findByIdALLDeletedAtIsNull(menuId, securityService.getCurrentUserId().orElseThrow())
                .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND));
        imageService.deleteImage(menu.getMenuImgUrl());
        menu.deletImage();
        menuRepository.save(menu);

        MenuInfoResponseDTO menuInfoResponseDTO = MenuInfoResponseDTO.builder()
                .categoryDtoOnly(modelMapper.map(menu.getCategory(), CategoryDtoOnly.class))
                .build();
        modelMapper.map(menu, menuInfoResponseDTO);
        return ResponseEntity.ok(menuInfoResponseDTO);
    }

    @Override
    public ResponseEntity<MenuInfoResponseDTO> getMenuInfoMenuId(Long menuId) {
        Menu menu = menuRepository.findMenuAndCategoryByMenuIdAndDeletedAtIsNull(menuId)
                .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND));
        CategoryDtoOnly categoryDtoOnly = modelMapper.map(menu.getCategory(), CategoryDtoOnly.class);

        MenuInfoResponseDTO menuInfoResponseDTO = MenuInfoResponseDTO.builder()
                .categoryDtoOnly(categoryDtoOnly)
                .build();
        modelMapper.map(menu, menuInfoResponseDTO);
        return ResponseEntity.ok(menuInfoResponseDTO);
    }

    private MenuInfoResponseDTO MerageMenuInfoResponseDTO(Object[] tuple) {
        Menu menu = (Menu) tuple[0];
        Category category = (Category) tuple[1];
        MenuInfoResponseDTO menuInfoResponseDTO = MenuInfoResponseDTO.builder()
                .categoryDtoOnly(modelMapper.map(category, CategoryDtoOnly.class))
                .build();
        modelMapper.map(menu, menuInfoResponseDTO);
        return menuInfoResponseDTO;
    }
}
