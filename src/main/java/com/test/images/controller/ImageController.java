package com.test.images.controller;

import com.test.images.service.IImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/")
public class ImageController {
    private static final Logger LOGGER= LogManager.getLogger(ImageController.class);
    @Autowired
    IImageService imageService;

    @GetMapping("getImage")
    public ResponseEntity<byte[]> getImage(@RequestParam int width, @RequestParam int height) {
        BufferedImage image=null;
        try {
            LOGGER.info("In controller");
            image=imageService.generateImage(width,height);
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            ImageIO.write(image,"jpeg",outputStream);
            byte[] imageByte=outputStream.toByteArray();
            return ResponseEntity.status(HttpStatus.OK)
                            .contentType(MediaType.IMAGE_JPEG)
                            .body(imageByte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
