package com.test.images.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements IImageService {
    private static final Logger LOGGER= LogManager.getLogger(ImageService.class);

    @Override
    public BufferedImage generateImage(int width, int height) {
        LOGGER.info("In service method "+width+"  "+height);
        BufferedImage image = null;
        if(width*height<=32768) {
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            LOGGER.info("After bufferedImage object creation" + image);
            List<Integer> pixels =generateUniqueColors();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int pixelcolor = (height * x) + y;
                    image.setRGB(x, y, pixels.get(pixelcolor));
                }
            }
            LOGGER.info("After setting colors on image");
        }
        else{
            LOGGER.info("Entered width and height exceeding the limit");
        }
        return image;
    }
    /*
    Method for generating unique colors
     */
    private List<Integer> generateUniqueColors() {
        LOGGER.info("In generateUniqueColors method");
        List<Integer> pixels=new ArrayList<>();
        for (int red = 0; red < 256; red += 8) {
            for (int green = 0; green < 256; green += 8) {
                for (int blue = 0; blue < 256; blue += 8) {
                    int a = (int) (Math.random() * 256);
                    int p = (a << 24) | (red << 16) | (green << 8) | blue;
                    pixels.add(p);
                }
            }
        }
        LOGGER.info("After generating colors");
        return pixels;
    }
}


