package org.likelion.kkokio.domain.menu.controller;

import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.menu.dto.request.MenuInfoPatchRequestDTO;
import org.likelion.kkokio.domain.menu.dto.request.MenuInfoRequestDTO;
import org.likelion.kkokio.domain.menu.dto.response.MenuInfoResponseDTO;
import org.likelion.kkokio.domain.menu.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/info/{categoryId}")
    public ResponseEntity<MenuInfoResponseDTO> createMenuInfo(
            @PathVariable Long categoryId,
            @RequestPart MenuInfoRequestDTO menuInfoRequestDTO,
            @RequestPart(required = false) MultipartFile image
    ) {
        return menuService.createMenuInfo(categoryId, menuInfoRequestDTO, image);
    }

    @PatchMapping("/info/{menuId}")
    public ResponseEntity<MenuInfoResponseDTO> updateMenuInfo(
            @PathVariable Long menuId,
            @RequestPart MenuInfoPatchRequestDTO menuInfoPatchRequestDTO,
            @RequestPart(required = false) MultipartFile image
    ) {
        return menuService.updateMenuInfo(menuId, menuInfoPatchRequestDTO, image);
    }

    @DeleteMapping("/info/{menuId}")
    public ResponseEntity<Void> deleteMenuInfo(@PathVariable Long menuId) {
        return menuService.deleteMenuInfo(menuId);
    }

    @GetMapping("/info/store/{storeId}")
    public ResponseEntity<Page<MenuInfoResponseDTO>> getMenuInfoStoreId(@PathVariable Long storeId, @PageableDefault(sort = "menuId") Pageable pageable) {
        return menuService.getMenuInfoStoreId(storeId, pageable);
    }

    @GetMapping("/info/store/{storeId}/category/{categoryId}")
    public ResponseEntity<Page<MenuInfoResponseDTO>> getMenuInfoStoreIdAndcategoryId(
            @PathVariable Long storeId, @PathVariable Long categoryId, @PageableDefault(sort = "menuId") Pageable pageable
    ) {
        return menuService.getMenuInfoStoreIdAndcategoryId(storeId, categoryId, pageable);
    }


    @DeleteMapping("/image/{menuId}")
    public ResponseEntity<MenuInfoResponseDTO> deleteImage(@PathVariable Long menuId) {
        return menuService.deleteImage(menuId);
    }
}
