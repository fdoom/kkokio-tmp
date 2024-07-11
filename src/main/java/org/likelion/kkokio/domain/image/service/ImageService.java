package org.likelion.kkokio.domain.image.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(MultipartFile image);
    void deleteImage(String imageUrl);
}
