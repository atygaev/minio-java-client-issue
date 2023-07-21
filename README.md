# Minio Java Client issue

After all operations done the program hangs on (during one minute).

# Intro

I wrote a simple program which only checks that some bucket exists.

The program have several output lines.

Take attention to two lines with time.

The first one is about when program is finished.

The second one is about when program is really finished (because of hanging).

```
Bucket: minio-java-client-issue-f8b1388d-eec3-40fe-b273-7261ed132908
Bucket status: doesn't exist
11:55:14.371738005
Done
11:56:14.373222335
```

```java
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
```

# Steps to reproduce

## 1. Run on Minio 8.3.9 (no hanging)

### Linux / MacOS
```
./gradlew clean run -PminioVersion=8.3.9
```
### Windows
```
gradlew.bat clean run -PminioVersion=8.3.9
```

## 2. Run on Minio 8.4.0 or higher (hanging)
### Linux / MacOS
```
./gradlew clean run -PminioVersion=8.4.0
```
### Windows
```
gradlew.bat clean run -PminioVersion=8.4.0
```

# Test environment

Windows / Ubuntu

Java 11, Java 17