package com.test.images;

import com.test.images.controller.ImageController;
import com.test.images.service.ImageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ImageControllerTest {

    @InjectMocks
    ImageController imageController;

    @Mock
    ImageService imageService;

    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    public void getImageSuccess() throws Exception {
        BufferedImage image=new BufferedImage(256,128,BufferedImage.TYPE_INT_RGB);
        given(imageService.generateImage(256,128)).willReturn(image);
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        ImageIO.write(image,"jpeg",outputStream);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/image")
                        .param("width", String.valueOf(256))
                        .param("height", String.valueOf(128))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(outputStream.toByteArray())
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }
    @Test(expected = Exception.class)
    public void getImageFailure() throws Exception {
        Mockito.doThrow(Exception.class).when(imageService).generateImage(400,123);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/image")
                        .param("width", String.valueOf(400))
                        .param("height", String.valueOf(123))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isInternalServerError());
    }
}

