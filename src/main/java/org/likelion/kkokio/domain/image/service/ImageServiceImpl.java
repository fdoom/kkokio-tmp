package org.likelion.kkokio.domain.image.service;

import com.google.cloud.WriteChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{

    @Value("${gcp_bucket_name}")
    private String bucketName;

    private final Storage storage;

    @Override
    public String uploadImage(MultipartFile image) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String extension = StringUtils.getFilenameExtension(image.getOriginalFilename());

        String filename = LocalDateTime.now().format(formatter) + UUID.randomUUID() + "."+ extension;
        BlobId blobId = BlobId.of(bucketName, filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(extension)
                .build();

        try(WriteChannel writer = storage.writer(blobInfo)) {
            writer.write(ByteBuffer.wrap(image.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "https://storage.googleapis.com/" + bucketName + "/" + filename;
    }

    @Override
    public void deleteImage(String imageUrl) {
        if(imageUrl == null || imageUrl.isEmpty()) return;

        String filename = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        Blob blob = storage.get(bucketName, filename);
        if(blob != null) {
            BlobId idWithGeneration = blob.getBlobId();
            storage.delete(idWithGeneration);
        }
    }
}
