package com.tasoskinas.Post.Management.System.rest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api")
public class FileRestController {

    @Value("${file.upload-dir}")
    String FILE_DIRECTORY;

    @PostMapping("/file")
    public ResponseEntity<Object> fileUpload(@RequestParam("File") MultipartFile file,
                                             @RequestParam("PostTitle") String postTitle) throws IOException {
        File myFile = new File(FILE_DIRECTORY + postTitle + ".jpg");
        myFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(myFile);
        fos.write(file.getBytes());
        fos.close();
        return new ResponseEntity<>("The File Uploaded Successfully.", HttpStatus.OK);
    }

    @GetMapping(value = "/file/{imageTitle}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImageWithMediaType(@PathVariable String imageTitle) throws IOException {
        File file = new File(FILE_DIRECTORY + imageTitle);

        if (file.exists()) {
            InputStream in = new FileInputStream(FILE_DIRECTORY + imageTitle);
            var byteImage = IOUtils.toByteArray(in);
            in.close();
            return byteImage;
        }

        return null;
    }
}
