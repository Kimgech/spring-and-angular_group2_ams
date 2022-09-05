package com.example.springandangular_group2_ams.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String saveFile(MultipartFile file) throws IOException;


    ResponseEntity<Resource> downloadFile(String fileName);
}
