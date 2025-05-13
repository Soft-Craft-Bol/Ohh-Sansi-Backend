package com.softcraft.ohhsansibackend.ocr.domain.service;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Service
public class TesseractService {

    @Autowired
    private Tesseract tesseract;

    public String recognizeText(InputStream imageInputStream) throws IOException {
        BufferedImage image = ImageIO.read(imageInputStream);
        try {
            return tesseract.doOCR(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }
}
