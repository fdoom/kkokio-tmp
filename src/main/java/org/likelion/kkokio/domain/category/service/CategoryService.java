package org.likelion.kkokio.domain.category.service;

import org.likelion.kkokio.domain.category.dto.request.CategoryInfoRequestDTO;
import org.likelion.kkokio.domain.category.dto.response.CategoryInfoResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<CategoryInfoResponseDTO> createCategoryInfo(Long storeId, CategoryInfoRequestDTO categoryInfoRequestDTO);

    ResponseEntity<CategoryInfoResponseDTO> updateCategoryInfo(Long categoryId, CategoryInfoRequestDTO categoryInfoRequestDTO);

    ResponseEntity<Void> deleteCategoryInfo(Long categoryId);

    ResponseEntity<List<CategoryInfoResponseDTO>> getCategoryInfo(Long storeId);
}
