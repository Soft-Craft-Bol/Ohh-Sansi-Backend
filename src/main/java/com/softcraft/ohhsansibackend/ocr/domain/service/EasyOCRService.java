package com.softcraft.ohhsansibackend.ocr.domain.service;

import cn.easyproject.easyocr.EasyOCR;
import cn.easyproject.easyocr.ImageType;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class EasyOCRService {

    private EasyOCR easyOCR;

    @PostConstruct
    public void init() {
        easyOCR = new EasyOCR();
        // Configuración para español (puedes usar OPTION_LANG_CHI_SIM para chino)
        easyOCR.setTesseractOptions("-l spa"); // Español
        // Configuración adicional para mejorar reconocimiento
        easyOCR.setTesseractOptions("--psm 6 --oem 1");
    }

    public String recognizeText(MultipartFile file) throws IOException {
        Path tempFile = Files.createTempFile("ocr", ".png");
        file.transferTo(tempFile.toFile());

        try {
            String result = easyOCR.discern(tempFile.toString());

            // Pequeño retraso antes de eliminar
            try {
                Thread.sleep(500); // 500 milisegundos de espera
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return result;
        } finally {
            // Intenta eliminar varias veces si falla
            int attempts = 0;
            while (attempts < 3) {
                try {
                    Files.deleteIfExists(tempFile);
                    break;
                } catch (IOException e) {
                    attempts++;
                    if (attempts == 3) {
                        System.err.println("No se pudo eliminar el archivo temporal: " + tempFile);
                    }
                    try {
                        Thread.sleep(300 * attempts);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    public String recognizeWithCustomRatios(MultipartFile file, double widthRatio, double heightRatio) throws IOException {
        Path tempFile = Files.createTempFile("ocr", ".jpg");
        file.transferTo(tempFile.toFile());

        try {
            return easyOCR.discernAndAutoCleanImage(
                    tempFile.toString(),
                    ImageType.CAPTCHA_NORMAL,
                    widthRatio,
                    heightRatio
            );
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }
}