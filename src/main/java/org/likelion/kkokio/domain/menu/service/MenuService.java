package org.likelion.kkokio.domain.menu.service;


import org.likelion.kkokio.domain.menu.dto.request.MenuInfoPatchRequestDTO;
import org.likelion.kkokio.domain.menu.dto.request.MenuInfoRequestDTO;
import org.likelion.kkokio.domain.menu.dto.response.MenuInfoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


public interface MenuService {
    ResponseEntity<MenuInfoResponseDTO> createMenuInfo(Long categoryId, MenuInfoRequestDTO menuInfoRequestDTO, MultipartFile image);

    ResponseEntity<MenuInfoResponseDTO> updateMenuInfo(Long menuId, MenuInfoPatchRequestDTO menuInfoPatchRequestDTO, MultipartFile image);

    ResponseEntity<Void> deleteMenuInfo(Long menuId);

    ResponseEntity<Page<MenuInfoResponseDTO>> getMenuInfoStoreId(Long storeId, Pageable pageable);

    ResponseEntity<Page<MenuInfoResponseDTO>> getMenuInfoStoreIdAndcategoryId(Long storeId, Long categoryId, Pageable pageable);

    ResponseEntity<MenuInfoResponseDTO> deleteImage(Long menuId);

    ResponseEntity<MenuInfoResponseDTO> getMenuInfoMenuId(Long menuId);
}
