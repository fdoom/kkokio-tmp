package org.likelion.kkokio.domain.extra.service;

import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.extra.dto.request.ExtraInfoRequestDTO;
import org.likelion.kkokio.domain.extra.dto.response.ExtraInfoResponseDTO;
import org.likelion.kkokio.domain.extra.entity.Extra;
import org.likelion.kkokio.domain.extra.repository.ExtraRepository;
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


@Service
@RequiredArgsConstructor
@Transactional
public class ExtraServiceImpl implements ExtraService {
    private final ExtraRepository extraRepository;
    private final MenuRepository menuRepository;
    private final SecurityService securityService;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<ExtraInfoResponseDTO> createExtraInfo(Long menuId, ExtraInfoRequestDTO extraInfoRequestDTO) {
        Menu menu = menuRepository.findByIdALLDeletedAtIsNull(menuId, securityService.getCurrentUserId().orElseThrow())
                .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND));

        Extra extra = modelMapper.map(extraInfoRequestDTO, Extra.class);
        extra.createInfo(menu);
        extraRepository.save(extra);

        return ResponseEntity.ok(modelMapper.map(extra, ExtraInfoResponseDTO.class));
    }

    @Override
    public ResponseEntity<ExtraInfoResponseDTO> updateExtraInfo(Long extraId, ExtraInfoRequestDTO extraInfoRequestDTO) {
        Extra extra = extraRepository.findByIdAndAdminIdAndDeleteAtIsNull(extraId, securityService.getCurrentUserId().orElseThrow())
                .orElseThrow(() -> new CustomException(ErrorCode.EXTRA_NOT_FOUND));
        modelMapper.map(extraInfoRequestDTO, extra);
        extraRepository.save(extra);
        return ResponseEntity.ok(modelMapper.map(extra, ExtraInfoResponseDTO.class));
    }

    @Override
    public ResponseEntity<Void> deleteExtraInfo(Long extraId) {
        Extra extra = extraRepository.findByIdAndAdminIdAndDeleteAtIsNull(extraId, securityService.getCurrentUserId().orElseThrow())
                .orElseThrow(() -> new CustomException(ErrorCode.EXTRA_NOT_FOUND));
        extra.deletedInfo();
        extraRepository.save(extra);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ExtraInfoResponseDTO> getExtraInfo(Long extraId) {
        Extra extra = extraRepository.findByIdAndDeletedAtIsNull(extraId)
                .orElseThrow(() -> new CustomException(ErrorCode.EXTRA_NOT_FOUND));
        return ResponseEntity.ok(modelMapper.map(extra, ExtraInfoResponseDTO.class));
    }

    @Override
    public ResponseEntity<Page<ExtraInfoResponseDTO>> getExtraInfoByMenuId(Long menuId, Pageable pageable) {
        return ResponseEntity.ok(extraRepository.findByMenuIdAndDeletedAtIsNull(menuId, pageable)
                        .map(extra -> modelMapper.map(extra, ExtraInfoResponseDTO.class)));
    }
}
