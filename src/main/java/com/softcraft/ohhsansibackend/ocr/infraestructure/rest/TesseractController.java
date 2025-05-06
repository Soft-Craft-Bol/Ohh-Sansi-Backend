package com.softcraft.ohhsansibackend.ocr.infraestructure.rest;


import com.softcraft.ohhsansibackend.ocr.domain.service.TesseractService;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class TesseractController {

    @Autowired
    private TesseractService tesseractService;

    @PostMapping("/ocr")
    public String recognizeText(@RequestParam MultipartFile img) throws IOException {
        return tesseractService.recognizeText(img.getInputStream());
    }
}
