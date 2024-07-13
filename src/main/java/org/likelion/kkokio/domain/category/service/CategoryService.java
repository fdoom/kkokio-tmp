package org.likelion.kkokio.domain.category.service;

import org.likelion.kkokio.domain.category.dto.request.CategoryInfoRequestDTO;
import org.likelion.kkokio.domain.category.dto.response.CategoryInfoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface CategoryService {
    ResponseEntity<CategoryInfoResponseDTO> createCategoryInfo(Long storeId, CategoryInfoRequestDTO categoryInfoRequestDTO);

    ResponseEntity<CategoryInfoResponseDTO> updateCategoryInfo(Long categoryId, CategoryInfoRequestDTO categoryInfoRequestDTO);

    ResponseEntity<Void> deleteCategoryInfo(Long categoryId);

    ResponseEntity<Page<CategoryInfoResponseDTO>> getCategoryInfoPage(Long storeId, Pageable pageable);

    ResponseEntity<CategoryInfoResponseDTO> getCategoryInfo(Long categoryId);
}
