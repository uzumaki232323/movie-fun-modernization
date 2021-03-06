package org.superbiz.moviefun;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.superbiz.moviefun.blobstore.BlobStore;
import org.superbiz.moviefun.blobstore.S3Store;
<<<<<<< HEAD
import org.superbiz.moviefun.moviesapi.MovieServlet;
import org.superbiz.cloudfoundry.ServiceCredentials;
=======
<<<<<<< HEAD:applications/movie-fun-app/src/main/java/org/superbiz/moviefun/Application.java
import org.superbiz.moviefun.moviesapi.MovieServlet;
import org.superbiz.cloudfoundry.ServiceCredentials;
=======
import org.superbiz.moviefun.movies.MovieServlet;
>>>>>>> 0dd44b557277f4520e6033aaaafc79551e955f9b:applications/movie-fun-app/src/main/java/org/superbiz/moviefun/Application.java
>>>>>>> cf317882c797a5c5edd36f52446d5a8eb5c219cd

@SpringBootApplication
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean actionServletRegistration(MovieServlet movieServlet) {
        return new ServletRegistrationBean(movieServlet, "/moviefun/*");
    }

    @Bean
    ServiceCredentials serviceCredentials(@Value("${vcap.services}") String vcapServices) {
        return new ServiceCredentials(vcapServices);
    }

    @Bean
    public BlobStore blobStore(
            ServiceCredentials serviceCredentials,
            @Value("${vcap.services.photo-storage.credentials.endpoint:#{null}}") String endpoint
    ) {
        String photoStorageAccessKeyId = serviceCredentials.getCredential("photo-storage", "user-provided", "access_key_id");
        String photoStorageSecretKey = serviceCredentials.getCredential("photo-storage", "user-provided", "secret_access_key");
        String photoStorageBucket = serviceCredentials.getCredential("photo-storage", "user-provided", "bucket");

        AWSCredentials credentials = new BasicAWSCredentials(photoStorageAccessKeyId, photoStorageSecretKey);
        AmazonS3Client s3Client = new AmazonS3Client(credentials);

        if (endpoint != null) {
            s3Client.setEndpoint(endpoint);
        }

        return new S3Store(s3Client, photoStorageBucket);
    }
}