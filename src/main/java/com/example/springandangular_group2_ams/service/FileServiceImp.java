package com.example.springandangular_group2_ams.service;

import com.example.springandangular_group2_ams.model.response.FileResponse;
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


    //upload single file
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

            //src/main/resources/images/ not yet create
            if (!Files.exists(root)) {
                Files.createDirectories(root); //create
            }

            Files.copy(file.getInputStream(), this.root.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

        return filename;

        }catch (IOException ex){
            throw new IOException(ex.getMessage());
        }

    }






    //display/save file
    @Override
    public ResponseEntity<Resource> downloadFile(String fileName) throws IOException {
        try {

            //dir that store file
            File file = new File("src/main/resources/images/" + fileName);
            //A data structure representing HTTP request or response headers, mapping String header names to a list of String values, also offering accessors for common application-level data types.
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.setContentType(MediaType.IMAGE_PNG);
            Path path = Paths.get("src/main/resources/images/" + fileName);

            //creating mail attachments from local content, where JavaMail needs to be able to read the stream multiple times
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));


            return ResponseEntity.ok()
                    //Set the media type of the body, as specified by the Content-Type header.
                    .contentType(MediaType.IMAGE_JPEG)
                    //Set the body of the response entity and returns it
                    .body(new InputStreamResource(resource.getInputStream()));

        }catch (IOException  e) {
           throw  new IOException (e.getMessage());
        }

    }






    @Override
    public FileResponse<?> savaFiles(MultipartFile[] files) throws IOException {
                var res = new FileResponse<>();
        try{
            //append string
            String reponse =null;

                for (int i = 0; i < files.length; i++) {
                    reponse  +=  "កhttp://localhost:8080/api/v1/files/"+saveFile(files[i]);
                    res.setPayload( reponse.split("nullក"));
                    res.setStatus("201");
                    res.setMessage("successfully uploaded file");
                }

            return res;

        }catch (Exception exception){

            throw  new IOException (exception.getMessage());

        }
    }







}
