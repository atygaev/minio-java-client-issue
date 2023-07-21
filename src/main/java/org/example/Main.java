package org.example;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;

import java.time.LocalTime;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws Exception {
        var minioClient =
                MinioClient.builder()
                        .endpoint("https://play.min.io")
                        .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
                        .build();

        var bucketName = "minio-java-client-issue-" + UUID.randomUUID();

        var found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

        System.out.println("Bucket: " + bucketName);
        System.out.println("Bucket status: " + (found ? "exists" : "doesn't exist"));

        System.out.println(LocalTime.now());
        System.out.println("Done");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println(LocalTime.now())));
    }
}
