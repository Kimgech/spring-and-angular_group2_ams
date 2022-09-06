package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.response.FileResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    //single upload
    String saveFile(MultipartFile file) throws IOException;


    //display/save file
    ResponseEntity<Resource> downloadFile(String fileName) throws IOException;


    //multi upload
    FileResponse<?> savaFiles(MultipartFile[] files) throws IOException;
}
