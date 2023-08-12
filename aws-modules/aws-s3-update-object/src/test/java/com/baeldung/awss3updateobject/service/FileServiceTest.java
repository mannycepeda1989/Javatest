package com.baeldung.awss3updateobject.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FileServiceTest {

    @Mock
    private AmazonS3 amazonS3;

    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private FileService fileService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        fileService = new FileService();
        fileService.awsS3Bucket = "test-bucket";
        fileService.amazonS3 = amazonS3;
      }

    @Test
    public void uploadFileTest() throws Exception {
        when(multipartFile.getName()).thenReturn("testFile");
        when(multipartFile.getContentType()).thenReturn("application/pdf");
        when(multipartFile.getSize()).thenReturn(1024L);
        when(multipartFile.getInputStream()).thenReturn(mock(InputStream.class));

        S3Object s3Object = new S3Object();
        when(amazonS3.putObject(any())).thenReturn(null);
        when(amazonS3.getObject(anyString(), anyString())).thenReturn(s3Object);

        String key = fileService.uploadFile(multipartFile);

        assertEquals("/documents/testFile", key);
    }

    @Test
    public void uploadFileErrorTest() throws Exception {
        when(multipartFile.getName()).thenReturn("testFile");
        when(multipartFile.getContentType()).thenReturn("application/pdf");
        when(multipartFile.getSize()).thenReturn(1024L);
        when(multipartFile.getInputStream()).thenReturn(mock(InputStream.class));

        AmazonS3Exception exception = new AmazonS3Exception("Test exception");
        exception.setErrorCode("NoSuchBucket");
        when(amazonS3.putObject(any(PutObjectRequest.class))).thenThrow(exception);

        assertThrows(Exception.class, () -> fileService.uploadFile(multipartFile));
    }

    @Test
    public void updateFileTest() throws Exception {
        when(multipartFile.getName()).thenReturn("testFile");
        when(multipartFile.getContentType()).thenReturn("application/pdf");
        when(multipartFile.getSize()).thenReturn(1024L);
        when(multipartFile.getInputStream()).thenReturn(mock(InputStream.class));

        S3Object s3Object = new S3Object();
        when(amazonS3.putObject(any(PutObjectRequest.class))).thenReturn(null);
        when(amazonS3.getObject(anyString(), anyString())).thenReturn(s3Object);

        String key = "/documents/existingFile";
        String resultKey = fileService.updateFile(multipartFile, key);

        assertEquals(key, resultKey);
    }

    @Test
    public void updateFileErrorTest() throws Exception {
        when(multipartFile.getName()).thenReturn("testFile");
        when(multipartFile.getContentType()).thenReturn("application/pdf");
        when(multipartFile.getSize()).thenReturn(1024L);
        when(multipartFile.getInputStream()).thenThrow(new IOException("Test IO Exception"));

        assertThrows(Exception.class, () -> fileService.updateFile(multipartFile, "/documents/existingFile"));
    }
}