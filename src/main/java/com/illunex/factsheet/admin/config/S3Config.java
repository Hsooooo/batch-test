package com.illunex.factsheet.admin.config;

//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
@Deprecated // Deprecated, use spring cloud aws
public class S3Config {

//    private AmazonS3 amazonS3;

//    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;
//    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;
//    @Value("${cloud.aws.region.static}")
    private String region;

//    @Bean
//    public AmazonS3Client amazonS3Client() {
//        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
//
//        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
//                .withRegion(region)
//                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//                .build();
//    }

}