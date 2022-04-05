package com.test.images;

import com.test.images.service.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.image.BufferedImage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(MockitoJUnitRunner.class)
public class ImageServiceTest {

    @InjectMocks
    @Spy
    ImageService imageService;

    @Test
    public void generateImageSuccess1(){
        BufferedImage image=imageService.generateImage(256,128);
        assertEquals(image.getHeight(),128);
        assertEquals(image.getWidth(),256 );
    }
    @Test
    public void generateImageSuccess2(){
        BufferedImage image=imageService.generateImage(300,200);
        assertNull(image);
    }
}
