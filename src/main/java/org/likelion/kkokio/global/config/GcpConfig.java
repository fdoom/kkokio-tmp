package org.likelion.kkokio.global.config;

import com.google.cloud.storage.*;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GcpConfig {

    @Value("${gcp_bucket_name}")
    private String bucketName;

    @Bean
    public Storage storage() {
        Cors cors = Cors.newBuilder()
                        .setOrigins(ImmutableList.of(Cors.Origin.of("*")))
                        .setMethods(ImmutableList.of(HttpMethod.GET))
                        .setResponseHeaders(ImmutableList.of("*"))
                        .setMaxAgeSeconds(3600)
                        .build();

        Storage storage = StorageOptions.getDefaultInstance().getService();

        storage.get(bucketName)
                .toBuilder()
                .setCors(ImmutableList.of(cors)).build().update();

        return storage;
    }
}
