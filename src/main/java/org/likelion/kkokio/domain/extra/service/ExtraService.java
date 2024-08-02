package org.likelion.kkokio.domain.extra.service;

import org.likelion.kkokio.domain.extra.dto.request.ExtraInfoRequestDTO;
import org.likelion.kkokio.domain.extra.dto.response.ExtraInfoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ExtraService {
    ResponseEntity<ExtraInfoResponseDTO> createExtraInfo(Long menuId, ExtraInfoRequestDTO extraInfoRequestDTO);

    ResponseEntity<ExtraInfoResponseDTO> updateExtraInfo(Long extraId, ExtraInfoRequestDTO extraInfoRequestDTO);

    ResponseEntity<Void> deleteExtraInfo(Long extraId);

    ResponseEntity<ExtraInfoResponseDTO> getExtraInfo(Long extraId);

    ResponseEntity<Page<ExtraInfoResponseDTO>> getExtraInfoByMenuId(Long menuId, Pageable pageable);
}
