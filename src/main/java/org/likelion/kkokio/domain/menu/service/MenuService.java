package org.likelion.kkokio.domain.menu.service;


import org.likelion.kkokio.domain.menu.dto.request.MenuInfoPatchRequestDTO;
import org.likelion.kkokio.domain.menu.dto.request.MenuInfoRequestDTO;
import org.likelion.kkokio.domain.menu.dto.response.MenuInfoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MenuService {
    ResponseEntity<MenuInfoResponseDTO> createMenuInfo(Long categoryId, MenuInfoRequestDTO menuInfoRequestDTO, MultipartFile image);

    ResponseEntity<MenuInfoResponseDTO> updateMenuInfo(Long menuId, MenuInfoPatchRequestDTO menuInfoPatchRequestDTO, MultipartFile image);

    ResponseEntity<Void> deleteMenuInfo(Long menuId);

    ResponseEntity<List<MenuInfoResponseDTO>> getMenuInfoStoreId(Long storeId);

    ResponseEntity<List<MenuInfoResponseDTO>> getMenuInfoStoreIdAndcategoryId(Long storeId, Long categoryId);

    ResponseEntity<MenuInfoResponseDTO> deleteImage(Long menuId);
}
