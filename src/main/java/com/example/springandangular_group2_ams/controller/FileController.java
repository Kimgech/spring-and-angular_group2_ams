package com.example.springandangular_group2_ams.controller;

import com.example.springandangular_group2_ams.model.response.FileResponse;
import com.example.springandangular_group2_ams.service.FileService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class FileController {

    private final FileService fileService;


    //upload multi files
    @JsonBackReference
    @PostMapping(value = "/files",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    @Operation(summary = "Upload multi Files")
    public FileResponse<?> savaFiles( @RequestPart(value = "files",required = false)  MultipartFile[] files)   {

        return fileService.savaFiles(files);

    }








    // Single File download
    @GetMapping("/files/{filename}")
    @Operation(summary = "Download a File")
    public ResponseEntity<Resource> downloadFile(@PathVariable("filename") String fileName) throws IOException {

        return fileService.downloadFile(fileName);

    }






}
