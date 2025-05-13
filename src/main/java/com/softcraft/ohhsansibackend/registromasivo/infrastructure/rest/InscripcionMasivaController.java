package com.softcraft.ohhsansibackend.registromasivo.infrastructure.rest;


import com.softcraft.ohhsansibackend.registromasivo.application.usecases.InscripcionMasivaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inscripcion")
public class InscripcionMasivaController {

    private final InscripcionMasivaService inscripcionMasivaService;

    public InscripcionMasivaController(InscripcionMasivaService inscripcionMasivaService) {
        this.inscripcionMasivaService = inscripcionMasivaService;
    }

    @PostMapping("/masiva")
    public ResponseEntity<?> uploadInscripcionMasiva(@RequestParam("file") MultipartFile file) {
        System.out.println("Iniciando procesamiento de archivo...");
        System.out.println("Nombre del archivo: " + file.getOriginalFilename());
        System.out.println("Tamaño del archivo: " + file.getSize() + " bytes");

        if (file.isEmpty()) {
            System.err.println("Error: Archivo vacío recibido");
            return ResponseEntity.badRequest().body("Por favor, sube un archivo Excel");
        }

        try {
            System.out.println("Procesando archivo Excel...");
            List<Map<String, Object>> resultados = inscripcionMasivaService.processInscripcionMasiva(file.getInputStream());

            System.out.println("Procesamiento completado. Resultados:");
            System.out.println("Total registros procesados: " + resultados.size());

            if (!resultados.isEmpty()) {
                System.out.println("Primer resultado: " + resultados.get(0));
            }

            return ResponseEntity.ok(resultados);

        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", "Error al procesar el archivo",
                            "detalle", e.getMessage()
                    ));
        }
    }

    private long contarExitosos(List<Map<String, Object>> resultados) {
        return resultados.stream()
                .filter(r -> r.containsKey("success") && (boolean) r.get("success"))
                .count();
    }

    private long contarFallidos(List<Map<String, Object>> resultados) {
        return resultados.stream()
                .filter(r -> r.containsKey("error"))
                .count();
    }

    // Clase para la respuesta estructurada
    private static class InscripcionMasivaResponse {
        private String mensaje;
        private List<Map<String, Object>> detalles;
        private long registrosExitosos;
        private long registrosFallidos;

        public InscripcionMasivaResponse(String algunosRegistrosFallaron, List<Map<String, Object>> resultados, long l, long l1) {
            this.mensaje = algunosRegistrosFallaron;
            this.detalles = resultados;
            this.registrosExitosos = l;
            this.registrosFallidos = l1;
        }
    }
}