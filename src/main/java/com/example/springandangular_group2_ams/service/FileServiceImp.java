package com.example.springandangular_group2_ams.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImp implements FileService{


    private final Path root = Paths.get("src/main/resources/images/");


    @Override
    public String saveFile(MultipartFile file) throws IOException {
        try{

        String filename = file.getOriginalFilename(); //file name with extention
        // cute-cat.png  => [cute-cat , png]
        assert filename != null;
        String[] fileParts = filename.split("\\.");
        // String extension = fileParts[1];

        // d99330f3-c7ab-45d0-813b-839916dff8a0.jpg
        filename = UUID.randomUUID() + "." +StringUtils.getFilenameExtension(file.getOriginalFilename());

            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
            Files.copy(file.getInputStream(), this.root.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

        return filename;

        }catch (IOException ex){
            throw new IOException(ex.getMessage());
        }

    }






    @Override
    public ResponseEntity<Resource> downloadFile(String fileName) {
        try {

            File file = new File("src/main/resources/images/" + fileName);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.setContentType(MediaType.IMAGE_PNG);
            Path path = Paths.get("src/main/resources/images/" + fileName);

            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));


            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(resource.getInputStream()));
        }catch (Exception e) {
            return null;
        }

    }


}
